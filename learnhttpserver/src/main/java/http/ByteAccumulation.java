package http;

import util.ByteUtil;

public class ByteAccumulation {
    private static int initCapital = 100;
    private byte[] content;
    public Integer endPos;  //空为-1，

    public ByteAccumulation() {
        content = new byte[initCapital];
        endPos = -1;
    }

    /**
     *  为空时，endpos为-1，返回整个content长度
     * @return
     */
    public Integer getFreeSize(){
        return content.length - endPos-1;
    }

    public void append(byte[] src, int start, int len){
        if(len <= getFreeSize()){
            //写入到Byte中
            System.arraycopy(src,start,content,endPos + 1, len);
            endPos = endPos+len;
        } else {
            //自动增加1.5倍
            byte[] newContent = new byte[len + content.length*3/2];
            if(endPos > -1){
                //不为空，先把原内容拷贝
                System.arraycopy(content,0,newContent,0,endPos+1);
            }
            System.arraycopy(src,start,newContent,endPos+1,len);
            content = newContent;
            endPos = endPos+1 + len -1 ;    //最终位置
        }
    }

    public byte[] consume() {
        byte[] result = new byte[endPos+1];
        System.arraycopy(content,0,result,0,endPos+1);
        endPos = -1;
        content = new byte[initCapital];
        return result;
    }


    public Integer length(){
        return endPos+1;
    }

    public void deleteTail(int num){
        endPos = endPos - num;
    }

}
