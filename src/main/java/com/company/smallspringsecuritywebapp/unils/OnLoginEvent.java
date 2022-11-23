package com.company.smallspringsecuritywebapp.unils;

import com.company.smallspringsecuritywebapp.model.User;
import org.springframework.context.ApplicationEvent;

public class OnLoginEvent extends ApplicationEvent {

    public OnLoginEvent(User user) {
        super(user);
    }

    public User getUser() {
        return (User) this.source;
    }
}
