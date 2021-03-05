package com.example.Lab3.controller;

import com.example.Lab3.model.Employee;
import com.example.Lab3.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepo empRepo;

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable("id") int id) {
        Employee emp = empRepo.findById(id).orElse(null);

        return emp;
    }

}
