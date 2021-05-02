package com.example.learch.dao;

import java.util.List;

import com.example.learch.pojo.Role;

import org.springframework.stereotype.Repository;

/*
 * @Author: guo yawen
 * @Date: 2021-05-01 22:57:54
 * @LastEditTime: 2021-05-02 01:56:17
 * @LastEditors: guo yawen
 * @Description: 
 * @FilePath: \learch\src\main\java\com\example\learch\dao\RoleDao.java
 * TrafalgarSX
 */


@Repository
public interface RoleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> getAllRoles();
}