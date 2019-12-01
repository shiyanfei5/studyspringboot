package http.response.sender;

import compress.HttpCompressFactory;
import compress.ICompress;
import http.response.Response;

import java.io.*;

public class ResponseLengthSender implements IResponseSender {

    private Response response; //发送响应

    public ResponseLengthSender(Response response) {
        this.response = response;
    }

    @Override
    public void sendResponse(byte[] content) throws Exception{
        ICompress compresser = HttpCompressFactory.getInstance().produce( response.getContentEncoding() );
        if(compresser != null){
            content = compresser.compress(content,0,content.length);
        }
        response.setContentLength(content.length);
        response.getSocketOut().write(response.formatRespHeadString().getBytes());
        response.getSocketOut().write(content);
    }

    @Override
    public void sendResponse(String content,String charset) throws Exception{
        if(charset == null){
            charset="utf-8";
        }
        byte[] contentBytes = content.getBytes(charset);
        sendResponse(contentBytes);
    }

    @Override
    public void sendResponse(InputStream content) throws Exception {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(content);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ICompress compresser = HttpCompressFactory.getInstance().produce( response.getContentEncoding() );
        if(compresser != null){
            compresser.compress(content,byteArrayOutputStream);
        } else{
            int len;
            byte[] readBuf = new byte[response.BufferSize];
            while( (len = bufferedInputStream.read(readBuf)) != -1){
                byteArrayOutputStream.write(readBuf,0,len);
            }
        }
        //完成压缩，一次性发出socket更有效率
        byte[] res = byteArrayOutputStream.toByteArray();
        response.setContentLength(res.length);
        response.getSocketOut().write(response.formatRespHeadString().getBytes());
        response.getSocketOut().write(res);
    }

    @Override
    public void sendResponse(Reader content, String charset) throws Exception {
        if(charset == null){
            charset = "utf-8";
        }
        BufferedReader bufferedReader = new BufferedReader(content);
        ICompress compresser = HttpCompressFactory.getInstance().produce( response.getContentEncoding() );
        //用于储存结果
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        char[] readBuf  = new char[response.BufferSize];
        int len;
        while( (len = bufferedReader.read(readBuf)) != -1){
            byte[] byteContent = new String(readBuf,0,len).getBytes(charset);
            if( compresser != null){
                compresser.compress( byteContent,byteArrayOutputStream);
            } else{
                byteArrayOutputStream.write(byteContent);
            }
        }
        //完成压缩，一次性发出socket更有效率
        byte[] res = byteArrayOutputStream.toByteArray();
        response.setContentLength(res.length);
        response.getSocketOut().write(response.formatRespHeadString().getBytes());
        response.getSocketOut().write(res);
    }
}
