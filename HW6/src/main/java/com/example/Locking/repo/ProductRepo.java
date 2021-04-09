package com.example.Locking.repo;

import com.example.Locking.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    // A PESSIMISTIC_WRITE lock request fails if another user currently holds either a
    // PESSIMISTIC_WRITE lock or a PESSIMISTIC_READ lock on that database object.
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Product p where p.id = :id")
    Product findProductForWrite(@Param("id") Long id);


    // A PESSIMISTIC_READ lock request fails if another user currently holds a
    // PESSIMISTIC_WRITE lock on that database object.
    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select p from Product p where p.id = :id")
    Product findProductForRead(@Param("id") Long id);

}
