package com.youyu.cotenant.security;

import com.youyu.cotenant.jwt.JWTAuthenticationFilter;
import com.youyu.cotenant.jwt.JWTLoginFilter;
import com.youyu.cotenant.jwt.JWTSmsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Order(99)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider provider;

    @Autowired
    private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;

    @Bean
    public JWTLoginFilter jwtLoginFilter() throws Exception {
        return new JWTLoginFilter("/login", authenticationManager());
    }

    @Bean
    public JWTSmsFilter jwtSmsFilter() throws Exception{
        return new JWTSmsFilter("/login/sms",authenticationManager());
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JWTAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()//允许跨域访问
                .and()
                .csrf().disable()//关闭csrf验证
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//基于token，因此不需要session
                .and()
                .authorizeRequests()//对请求进行认证
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(HttpMethod.POST, "/login","/login/sms", "/user/register").permitAll()//login的POST请求放行
                .antMatchers(HttpMethod.GET, "/health", "/sms/send","/qiniu/token").permitAll()
                .antMatchers("/group/list","/group/**/detail","/sys/update","/user/info").permitAll()
                .anyRequest().authenticated()//所有请求需要身份认证
                .and()//
                //添加一个过滤器 所有访问/login的请求交给JWTLoginFilter
                .addFilterBefore(jwtLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtSmsFilter(),UsernamePasswordAuthenticationFilter.class)
                //添加一个过滤器验证其他请求的Token是否合法
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .headers().cacheControl();//禁用缓存

        //自定义身份验证失败的返回值
        http.exceptionHandling().authenticationEntryPoint(entryPointUnauthorizedHandler);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义身份验证组件
        auth.authenticationProvider(provider);
    }

}
