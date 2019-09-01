package com.yfshi.class007;

import com.yfshi.class006.Bean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class Bean1Test {
    ApplicationContext context = new ClassPathXmlApplicationContext("spring_3.xml");

    @Test
    public void test(){
        System.out.println("***************开始获取bean**********************");
        Bean bean = (Bean)context.getBean("bean1");
        System.out.println(bean);




    }
}