package com.yfshi.class011;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Bean implements InitializingBean, DisposableBean {

    public void initDo(){
        System.out.println("init-method-----初始化");
    }

    @Override
    public void afterPropertiesSet(){
        System.out.println("接口InitializingBean----初始化");
    }

    public void destoryDo(){
        System.out.println("destroy-method-----销毁");
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("接口DisposableBean---的销毁逻辑");
    }
}
