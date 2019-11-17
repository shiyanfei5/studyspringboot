package util;

public class ByteUtil {


    /**
     *
     * @param src 要追加的byte数组
     * @param start 追加数组起始
     * @param len
     * @param des
     * @return
     */
    public static byte[] byteArrayAppend(byte[] src,  int start, int len,byte[] des){
        int desLength = des.length + len;
        int desPos =  des.length;
        byte[] newDesByte = new byte[desLength];

        System.arraycopy(
                des,0,
                newDesByte,0,     //追加的位置再尾部
                des.length
        );

        System.arraycopy(
                src,start,
                newDesByte,desPos,     //追加的位置再尾部
                len
        );
        return newDesByte;
    }
}
