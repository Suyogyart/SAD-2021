package com.example.Lab1.dao;

import com.example.Lab1.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {

}
