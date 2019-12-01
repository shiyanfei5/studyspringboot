package http.response;

import compress.HttpCompressFactory;
import compress.ICompress;
import constants.HttpConstant;
import http.request.Request;
import http.response.sender.IResponseSender;
import http.response.sender.ResponseChunkSender;
import http.response.sender.ResponseLengthSender;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * -----------------
 *  1.content-length: 完成全部响应体的获取和压缩，再发送
 *  2.chunk： 支持压缩部分，发送部分
 * ----------------
 */
public class Response {
    // 响应首行属性 默认200了
    private Integer status = 200;
    private String httpVersion = "HTTP/1.1";
    private String statusInfo = "OK";
    // 请求默认编码
    private String contentCharset;
    //http header 字段,
    private Map<String, String> headersMap;  //headerMap 里 仅仅存储唯一的属性，即一个响应里面只能有一个该属性的 内容
    private List<String> cookieList;   //cookie,一个响应里面可包含多个cookie属性，单独拎出来

    // 该响应对应的请求
    private Request request;
    // socket流
    private OutputStream socketOut;

    // 是否进行chunk
    private boolean isChunk;
    // 报文体缓冲区
    private ByteBuffer bodyBuffer;
    // http响应配置表
    public static final String  BLANK = " ";
    public static final String CRLF = "\r\n";
    public static final Integer BufferSize = 2048;  //单位是字节



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
    public void setContentType(String type){
        StringBuilder sb = new StringBuilder();
        if(type != null ){
            sb.append(type);
        }
        if(contentCharset != null ){
            sb.append(";");
            sb.append("charset=");
            sb.append(contentCharset);
        }
        if(sb.length() > 0 ){
            setRespHeader("Content-Type",sb.toString());
        }
    }
    public void setContentCharset(String charset){
        contentCharset = charset;
    }
    public void setTransferEncoding(String type){
        setRespHeader("Transfer-Encoding",type);
        if(type.equals("chunked")){
            isChunk = true;
        }
    }


    public String getContentEncoding(){ return headersMap.get("Content-Encoding");}
    public OutputStream getSocketOut() {        return socketOut;    }

    public String formatRespHeadString(){

        StringBuilder sb = new StringBuilder();
        //首行
        sb.append(httpVersion).append(BLANK).append(status.toString()).append(BLANK).append(statusInfo).append(CRLF);
        //将headerMap中的信息转换为请求头字符串
        for(String key:headersMap.keySet()){
            sb.append(key);
            sb.append(":"+BLANK);
            sb.append(headersMap.get(key));
            sb.append(CRLF);
        }
        //将cookie内容设置为字符串
        if(cookieList != null){
            for(String cookie:cookieList){
                sb.append("Set-Cookie"+": "+cookie);
                sb.append(CRLF);
            }
        }
        //设置请求头的结束
        sb.append(CRLF);
        return sb.toString();
    }


    public void send(byte[] content ) throws Exception{
        IResponseSender sender = null;
        if(!isChunk){
            sender = new ResponseLengthSender(this);
        } else{
            sender = new ResponseChunkSender(this);
        }
        sender.sendResponse(content);
    }
    public void send(InputStream content ) throws Exception{
        IResponseSender sender = null;
        if(!isChunk){
            sender = new ResponseLengthSender(this);
        } else{
            sender = new ResponseChunkSender(this);
        }
        sender.sendResponse(content);
    }
    public void send(Reader content ) throws Exception{
        IResponseSender sender = null;
        if(!isChunk){
            sender = new ResponseLengthSender(this);
        } else{
            sender = new ResponseChunkSender(this);
        }
        sender.sendResponse(content,contentCharset);
    }
    public void send(String content ) throws Exception{
        IResponseSender sender = null;
        if(!isChunk){
            sender = new ResponseLengthSender(this);
        } else{
            sender = new ResponseChunkSender(this);
        }
        sender.sendResponse(content,contentCharset);
    }




}




