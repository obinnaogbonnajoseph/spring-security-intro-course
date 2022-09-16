package com.obinna.springsecurity.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .logout();
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager manager() {
        List<UserDetails> users = new ArrayList<>();
        var user1 = User.builder().username("victoria").password("{noop}password").roles("USER").build();
        var user2 = User.builder().username("dave").password("{noop}password").roles("USER").build();
        users.add(user1);
        users.add(user2);
        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers("/css/**", "/webjars/**");
    }

}