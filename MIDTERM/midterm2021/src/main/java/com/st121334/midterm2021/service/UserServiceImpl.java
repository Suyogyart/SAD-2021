package com.st121334.midterm2021.service;

import com.st121334.midterm2021.helper.LocalDateConverter;
import com.st121334.midterm2021.model.Address;
import com.st121334.midterm2021.model.Employee;
import com.st121334.midterm2021.model.User;
import com.st121334.midterm2021.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AddressService addressService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    LocalDateConverter converter = new LocalDateConverter();

    @Override
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User save(User user, Address address, Employee employee) {

        address.setEmp(employee);
        employee.addAddress(address);
        addressService.save(address);

        user.setEmp(employee);
        String hashedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setActive(true);

        employee.setUser(user);
        employee.setBirthday(converter.convertToEntityAttribute(employee.getBirthday_()));

        return userRepo.save(user);
    }

    @Override
    public void delete(User user) {

        userRepo.delete(user);
    }

    @Override
    public User findById(int userId) {
        return userRepo.findById(userId).orElse(null);
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }
}
