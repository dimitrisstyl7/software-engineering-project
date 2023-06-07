package com.unipi.findoctor.services.impl;

import com.unipi.findoctor.models.User;
import com.unipi.findoctor.repositories.UserRepository;
import com.unipi.findoctor.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
