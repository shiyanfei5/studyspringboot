package http;

import compress.HttpCompressFactory;
import compress.HttpDeflate;
import compress.HttpGzip;
import compress.ICompress;
import constants.HttpConstant;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * -----------------
 *
 * ----------------
 */
public class Response {
    // 响应首行属性 默认200了
    private Integer status = 200;
    private String httpVersion = "HTTP/1.1";
    private String statusInfo = "OK";
    //http header 字段,
    private Map<String, String> headersMap;  //headerMap 里 仅仅存储唯一的属性，即一个响应里面只能有一个该属性的 内容
    private List<String> cookie;   //cookie,一个响应里面可包含多个cookie属性，单独拎出来

    // 该响应对应的请求
    private Request request;
    // socket流
    private OutputStream socketOut;
    // 报文体缓冲区
    private ByteBuffer bodyBuffer;
    // chunk模式下
    private Integer chunkSize;   //chunk模式即为每个chunk大小，content-length即为lenth大小
    // http响应配置表
    private static final String  BLANK = " ";
    private static final String CRLF = "\r\n";
    private static final Integer BufferSize = 2048;  //单位是字节

    public Response(OutputStream socketOutStream){
        headersMap = new HashMap<>();
        socketOut = socketOutStream;
    }

    public void setRespHeader(String key , String value){
        headersMap.put(key,value);
    }
    public void setStatusLine(Integer statusCode){
        status = statusCode;
        statusInfo  = HttpConstant.StatusInfoMap.get(statusCode);
    }
    public void setContentEncoding(String encoding){
        setRespHeader("Content-Encoding",encoding);
    }
    public void setContentLength(Integer length){
        String len = length==null? null:length.toString();
        setRespHeader("Content-Length",len);
    }
    public void initBodyByteBuffer(){
        if(bodyBuffer == null){
            ByteBuffer.allocate( BufferSize);
        }
    }
    /**
     * @param type
     * @param charset 字符集编码，可以传null，即此时没有字符集
     */
    public void setContentType(String type, String charset){
        StringBuilder sb = new StringBuilder();
        if(type != null ){
            sb.append(type);
        }
        if(charset != null ){
            sb.append(";");
            sb.append("charset=");
            sb.append(charset);
        }
        if(sb.length() > 0 ){
            setRespHeader("Content-Type",sb.toString());
        }
    }

    public String formatRespHeadString(){

        StringBuilder sb = new StringBuilder();
        //首行
        sb.append(httpVersion).append(BLANK).append(status.toString()).append(BLANK).append(statusInfo).append(CRLF);
        for(String key:headersMap.keySet()){
            sb.append(key);
            sb.append(":"+BLANK);
            sb.append(headersMap.get(key));
            sb.append(CRLF);
        }
        sb.append(CRLF);
        return sb.toString();
    }

    /**
     * @param compressedByteArr 等待被压缩的字节数据,其必须为完成的消息实体，否则有影响
     * @return 返回字节数组
     */
    public byte[] compressBodyBuffer(){
        byte[] result;
        ICompress compressor = HttpCompressFactory.getInstance().produce( headersMap.get("Content-Encoding")  );
        if(compressor != null){
            try{
                result = compressor.compress(compressedByteArr);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return result;
    }


    private byte[] compressByteArr(byte[] compressedByteArr,int offset, int len,ICompress compressor ){
        if(compressor != null){
            try{
                compressedByteArr = compressor.compress(compressedByteArr,offset,len);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return compressedByteArr;
    }

    /**
     * 读入字节流
     * @param in
     * @return 返回消息传输后body
     */
    public byte[] formBodyLength(InputStream in){
        ByteArrayOutputStream byteArr = new ByteArrayOutputStream(); //储存内容
        byte[] buff = new byte[1024];
        int len ;
        byte[] result = null ;
        try{
            while( (len = in.read(buff,0,1024)) != -1){
                byteArr.write(buff,0,len);
            }
            result = formBodyLength(byteArr.toByteArray());
            byteArr.flush();
            byteArr.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 读入字符流,charset为预期编码
     * @param reader
     * @return 返回消息传输后body
     */
    public byte[] formBodyLength(Reader reader,  String charset){
        char[] buffer  = new char[1024];
        StringBuilder sb = new StringBuilder();
        int len ;
        byte[] res  = null;
        try{
            while( (len = reader.read(buffer,0,1024)) != -1){
                sb.append(buffer,0,len);
            }
            res = sb.toString().getBytes(charset);
        } catch (Exception e ){
            e.printStackTrace();
        }
        return formBodyLength(res);
    }
    /**
     * 读入字节数组
     * @param bodyContent
     * @return 返回消息传输后body
     */
    public byte[] setResponseBody(byte[] bodyContent){
        initBodyByteBuffer();       //初始化缓冲区
        byte[] result ;             //初始化结果集
        //获取本次的压缩器
        ICompress compressor =
                HttpCompressFactory.getInstance().produce( headersMap.get("Content-Encoding"));
        //bodyBuffer写入次数
        int num ;
        if( bodyContent.length % bodyBuffer.capacity() == 0  ){
            num = bodyContent.length/bodyBuffer.capacity();
        } else{
            num = bodyContent.length/bodyBuffer.capacity()+1;
        }
        //开始写入
        int pos = 0;
        for(int i = 0 ; i < num ; i++){
            byte[] compressed = compressByteArr(bodyContent,pos,bodyBuffer.capacity(), compressor);
            bodyBuffer.put(compressed); //存入缓冲器
            if(bodyBuffer.remaining() <= 0 ){
                
            }

        }













        bodyContent = compressByteArr(bodyContent);
        setContentLength(bodyContent.length);
        return bodyContent;
    }



    public void send() throws Exception{

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(this.socketOut);



        // 将内存中对象进行编码为byte数组
        InputStreamReader in = new InputStreamReader(
                new FileInputStream("E:/idla_java_project/studyspringboot/learnhttpserver/src/main/resources/test.xml")
        );

        byte[] res =  formBodyLength(in,"utf-8");
        byte[] head = formatRespHeadString().getBytes();
        bufferedOutputStream.write(head);
        bufferedOutputStream.write(res );
        bufferedOutputStream.flush();


    }

    public static void main(String[] args) throws Exception{
//        Response response = new Response();
//        ICompress compress = new HttpDeflate();
//        //设置响应头
//        response.setStatusLine(200);
//        // 设置响应传输编码（可为空）
//        response.setRespHeader("Content-Encoding","deflate");
//
//        // 对byte进行压缩
//        System.out.println(res.length);
//        byte[] orgin = compress.decompress(res);
//        System.out.println(orgin.length);
//        System.out.println(new String(orgin));



    }

    public static void method2(String srcString, String destString)
            throws IOException {
        FileInputStream fis = new FileInputStream(srcString);
        FileOutputStream fos = new FileOutputStream(destString);

        byte[] bys = new byte[1024];
        int len = 0;
        while ((len = fis.read(bys)) != -1) {
            fos.write(bys, 0, len);
        }

        fos.close();
        fis.close();
    }

}




