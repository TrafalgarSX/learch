/*
 * @Author: guo yawen
 * @Date: 2021-05-01 23:22:22
 * @LastEditTime: 2021-05-02 02:46:42
 * @LastEditors: guo yawen
 * @Description: 
 * @FilePath: \learch\src\main\java\com\example\learch\config\CustomAccessDecisionManager.java
 * TrafalgarSX
 */
package com.example.learch.config;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {

    @Override
    /**
     * * Authentication 参数包含当前登录用户的信息。
     * * Object ：是一个FilterInvocation对象，可以获取当前请求对象
     * * 第三个参数就是 MetadataSource中 getAttributes方法的返回值，即当前请求url所需要的角色。
     */
    public void decide(Authentication auth, Object o, Collection<ConfigAttribute> cas)
            throws AccessDeniedException, InsufficientAuthenticationException {
        Iterator<ConfigAttribute> iterator = cas.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute ca = iterator.next();
            String needRole = ca.getAttribute();
            /**
             * *如果所需要的角色是ROLE_LOGIN，那么只需要判断auth不是匿名用户的实例
             * * 即表示当前用户已登录。
             * *若用户已登录，该方法到此结束，否则进入正常的流程。
             */
            if("ROLE_LOGIN".equals(needRole)){
                if(auth instanceof AnonymousAuthenticationToken){
                    throw new BadCredentialsException("未登录");
                }else{
                    return;
                }
            }
            /**
             * *将用户登录的信息中的角色 当前请求url需要的角色进行比较
             * *如果包含，那么权限符合，进入下一步比较。
             * !该方法是 Hr类中的继承UserDetails的方法，是Hr类的角色信息。
             */
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if(authority.getAuthority().equals(needRole)){
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute arg0) {
        return true;
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
    
}