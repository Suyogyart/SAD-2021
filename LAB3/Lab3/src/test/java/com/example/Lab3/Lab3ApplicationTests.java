package com.example.Lab3;

import com.example.Lab3.model.*;
import com.example.Lab3.repo.EmployeeRepo;
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
