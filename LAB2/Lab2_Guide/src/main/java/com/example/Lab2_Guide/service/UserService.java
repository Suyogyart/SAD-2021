package com.example.Lab2_Guide.service;

import com.example.Lab2_Guide.model.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
}
