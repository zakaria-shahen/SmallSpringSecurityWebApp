package com.company.smallspringsecuritywebapp;

import com.company.smallspringsecuritywebapp.model.Authority;
import com.company.smallspringsecuritywebapp.model.User;
import com.company.smallspringsecuritywebapp.services.UserServices;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class DateLoad implements ApplicationRunner {

    private UserServices userServices;
    private Function<String, String> passwordEncode;

    public DateLoad(UserServices userServices, PasswordEncoder passwordEncoder) {
        this.userServices = userServices;
        this.passwordEncode = passwordEncoder::encode;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        loadUsers();
    }

    private void loadUsers() {
        Authority user = Authority.builder().name("ROLE_USER").build();
        Authority admin = Authority.builder().name("ROLE_ADMIN").build();
        userServices.saveAll(
                User.builder()
                        .username("user")
                        .password(passwordEncode.apply("user"))
                        .authority(List.of(user))
                        .build()
                ,
                User.builder()
                        .username("admin")
                        .password(passwordEncode.apply("admin"))
                        .authority(List.of(admin, user))
                        .build()
        );

    }

}
