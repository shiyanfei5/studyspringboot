package http;

import com.sun.net.httpserver.HttpServer;
import myhttp.HttpRequest;

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

        while(reqState != State.FINISH ){
            //进行请求头处理
            if(reqState == State.HEADER){
                len = in.read(buffer);
                Integer processPos =
                        reqhandler.process(buffer,0,len,content);    //每次处理一个buffer大小的内容
                //当请求头校验结束后，若该buffer即包含请求头，又包含请求体
                if(reqState == State.BODY && processPos < len - 1 ){
                    waitProcessStartPos = processPos+1 ;
                    len = len-(processPos+1);
                }
            }
            if(reqState == State.BODY) {
                //开始解析请求体之前，首先判断是否为get请求，get无请求体
                if( "GET".equals( reqHeader.get("method"))){
                    reqState = State.FINISH;
                    break;
                }
                //查看是否存在待处理报文（分割或其他所致）
                if(waitProcessStartPos == null){
                    len = in.read(buffer);  //更新buffer
                    waitProcessStartPos = 0;
                }
                //选择处理模式,byteLength存在时用此模式
                if(isChunck ){


                } else {
                    // 按照content-length进行处理
                    bodyHandler.processByContentLength(buffer,waitProcessStartPos,len,content);
                    waitProcessStartPos = null; //表示没有待处理的了
                }




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
