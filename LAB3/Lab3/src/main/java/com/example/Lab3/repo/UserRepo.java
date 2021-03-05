package com.example.Lab3.repo;

import com.example.Lab3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
