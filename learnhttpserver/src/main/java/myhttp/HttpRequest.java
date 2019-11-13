package myhttp;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private Map<String,String> reqHeader;
    private boolean hasBody;
    private String body;

    public HttpRequest(){
        reqHeader = new HashMap<String, String>();
    }

    public Map<String, String> getReqHeader() {
        return reqHeader;
    }

    public void setReqHeader(Map<String, String> reqHeader) {
        this.reqHeader = reqHeader;
    }

    public boolean isHasBody() {
        return hasBody;
    }

    public void setHasBody(boolean hasBody) {
        this.hasBody = hasBody;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
