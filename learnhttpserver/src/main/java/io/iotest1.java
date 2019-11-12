package io;

import javax.jws.soap.SOAPBinding;
import java.io.*;

public class iotest1 {

    public static  void main(String[] args) throws Exception{
        //查看工作目录
        System.out.println(System.getProperty("user.dir"));
        //创建源
        File src = new File("E:\\idla_java_project\\studyspringboot\\learnhttpserver\\src\\study");
        //选择流
        InputStream in = null;

        try{
            //读取
            in = new FileInputStream(src);
            byte[] byteArr =new byte[3]; //缓冲
            int len; //实际读取长度，可能实际只读了2个字节
            while( ( len=in.read(byteArr)) != -1){  //文件结束读取为-1
                //字节数组-》字符串
                String str = new String(byteArr,0,len);
            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            //释放源
            try{
                if(null!=in){
                    in.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}
