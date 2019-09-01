package com.yfshi;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 1.实例化bean
 * 2.保存bean
 * 3.提供bean
 * 4.给每个bean产生一个唯一id
 */
public class IOCContainer {

    private Map<String, Object> beans = new ConcurrentHashMap<String, Object>();

    public Object getBean(String id ){
        return beans.get(id);
    }


    // paramBeanIds是创建一个Bean的构造方法所需要的参数的beanid
    public Object setBean(Class<?> clazz, String Bean,String... paramBeanIds ){
        //组装构造方法需要的参数值
        Object [] paramValues = new Object[paramBeanIds.length];
        for(int i = 0; i<paramBeanIds.length; i++){
            paramValues[i] = beans.get(paramBeanIds[i]);
        }
        //构造构造方法实例化bean
        Object bean = null;
        for(Constructor<?> constuctor:clazz.getConstructors()){ //拿到所有的构造方法
            try{
                bean = constuctor.newInstance(paramValues);
            }
            catch (InstantiationException e){

            } catch (IllegalAccessException e){

            } catch (InvocationTargetException e){

            }
        }
        if(bean==null){
            throw new RuntimeException("找不到合适的构造方法构造bean");
        }
        //将实例化的bean放入beans

        return bean;

    }


}
