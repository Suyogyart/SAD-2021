package com.example.Lab2_Guide.service;
import com.example.Lab2_Guide.dao.UserJPADao;
import com.example.Lab2_Guide.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserJPADao userdao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;


    @Override
    public void save(User user) {
        String hashedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setActive(true);
        userdao.save(user);

        // Successful registration -> Send emails
        SimpleMailMessage emailMsg = new SimpleMailMessage();
        emailMsg.setTo(user.getEmail());
        emailMsg.setText("You are registered");
        emailMsg.setSubject("Registration successful!");
        emailMsg.setFrom("admin@random.asia");

        try {
            emailService.sendEmail(emailMsg);
            System.out.println("successful");
        } catch (MailException ex) {
            // Simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public User findByUsername(String username) {
        return userdao.findByUsername(username);
    }
}
