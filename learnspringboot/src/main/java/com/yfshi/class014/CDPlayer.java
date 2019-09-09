package com.yfshi.class014;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("CDPlayer")
public class CDPlayer {

    CompactDisc smgPeppers;


    public CDPlayer(CompactDisc smgPeppers){
        System.out.println("CDPlayer 进行了CDPlayer(CompactDisc smgPeppers)初始化注入");
    }
    @Autowired
    public CDPlayer(CompactDisc smgPeppers,CompactDisc sgtPeppers){
        this.smgPeppers = sgtPeppers;
        System.out.println("CDPlayer 进行了CDPlayer(CompactDisc smgPeppers,CompactDisc sgtPeppers)初始化注入");
    }

    public void start(){
        this.smgPeppers.player();
    }

}
