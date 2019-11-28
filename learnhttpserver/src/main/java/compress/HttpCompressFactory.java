package compress;

public class HttpCompressFactory {
    private static HttpCompressFactory instance ;
    public static  HttpCompressFactory getInstance(){
        if(instance == null){
            instance = new HttpCompressFactory();   //lazy实例化
        }
        return instance;
    }
    //私有化构造函数，防止其被外部实例化
    private HttpCompressFactory(){}
    public ICompress produce(String byteEncoding){
        if("gzip".equals(byteEncoding)){
            return new HttpGzip();
        }
        if("deflate".equals(byteEncoding)){
            return new HttpDeflate();
        }
        return null;
    }




}
