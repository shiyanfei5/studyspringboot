package compress;


import java.io.*;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.InflaterInputStream;

/**
 * Http1.1中  GZIP 是指 GZIP 格式
 */
public class HttpGzip extends AbstractHttpCompress  implements ICompress {

    @Override
    public void compress(InputStream in , OutputStream os ) throws IOException {
        GZIPOutputStream dos = new GZIPOutputStream(os);
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
        GZIPInputStream deflaterInputStream = new GZIPInputStream(in);
        int len;
        byte[] data = new byte[BUFFERSIZE];
        while( (len = deflaterInputStream.read(data,0,BUFFERSIZE)) != -1){
            out.write(data,0,len);
        }
    }



}
