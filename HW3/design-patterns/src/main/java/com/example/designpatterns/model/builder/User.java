package com.example.designpatterns.model.builder;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@Data
public class User {

    @Id
    private int uid;
    private String name;
    private String nationality;
    private String email;

}
