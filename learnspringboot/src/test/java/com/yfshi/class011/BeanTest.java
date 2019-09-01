package com.yfshi.class011;

import com.yfshi.class011.Bean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class BeanTest {


    @Test
    public void test(){
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring_4.xml");
        System.out.println("context被创建");
        Bean bean1 = context.getBean("bean",Bean.class);
        context.close();

    }


}