package com.itheima.class03.dao;

import com.itheima.class03.entity.Account;

import java.util.List;

public interface IAccountDao {


    List<Account> findAllAccount();

    Account findAccountById(Integer accountId);

    void updateAccount(Account account);

    void deleteAccount(Integer accountId);

    void saveAccount(Account account);
}
