package compress;


import java.io.*;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/**
 * Http1.1中deflate 是指 ZLIB 格式
 */
public class HttpDeflate implements ICompress {

    /**
     * 压缩
     * @param data 待压缩数据
     * @return byte[] 压缩后的数据
     */
    public  byte[] compress(byte[] data) {
        byte[] output ;
        Deflater compresser = new Deflater();
        compresser.reset();
        compresser.setInput(data);
        compresser.finish();
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
        try {
            byte[] buf = new byte[1024];
            while (!compresser.finished()) {
                int i = compresser.deflate(buf);
                bos.write(buf, 0, i);
            }
            output = bos.toByteArray();
        } catch (Exception e) {
            output = data;
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        compresser.end();
        return output;
    }

    /**
     * 压缩
     * @param data    待压缩数据
     * @param os      输出流
     */
    public  void compress(byte[] data, OutputStream os) {
        DeflaterOutputStream dos = new DeflaterOutputStream(os);
        try {
            dos.write(data, 0, data.length);
            dos.finish();
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解压缩
     * @param data     待压缩的数据
     * @return byte[] 解压缩后的数据
     */
    public  byte[] decompress(byte[] data) {
        byte[] output = new byte[0];
        Inflater decompresser = new Inflater();
        decompresser.reset();
        decompresser.setInput(data);
        ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);
        try {
            byte[] buf = new byte[1024];
            while (!decompresser.finished()) {
                int i = decompresser.inflate(buf);
                o.write(buf, 0, i);
            }
            output = o.toByteArray();
        } catch (Exception e) {
            output = data;
            e.printStackTrace();
        } finally {
            try {
                o.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        decompresser.end();
        return output;
    }

    /**
     * 解压缩
     * @param is 输入流
     * @return byte[] 解压缩后的数据
     */
    public  byte[] decompress(InputStream is) {
        InflaterInputStream iis = new InflaterInputStream(is);
        ByteArrayOutputStream o = new ByteArrayOutputStream(1024);
        try {
            int i = 1024;
            byte[] buf = new byte[i];

            while ((i = iis.read(buf, 0, i)) > 0) {
                o.write(buf, 0, i);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return o.toByteArray();
    }


    public static void main(String[] args) throws  Exception{
        HttpDeflate a = new HttpDeflate();
        String data = "0000007901qqqq.qq.qqqqqqqq0000002171779ad0dfdb4a4f9ac4be18ef3a78080837403181000drpcpqq你好啊";
        System.out.println(data.getBytes().length);
        byte[] compressData = a.compress(data.getBytes("utf-8"));
        System.out.println(compressData.length);
        System.out.println( new String( compressData,"utf-8") );
        byte[] unCompressData = a.decompress(compressData);
        System.out.println( new String( unCompressData,"utf-8") );


    }
}

