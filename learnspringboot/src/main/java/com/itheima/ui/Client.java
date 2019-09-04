package com.itheima.ui;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.impl.AccountDao;
import com.itheima.factory.Beanfactory;
import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {


    public static void main(String[] args){



        IAccountService accountService =
                (AccountService)Beanfactory.getBean("accountService");
        accountService.saveAccount();





    }





}
