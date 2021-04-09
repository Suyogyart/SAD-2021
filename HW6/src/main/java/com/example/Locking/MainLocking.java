package com.example.Locking;

import com.example.Locking.service.ProductServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MainLocking {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MainLocking.class, args);
        ProductServiceImpl ps = applicationContext.getBean(ProductServiceImpl.class);

        ps.createMockProduct();
        ps.writeLockTransaction(); // This write lock will block the read block
        ps.readLockTransaction();

        Thread.sleep(15000L);
        ps.readLockTransaction();
    }

}
