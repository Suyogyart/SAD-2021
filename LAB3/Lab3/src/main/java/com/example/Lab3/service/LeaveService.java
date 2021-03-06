package com.example.Lab3.service;

import com.example.Lab3.model.Employee;
import com.example.Lab3.model.Leave;

import java.util.List;

public interface LeaveService {

    List<Leave> getAllLeaves();

    List<Leave> getAllUnapprovedLeaves();

    void save(Leave leave);

    void deleteById(int id);

    Leave getLeaveById(int id);

}
