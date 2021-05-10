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
    public Product updateStock(Product product, int newStock) {

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
        return updatedProduct;
    }


    // Mock methods to test locking
    @Transactional
    @Async
    public void readLockTransaction() throws InterruptedException {
        System.out.println(LocalTime.now() + " <-- Reading Product Entity -->");

        Product product1 = null;
        try {
            product1 = productRepo.findProductForRead(1);
        } catch (Exception e) {
            System.err.println(LocalTime.now() + "-- Got exception while " + "acquiring the read lock:\n " + e + " --");
        }

        System.out.println(LocalTime.now() + " -- Acquired the read lock -- ");
        System.out.println(LocalTime.now() + " -- Read product: " + product1 + " --");
        System.out.println(" -- READ-LOCK sleeping for 10s --");
        Thread.sleep(10000);
    }

    @Transactional
    @Async
    public void writeLockTransaction() throws InterruptedException {
        System.out.println(" -- WRITE-LOCK sleeping for 100ms --");
        Thread.sleep(100L);
        System.out.println(LocalTime.now() + " <-- Writing Product Entity -->");

        Product product2 = null;
        try {
            product2 = productRepo.findProductForWrite(1);
        } catch (Exception e) {
            System.err.println(LocalTime.now() + "-- Got exception while " + "acquiring the write lock:\n " + e + " --");
        }

        System.out.println(LocalTime.now() + " -- Acquired the write lock --");
        product2.setName("New Name");
        productRepo.save(product2);

        System.out.println(LocalTime.now() + " -- User 2 updated product: " + product2 + " --");
    }


}
