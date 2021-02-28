package com.example.domainmodel.repository;

import com.example.domainmodel.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractJpaRepository extends JpaRepository<Contract, Integer> {
}
