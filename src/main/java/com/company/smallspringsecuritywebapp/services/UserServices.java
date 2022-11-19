package com.company.smallspringsecuritywebapp.services;

import com.company.smallspringsecuritywebapp.model.User;
import com.company.smallspringsecuritywebapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServices {

    private UserRepository userRepository;

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void saveAll(User...users) {
        userRepository.saveAll(Arrays.stream(users).toList());
    }

}
