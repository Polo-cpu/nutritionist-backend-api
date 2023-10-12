package com.nutritionist.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;
    public void configure(HttpSecurity http) throws Exception{
        http.httpBasic();
        http
                .authorizeHttpRequests()
                .antMatchers("/customer/{id}").hasAnyRole("ADMIN","USER")
                .antMatchers("/customer/all").hasRole("ADMIN")
                .antMatchers("/customer/create").hasAnyRole("USER","ADMIN")
                .antMatchers("/customer/delete").hasRole("ADMIN")
                .antMatchers("/product").hasRole("ADMIN")
                .and()
                .authorizeHttpRequests()
                .anyRequest()
                .permitAll();
    }
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(userDetailsService);
    }



}
