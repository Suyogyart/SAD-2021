package com.example.Lab1.dao;

import com.example.Lab1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPADao extends JpaRepository<User, Integer> {

}
