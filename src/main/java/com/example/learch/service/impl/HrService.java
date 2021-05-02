/*
 * @Author: guo yawen
 * @Date: 2021-05-01 22:49:11
 * @LastEditTime: 2021-05-02 02:16:21
 * @LastEditors: guo yawen
 * @Description: 
 * @FilePath: \learch\src\main\java\com\example\learch\service\impl\HrService.java
 * TrafalgarSX
 */
package com.example.learch.service.impl;


import com.example.learch.dao.HrDao;
import com.example.learch.pojo.Hr;
import com.example.learch.service.IHrService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class HrService implements IHrService, UserDetailsService{
    @Autowired
    HrDao hrDao;
    /**
     * *该方法将在用户登录时自动调用。
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Hr hr = hrDao.loadUserByUsername(s);
        if(hr == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        hr.setRoles(hrDao.getHrRolesById(hr.getId()));
        return hr;
    }
    
}
