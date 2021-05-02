/*
 * @Author: guo yawen
 * @Date: 2021-05-01 23:26:25
 * @LastEditTime: 2021-05-01 23:27:03
 * @LastEditors: guo yawen
 * @Description: 
 * @FilePath: \learch\src\main\java\com\example\learch\pojo\Meta.java
 * TrafalgarSX
 */
package com.example.learch.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Meta implements Serializable{
    private Boolean keepAlive;
    private Boolean requireAuth;

}
