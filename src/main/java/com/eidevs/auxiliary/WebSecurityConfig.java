/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eidevs.auxiliary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author eisrael - israelewisdom@gmail.com
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/api/**","/biller/**", "/ft/**", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/").loginProcessingUrl("/")
                .defaultSuccessUrl("/home/client-dashboard", true)
                .failureForwardUrl("/login?error=true")
                .and()
                .logout().logoutSuccessUrl("/auth/logout")
                .deleteCookies("JSESSIONID")
                .and()
                .csrf().disable();
    }

    @Autowired
    public void init(AuthenticationManagerBuilder auth) throws Exception {

    }
}
