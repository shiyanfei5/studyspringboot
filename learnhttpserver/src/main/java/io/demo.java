package io;


import java.io.File;

/**
 *
 */
public class demo {


    public static  void main(String[] args){



        //路径的分隔符,不同操作系统不同。windows为\，linux为/
        System.out.println("路径的分隔符"+ File.separator);
        //路径在java中尽量使用unix的路径分隔符，java平台通用
        String path1 = "E:/music/CloudMusic/cloudmusic.exe";
        String path2 = "E:/music"+File.separator+"CloudMusic";
        System.out.println(path2);

        //File对象的构建
        File src = new File(path1);
        System.out.println(src.length());
        src = new File(path2,"cloudmusic.exe"); //目录+程序名构建
        System.out.println(src.length());

        //获取绝对路径
        src = new File("xiangdui/1.txt");   //不存在的文件
        System.out.println( src.getAbsolutePath());

        //获取文件名
        System.out.println(src.getName());
        System.out.println(src.getPath()); //若new时为相对则相对，否则为绝对
        System.out.println(src.getParent()); //父路径

        //判断文件状态
        src.exists();
        src.isFile();
        src.isDirectory();

        //文件长度
        src.length();//src若为目录，则为文件中的文件和文件夹数量，否则为文件大小

    }
}
