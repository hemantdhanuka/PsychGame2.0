package com.dhanuka.psych.game.config;

import com.dhanuka.psych.game.auth.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configurable
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService customUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //for authentication
        auth.parentAuthenticationManager(authenticationManagerBean())
                .userDetailsService(customUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //for authorization
        http
                .authorizeRequests()
                .antMatchers("/dev-test/*").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/play/").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().logout().permitAll();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(5);
    }


}
