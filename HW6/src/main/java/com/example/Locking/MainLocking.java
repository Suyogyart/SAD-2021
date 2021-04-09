package com.example.Locking;

import com.example.Locking.service.ProductService;
import com.example.Locking.service.ProductServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class MainLocking {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(LockingApplication.class, args);
        ProductServiceImpl ps = applicationContext.getBean(ProductServiceImpl.class);

        ps.createMockProduct();
        ps.writeLockTransaction(); // This write lock will block the read block
        ps.readLockTransaction();
    }

}
