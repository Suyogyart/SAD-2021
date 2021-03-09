package com.st121334.midterm2021.service;

import com.st121334.midterm2021.model.Employee;
import com.st121334.midterm2021.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public void save(Employee employee) {
        employeeRepo.save(employee);
    }
}
