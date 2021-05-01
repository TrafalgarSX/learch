/*
 * @Author: guo yawen
 * @Date: 2021-04-29 16:56:07
 * @LastEditTime: 2021-04-29 17:53:52
 * @LastEditors: guo yawen
 * @Description: 
 * @FilePath: \learch\src\main\java\com\example\learch\Controller\AccountController.java
 * TrafalgarSX
 */
package com.example.learch.controller;

import com.example.learch.pojo.Account;
import com.example.learch.service.IAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    IAccountService accountService;

    @PostMapping("/login")
    @ResponseBody
    public String accountLogin(@RequestBody Account account) {
        Account queryRet = accountService.accountLogin(account.getName());

        String ret = "Failed!";
        if(queryRet != null && account.getPassword().equals(queryRet.getPassword())){
            ret = "Success!";
        }
        return ret;
    }
}
