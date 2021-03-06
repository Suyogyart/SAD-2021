package com.example.Lab3.service;

import com.example.Lab3.model.Employee;
import com.example.Lab3.model.Leave;
import com.example.Lab3.model.LeaveType;
import com.example.Lab3.model.SickLeave;
import com.example.Lab3.repo.LeaveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepo leaveRepo;

    @Override
    public List<Leave> getAllLeaves() {
        return leaveRepo.findAll();
    }

    @Override
    public List<Leave> getAllUnapprovedLeaves() {
        return leaveRepo.getAllUnapprovedLeaves();
    }

    @Override
    public void save(Leave leave) {
        leaveRepo.save(leave);
    }

    @Override
    public void deleteById(int id) {
        leaveRepo.deleteById(id);
    }

    @Override
    public Leave getLeaveById(int id) {
        return leaveRepo.findById(id).orElse(null);
    }
}
