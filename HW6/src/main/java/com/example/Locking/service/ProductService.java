package com.example.Locking.service;

import com.example.Locking.model.Product;
import com.example.Locking.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;

public interface ProductService {

    Product findById(Long id);
    Product save(Product product);

}
