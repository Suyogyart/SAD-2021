package com.example.Lab1.dao;

import com.example.Lab1.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<User, Integer> {
    // By default, CrudRepository already supports basic queries

    // Also supports many auto query by fields
    List<User> findByNationality(String nationality);
    List<User> findByUidGreaterThan(int uid);

    // Also supports writing your own query
    @Query("FROM User WHERE nationality = ?1 ORDER BY name")
    List<User> findByNationaltySortedByName(String nationality);

}
