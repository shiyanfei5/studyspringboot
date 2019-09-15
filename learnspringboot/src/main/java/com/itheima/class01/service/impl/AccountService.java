package com.itheima.class01.service.impl;

import com.itheima.class01.dao.IAccountDao;
import com.itheima.class01.service.IAccountService;

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
