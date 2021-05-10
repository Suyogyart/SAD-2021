package com.example.sessionredis.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import java.io.Serializable;

//@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String role;

    private boolean active;

    public User(String username, String password, String role, boolean active) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.active = active;
    }
}
