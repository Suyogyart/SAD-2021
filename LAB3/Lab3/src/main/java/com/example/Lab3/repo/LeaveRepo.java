package com.example.Lab3.repo;

import com.example.Lab3.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Lab3.model.Leave;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeaveRepo extends JpaRepository<Leave, Integer> {

    @Query("FROM Leave l WHERE l.approved = false")
    List<Leave> getAllUnapprovedLeaves();

}
