package com.yfshi;

import org.apache.naming.factory.BeanFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class factory {

    private  static Properties props = null;

    static {        //静态代码块，类对象初始化后获取
        try{
            InputStream in = BeanFactory.class.
                    getClassLoader().getResourceAsStream("bean.properties");
//            InputStream in = new FileInputStream("src/bean.properties")
            //不能使用，因为项目发布后路径没有src
            props.load(in);
        } catch ( Exception e){
            throw new ExceptionInInitializerError("读取配置文件失败"+e.getMessage());
        }

    }

    public static void getBean(String beanName){
        try{

            //1.读取配置文件，跟beanName获得全类名,如何读？
            String beanPath = "";


//            return Class.forName().newInstance();
        } catch (Exception e){
            throw  new RuntimeException(e);
        }

    }
}
