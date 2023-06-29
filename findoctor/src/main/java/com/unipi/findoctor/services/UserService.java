package com.unipi.findoctor.services;

import com.unipi.findoctor.dto.UserDto;
import com.unipi.findoctor.models.Doctor;
import com.unipi.findoctor.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findByUsername(String username);

    void updateUser(User user);
}
