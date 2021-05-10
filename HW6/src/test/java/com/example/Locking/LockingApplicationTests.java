package com.example.Locking;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
class LockingApplicationTests {

	@PersistenceContext
	EntityManager em;

	@Test
	void contextLoads() {
	}



}
