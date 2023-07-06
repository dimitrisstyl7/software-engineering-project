package com.unipi.findoctor.services;

import com.unipi.findoctor.models.User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserService {
    User findByUsername(String username);

    void updateUser(User user);

    Map<String, String> getAdminDetails();
}
