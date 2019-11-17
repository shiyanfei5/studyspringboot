package http;

import util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import static util.ByteUtil.byteArrayAppend;

public class ReqHeaderHandler {

    /**
     * 状态机
     */
    private Request request;    //绑定处理的请求
    private boolean isFinish = false;   //对象的状态机，若其完成则委托后续请求体处理
    private ReqBodyHandler nextHandler;
    private HttpVerityEnd verifyHeader = new HttpVerityEnd("\n\r\n\r");

    public ReqHeaderHandler(Request request) {
        this.request = request;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public void setNextHandler(ReqBodyHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public Integer process(byte[] contentArr, int start,int len,ByteAccumulation content){
        //若未完成，首先进行校验
        int checkPos = verifyHeader.verify(contentArr,start,len);
        //说明已经校验到结尾了
        if (verifyHeader.getResult()) {
            int checkLength = checkPos-start+1;
            content.append(contentArr, start, checkLength);

            //设置请求头的map
            Map<String,String> reqHeaderMap =
                    HttpUtil.contentTransformHeaderMap(content.getContent());  //将content字符串转为map
            request.setReqHeader(reqHeaderMap);
            //清空累计内容,迎接请求体解析
            content.clear();

            // 设置属性
            Integer length = request.getReqHeader().get("Content-Length") == null?
                    null:Integer.parseInt(
                    request.getReqHeader().get("Content-Length")
            );
            request.setByteLength(length); //length有值或未null
            request.setChunck(
                    "chunked".equals( request.getReqHeader().get("Transfer-Encoding") )
            );
            //推进处理状态
            request.setReqState(Request.State.BODY);
        } else {
            content.append(contentArr,start, checkPos-start+1); //加入内容
        }
        return checkPos;

    }





}









