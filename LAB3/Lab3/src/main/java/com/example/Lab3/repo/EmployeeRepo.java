package com.example.Lab3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Lab3.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
