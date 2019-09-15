package com.itheima.class01.ui;

import com.itheima.class01.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {


    public static void main(String[] args){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean.xml");
        IAccountService service = context.getBean("accountService", IAccountService.class );



    }





}
