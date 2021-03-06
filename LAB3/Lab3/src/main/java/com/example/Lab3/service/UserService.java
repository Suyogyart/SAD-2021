package com.example.Lab3.service;

import com.example.Lab3.model.User;

public interface UserService {
    User findByUsername(String username);
}
