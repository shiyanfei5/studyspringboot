package com.yfshi.class004;

import com.yfshi.class002.car.Audi;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAudi {


    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "spring.xml"
        );

        System.out.println("---------------------------------------------");
        System.out.println("---------------------------------------------");
        System.out.println("---------------------------------------------");
        Audi audi = context.getBean("audi", Audi.class);
        audi.start();

    }




}
