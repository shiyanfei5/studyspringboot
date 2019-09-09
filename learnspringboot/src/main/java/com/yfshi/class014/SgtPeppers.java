package com.yfshi.class014;


import org.springframework.stereotype.Component;

@Component
public class SgtPeppers implements CompactDisc {

    public SgtPeppers(){
        System.out.println("SgtPeppers 进行了默认初始化");
    }
    @Override
    public void player() {
        System.out.println("SgtPeppers光盘    playing....  ");
    }
}
