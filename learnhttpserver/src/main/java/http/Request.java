package http;

import com.sun.net.httpserver.HttpServer;
import myhttp.HttpRequest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Request {

    private Map<String,String> reqHeader;

    public Map<String, String> getReqHeader() {
        return reqHeader;
    }
    
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
        hadler.setRequest(this);
        ReqBodyHandler bodyHandler = new ReqBodyHandler();
        hadler.setNextHandler(bodyHandler);
        Boolean is = false;
        StringBuilder content = new StringBuilder();    //字符累计器，用于累计

        while(!is){

            try{
                len = bufferedReader.read(buffer);
                is = hadler.process(buffer,0,len,content);    //每次处理一个buffer大小的内容
            } catch (IOException e){
                e.printStackTrace();
            }

        }
        System.out.println("xx");

    }


    public void contentTransformHeaderMap(StringBuilder content) {
        String[] stringList =  content.toString().split("\r\n");
        if(reqHeader == null){
            reqHeader =  new HashMap<String, String>();
        }
        for(String line :stringList) {
            if (line.contains("POST") || line.contains("GET")) {
                String[] i = line.split(" ");
                reqHeader.put("method", i[0].trim());
                reqHeader.put("url", i[1].trim());
                continue;
            }
            if (line.equals("")) {   //自动去除换行符
                break;
            } else {
                String[] i = line.split(":");
                String value = i[1].trim();
                if (value == null || value.length() < 1) {
                    value = null;
                }
                reqHeader.put(i[0].trim(), value);
            }
        }
        content.delete(0,content.length());     //形成后清空
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
