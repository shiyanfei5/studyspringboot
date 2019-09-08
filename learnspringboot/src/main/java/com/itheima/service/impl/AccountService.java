package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.factory.Beanfactory;
import com.itheima.service.IAccountService;

public class AccountService implements IAccountService {


    private IAccountDao accountDao ;


    public AccountService(){
        System.out.println("对象创建了");
    }

    @Override
    public void saveAccount() {
        accountDao.saveAccount();
    }

    //setter进行依赖注入
    public void setAccountDao(IAccountDao accountDao){
        this.accountDao = accountDao;
    }
}
