package com.example.Lab2_Guide.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendEmail(SimpleMailMessage emailMsg);
}
