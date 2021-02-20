package com.example.Lab2_Guide.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    // mapped by required so that it understands that this field is already mapped before
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
