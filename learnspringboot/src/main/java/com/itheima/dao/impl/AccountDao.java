package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;

public class AccountDao implements IAccountDao {

    @Override
    public void saveAccount() {
        System.out.println("保存该数据");
    }
}
