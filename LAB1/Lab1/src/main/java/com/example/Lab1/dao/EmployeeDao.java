package com.example.Lab1.dao;

import com.example.Lab1.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {

    @Query("FROM Employee ORDER BY name")
    List<Employee> getEmployeeSortedByName();
}
