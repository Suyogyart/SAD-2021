package com.example.Lab3;

import com.example.Lab3.model.*;
import com.example.Lab3.repo.EmployeeRepo;
import org.hibernate.Hibernate;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@SpringBootTest
class Lab3ApplicationTests {

	Logger logger = LoggerFactory.getLogger(Lab3ApplicationTests.class);

	@PersistenceContext
	private EntityManager em;

	@Autowired
	EmployeeRepo empRepo;

	@Test
	void contextLoads() {
	}

	// Test Lazy Fetch
	@Transactional
	@Test
	void testFetch() {
		System.out.println("-- Loading Entities --");
		Employee employee = em.find(Employee.class, 1);
		System.out.println("Employee loaded: " + employee.getName().getFname());

		// Test that employee fetched is Chaklam
		Assertions.assertEquals("Chaklam", employee.getName().getFname());


		// Test that employee addressess are not loaded yet
		Assertions.assertFalse(Hibernate.isInitialized(employee.getAddresses()));
		// Address is lazy load, so will not be queried unless its info is needed
		System.out.println("-- Loading addresses --");
		System.out.println("City addresses loaded: " + employee.getAddresses());

		// Test that employee address has been loaded now
		Assertions.assertEquals("Bangkok", employee.getAddresses().iterator().next().getId().getCity());
		Assertions.assertEquals("Ramindra", employee.getAddresses().iterator().next().getId().getStreetAddress());


		// Test that employee benefits are not initialized yet
		Assertions.assertFalse(Hibernate.isInitialized(employee.getBenefits()));
		// Benefits are lazy load
		System.out.println("-- Loading Benefits --");
		System.out.println("Benefits loaded: " + employee.getBenefits().iterator().next().getTitle());

		// Test that employee benefits are initialized
		Assertions.assertTrue(Hibernate.isInitialized(employee.getBenefits()));
		Assertions.assertEquals("Benefit Free Coffee", employee.getBenefits().iterator().next().getTitle());


		// Test that user property is not initialized yet
		Assertions.assertFalse(Hibernate.isInitialized(employee.getUser()));
		// User is lazy load
		System.out.println("-- Loading User --");
		System.out.println("User loaded: " + employee.getUser().getUsername());

		// Test that user has now been initialized
		Assertions.assertTrue(Hibernate.isInitialized(employee.getUser()));
		// Test that user has been loaded.
		Assertions.assertEquals("chaklam", employee.getUser().getUsername());

	}

	@Transactional
	@Test
	void whenParentRemovedChildIsRemoved() {
		int userId = createUser();

		User actualUser = em.find(User.class, userId);

		System.out.println(actualUser.getUsername());
		System.out.println(actualUser.getEmp().getName());
	}

	int createUser() {

		Employee employee = new Employee();
		employee.setName(new Name("SRT", "Tamrakar", "Ratna"));
		employee.setAge(26);

		AddressId addressId = new AddressId();
		addressId.setCity("Kathmandu");
		addressId.setHouseNo("123");
		addressId.setStreetAddress("Dhalko");
		addressId.setZipcode("44600");

		Address address = new Address();
		address.setEmp(employee);
		address.setId(addressId);

		employee.setAddresses(List.of(address));

		Benefit benefit = new Benefit();
		benefit.setTitle("Free Benefit");
		benefit.setEmp(Set.of(employee));

		employee.setBenefits(Set.of(benefit));

		User user = new User("srt", "srt", "ROLE_USER", true, employee);

		employee.setUser(user);


		em.persist(user);

		return user.getId();
	}

}
