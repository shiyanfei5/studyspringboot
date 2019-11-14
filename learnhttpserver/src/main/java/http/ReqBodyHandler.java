package http;

public class ReqBodyHandler {


    private Integer length;   //默认为不定长度
    private HttpVerityEnd verifyHeader = new HttpVerityEnd("\n\r0\n\r");
    private Request request;


    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Boolean process(char[] contentArr, int start, int len, StringBuilder content) {
        if(length == null){
            //未指定长度验证
            //获取本次check的位置
            int checkPos = verifyHeader.verify(contentArr,start,len);
            //加入
            if(verifyHeader.getResult()){
                return true;    //处理完成
            }
            content.append(contentArr,start,checkPos+1);
            return false ;      //未处理完成
        }
        else{
            //指定长度
            content.append(contentArr, start, len);
            length = length - len;
            if (length>0) {
                return false;
            } else {
                return true; //处理完成
            }
        }
    }


}
