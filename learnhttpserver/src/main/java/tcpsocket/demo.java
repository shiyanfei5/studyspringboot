package tcpsocket;

import myhttp.HttpRequest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class demo {


    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(20000);
        System.out.print("aaa"+(char)10);
        System.out.print("aaa"+(char)10);
        while(true){

            Socket socket = server.accept();
            System.out.println("-----------请求已进入xxx");
            socket.getInputStream();
//            InputStreamReader inputReader = new InputStreamReader( socket.getInputStream() );
//            BufferedReader bufferedReader = new BufferedReader(inputReader);
            InputStream in = socket.getInputStream();
            byte[] byteContent = new byte[100];
            Integer length ;
//            String line;
            HttpRequest request = new HttpRequest();
            //处理请求头
            while ( (length = in.read(byteContent)) != -1 ){
                System.out.print(new String(byteContent,0,length,"utf-8"));
                System.out.println("该次读取结束");
//                if(line.contains("POST") || line.contains("GET")){
//                    String[] i = line.split(" ");
//                    request.getReqHeader().put("method",i[0].trim());
//                    request.getReqHeader().put("url",i[1].trim());
//                    continue;
//                }
//                if(line.equals("") ){   //自动去除换行符
//                    break;
//                }else{
//                    String[] i = line.split(":");
//                    request.getReqHeader().put(i[0].trim(),i[1].trim());
//                }
            }
//            Integer length  =  request.getReqHeader().get("content-length") !=null ? ;
            System.out.println("开始下一个");
            socket.close();
        }

    }
}
