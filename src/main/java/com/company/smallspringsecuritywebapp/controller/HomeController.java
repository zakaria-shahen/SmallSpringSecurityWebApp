package com.company.smallspringsecuritywebapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/home")
    public Map<String, Object> get(Authentication authentication) {

        return Map.of(
                "You: ", authentication.getAuthorities()
        );
    }

    @PostMapping("/home")
    public Map<String, Object> post(Authentication authentication) {

        return Map.of(
                "(POST) - You: ", authentication.getAuthorities()
        );
    }

}
