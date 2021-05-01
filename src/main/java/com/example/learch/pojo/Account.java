/*
 * @Author: guo yawen
 * @Date: 2021-04-29 16:51:17
 * @LastEditTime: 2021-04-29 16:53:27
 * @LastEditors: guo yawen
 * @Description: 
 * @FilePath: \learch\src\main\java\com\example\learch\Pojo\Account.java
 * TrafalgarSX
 */
package com.example.learch.pojo;

public class Account {
    private int id;
    private String name;
    private String password;
    public Account(){
        id = 0;
        name = "";
        password = "";
    }
    public Account(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
    @Override
    public String toString() {
        return "Account [id=" + id + ", name=" + name + ", password=" + password + "]";
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}
