package com.itheima.class02.service.impl;

//import com.itheima.class02.entity.Account;
//import com.itheima.class02.service.IAccountService;
//import com.itheima.class02.config.MyConfigration;
import com.itheima.class03.entity.Account;
import com.itheima.class03.service.IAccountService;
import com.itheima.class03.config.MyConfigration;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class AccountServiceImplTest {

//    ApplicationContext context = new ClassPathXmlApplicationContext("itheima_class02.xml");

    ApplicationContext context = new AnnotationConfigApplicationContext(MyConfigration.class);

    @Test
    public void testconfig(){
        String[] names = context.getBeanDefinitionNames();
        MyConfigration myConfigration = context.getBean("myConfigration",MyConfigration.class);

        for(String item : names){
            System.out.println(item);
        }

    }

    @Test
    public void testFindAll(){
        IAccountService accountService =
                context.getBean("accountService",IAccountService.class);
        List<Account> accounts = accountService.findAllAccount();
        System.out.println(accounts);

    }


    @Test
    public void testSave(){
        IAccountService accountService =
                context.getBean("accountService",IAccountService.class);
        Account ac = new Account();
        ac.setMoney(9999f);
        ac.setName("yfshi");
        accountService.saveAccount(ac);
        testFindAll();

    }

    @Test
    public void testUpdate(){
        IAccountService accountService =
                context.getBean("accountService",IAccountService.class);
        Account ac = new Account();
        ac.setMoney(9999f);
        ac.setName("shiyanfei");
        ac.setId(4);

        accountService.updateAccount(ac);
        testFindAll();

    }

}