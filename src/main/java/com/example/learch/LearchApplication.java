/*
 * @Author: guo yawen
 * @Date: 2021-04-27 19:24:24
 * @LastEditTime: 2021-04-29 17:11:45
 * @LastEditors: guo yawen
 * @Description: 
 * @FilePath: \learch\src\main\java\com\example\learch\LearchApplication.java
 * TrafalgarSX
 */
package com.example.learch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 ** @SpringBootApplication   相当于＠EnableAutoConfiguration 和@ComponentScan
 **  @ComponentScan 配置包扫描，才能将 Controller等注册到springMVC等
 ** ＠EnableAutoConfiguration 注解表示开启自动化配置。 比如自动配置spring 和 springMVC
 */
@SpringBootApplication
@MapperScan("com.example.learch.dao")
public class LearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearchApplication.class, args);
	}

}
