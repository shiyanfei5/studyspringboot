package com.itheima.class01.dao.impl;

import com.itheima.class01.dao.IAccountDao;

public class AccountDao implements IAccountDao {

    @Override
    public void saveAccount() {
        System.out.println("保存该数据");
    }
}
