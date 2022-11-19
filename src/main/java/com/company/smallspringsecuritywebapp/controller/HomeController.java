package com.company.smallspringsecuritywebapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
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
}
