package com.company.smallspringsecuritywebapp.security;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;

@EnableWebSecurity(debug = true)
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public DefaultSecurityFilterChain configSecurity
            (HttpSecurity httpSecurity, CustomAuthenticationProvider customAuthenticationProvider) throws Exception {

         httpSecurity.httpBasic()
                 .and().authorizeHttpRequests().anyRequest()
                 .hasAnyRole("USER", "ADMIN");

        return httpSecurity.build();
    }

}
