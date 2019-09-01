package com.yfshi.class010;

import com.yfshi.class010.Bean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class BeanTest {


    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_4.xml");
        System.out.println("context被创建");
        System.out.println("拿第一个");
        Bean bean1 = (Bean)context.getBean("bean1");
        System.out.println("拿第二个");
        Bean bean2 = (Bean)context.getBean("bean2");





    }

}