/*
 * @Author: guo yawen
 * @Date: 2021-05-01 22:58:50
 * @LastEditTime: 2021-05-02 02:53:39
 * @LastEditors: guo yawen
 * @Description: 
 * @FilePath: \learch\src\main\java\com\example\learch\service\IMenuService.java
 * TrafalgarSX
 */
package com.example.learch.service;

import java.util.List;

import com.example.learch.pojo.Menu;

public interface IMenuService {
    List<Menu> getAllMenusWithRole();
}
