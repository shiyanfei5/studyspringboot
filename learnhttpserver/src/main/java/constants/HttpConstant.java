package constants;

import java.util.HashMap;
import java.util.Map;

public class HttpConstant {
    public  static Map<Integer,String> StatusInfoMap;

    static{
        StatusInfoMap  = new HashMap<Integer, String>();
        StatusInfoMap.put(200,"OK");
        StatusInfoMap.put(400,"Bad Request");
        StatusInfoMap.put(401,"Unauthorized");
        StatusInfoMap.put(403,"Forbidden");
        StatusInfoMap.put(404,"NotFound");
        StatusInfoMap.put(500,"Internal Server Error");
        StatusInfoMap.put(503,"Service Unavailable");
    }


}
