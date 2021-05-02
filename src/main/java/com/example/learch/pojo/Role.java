/*
 * @Author: guo yawen
 * @Date: 2021-05-01 22:10:13
 * @LastEditTime: 2021-05-01 22:28:07
 * @LastEditors: guo yawen
 * @Description: 
 * @FilePath: \learch\src\main\java\com\example\learch\dao\Role.java
 * TrafalgarSX
 */
package com.example.learch.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable{
    private Integer id;

    private String name;

    private String nameZh;
}
