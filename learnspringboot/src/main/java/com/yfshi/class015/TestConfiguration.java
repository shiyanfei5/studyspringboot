package com.yfshi.class015;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;




@Configuration
@ComponentScan(basePackages = {"com.yfshi.class015"})
public class TestConfiguration{

    @Bean("testBean1")
    @Scope("prototype")
    public TestBean testBean(){
        System.out.println("Configuration类中的scop方法被调用");
        return new TestBean();
    }

}
