package com.example.Locking.service;

import com.example.Locking.model.Product;
import com.example.Locking.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo productRepo;

    @Override
    public Product findById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return productRepo.save(product);
    }

    public void createMockProduct() {
        // creating and persisting an product
        Product pro = new Product();
        pro.setName("Microsoft Word");
        pro.setPrice(499L);
        productRepo.save(pro);
    }

    @Transactional
    @Async
    public void readLockTransaction() throws InterruptedException {
        System.out.println(LocalTime.now() + "<-- Reading Product Entity");

        Product product1 = null;
        try {
            product1 = productRepo.findProductForRead(1L);
        } catch (Exception e) {
            System.err.println(LocalTime.now() + "-- Got exception while " + "acquiring the read lock:\n " + e + " --");
        }

        System.out.println(LocalTime.now() + " -- Acquired the read lock --");
        System.out.println(LocalTime.now() + " -- Read product: " + product1 + " --");
        Thread.sleep(10000);
    }

    @Transactional
    @Async
    public void writeLockTransaction() throws InterruptedException {
        Thread.sleep(100L);
        System.out.println(LocalTime.now() + "<-- Writing Product Entity");

        Product product2 = null;
        try {
            product2 = productRepo.findProductForWrite(1L);
        } catch (Exception e) {
            System.err.println(LocalTime.now() + "-- Got exception while " + "acquiring the write lock:\n " + e + " --");
        }

        System.out.println(LocalTime.now() + " -- Acquired the write lock --");
        product2.setName("New Name");
        productRepo.save(product2);

        System.out.println(LocalTime.now() + " -- User 2 updated product: " + product2 + " --");
    }
}
