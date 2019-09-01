package com.yfshi.class002;


import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
1.实例化Bean
2.保存bean
3.注入bean
4.每个bean产生一个唯一的id
 */
public class IocContainer {

    private Map<String,Object> beans = new ConcurrentHashMap<String,Object>();  //容器

    public Object getBean(String beanId){
        return beans.get(beanId);
    }

    /**
     * Ioc容器实例化一个bean
     * @param clazz  ： bean的class对象
     * @param beanId    ： bean id
     * @param paramsBeanIds： 实例化bean对象所依赖的beanId
     */
    public void setBean(Class<?> clazz, String beanId, String ... paramsBeanIds){
        Object[] paramValues = new Object[ paramsBeanIds.length];
        for(int i = 0;i<paramsBeanIds.length;i++){
            paramValues[i] = this.getBean(paramsBeanIds[i]);    //从容器中获取Bean对象
        }
        Object res = null;
        for(Constructor<?> constructor:clazz.getConstructors()){
            //获取所有构造函数，一个一个试
            try{
                res = constructor.newInstance(paramValues);
            }catch (Exception e){

            }

        }

        if(res == null){
            throw new RuntimeException("实例化bean对象失败");
        }
        beans.put(beanId,res);

    }

}
