package com.st121334.midterm2021.service;

import com.st121334.midterm2021.model.Address;
import com.st121334.midterm2021.model.Employee;
import com.st121334.midterm2021.model.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User save(User user, Address address, Employee employee);

    void delete(User user);

    User findById(int userId);

    void save(User user);
}
