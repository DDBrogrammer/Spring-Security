package com.example.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class AuthConfigure extends WebSecurityConfigurerAdapter {

    private  PasswordEncoder passwordEncoder;

    @Autowired
    public  AuthConfigure(PasswordEncoder passwordEncoder){
        this.passwordEncoder=passwordEncoder;
    }// get configure pass
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .authorizeRequests()
               .antMatchers("/client/home").permitAll()
               .antMatchers("//api/**").hasRole(ApplicationUserRole.STUDENT.name())
               .anyRequest()
               .authenticated()
               .and()
               .httpBasic();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
       UserDetails dai= User.builder()
                .username("dai")
                .password(passwordEncoder.encode("1234"))
                .roles(ApplicationUserRole.STUDENT.name())//ROLE_STUDENT
                .build();
       /* User.builder()
                .username("daivip")
                .password(passwordEncoder.encode("dai123"))
                .roles("ADMIN")
                .build(); */
        UserDetails daivip = User.builder()
                .username("daivip")
                .password(passwordEncoder.encode("dai123"))
                .roles(ApplicationUserRole.ADMIN.name())
                .build();
        return new InMemoryUserDetailsManager(
                dai,
                daivip
        );
    }
}
