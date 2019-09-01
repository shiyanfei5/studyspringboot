package com.syf.reflect;

public class ClassDemo1 {

    public static void main(String[] args){
        //Java同Python万物皆对象
        Foo foo1 = new Foo(); //Foo类的实例对象foo1,

        //Foo类也是一个对象，其属于Class类
        //任何一个类都是Class的实例对象，其由三种表示方式
        Class F1 = Foo.class;   //获取某个对象所属的类

        //实际在说任何一个类都有一个隐含的静态成员变量
        Class F2 = foo1.getClass();
        //第二种表达，通过该类的实例对象调用getClass方法

        Class F3 = null;
        try{
            F3 = Class.forName("com.syf.reflect.Foo");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        System.out.print(F1 == F2);
        System.out.print(F2 == F3);

    }


}
