package http;

import com.sun.net.httpserver.HttpServer;
import myhttp.HttpRequest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Request {


    public Request(InputStream in ){
        BufferedReader bufferedReader = null;
        try{
            bufferedReader = new BufferedReader(new InputStreamReader(in,"utf-8"));
        } catch (UnsupportedEncodingException e ){
            e.printStackTrace();
        }

        char[] buffer = new char[100];
        int len;
        ReqHeaderHandler hadler = new ReqHeaderHandler();
        ReqBodyHandler bodyHandler = new ReqBodyHandler();
        hadler.setNextHandler(bodyHandler);


        while(true){
            try{
                len = bufferedReader.read(buffer);
                hadler.process(buffer,0,len);    //每次处理一个buffer大小的内容
            } catch (IOException e){
                e.printStackTrace();
            }

        }

    }



    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(20000);
        while(true){

            Socket socket = server.accept();
            System.out.println("-----------请求已进入xxx");
            Request request = new Request(socket.getInputStream());
            System.out.println("开始下一个");
            socket.close();
        }




   }



}
