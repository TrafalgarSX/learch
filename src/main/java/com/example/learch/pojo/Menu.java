package com.example.learch.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @Author: guo yawen
 * @Date: 2021-05-01 22:58:16
 * @LastEditTime: 2021-05-01 23:27:14
 * @LastEditors: guo yawen
 * @Description: 
 * @FilePath: \learch\src\main\java\com\example\learch\pojo\Menu.java
 * TrafalgarSX
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable{
    private Integer id;

    private String url;

    private String path;

    private String component;

    private String name;

    private String iconCls;

    private Meta meta;

    private Integer parentId;

    private Boolean enabled;
    private List<Menu> children;
    private List<Role> roles;
}