package com.syf.reflect;

import java.lang.reflect.Method;

public class ClassUtil {


    /**
     * 打印类的信息，类成员函数，成员变量
     * @param obj obj为实例对象
     */
    public static void printClassMessage(Object obj){
        // obj 是一个实例对象
        Class c = obj.getClass(); //获取类对象

        System.out.println(c.getName());    //获取类对象的名称
        /**
         * Method类，方法对象，一个成员方法就是Method对象
         * Class 类的getMethods()获得所有的public函数（含父类继承的）
         * Class 类的getDeclaredMethods()获取所有该类自己声明的方法，不管访问权限，不包含继承的
         */
        Method[] ms = c.getMethods();
        for(int i = 0; i < ms.length; i ++){
            Class returnType = ms[i].getReturnType(); //返回类型对象，也是一个类类型的
            System.out.print(returnType.getName());   //获得类型名
            //得到方法名
            System.out.print(ms[i].getName()+"(");
            //获取参数类型
            Class[] paramTypes = ms[i].getParameterTypes();
            for(Class item:paramTypes){
                System.out.print(item.getName());   //获取参数的类对象的名字
            }
            System.out.print(")\n");
        }

    }
}
