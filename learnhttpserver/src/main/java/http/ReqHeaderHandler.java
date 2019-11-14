package http;

import java.util.HashMap;

public class ReqHeaderHandler {

    /**
     * 状态机
     */
    private Request request;    //绑定处理的请求
    private boolean isFinish = false;   //对象的状态机，若其完成则委托后续请求体处理
    private ReqBodyHandler nextHandler;
    private HttpVerityEnd verifyHeader = new HttpVerityEnd("\n\r\n\r");

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public void setNextHandler(ReqBodyHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
    public void setRequest(Request request) {
        this.request = request;
    }

    public Boolean process(char[] contentArr, int start,int len,StringBuilder content){

        //若请求头完成校验，责任链直接传给ReqBodyHandler处理
        if(isFinish){
            return nextHandler.process(contentArr,start,len,content);
        } else {
            //若未完成，首先进行校验
            int checkPos = verifyHeader.verify(contentArr,start,len);
            //说明结尾再该字符数组中
            if (verifyHeader.getResult()) {
                content.append(contentArr, start, checkPos+1);
                setFinish(true);    //表明已完成校验
                this.contentTransformHeaderMap(content);  //将content字符串转为map

                //判断请求类型
                String type = request.getReqHeader().get("method");
                if("GET".equals(type)){
                    //若为GET请求，仅仅有请求头
                    return true;
                }

                //是否包含请求体长度
                Integer length = Integer.parseInt(
                        request.getReqHeader().get("Content-Length")
                );
                nextHandler.setLength(length); //length有值或未null

                // 当checkPos 小于最后一个字节，说明一部分字节时请求体的，首先对其检查
                if (checkPos < len-1) {
                    return nextHandler.process
                            (contentArr, checkPos+1, len - checkPos -1,content);
                }

            } else {
                content.append(contentArr, start, checkPos+1); //加入内容
            }
        }
        return false;
    }


    private void contentTransformHeaderMap(StringBuilder content) {
        String[] stringList =  content.toString().split("\r\n");
        if(request.getReqHeader() == null){
            request.setReqHeader( new HashMap<String, String>());
        }
        for(String line :stringList) {
            if (line.contains("POST") || line.contains("GET")) {
                String[] i = line.split(" ");
                request.getReqHeader().put("method", i[0].trim());
                request.getReqHeader().put("url", i[1].trim());
                continue;
            }
            if (line.equals("")) {   //自动去除换行符
                break;
            } else {
                String[] i = line.split(":");
                String value = i[1].trim();
                if (value == null || value.length() < 1) {
                    value = null;
                }
                request.getReqHeader().put(i[0].trim(), value);
            }
        }
        content.delete(0,content.length());     //形成后清空
    }


}









