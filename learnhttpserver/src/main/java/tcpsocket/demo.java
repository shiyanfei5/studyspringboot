package tcpsocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class demo {


    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(20000);
        while(true){

            Socket socket = server.accept();
            System.out.println("-----------请求已进入xxx");
            socket.getInputStream();
            InputStreamReader inputReader = new InputStreamReader( socket.getInputStream() );
            BufferedReader bufferedReader = new BufferedReader(inputReader);

            String line;
            Map<String,String> reqHeader = new HashMap<String, String>();
            while(true){
                System.out.println( bufferedReader.read());
                System.out.println("-----------请求已进入");
            }
//            while ((line=bufferedReader.readLine())!=null){
//
//                if(line.contains("POST") || line.contains("GET")){
//                    String[] i = line.split(" ");
//                    reqHeader.put("method",i[0].trim());
//                    reqHeader.put("url",i[1].trim());
//                    continue;
//                }
//                if(line.equals("") ){   //自动去除换行符
//                    break;
//                }else{
//                    String[] i = line.split(":");
//                    reqHeader.put(i[0].trim(),i[1].trim());
//                }
//
//            }
//            System.out.println(reqHeader);
//            socket.close();
        }

    }
}
