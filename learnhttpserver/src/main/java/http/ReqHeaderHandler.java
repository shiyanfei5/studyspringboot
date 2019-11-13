package http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReqHeaderHandler {
    /**
     * 存在状态
     */
    private boolean isFinish = false;   //对象的状态机
    private ReqBodyHandler nextHandler;
    private StringBuilder content = new StringBuilder();
    private HttpVerifyHeader verifyHeader = new HttpVerifyHeader("\n\r\n\r");
    private Map<String,String> headerMap = new HashMap<String, String>();

    public void setNextHandler(ReqBodyHandler nextHandler) {
        this.nextHandler = nextHandler;
    }


    public void process(char[] contentArr, int start,int len){
        //本次校验的字符位置
        int checkPos = verifyHeader.verify(contentArr,start,len);
        //完成校验，责任链直接传给ReqBodyHandler处理
        if(isFinish){
            nextHandler.process(contentArr,start,len);
        } else {
            //说明结尾再该字符数组中
            if (verifyHeader.getResult()) {
                content.append(contentArr, start, checkPos+1);
                isFinish = true;
                this.formatHeaderMap();
                // 当checkPos 小于最后一个字节，说明一部分字节时请求体的，首先对其检查
                if (checkPos < len-1) {
                    char[] bodyArr = new char[len - checkPos -1 ];
                    System.arraycopy(contentArr, checkPos+1, bodyArr, 0, len - checkPos -1);
                    nextHandler.process(bodyArr, 0, len - checkPos -1);
                }

            } else {
                content.append(contentArr, start, checkPos+1); //加入内容
            }
        }
    }


    private void formatHeaderMap() {
        String a = content.toString();
        String[] stringList =  content.toString().split("\r\n");
        for(String line :stringList){
            if(line.contains("POST") || line.contains("GET")){
                String[] i = line.split(" ");
                headerMap.put("method",i[0].trim());
                headerMap.put("url",i[1].trim());
                continue;
            }
            if(line.equals("") ){   //自动去除换行符
                break;
            }else{
                String[] i = line.split(":");
                String value = i[1].trim();
                if(value == null || value.length() < 1){
                    value = null;
                }
                headerMap.put(i[0].trim(),value);
            }


    }


}








}
