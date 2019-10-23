package com.syf.reflect;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ClassDemo3 {

     interface IFunctionInerterface{
        void fun(); //抽象方法fun
        boolean equals(Object obj); //抽象方法equals
    }

    private static void test(IFunctionInerterface iFunctionInerterface){
        iFunctionInerterface = new IFunctionInerterface() {
            @Override
            public void fun() {
                System.out.println("匿名内部类，本质上java生成一个内部类");
            }
        };
        iFunctionInerterface.fun();
        System.out.println( "调用Object的方法:"+iFunctionInerterface.equals("1"));
        System.out.println( "调用Object的方法:"+iFunctionInerterface.equals(iFunctionInerterface));
    }

    public static void main(String[] arg){
        test( ()->System.out.println("重写的函数式接口"));

    }
}
