package io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class reader {

    public static  void main(String[] args)  {

        //创建源
        File src = new File("E:\\idla_java_project\\studyspringboot\\learnhttpserver\\src\\study");
        //选择留
        Reader reader = null;
        //操作

        try {
            reader = new FileReader(src);
            char[] flush = new char[2]; //缓冲队列
            int len = -1;
            while ((len = reader.read(flush)) != -1) {
                String str = new String(flush, 0, len);
                System.out.print(str);
            }
        } catch ( IOException e){
            e.printStackTrace();
        } finally {
            try{
                if(reader !=null ) reader.close();
            } catch ( IOException e){
                e.printStackTrace();
            }

        }
    }


}
