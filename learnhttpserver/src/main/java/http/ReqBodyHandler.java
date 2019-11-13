package http;

public class ReqBodyHandler {


    private boolean isLength = false;   //默认为不定长度
    private StringBuilder content = new StringBuilder();
    private HttpVerifyHeader verifyHeader = new HttpVerifyHeader("\n\r0\n\r");

    public StringBuilder getContent() {
        return content;
    }

    public boolean isLength() {
        return isLength;
    }

    public void setLength(boolean length) {
        isLength = length;
    }

    public void process(char[] contentArr, int start,int len) {
        if(isLength){
            content.append(contentArr,start,len);
        }
        else{
            //未指定长度
            //获取本次校验字符数量
            int checkSize = verifyHeader.verify(contentArr,start,len);
            if (verifyHeader.getResult()) {
                content.append(contentArr, start, checkSize);   //加入结尾内容
            } else {
                content.append(contentArr, start, checkSize); //加入内容
            }
        }
    }


}
