package com.nutritionist.api.config;
import com.nutritionist.api.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import javax.servlet.Filter;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter authenticationFilter;
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers("/nutritionist/**","/user/**","/customer/add","/customer/delete","/product/add","/product/delete")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore((Filter) authenticationFilter, Filter.class);
        return httpSecurity.build();
    }

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
}
