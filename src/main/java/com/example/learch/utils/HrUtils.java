/*
 * @Author: guo yawen
 * @Date: 2021-05-02 01:14:32
 * @LastEditTime: 2021-05-02 01:16:32
 * @LastEditors: guo yawen
 * @Description: 
 * @FilePath: \learch\src\main\java\com\example\learch\\utils\HrUtils.java
 * TrafalgarSX
 */
package com.example.learch.utils;

import com.example.learch.pojo.Hr;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @作者 江南一点雨
 * @公众号 江南一点雨
 * @微信号 a_java_boy
 * @GitHub https://github.com/lenve
 * @博客 http://wangsong.blog.csdn.net
 * @网站 http://www.javaboy.org
 * @时间 2019-10-24 8:11
 */
public class HrUtils {
    public static Hr getCurrentHr() {
        return ((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
