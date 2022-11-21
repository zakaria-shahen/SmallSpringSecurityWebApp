package com.company.smallspringsecuritywebapp.security;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity(debug = true)
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public DefaultSecurityFilterChain configSecurity
            (HttpSecurity httpSecurity, CustomAuthenticationProvider customAuthenticationProvider, SendEmailAfterLoginFilter sendEmailAfterLoginFilter) throws Exception {

         httpSecurity.httpBasic()
                 .and().authorizeHttpRequests().anyRequest()
                 .hasAnyRole("USER", "ADMIN")
                 .and().addFilterAfter(sendEmailAfterLoginFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}
