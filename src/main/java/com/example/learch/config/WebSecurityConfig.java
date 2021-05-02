/*
 * @Author: guo yawen
 * @Date: 2021-05-01 23:15:03
 * @LastEditTime: 2021-05-02 17:17:17
 * @LastEditors: guo yawen
 * @Description: 
 * @FilePath: \learch\src\main\java\com\example\learch\config\WebSecurityConfig.java
 * TrafalgarSX
 */
package com.example.learch.config;


import java.io.PrintWriter;

import com.example.learch.pojo.Hr;
import com.example.learch.pojo.RespBean;
import com.example.learch.service.impl.HrService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    HrService hrService;
    @Autowired
    CustomFilterInvocationSecurityMetadataSource metadataSource;
    @Autowired
    CustomAccessDecisionManager urlAccessDecisionManager;
    @Autowired
    AuthenticationAccessDeniedHandler deniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(hrService)
                 .passwordEncoder(new BCryptPasswordEncoder());
    }
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/index.html", "/static/**");
    }

    @Bean
    LoginFilter loginFilter()  throws Exception{
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {

            response.setContentType("application/json;charset=utf-8");
            Hr hr = (Hr) authentication.getPrincipal();
            hr.setPassword(null);
            RespBean respBean = RespBean.ok("登录成功！", hr);
            ObjectMapper om = new ObjectMapper();
            PrintWriter out = response.getWriter();
            out.write(om.writeValueAsString(respBean));
            out.flush();
            out.close();
        });
        loginFilter.setAuthenticationFailureHandler((request, response, exception) ->{
            response.setContentType("application/json;charset=utf-8");
            RespBean respBean = null;
            if(exception instanceof BadCredentialsException ||
                  exception instanceof UsernameNotFoundException){
                respBean = RespBean.error("账户名或者密码输入错误！");
            }else if( exception instanceof LockedException){
                respBean = RespBean.error("账户被锁定，请联系管理员！");
            }else if(exception instanceof CredentialsExpiredException){
                respBean = RespBean.error("密码过期，请联系管理员！");
            }else if(exception instanceof AccountExpiredException){
                respBean = RespBean.error("账户过期，请联系管理员！");
            }else if(exception instanceof DisabledException){
                respBean = RespBean.error("账户被禁用，请联系管理员！");
            }else{
                respBean = RespBean.error("登录失败！");
            }
            response.setStatus(500);
            ObjectMapper om = new ObjectMapper();
            PrintWriter out = response.getWriter();
            out.write(om.writeValueAsString(respBean));
            out.flush();
            out.close();
        });
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setFilterProcessesUrl("/doLogin");
        
        return loginFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .withObjectPostProcessor(
                new ObjectPostProcessor<FilterSecurityInterceptor>(){
               
                   @Override
                   public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                       o.setSecurityMetadataSource(metadataSource);
                       o.setAccessDecisionManager(urlAccessDecisionManager);
                       return o;
                   }
                })
            .and()
            .logout()
            .logoutSuccessHandler((request, response, authentication) -> {
               response.setContentType("application/json;charset=utf-8");
               PrintWriter out = response.getWriter();
               out.write(new ObjectMapper().writeValueAsString(RespBean.ok("注销成功！")));
               out.flush();
               out.close();
            })
            .permitAll()
            .and()
            .csrf().disable()
            .exceptionHandling().accessDeniedHandler(deniedHandler);
        http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}