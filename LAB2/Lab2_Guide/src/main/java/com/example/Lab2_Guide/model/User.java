package com.example.Lab2_Guide.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Data
public class User {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Incremented Value
    private int id;

    @Column(nullable = false) // database level
    @NotBlank(message = "This field is required.") // UI level, no need to use JavaScript, Supported by validation dependency
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "This field is required.")
    private String password;

    @Transient
    @NotBlank(message = "This field is required.")
    private String passwordConfirm;

    @Column(nullable = false)
    @NotBlank(message = "This field is required.")
    //in user interface level - valid email
    @Email(message = "Invalid Email")
    private String email;

    private boolean active;

    // one user has many roles
    // one role has many roles
    // many to many relationship
    @ManyToMany(fetch = FetchType.EAGER) // Creates an intermediate table
    //User_id, Role_id (by default)
    // If we don't like this name, we can use @JoinTable (complicated)
    // (fetch) - if I get one user, should JPA also get the roles of that user? (EAGER INITIALIZATION)
    // Or if I get one user, may be roles are retrieved only when asked (LAZY INITIALIZATION)
    // If we use spring security, it forces user to always be EAGER
    // In many to many relationship, who is parent
    // If parent is saved, child is saved
    // Or if child is saved, parent is not saved
    @JsonBackReference
    private Set<Role> roles;

}
