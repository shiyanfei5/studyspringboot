package http.response.sender;

import compress.HttpCompressFactory;
import compress.ICompress;
import http.response.Response;
import util.ByteUtil;

import java.io.*;
import java.nio.ByteBuffer;

public class ResponseChunkSender implements IResponseSender {

    private ByteBuffer byteBuffer; //用于
    private Response response; //发送响应
    private Integer headPosition;   //若其存在表明 byteBuffer包含了请求头的结尾
    private boolean isHead; //表明byteBuffer是否属于请求头

    public ResponseChunkSender(Response response) {
        this.response = response;
        byteBuffer = ByteBuffer.allocate( response.BufferSize*2);
    }

    /**
     * ByteBuffer中写入，若超过Buffer则先进行send
     * @param contentBytes
     * @param offset
     * @param len
     * @throws Exception
     */
    public void writerByteBuffer(byte[] contentBytes,int offset, int len) throws Exception{
        //获取 byteBuffer可存储长度
        int remaining = byteBuffer.remaining();
        //若 剩余长度大于 len，说明可全部写入缓冲区
        if(remaining > len ){
            byteBuffer.put(contentBytes,offset,len);
            if(isHead && headPosition == null ){        //此时未拿到请求头的位置，即请求头结尾
                headPosition = byteBuffer.position();
            }
        } else {
            // byteBuffer剩余长度小于等于len
            while(true){
                if(len > remaining ){
                    byteBuffer.put(contentBytes,offset,remaining);
                    len = len - remaining;  //剩余长度
                    sendByteBuffer(false);  //满了发送，
                    remaining = byteBuffer.remaining(); //更新剩余可存储

                } else {
                    // 退出循环，剩余长度 小于或相等 于 容量时，放入后即可退出
                    // 此时不会消费byteBuffer的内容
                    byteBuffer.put(contentBytes,offset,len);
                    if(isHead && headPosition == null ){        //此时未拿到请求头的位置，即请求头结尾
                        headPosition = byteBuffer.position();
                    }
                    break;
                }

            }

        }
    }

    /**
     * 发送Byte的中的内容
     */
    public void sendByteBuffer(boolean withEnd) throws Exception{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if(isHead){
            if(headPosition == null){
                byteArrayOutputStream.write( ByteUtil.consumeByteBuffer( byteBuffer));
            } else{
                int size = byteBuffer.position() - headPosition;
                if(size > 0){
                    //这次的byteBuffer既有请求体又有请求头
                    //首先添加请求头
                    byte[] res = ByteUtil.consumeByteBuffer(byteBuffer);
                    byteArrayOutputStream.write( res,0,headPosition);
                    //添加chunk大小
                    String chunkSize = Integer.toHexString(size)+response.CRLF;
                    byteArrayOutputStream.write( chunkSize.getBytes());
                    //添加body
                    byteArrayOutputStream.write( res,headPosition,size);
                    //添加body结束符
                    byteArrayOutputStream.write( response.CRLF.getBytes());

                }else{
                    byteArrayOutputStream.write( ByteUtil.consumeByteBuffer( byteBuffer));
                    //请求头无需添加结束符
                }
                isHead = false  ;   //表明请求头处理完毕
                headPosition = null;
            }
        } else{
            String chunkSize =
                    Integer.toHexString(byteBuffer.position())+response.CRLF;
            byteArrayOutputStream.write( chunkSize.getBytes());
            byteArrayOutputStream.write( ByteUtil.consumeByteBuffer( byteBuffer));
            //添加结束符
            byteArrayOutputStream.write( response.CRLF.getBytes());
        }
        if(withEnd){
            byteArrayOutputStream.write( "0\r\n\r\n".getBytes());
        }
        response.getSocketOut().write(byteArrayOutputStream.toByteArray());
    }


    /**
     * 请求体此时已经全部在内存中
     * @param content 字节
     * @throws Exception
     */
    @Override
    public void sendResponse(byte[] content) throws Exception {
        //将请求头写入ByteBuffer中
        byte[] head = response.formatRespHeadString().getBytes();
        isHead = true;
        writerByteBuffer( head,0,head.length);

        ICompress compresser = HttpCompressFactory.getInstance().produce( response.getContentEncoding() );
        // TODO: 2019/12/1  由于不会写多线程，只能实现先压缩部分，然后发送该部分。。

        int start = 0 ; // 本次处理的起始位置
        int len;    // 本次处理长度
        while( start < content.length ){
            if(start + response.BufferSize < content.length){
                len =  response.BufferSize;
            }else{
                len = content.length -start;
            }

            if(compresser != null){
                byte[] tmp =
                        compresser.compress( content,start,len);
                writerByteBuffer(tmp,0,tmp.length);
            }else{
                writerByteBuffer(content,start,len);
            }
            start += len;   //指向下一个开始点
        }
        sendByteBuffer(true);
    }

    @Override
    public void sendResponse(String content, String charset) throws Exception {
        if(charset == null){
            charset="utf-8";
        }
        byte[] contentBytes = content.getBytes(charset);
        sendResponse(contentBytes);
    }

    /**
     * 响应内容为inputstream，可为外部
     * @param content 字节
     * @throws Exception
     */
    @Override
    public void sendResponse(InputStream content) throws Exception {
        //缓冲流，加快速度
        BufferedInputStream bufferedInputStream = new BufferedInputStream(content);

        //将请求头写入ByteBuffer中
        byte[] head = response.formatRespHeadString().getBytes();
        isHead = true;
        writerByteBuffer( head,0,head.length);

        //设置压缩器
        ICompress compresser = HttpCompressFactory.getInstance().produce( response.getContentEncoding() );
        // TODO: 2019/12/1  由于不会写多线程，只能实现先压缩部分，然后发送该部分。。
        byte[] rawContentByte = new byte[ response.BufferSize];
        int len;
        while(  ( len = bufferedInputStream.read(rawContentByte) ) != -1){
            if(compresser == null){
                writerByteBuffer(rawContentByte,0,len);
            } else{
                byte[] compressed =
                        compresser.compress(rawContentByte,0,len);
                writerByteBuffer(compressed,0,compressed.length);
            }
        }
        //清空ByteBuffer
        sendByteBuffer(true);
    }

    @Override
    public void sendResponse(Reader content, String charset) throws Exception {
        if(charset == null){
            charset = "utf-8";
        }
        //将请求头写入ByteBuffer中
        byte[] head = response.formatRespHeadString().getBytes();
        isHead = true;
        writerByteBuffer( head,0,head.length);
        //缓冲区读取字符，更快
        BufferedReader bufferedReader = new BufferedReader(content);
        //设置压缩器
        ICompress compresser = HttpCompressFactory.getInstance().produce( response.getContentEncoding() );

        char[] readBuf  = new char[response.BufferSize];
        int len;
        while( (len = bufferedReader.read(readBuf)) != -1){
            byte[] byteContent = new String(readBuf,0,len).getBytes(charset);
            if( compresser != null){
                byte[] res = compresser.compress( byteContent,0,byteContent.length);
                writerByteBuffer(res,0,res.length);
            } else{
                writerByteBuffer(byteContent,0,byteContent.length);;
            }
        }
        //清空ByteBuffer
        sendByteBuffer(true);
    }


}
