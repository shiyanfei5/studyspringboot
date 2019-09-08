package com.yfshi.class013;

public class Bean2 {

     public Bean1 bean1;

    public Bean2( ){
        System.out.println("Bean2调用默认构造函数");
    }

    public Bean2(Bean1 bean1){
        System.out.println("Bean2通过构造函数进行注入bean1");
        this.bean1 = bean1;
    }

    @Override
    public String toString() {
        String s = bean1!=null? bean1.toString():"null";
        return super.toString()+"{"+s+"}";
    }
}
