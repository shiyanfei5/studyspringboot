package http;

import util.ByteUtil;

public class ByteAccumulation {
    private byte[] content;

    public ByteAccumulation() {
        content = new byte[0];
    }

    public void append(byte[] src, int start, int len){
        content = ByteUtil.byteArrayAppend(src,start,len,content);
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void clear(){
        content = new byte[0];
    }

}
