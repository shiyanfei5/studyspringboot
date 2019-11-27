package http;

import constants.HttpConstant;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response {
    // 响应首行属性 默认200了
    private Integer status = 200;
    private String httpVersion = "HTTP/1.1";
    private String statusInfo = "OK";
    //http header 字段,
    private Map<String, String> headersMap;  //headerMap 里 仅仅存储唯一的属性，即一个响应里面只能有一个该属性的 内容
    private List<String> cookie;   //cookie,一个响应里面可包含多个cookie属性，单独拎出来
    //http body
    private Integer chunkNum;       //chunkNum 为null表示使用content-length模式
    private Integer size;   //chunk模式即为每个chunk大小，content-length即为lenth大小
    private byte[] bodyContent;

    // http响应配置表
    private static final String  BLANK = " ";
    private static final String CRLF = "\r\n";
    private static final Integer BufferSize = 1024;  //单位是字节

    public Response(){
        setStatusLine(200);
    }

    public void setRespHeader(String key , String value){
        headersMap.put(key,value);
    }
    private void setStatusLine(Integer statusCode){
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

    public void setContentType(String type, String charset){
        StringBuilder sb = new StringBuilder();
        sb.append(type);
        sb.append(";");
        sb.append("charset=");
        sb.append(charset);
        setRespHeader("Content-Type",sb.toString());
    }

    public String formatRespHeadString(){

        StringBuilder sb = new StringBuilder();
        //首行
        sb.append(status.toString()).append(BLANK).append(httpVersion).append(BLANK).append(statusInfo).append(CRLF);
        for(String key:headersMap.keySet()){
            sb.append(key);
            sb.append(":"+BLANK);
            sb.append(headersMap.get(key));
            sb.append(CRLF);
        }
        return sb.toString();
    }

    /**
     * 要求传入的字节byte content已经经过了charset类型的编码
     * @param content
     * @param type
     * @param charset
     * @param byteEncoding
     */
    public void formatBodyContentLength(byte[] content,String type,String charset,String byteEncoding){
        //1.设置content-type
        setContentType(type,charset);
        //2.进行content-encoding,对 content字节进行编码
        setContentLength(content.length);






    }


    }




