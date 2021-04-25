package com.example.sessionredis.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin")
                .password(bCryptPasswordEncoder().encode("password"))
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/**").hasRole("ADMIN")
                .anyRequest().authenticated();
    }

    //    @Autowired
//    private MyUserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//                .and()
//                .authorizeRequests()
//                    .antMatchers("/h2-console/**", "/login").permitAll()
//                    .antMatchers("/**").hasRole("ADMIN")
//                .and()
//                    .formLogin()
//                    .successForwardUrl("/")
//                    .defaultSuccessUrl("/")
//                .and()
//                    .logout()
//                    .invalidateHttpSession(true)
//                    .clearAuthentication(true)
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                    .logoutSuccessUrl("/").permitAll();
//
//        // Only for testing, remove for production
//        // To make h2 frame visible
//        http.headers().frameOptions().disable();
//    }
}
