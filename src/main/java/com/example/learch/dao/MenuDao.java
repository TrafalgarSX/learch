package com.example.learch.dao;
import java.util.List;

import com.example.learch.pojo.Menu;

import org.springframework.stereotype.Repository;

/*
 * @Author: guo yawen
 * @Date: 2021-05-02 00:10:02
 * @LastEditTime: 2021-05-02 02:51:32
 * @LastEditors: guo yawen
 * @Description: 
 * @FilePath: \learch\src\main\java\com\example\learch\dao\menuDao.java
 * TrafalgarSX
 */

@Repository
public interface MenuDao{
    List<Menu> getAllMenusWithRole();
}
