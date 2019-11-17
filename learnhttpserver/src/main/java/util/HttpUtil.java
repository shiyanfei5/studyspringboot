package util;

import java.util.HashMap;
import java.util.Map;

public class HttpUtil {

    /**
     * String 解析请求头形成map
     * @param content
     * @return
     */
    public  static Map<String,String> contentTransformHeaderMap(byte[] content) {
        //初始化返回结果
        Map<String,String> reqHeader = new HashMap<String, String>();
        //转为String，注意字符集
        String contentString = null;
        try{
            contentString = new String(content);
        } catch (Exception e){
            e.printStackTrace();
            return reqHeader;
        }

        String[] stringList =  contentString.split("\r\n");
        for(int i = 0 ; i < stringList.length; i++) {
            String line = stringList[i];    //获取该行
            //若为第一行处理请求首行url
            if (i == 0 ) {
                String[] string = line.split(" ");
                reqHeader.put("method", string[0].trim());
                String url = string[1].trim();
                String urlParameters = parseUrlParameters(url);
                reqHeader.put("url", string[1].trim());
                reqHeader.put("urlParameters",urlParameters);
                continue;
            }
            if (line.equals("")) {   //自动去除换行符
                break;
            } else {
                String[] s = line.split(":");
                String value = null ;   //初始化为null
                if( s.length > 1 ){
                    value = s[1].trim();
                }
                reqHeader.put(s[0].trim(), value);
            }
        }
        return reqHeader;
    }

    public static String parseUrlParameters(String url){
        // url若存在?问号说明包含请求参数
        int pos = url.indexOf("?");
        if(pos == -1){
            return null;
        } else{
            return url.substring(pos+1);
        }
    }

    public static byte[] contentTransformBodyMap(StringBuilder content,String type  ){
       return null;

    }





    public static void main(String[] args){
        System.out.print( parseUrlParameters("/asdf?a=2&b=3"));
        "".equals(null);

    }



}
