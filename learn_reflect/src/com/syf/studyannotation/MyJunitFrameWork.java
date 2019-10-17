package com.syf.studyannotation;

import com.syf.studyannotation.myannotation.MyAfter;
import com.syf.studyannotation.myannotation.MyBefore;
import com.syf.studyannotation.myannotation.MyTest;

import java.lang.reflect.Method;
import java.util.*;

public class MyJunitFrameWork {

    public static void main(String[] args) throws Exception{
        Class clazz = EmployeeDaoTest.class;
        Object obj = clazz.newInstance(); //必须要获取一个对象

        //反射获取所有的方法
        Method[] methods = clazz.getMethods(); //获取所有的publibc方法
        //分类，遍历后方法进行分类
        Map<String, List<Method>> methodMap = new LinkedHashMap<>(); //有序的按照
        methodMap.put("before", new ArrayList<>());
        methodMap.put("test", new ArrayList<>());
        methodMap.put("after", new ArrayList<>());
        for(Method m:methods){
            if(m.isAnnotationPresent(MyBefore.class)){
                methodMap.get("before").add(m);
            }
            if(m.isAnnotationPresent(MyTest.class)){
                methodMap.get("test").add(m);
            }
            if( m.getAnnotation(MyAfter.class) != null){
                methodMap.get("after").add(m);
            }
        }
        //完成了method检索和分类，接着调用,其含有顺序
        //LinkhashMap有序Map（key有序）
        for(String key : methodMap.keySet()){
            for(Method m : methodMap.get(key)){
                m.invoke(obj);
            }

        }
    }






}
