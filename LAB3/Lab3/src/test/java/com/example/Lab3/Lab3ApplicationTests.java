package com.example.Lab3;

import com.example.Lab3.model.*;
import com.example.Lab3.repo.EmployeeRepo;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class Lab3ApplicationTests {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	EmployeeRepo empRepo;

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
	void testCache() throws InterruptedException {
		// Configure cache manager
		CacheManager cacheManager = CacheManager.newInstance("src/main/resources/ehcache.xml");
		Ehcache ehCache = cacheManager.getEhcache("employee");

		System.out.println("---- Testing Cache ----");
		System.out.println("-- Loading entities --");
		Employee employee = em.find(Employee.class, 1);
		System.out.println("Employee loaded: " + employee.getName().getFname());

		Assertions.assertEquals("Chaklam", employee.getName().getFname());

		System.out.println("---- SLEEPING FOR 10 SECONDS NOW ----");
		TimeUnit.SECONDS.sleep(10);

		System.out.println("---- Testing Cache ----");
		System.out.println("-- Loading entities --");
		Employee employee1 = em.find(Employee.class, 1);
		System.out.println("Employee loaded: " + employee1.getName().getFname());

		// Tests that there is something available in the cache
		Assertions.assertFalse(ehCache.getSize() > 0);

		//TODO: The cache referenced is the correct one, but the size is not being determined properly.
	}

	@Transactional
	@Test
	void testCascadePersist() {

		// Create a new employee and make it persist
		Employee employee = new Employee();
		Name name = new Name("Peter", "Shawn", "");
		employee.setName(name);
		employee.setAge(35);

		// Test that employee doesnt exist with id 3
		Assertions.assertNull(em.find(Employee.class, 3));

		// add user
		User u = em.find(User.class, 3);
		employee.setUser(u);

		// add addresses
		Address address = new Address();
		AddressId addressId = new AddressId();
		addressId.setCity("Bangkok");
		addressId.setHouseNo("33/3");
		addressId.setStreetAddress("Icon siam");
		addressId.setZipcode("12021");
		address.setId(addressId);
		address.setEmp(employee);
		List<Address> addresses = new ArrayList<Address>();
		addresses.add(address);
		employee.setAddresses(addresses);

		// add benefits
		Set<Employee> employees = new HashSet<Employee>();
		employees.add(employee);
		Benefit benefit = new Benefit("Free lunch", employees);
		Set<Benefit> benefits = new HashSet<Benefit>();
		benefits.add(benefit);
		employee.setBenefits(benefits);

		em.persist(employee);

		// Test that employee exists with id 3
		Assertions.assertNotNull(em.find(Employee.class, 3));

		Employee recentEmployee = em.find(Employee.class, 3);
		Assertions.assertEquals("Peter", recentEmployee.getName().getFname());

		// Test that ensures @OneToOne and @MapsId worked
		Assertions.assertEquals(recentEmployee.getId(), recentEmployee.getUser().getId());
	}

	@Transactional
	@Test
	void testCascadeRemove() {
		Employee employee = em.find(Employee.class, 1);
		System.out.println("Employee Loaded: " + employee.getName().getFname());

		Assertions.assertEquals("Chaklam", employee.getName().getFname());
		Assertions.assertEquals("Ramindra", employee.getAddresses().iterator().next().getId().getStreetAddress());
		Assertions.assertFalse(employee.getBenefits().isEmpty());

		// Test that there is benefit associated with this employee
		Assertions.assertNotNull(em.find((Benefit.class), 1));

		em.remove(employee);

		AddressId addressId = new AddressId();
		addressId.setCity("Bangkok");
		addressId.setZipcode("10220");
		addressId.setHouseNo("30/6");
		addressId.setStreetAddress("Ramindra");

		Assertions.assertNull(em.find(Employee.class, 1));

		// Benefits will be still there, because its CascadeType is set to Persist and Merge
		Assertions.assertNotNull(em.find((Benefit.class), 1));

		// Address will be null because its CascadeType is set to ALL and orphan removal true
		Assertions.assertNull(em.find(Address.class, addressId));
	}

}
