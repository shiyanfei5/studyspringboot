package com.syf.dynamicproxy;

import com.syf.dynamicproxy.impl.CalculatorImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {


    public static void main(String[] arg){
        Class calculatorProxyClazz  = Proxy.getProxyClass(
                ICaculator.class.getClassLoader(),
                ICaculator.class
        );
        System.out.println(CalculatorImpl.class.getName());
        System.out.println(calculatorProxyClazz.getName());
        //打印代理Class对象的构造器
        Constructor[] constructors = calculatorProxyClazz.getConstructors();
        System.out.println("----构造器----");
        printClassInfo(constructors);
        //打印代理Class对象的方法
        Method[] methods = calculatorProxyClazz.getMethods();
        System.out.println("----方法----");
        printClassInfo(methods);

    }
    public static void printClassInfo(Executable[] targets) {
        for (Executable target : targets) {
            // 构造器/方法名称
            String name = target.getName();
            StringBuilder sBuilder = new StringBuilder(name);
            // 拼接左括号
            sBuilder.append('(');
            Class[] clazzParams = target.getParameterTypes();
            // 拼接参数
            for (Class clazzParam : clazzParams) {
                sBuilder.append(clazzParam.getName()).append(',');
            }
            //删除最后一个参数的逗号
            if (clazzParams != null && clazzParams.length != 0) {
                sBuilder.deleteCharAt(sBuilder.length() - 1);
            }
            //拼接右括号
            sBuilder.append(')');
            //打印 构造器/方法
            System.out.println(sBuilder.toString());
        }
    }

}
