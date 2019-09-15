package com.itheima.class02.service;

import com.itheima.class02.entity.Account;

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
