package com.syf.studyannotation;

import com.syf.studyannotation.myannotation.MyAfter;
import com.syf.studyannotation.myannotation.MyBefore;
import com.syf.studyannotation.myannotation.MyTest;

public class EmployeeDaoTest {


    @MyBefore
    public void init(){
        System.out.println("初始化");
    }

    //析构
    @MyAfter
    public void destory(){
        System.out.println("销毁");
    }

    @MyTest
    public void testSave(){
        System.out.println("save");
    }

    @MyTest
    public void testDelete(){
        System.out.println("delete");
    }


}
