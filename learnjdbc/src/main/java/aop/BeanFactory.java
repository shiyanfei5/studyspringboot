package aop;

import aop.transation.MyTransation;
import aop.transation.ProxyFactoryBean;

public class BeanFactory {

    public static Object getBean(String name) throws Exception{
        Class clazz = Class.forName(name);
        Object obj = clazz.newInstance();
        //代理封装
        if(clazz.isAnnotationPresent(MyTransation.class)){
            ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
            obj = proxyFactoryBean.getProxy(obj);
        }
        return obj;

    }
}
