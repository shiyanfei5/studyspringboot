package http.response.sender;

import http.response.Response;

import java.io.InputStream;
import java.io.Reader;

public interface IResponseSender {

    /**
     * content此时为byte数组，表明此时传输的请求内容已全部在内存中
     * @param content 字节
     */
    public void sendResponse(byte[] content) throws Exception;
    /**
     * content此时为字符串，表明此时传输的请求内容已全部在内存中
     * @param content 字节
     */

    public void sendResponse(String content,String charset) throws Exception ;
    /**
     * content此时为字节流，需要读取content内容
     * @param content 字节
     */
    public void sendResponse(InputStream content) throws Exception ;
    /**
     * content此时为字符流，需要读取content内容
     * @param content 字节
     */
    public void sendResponse(Reader content,String charset) throws Exception;



}
