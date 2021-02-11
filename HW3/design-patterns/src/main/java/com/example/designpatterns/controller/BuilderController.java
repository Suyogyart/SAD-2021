package com.example.designpatterns.controller;

import com.example.designpatterns.model.User;
import com.example.designpatterns.model.UserBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/builder")
public class BuilderController {

    @GetMapping("/user1")
    public String getUser1() {
        User user1 = new UserBuilder()
                .setUid(1)
                .setName("Suyogya")
                .setNationality("Nepal")
                .setEmail("st121334@ait.asia")
                .build();

        System.out.println("User 1: " + user1);
        return "User 1 Created !";
    }

    @GetMapping("/user2")
    public String getUser2() {
        User user2 = new UserBuilder()
                .setUid(2)
                .setName("Ratna")
                .setNationality("Nepal")
                .setEmail("ratna@gmail.com")
                .build();

        System.out.println("User 2: " + user2);
        return "User 2 Created !";
    }
}
