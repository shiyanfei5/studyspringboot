package compress;

import java.io.InputStream;
import java.io.OutputStream;

public interface ICompress {

    /**
     * compress 压缩，输入字节数组，对其进行压缩
     * @param data
     * @return 返回一个数组
     */
    public byte[] compress(byte[] data);
    /**
     * compress 压缩，输入字节数组，对其进行压缩
     * @param data
     * @return 返回一个数组
     */
    public void compress(byte[] data, OutputStream os);
    public byte[] decompress(byte[] data);
    public byte[] decompress(InputStream in );


}
