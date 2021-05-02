/*
 * @Author: guo yawen
 * @Date: 2021-05-01 23:19:36
 * @LastEditTime: 2021-05-02 02:53:56
 * @LastEditors: guo yawen
 * @Description: 动态权限配置
 * @FilePath: \learch\src\main\java\com\example\learch\config\CustomFilterInvocationSecurityMetadataSource.java
 * TrafalgarSX
 */
package com.example.learch.config;

import java.util.Collection;
import java.util.List;

import com.example.learch.pojo.Menu;
import com.example.learch.pojo.Role;
import com.example.learch.service.impl.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

@Component
public class CustomFilterInvocationSecurityMetadataSource
        implements FilterInvocationSecurityMetadataSource {
        @Autowired
        MenuService menuService;

        AntPathMatcher antPathMatcher = new AntPathMatcher();
        /**
         * *该方法用来返回所有定义好的权限资源
         * *spring security在启动时会校验相关配置是否正确。
         * *如果不需要校验，直接返回null即可。
         */
        @Override
        public Collection<ConfigAttribute> getAllConfigAttributes() {
            return null;
        }
        @Override
        public Collection<ConfigAttribute> getAttributes(Object arg0) throws IllegalArgumentException {
            /**
             * *获得传来的url,提取出请求url
             */
            String requestUrl = ((FilterInvocation) arg0).getRequestUrl();
            //*menu在每次请求中都会调用，所以将menu缓存。
            List<Menu> allMenu = menuService.getAllMenusWithRole();
            /**
             * *对于获得的每个菜单项的url和请求url进行比较，
             * *如果匹配，并且有角色可以访问该菜单，
             * *就返回所有可以访问该菜单项的角色。
             */
            for (Menu menu : allMenu) {
                if(antPathMatcher.match(menu.getUrl(), requestUrl) && menu.getRoles().size() > 0){
                    List<Role> roles = menu.getRoles();
                    int size = roles.size();
                    String[] values = new String[size];
                    for (int i = 0; i < size; i++) {
                        values[i] = roles.get(i).getName();
                    }
                    return SecurityConfig.createList(values);
                }
            }
            /**
             * *对于所有未成功匹配的请求，默认都是登陆后访问。
             */
            return SecurityConfig.createList("ROLE_LOGIN");
        }
        /**
         * *返回 类对象是否支持校验。
         */
        @Override
        public boolean supports(Class<?> arg0) {
            return FilterInvocation.class.isAssignableFrom(arg0);
        }
    
}