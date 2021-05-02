/*
 * @Author: guo yawen
 * @Date: 2021-05-01 22:59:12
 * @LastEditTime: 2021-05-02 02:53:06
 * @LastEditors: guo yawen
 * @Description: 
 * @FilePath: \learch\src\main\java\com\example\learch\service\impl\MenuService.java
 * TrafalgarSX
 */
package com.example.learch.service.impl;

import java.util.List;

import com.example.learch.dao.MenuDao;
import com.example.learch.pojo.Menu;
import com.example.learch.service.IMenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

 @Service
 @Transactional
 @CacheConfig(cacheNames = "menus_cache")
public class MenuService implements IMenuService{
    @Autowired
    MenuDao menuDao;
    /**
     * *这里使用方法名作为缓存的Key
     * *另外需要在项目启动类上添加@EnableCaching注解开启缓存
     */
    @Override
    @Cacheable(key = "#root.methodName")
    public List<Menu> getAllMenusWithRole() {
        return menuDao.getAllMenusWithRole();
    }


    
}