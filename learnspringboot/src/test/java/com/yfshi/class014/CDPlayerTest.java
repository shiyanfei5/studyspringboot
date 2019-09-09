package com.yfshi.class014;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class CDPlayerTest {

    @Test
    public void test(){
        ApplicationContext context = new AnnotationConfigApplicationContext( AppConfig.class);
        CDPlayer player = context.getBean("CDPlayer",CDPlayer.class);
        player.start();

    }
}
