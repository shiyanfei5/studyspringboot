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

    }
}

