package com.yfshi.class014;


import org.springframework.stereotype.Component;

@Component
public class SmgPeppers implements CompactDisc {

    public SmgPeppers(){
        System.out.println("SmgPeppers 进行了默认初始化");
    }
    @Override
    public void player() {
        System.out.println("SmgPeppers 光盘 is …… playing ");
    }
}
