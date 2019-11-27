package compress;


import java.io.InputStream;
import java.io.OutputStream;

/**
 * Http1.1中  GZIP 是指 GZIP 格式
 */
public class HttpGzip implements ICompress {


    @Override
    public byte[] compress(byte[] data) {
        ByteArr


    }

    @Override
    public void compress(byte[] data, OutputStream os) {

    }

    @Override
    public byte[] decompress(byte[] data) {
        return new byte[0];
    }

    @Override
    public byte[] decompress(InputStream in) {
        return new byte[0];
    }
}
