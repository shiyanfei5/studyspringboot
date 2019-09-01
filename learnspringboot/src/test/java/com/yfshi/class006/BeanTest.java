package com.yfshi.class006;

import com.yfshi.class007.Bean1;
import com.yfshi.class007.Bean2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class BeanTest {


    @Test
    public void test(){
        ApplicationContext context1 = new ClassPathXmlApplicationContext("spring_3.xml");
//        ApplicationContext context2 = new ClassPathXmlApplicationContext("spring_3.xml");

        Bean2 bean2_1 = context1.getBean("bean2",Bean2.class);
        System.out.println("bean2_1=" + bean2_1);
        Bean2 bean2_2 = context1.getBean("bean2",Bean2.class);
        System.out.println("bean2_2=" + bean2_2);
        Bean1 bean1_1 = context1.getBean("bean1", Bean1.class);
        System.out.println("bean1_1=" + bean1_1);
        Bean1 bean1_2 = context1.getBean("bean1", Bean1.class);
        System.out.println("bean1_2=" + bean1_2);
        System.out.println(bean1_1==bean1_2);

//
//        Bean2 bean2_3 = context2.getBean("bean2",Bean2.class);
//        System.out.println("bean2_1=" + bean2_3);
//        Bean2 bean2_4 = context2.getBean("bean2",Bean2.class);
//        System.out.println("bean2_2=" + bean2_4);
//        Bean1 bean1_2 = context2.getBean("bean1", Bean1.class);
//        System.out.println("bean1=" + bean1_2);

    }
}