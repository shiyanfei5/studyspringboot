package com.yfshi.class014;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component("CDPlayer")
public class CDPlayer {

    CompactDisc smgPeppers;

    List<String> li;


    public CDPlayer(CompactDisc smgPeppers){
        System.out.println("CDPlayer 进行了CDPlayer(CompactDisc smgPeppers)初始化注入");
    }

    @Autowired
    public CDPlayer(CompactDisc smgPeppers,CompactDisc sgtPeppers){
        this.smgPeppers = sgtPeppers;
        System.out.println("CDPlayer 进行了CDPlayer(CompactDisc smgPeppers,CompactDisc sgtPeppers)初始化注入");
    }



    public void valueTest(String s){
        System.out.println("valueTest方法被调用");
        List<String> tmp = new ArrayList<String>();
        tmp.add(s);
        this.li = tmp;
    }

    public void start(){
        this.smgPeppers.player();
    }




}
