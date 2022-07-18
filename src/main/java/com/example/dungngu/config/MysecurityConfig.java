package com.example.dungngu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity

public class MysecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure (HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable();
        http.authorizeRequests();
        http.authorizeHttpRequests().antMatchers("/api/v1/hello").permitAll();
        http.authorizeHttpRequests().antMatchers("/api/v1/user").hasAnyAuthority("user");
        http.authorizeHttpRequests().antMatchers("/api/v1/admin").hasAnyAuthority("admin");
    }
}
