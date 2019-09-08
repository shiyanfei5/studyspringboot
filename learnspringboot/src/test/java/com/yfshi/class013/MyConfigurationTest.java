package com.yfshi.class013;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class MyConfigurationTest {


    @Test
    public void Test(){
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MyConfiguration.class);

        Bean1 bean1 = applicationContext.getBean("bean1",Bean1.class);
        Bean2 bean2_1 = applicationContext.getBean("bean2_1",Bean2.class);
        Bean2 bean2_2 = applicationContext.getBean("bean2_2",Bean2.class);
        Bean2 bean2_3 = applicationContext.getBean("bean2_3",Bean2.class);
        System.out.println(bean1);
        System.out.println(bean2_1);
        System.out.println(bean2_2);
        System.out.println(bean2_3);

    }

}