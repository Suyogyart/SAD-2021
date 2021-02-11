package com.example.Lab2_Guide.security;

import com.example.Lab2_Guide.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

// for setting up
// authentication and authorization
// authentication - login/logout
// authorization - who can access which part
@Configuration // This file will be run at runtime
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    // I want an encoder that will encode password
    // So this is singleton
    @Bean // Method Level
    // This method now can be autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Override two functions
    // First function -> Authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // what are the userDetailsService
        // what are password encoder
        // When we launch the app AuthBulder gets called
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
    }

    // Second function -> authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // h2-console, /login, /register - anyone
        // admin/** - admin
        // In any page, /** - ROLE_USER, ROLE_ADMIN
        // premium/** - ROLE_ADMIN, ROLE_PREMIUM_USER
        http
                .csrf().disable() // disable so that h2 works
                .authorizeRequests()
                    .antMatchers("/h2-console/**", "/login", "/register").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/**").hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .defaultSuccessUrl("/home", true)
                .and()
                .logout().invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/logout-success").permitAll();

        // To make h2 frame visible
        http.headers().frameOptions().disable();
    }
}
