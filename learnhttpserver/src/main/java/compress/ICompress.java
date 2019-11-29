package compress;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ICompress {
    public void compress  (InputStream in , OutputStream out) throws IOException;
    public void decompress(InputStream in , OutputStream out) throws IOException;
    public byte[] compress(byte[] data, int offset , int len) throws IOException;
    public void compress(byte[] data, OutputStream os) throws IOException;
    public byte[] compress(InputStream in ) throws IOException;
    public byte[] decompress(byte[] data, int offset , int len)  throws IOException;
    public void decompress(byte[] data, OutputStream os) throws IOException;
    public byte[] decompress(InputStream in) throws IOException;

}
