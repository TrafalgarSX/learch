/*
 * @Author: guo yawen
 * @Date: 2021-04-29 14:51:43
 * @LastEditTime: 2021-04-29 17:11:10
 * @LastEditors: guo yawen
 * @Description: 
 * @FilePath: \learch\src\main\java\com\example\learch\Controller\HelloController.java
 * TrafalgarSX
 */
package com.example.learch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello spring boot!";
    }
}
