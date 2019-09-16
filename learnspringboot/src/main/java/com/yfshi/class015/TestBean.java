package com.yfshi.class015;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("testBean2")
@Scope("prototype")
public class TestBean {

    public TestBean(){
        System.out.println("TestBean的构造方法被调用");
    }
}
