package com.yfshi.class013;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class MyConfiguration {

    public MyConfiguration(){
        System.out.println("配置类完成了初始化");
    }

    @Bean
    public Bean1 bean1(){
        System.out.println("bean1函数被调用了");
        return new Bean1();
    }

    public Bean1 createbean1(){
        return  bean1();
    }

    @Bean
    public Bean2 bean2_1(){
        System.out.println("bean2_1函数被调用");
        return new Bean2();
    }

    @Bean
    public Bean2 bean2_2(){
        System.out.println("bean2_2函数被调用");
        return new Bean2(bean1());
    }

    @Bean
    public Bean2 bean2_3(){
        System.out.println("bean2_3函数被调用");
        return new Bean2(createbean1());
    }


}
