package com.yfshi.class005;

import org.springframework.context.annotation.Bean;

public class Bean1Factory {

    public static Bean1 createBean1(){
        System.out.println("工厂方法调用喽");
         return new Bean1(1);
    }
}
