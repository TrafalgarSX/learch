/*
 * @Author: guo yawen
 * @Date: 2021-04-29 17:17:43
 * @LastEditTime: 2021-04-29 17:21:39
 * @LastEditors: guo yawen
 * @Description: 
 * @FilePath: \learch\src\main\java\com\example\learch\service\impl\AccountService.java
 * TrafalgarSX
 */
package com.example.learch.service.impl;

import com.example.learch.dao.AccountDao;
import com.example.learch.pojo.Account;
import com.example.learch.service.IAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService{
    @Autowired
    AccountDao accountDao;
    @Override
    public Account accountLogin(String name) {
        return accountDao.accountLogin(name);
    }
    
}
