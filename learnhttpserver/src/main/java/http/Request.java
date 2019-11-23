package http;

import com.sun.net.httpserver.HttpServer;
import myhttp.HttpRequest;
import org.apache.coyote.ProtocolHandler;
import sun.net.httpserver.HttpServerImpl;
import sun.net.www.http.HttpClient;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class Request {

    private Map<String,String> reqHeader;   //请求参数比存在可能为null
    private byte[] reqBody;     //请求体 ，byte类型用户自己赚


    private Integer byteLength;  //contentLength字段，为null表示不用，
    private Boolean isChunck;   //通过chunked传输
    private Integer chunkSize;  // chunk的大小
    enum State{HEADER,BODY,FINISH}
    private State reqState;




    public void setByteLength(Integer contentLength) {
        this.byteLength = contentLength;
    }
    public byte[] getReqBody() {
        return reqBody;
    }
    public Map<String, String> getReqHeader() {
        return reqHeader;
    }
    public void setReqBody(byte[] reqBody) {
        this.reqBody = reqBody;
    }
    public void setReqHeader(Map<String, String> reqHeader) {
        this.reqHeader = reqHeader;
    }
    public void setChunck(Boolean chunck) {
        isChunck = chunck;
    }
    public void setChunkSize(Integer chunkSize) {
        this.chunkSize = chunkSize;
    }
    public void setReqState(State reqState) {
        this.reqState = reqState;
    }
    public Integer getByteLength() {
        return byteLength;
    }

    public Boolean getChunck() {
        return isChunck;
    }

    public Integer getChunkSize() {
        return chunkSize;
    }

    public Request(Socket socket) throws Exception {
        this(socket.getInputStream());
    }

    public Request(InputStream in ) throws  Exception{
        byte[] buffer = new byte[1024];     //待处理报文缓冲区
        Integer waitProcessStartPos = null;  //储存待处理的报文处的起始位置
        Integer len = null ;    //储存待处理的报文长度

        ReqHeaderHandler reqhandler = new ReqHeaderHandler(this);
        ReqBodyHandler bodyHandler = new ReqBodyHandler(this);
        reqhandler.setNextHandler(bodyHandler);
        ByteAccumulation content = new ByteAccumulation();   //content累加
        ByteAccumulation chunkContent = new ByteAccumulation(); //chunkSize字节累加

        //读取一次
        while( (len = in.read(buffer)) != -1 ){
            waitProcessStartPos = 0 ; //每次read完待处理位置都为0
            //若等待请求头的处理，则处理
            if(reqState == State.HEADER){
                Integer processPos =
                        reqhandler.process(buffer,waitProcessStartPos,len,content);    //每次处理一个buffer大小的内容
                waitProcessStartPos = processPos + 1;
                len = len-(processPos+1); //剩余长度
                //当请求头校验结束后，若该buffer即包含请求头，又包含请求体
                if(reqState != State.BODY || len <= 0 ){   //待处理报文
                    continue;
                }
            }
            if(reqState == State.BODY) {
                //开始解析请求体之前，首先判断是否为get请求，get无请求体
                if( "GET".equals( reqHeader.get("method"))){
                    reqState = State.FINISH;
                }
                //选择处理模式,byteLength存在时用此模式
                if(isChunck ){
                    while(true){
                        if(len <= 0 || reqState.equals( State.FINISH) ){
                            //退出条件
                            break;
                        }
                        else{
                            if(chunkSize == null ){
                                Integer processPos =
                                        bodyHandler.processChunkSize(buffer,waitProcessStartPos,len,chunkContent);
                                waitProcessStartPos = processPos + 1;
                                len = len-(processPos+1); //剩余长度
                                if(chunkSize == 0){
                                    reqState = State.FINISH;
                                }
                            }
                            if(chunkSize != null) {  //说明已经拿到了chunkSize
                                //To-Do读取chunkSize的body内容

                                chunkSize = null;
                            }
                        }
                    }
                } else {
                    // 按照content-length进行处理
                    bodyHandler.processByContentLength(buffer,waitProcessStartPos,len,content);
                }
            }
            if(reqState.equals(State.FINISH)){
                break;
            }
        }
        System.out.println("xx");
    }





    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(20000);
            while (true) {
                Socket socket = server.accept();
                System.out.println("-----------请求已进入xxx");
                Request request = new Request(socket);
                System.out.println(request.getReqHeader());
                System.out.println(new String(request.getReqBody()));
                System.out.println("开始下一个");
            }
        } catch ( Exception e){
            e.printStackTrace();
        }




   }



}
