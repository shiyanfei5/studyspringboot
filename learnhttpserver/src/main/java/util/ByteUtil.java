package util;

import java.nio.ByteBuffer;

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

    /**
     * 消费byteBuffer中所有的数据，消费完成后 进入初始化状态等待写入
     * @param buffer ByteBuffer读取
     * @return
     */
    public static  byte[] consumeByteBuffer(ByteBuffer buffer){
        // flip方法将Buffer从写模式切换到读模式。调用flip()方法会将position设回0，并将limit设置成之前position的值。
        buffer.flip();
        byte[] result = new byte[ buffer.limit()];  //获取结果集长度
        buffer.get(result); //消费
        // 初始化等待吸入
        buffer.clear();
        return result;
    }


}
