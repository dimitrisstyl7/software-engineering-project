package com.unipi.findoctor.services.impl;

import com.unipi.findoctor.models.User;
import com.unipi.findoctor.repositories.UserRepository;
import com.unipi.findoctor.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public void updateUser(User user) {

        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        existingUser.setPhone(user.getPhone());
        existingUser.setEmail(user.getEmail());
        userRepository.save(existingUser);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
