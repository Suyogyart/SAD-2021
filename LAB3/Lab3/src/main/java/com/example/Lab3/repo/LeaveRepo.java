package com.example.Lab3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Lab3.model.Leave;

public interface LeaveRepo extends JpaRepository<Leave, Integer> {

}
