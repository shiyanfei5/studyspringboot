package com.itheima.class03.service;

import com.itheima.class03.entity.Account;

import java.util.List;

public interface IAccountService {

    /**
     * 查询所有
     * @return
     */
    List<Account> findAllAccount();

    Account findAccountById(Integer accountId);

    void updateAccount(Account account);

    void deleteAccount(Integer accountId);

    void saveAccount(Account account);

}
