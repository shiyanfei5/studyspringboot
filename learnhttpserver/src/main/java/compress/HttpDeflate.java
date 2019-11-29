package compress;


import java.io.*;
import java.util.zip.*;

/**
 * Http1.1中deflate 是指 ZLIB 格式
 */
public class HttpDeflate extends AbstractHttpCompress implements ICompress{


    @Override
    public void compress(InputStream in , OutputStream os ) throws IOException{
        DeflaterOutputStream dos = new DeflaterOutputStream(os);
        int len;
        byte[] data = new byte[BUFFERSIZE];
        while( (len = in.read(data,0,BUFFERSIZE)) != -1 ){
            dos.write(data,0,len);
        }
        dos.finish();
        dos.flush();
        dos.close();

    }

    @Override
    public void decompress(InputStream in, OutputStream out) throws IOException {
        InflaterInputStream deflaterInputStream = new InflaterInputStream(in);
        int len;
        byte[] data = new byte[BUFFERSIZE];
        while( (len = deflaterInputStream.read(data,0,BUFFERSIZE)) != -1){
            out.write(data,0,len);
        }
    }

    public static void main(String[] args) throws  Exception{
        HttpDeflate a = new HttpDeflate();
        String data = "0000007901qqqq.qq.qqqqqqqq0000002171779ad0dfdb4a4f9ac4be18ef3a78080837403181000drpc你好pqq";
        System.out.println(data.getBytes().length);
        byte[] compressData = a.compress(data.getBytes("utf-8"));
        System.out.println(compressData.length);
        System.out.println( new String( compressData,"utf-8") );
        byte[] unCompressData = a.decompress(compressData);
        System.out.println( new String( unCompressData,"utf-8") );


    }
}

