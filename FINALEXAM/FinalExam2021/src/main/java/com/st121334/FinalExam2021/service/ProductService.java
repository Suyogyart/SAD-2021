package com.st121334.FinalExam2021.service;

import com.st121334.FinalExam2021.model.Product;
import com.st121334.FinalExam2021.repo.ProductRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product findProductById(int id) {
        return productRepo.findById(id).orElse(new Product());
    }

    @Transactional
    @Async
    public CompletableFuture<Product> updateStock(Product product, int newStock) {

        // Update Stock
        product.setStock(newStock);

        Product updatedProduct = null;

        // Handle locking here
        try {
            updatedProduct = productRepo.save(product);

        } catch (PessimisticLockingFailureException e) {
            LOGGER.error("PESSIMISTIC LOCKING EXCEPTION OCCURRED");
            updateStock(product, newStock);

        } catch (ObjectOptimisticLockingFailureException e) {
            LOGGER.error("OPTIMISTIC LOCKING EXCEPTION OCCURRED");
            updateStock(product, newStock);
        }
        return CompletableFuture.completedFuture(updatedProduct);
    }


}
