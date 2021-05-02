/*
 * @Author: guo yawen
 * @Date: 2021-05-02 14:50:34
 * @LastEditTime: 2021-05-02 16:55:20
 * @LastEditors: guo yawen
 * @Description: 
 * @FilePath: \learch\src\main\java\com\example\learch\config\loginFilter.java
 * TrafalgarSX
 */
package com.example.learch.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.learch.pojo.Hr;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class LoginFilter extends UsernamePasswordAuthenticationFilter{
    // @Autowired
    // SessionRegistry sessionRegistry;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthorizationServiceException(
                "Authentication method not supported:" + request.getMethod()
            );
        }
        if(request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)
            || request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)){
            Map<String, String> loginData = new HashMap<>();
            try {
                loginData = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            } catch (Exception e) {
                //Handle Exception
                e.printStackTrace();
            }
            String username = loginData.get(getUsernameParameter());
            String password = loginData.get(getPasswordParameter());
            if(username == null) username = "";
            if(password == null) password = "";
            username = username.trim();
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            setDetails(request, authRequest);
            // Hr principal = new Hr();
            // principal.setUsername(username);
            // sessionRegistry.registerNewSession(request.getSession().getId(), principal);
            return this.getAuthenticationManager().authenticate(authRequest);
        }else{
            return super.attemptAuthentication(request, response);
        }
    }
    
}

