package com.st121334.FinalExam2021;

import com.st121334.FinalExam2021.model.Product;
import com.st121334.FinalExam2021.repo.ProductRepo;
import com.st121334.FinalExam2021.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalTime;

@SpringBootTest
class FinalExam2021ApplicationTests {

	@PersistenceContext
	EntityManager em;

	@Autowired
	ProductService productService;

	@Autowired
	ProductRepo productRepo;

	@Test
	void contextLoads() {
	}

	// Mock methods to test locking
//	@Test
	void readLockTest() throws InterruptedException {

		System.out.println(" -- WRITE-LOCK sleeping for 100ms --");
		Thread.sleep(100L);
		System.out.println(LocalTime.now() + " <-- Writing Product Entity -->");

		Product product1 = null;
		try {
			product1 = productRepo.findProductForWrite(1);
		} catch (Exception e) {
			System.err.println(LocalTime.now() + "-- Got exception while " + "acquiring the write lock:\n " + e + " --");
			Assertions.assertNotEquals(5, product1.getStock());
		}

		System.out.println(LocalTime.now() + " -- Acquired the write lock --");
		product1.setName("New Name");
		productRepo.save(product1);

		System.out.println(LocalTime.now() + " -- User 2 updated product: " + product1 + " --");

	}

//	@Test
	void writeLockTest() throws InterruptedException {

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

		Assertions.assertNotEquals(6, product2.getStock());

		System.out.println(LocalTime.now() + " -- User 2 updated product: " + product2 + " --");
	}


}
