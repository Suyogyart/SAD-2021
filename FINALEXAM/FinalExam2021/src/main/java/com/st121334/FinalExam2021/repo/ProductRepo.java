package com.st121334.FinalExam2021.repo;

import com.st121334.FinalExam2021.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    // Locking Queries
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Product p where p.id = :id")
    Product findProductForWrite(@Param("id") int id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from  Product p where p.id = :id")
    Product findProductForRead(@Param("id") int id);

}
