package com.itheima.factory;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 1.通过配置文件配置我们需要的service和dao
 * 2.通过配置文件的内容反射创建对象
 */
public class Beanfactory {


    private static Properties props;    //静态属性，加载配置文件配置
    private static Map<String,Object> beansMap       ;  //Map容器

    // 静态初始化，读取配置
    static {
        try{
            props = new Properties();
            InputStream in = Beanfactory.class.getClassLoader()
                    .getResourceAsStream("bean.properties");
            props.load(in);
        } catch(Exception e){
            throw new ExceptionInInitializerError("初始化properties失败");
        }
        beansMap = new HashMap<String, Object>();
        Enumeration element = props.keys();
        while(element.hasMoreElements()){
            String key  = element.nextElement().toString();
            String classPath = props.getProperty(key);

            try{
                beansMap.put(key,Class.forName(classPath).newInstance());
            } catch (Exception e){
                System.out.print("实例化该bean("+key+")失败");
            }

        }
    }

    
    public static Object getBean(String beanName) {
        return beansMap.get(beanName);
    }

    public static<T>  T getBean(String beanName, Class<T> clzz) {
        return clzz.cast(beansMap.get(beanName));
    }

}
