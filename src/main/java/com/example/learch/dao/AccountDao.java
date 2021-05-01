/*
 * @Author: guo yawen
 * @Date: 2021-04-29 17:02:29
 * @LastEditTime: 2021-04-29 17:15:38
 * @LastEditors: guo yawen
 * @Description: 
 * @FilePath: \learch\src\main\java\com\example\learch\Dao\AccountDao.java
 * TrafalgarSX
 */
package com.example.learch.dao;

import com.example.learch.pojo.Account;

import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao {


    public Account accountLogin(String name);

} 