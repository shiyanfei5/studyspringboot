package http;

import static util.ByteUtil.byteArrayAppend;

public class ReqBodyHandler {


    private HttpVerityEnd verifyBody = new HttpVerityEnd("\n\r");
    private Request request;

    public ReqBodyHandler(Request request) {
        this.request = request;
    }
    
    public Integer processChunkSize(byte[] contentArr, int start, int len, ByteAccumulation content) {

        //chunk模式下，首先判断是否已经提取出该chunk的大小，则通过httpverify验证\r\
        Integer checkPos = null  ;
        if(request.getChunkSize() == null){
            //未提取出chunk大小
            checkPos = verifyBody.verify(contentArr,start,len);
            if(verifyBody.getResult()){

                verifyBody.refresh(); //保证下回继续使用，进行如refresh
                //若成功验证到了结尾，去除结尾结束符
                content.append(contentArr,start,checkPos+1-start);
                request.setChunkSize(
                         extractChunkSize(content.consume(),0)+2
                );


            } else{
                //当未校验到结尾时，全部加入content中 用以下次验证
                content.append(contentArr,start, len);   //追加

            }
        }
        return checkPos;

    }

    public Integer processChunkBody(byte[] contentArr, int start, int len, ByteAccumulation content) {

        //chunk模式下，首先判断是否已经提取出该chunk的大小，则通过httpverify验证\r\
        Integer checkPos  ;

        //此时len<chunk大小，未读取完
        if(len < request.getChunkSize()){
            content.append(contentArr,start,len);
            checkPos = start + len - 1;
            request.setChunkSize( request.getChunkSize() - len);
        } else{
            //读取完该chunk内容
            content.append(contentArr,start,request.getChunkSize());
            content.deleteTail(2);  //删除chunk最后的两个分隔符 '\r,\n'
            checkPos = start + request.getChunkSize() - 1;
            request.setChunkSize(null);

        }
        return checkPos;


    }

    private Integer extractChunkSize(byte[] content,int start){
        int result = 0;
        for(int i = start; i < content.length ; i ++){
            if(content[i] != 0x0d && content[i] != 0x0a){       //不等于cr和lf的字符，其以该字符结尾
                //若为数字
                if(content[i] >= 48 && content[i] <= 57){
                    result = result * 16 + ( (char)content[i] - '0');
                } else if(content[i] >= 97 && content[i] <= 102){
                    //若为字母（小写）
                    result = result * 16  + (content[i] - 87);
                }

            }
        }
        return result;
    }

    public void processByContentLength(byte[] contentArr, int start, int len, ByteAccumulation content){
        content.append(contentArr,start,len);
        request.setByteLength( request.getByteLength() - len);
        if (request.getByteLength()<=0) {
            request.setReqState(Request.State.FINISH);
            request.setReqBody(content.consume());
        }

    }



}
