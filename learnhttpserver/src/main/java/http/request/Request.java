package http.request;

import http.ByteAccumulation;
import http.response.Response;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Request {

    private Map<String,String> reqHeader;   //请求参数比存在可能为null
    private byte[] reqBody;     //请求体 ，byte类型用户自己赚

    private Integer byteLength;  //contentLength字段，为null表示不用，
    private Boolean isChunck;   //通过chunked传输
    private Integer chunkSize;  // chunk的大小

    enum State{HEADER,BODY,FINISH}
    private State reqState ;



    //***************************bean对应的方法***********************************
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

    /**
     * 获取请求头的属性，不存在返回null
     * @param attribute
     * @return
     */
    public String getHeader(String attribute){
        return reqHeader.get(attribute);
    }


    public Request(Socket socket) throws Exception {
        this(socket.getInputStream());
    }

    public Request(InputStream in ) throws  Exception{
        reqState = State.HEADER;
        byte[] buffer = new byte[100];     //待处理报文缓冲区

        Integer waitProcessStartPos ;  //储存待处理的报文处的起始位置
        Integer len  ;    //储存待处理的报文长度

        ReqHeaderHandler reqhandler = new ReqHeaderHandler(this);
        ReqBodyHandler bodyHandler = new ReqBodyHandler(this);
        reqhandler.setNextHandler(bodyHandler);
        ByteAccumulation contentHeaderOrSize = new ByteAccumulation();   //content累加
        ByteAccumulation content = new ByteAccumulation(); //chunkSize字节累加

        //读取一次
        while( (len = in.read(buffer)) != -1 ){
            waitProcessStartPos = 0 ; //每次待处理位置都为0

            //若等待请求头的处理
            if(reqState.equals(State.HEADER)){
                Integer processPos =
                        reqhandler.process(buffer,waitProcessStartPos,len,contentHeaderOrSize);    //每次处理一个buffer大小的内容
                len = len-(processPos-waitProcessStartPos+1); //剩余长度
                waitProcessStartPos = processPos + 1;
                //当请求头校验结束后，若该buffer即包含请求头，又包含请求体
            }
            if(reqState.equals(State.BODY)) {
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
                                        bodyHandler.processChunkSize(buffer,waitProcessStartPos,len,contentHeaderOrSize);

                                len = len-(processPos-waitProcessStartPos+1); //剩余长度
                                waitProcessStartPos = processPos + 1;

                                if(chunkSize == 0+2){   //算上分隔符,此时完成
                                    reqState = State.FINISH;
                                    break;
                                }
                            }
                            if(chunkSize != null) {  //说明已经拿到了chunkSize
                                Integer processPos = bodyHandler.processChunkBody(
                                        buffer,waitProcessStartPos,len,content
                                );
                                len = len-(processPos-waitProcessStartPos+1); //剩余长度
                                waitProcessStartPos = processPos  +1 ; //下次起始位置

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





    public static void main(String[] args) throws  Exception{

            ServerSocket server = new ServerSocket(20000);
            int i = 0;
            while (true) {
                try {
                Socket socket = server.accept();
                System.out.println("-----------请求已进入xxx");
                Request request = new Request(socket);
                System.out.println(request.getReqHeader());
                Response resp = new Response(socket.getOutputStream());
                //设置响应头
                resp.setStatusLine(200);
                // 设置响应传输编码（可为空）
                resp.setContentEncoding("gzip");
                if(i%2 == 0){
                    resp.setTransferEncoding("chunked");
                }
                InputStream in = new FileInputStream(
                        Request.class.getClassLoader().getResource("QQWhatsnew.txt").getPath());
                resp.send(in);
                System.out.println("开始下一个");

                } catch ( Exception e){
                    e.printStackTrace();
                }
                i++;

            }


   }



}
