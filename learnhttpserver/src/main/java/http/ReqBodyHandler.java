package http;

import static util.ByteUtil.byteArrayAppend;

public class ReqBodyHandler {


    private HttpVerityEnd verifyBody = new HttpVerityEnd("\n\r");
    private Request request;

    public ReqBodyHandler(Request request) {
        this.request = request;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
    public void setChunked(Boolean chunked) {
        isChunked = chunked;
    }


    public Int processBySingleChunk(byte[] contentArr, int start, int len, ByteAccumulation content) {

        //chunk模式下，首先判断是否已经提取出该chunk的大小，则通过httpverify验证\r\
        if(request.getChunkSize() == null){
            //未提取出chunk大小
            int checkPos = verifyBody.verify(contentArr,start,len);
            if(verifyBody.getResult()){
                //若成功验证到了结尾，去除结尾结束符
                request.setChunkSize(
                         extractChunkSize(contentArr,start,checkPos-start+1-2)
                );
                //含有结束符"/r/n，所有长度-2
                content.clear();
                //多读了
                if(checkPos < start+len-1){
                    chunkSize = chunkSize - ( len - (checkPos - start+1) );
                    content.append(contentArr,checkPos+1, len - (checkPos - start+1));
                }


            } else{
                //当未校验到结尾时，全部加入content中
                content.append(contentArr,start, checkPos-start+1);   //追加
                return false ;      //未处理完成
            }
        }
        //获取本次check的位置
        int checkPos = verifyBody.verify(contentArr,start,len);





    }


    private Integer extractChunkSize(byte[] content,int start,int len){
        int result = 0;
        for(int i = start; i < len ; i ++){
            result = result * 16 + ( (char)content[i] - '0');
        }
        return result;
    }

    public void processByContentLength(byte[] contentArr, int start, int len, ByteAccumulation content){
        content.append(contentArr,start,len);
        request.setByteLength( request.getByteLength() - len);
        if (request.getByteLength()<=0) {
            request.setReqState(Request.State.FINISH);
            request.setReqBody(content.getContent());
        }

    }



}
