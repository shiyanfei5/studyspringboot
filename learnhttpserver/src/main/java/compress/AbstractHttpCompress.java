package compress;

import java.io.*;

/**
 * HttpCompress类，模板模式进行 响应体的压缩于解压
 * 模板模式：定义出运行的基本步骤，抽象出要特异化的部分，继承类实现
 */
public abstract class AbstractHttpCompress implements ICompress{

    static Integer BUFFERSIZE = 1024;
    public abstract void compress  (InputStream in , OutputStream out) throws IOException;
    public abstract void decompress(InputStream in , OutputStream out) throws IOException;


    public byte[] compress(byte[] data) throws IOException {
        //输入待压缩byte数组
        ByteArrayInputStream byteIn = new ByteArrayInputStream(data);
        //结果
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();

        compress(byteIn,byteOut);
        byte[] result = byteOut.toByteArray();
        byteOut.flush();
        byteOut.close();
        byteIn.close();
        return result;
    }

    public void compress(byte[] data, OutputStream os) throws IOException  {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        compress(byteArrayInputStream,os);
        byteArrayInputStream.close();
    }

    public byte[] compress(InputStream in ) throws IOException{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        compress(in,byteArrayOutputStream);
        byte[] result = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        return result;
    }


    //**********************解压，输入的为压缩过的二进制数据******************//
    public byte[] decompress(byte[] data)  throws IOException{
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //创建两个ByteArray字节流
        decompress(byteArrayInputStream,byteArrayOutputStream);
        //关闭输入流
        byteArrayInputStream.close();
        byte[] result = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        return result;
    }


    public void decompress(byte[] data, OutputStream os) throws IOException{
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        decompress(byteArrayInputStream,os);
        byteArrayInputStream.close();
    }

    public byte[] decompress(InputStream in) throws IOException{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        decompress(in,byteArrayOutputStream);
        byte[] result = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        return result;


    }




}
