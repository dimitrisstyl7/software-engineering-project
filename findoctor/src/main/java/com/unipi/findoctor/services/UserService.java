package com.unipi.findoctor.services;

import com.unipi.findoctor.models.User;

public interface UserService {
    User findByUsername(String username);
}
