package com.nutritionist.api.config;
import com.nutritionist.api.security.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public org.springframework.security.core.userdetails.UserDetailsService userDetailsService(){
        return new UserDetailsService();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
    }
    @Override
    public void configure(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/user/create").permitAll()
                .antMatchers("/customer/all").permitAll()
                .antMatchers("/customer/{}").permitAll()
                .antMatchers("/product/{}/{}").permitAll()
                .antMatchers("/nutritionist/{}/{}").permitAll()
                .antMatchers("/product/all").permitAll()
                .antMatchers("/nutritionist/all").permitAll()
                .antMatchers("/product/find/{}").permitAll()
                .antMatchers("/customer/create").hasAnyAuthority("ROLE_USER")
                .antMatchers("/nutritionist/find/{}").hasAnyAuthority("ROLE_USER")
                .antMatchers("/customer/delete/{}").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/customer/{}").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/user/delete/{}").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/product/delete/{}").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/product/create").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/nutritionist/create").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/nutritionist/delete/{}").hasAnyAuthority("ROLE_ADMIN")
                .and()
                        .servletApi();
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }
}
