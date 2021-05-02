package com.example.learch.dao;

import java.util.List;

import com.example.learch.pojo.Hr;
import com.example.learch.pojo.Role;

import org.springframework.stereotype.Repository;

/*
 * @Author: guo yawen
 * @Date: 2021-05-01 22:28:31
 * @LastEditTime: 2021-05-02 01:30:54
 * @LastEditors: guo yawen
 * @Description: 
 * @FilePath: \learch\src\main\java\com\example\learch\dao\HrDao.java
 * TrafalgarSX
 */

@Repository
public interface HrDao {
    Hr loadUserByUsername(String username);
    List<Role> getHrRolesById(Long id);
}