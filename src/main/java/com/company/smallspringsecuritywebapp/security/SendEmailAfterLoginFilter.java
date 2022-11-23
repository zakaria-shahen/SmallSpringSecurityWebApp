package com.company.smallspringsecuritywebapp.security;

import com.company.smallspringsecuritywebapp.model.User;
import com.company.smallspringsecuritywebapp.unils.OnLoginEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SendEmailAfterLoginFilter extends OncePerRequestFilter {

    private final ApplicationEventPublisher applicationEventPublisher;

    public SendEmailAfterLoginFilter(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        applicationEventPublisher.publishEvent(new OnLoginEvent(User.builder().build()));

        filterChain.doFilter(request, response);

    }
}
