package com.example.Lab2_Guide.dao;

import com.example.Lab2_Guide.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleJPADao extends JpaRepository<Role, Integer> {

}
