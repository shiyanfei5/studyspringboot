package tcpsocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    private ServerSocket serverSocket;




    //server启动
    public void start(){
        try{
            serverSocket = new ServerSocket(8888);
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("服务器启动失败");
        }

    }

    //接收请求，阻塞
    public void receive(){
        try{
            Socket client = serverSocket.accept();
        } catch (IOException e ){
            e.printStackTrace();
            System.out.println("客户端异常");
        }

    }

    //停止
    public void stop(){
    }




}
