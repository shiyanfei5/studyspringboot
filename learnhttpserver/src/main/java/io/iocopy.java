package io;

import java.io.*;

public class iocopy {

    public static void main(String[] args){
        //创建源
        File srcfile = new File("E:\\idla_java_project\\studyspringboot\\learnhttpserver\\src\\study");
        File targetfile = new File("../studycopy");
        //选择流
        InputStream in = null;
        OutputStream out = null ;
        try{
            //赋予实际流
            in = new FileInputStream(srcfile);
            out = new FileOutputStream(targetfile);
            int length;
            //操作
            byte[] buffer = new byte[5];
            while( (length = in.read(buffer)) != -1){
                out.write(buffer,0,length); //
                out.flush();
            }
        } catch ( FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            //资源释放，先打开后关闭，类似栈
            try{
                if(out != null ){ out.close(); }
            } catch ( IOException e){
                e.printStackTrace();
            } finally {
                try {
                    if(out != null ){ out.close(); }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
