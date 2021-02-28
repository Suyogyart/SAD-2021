package com.example.Lab3.service;

import com.example.Lab3.model.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TestService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void testCache() throws InterruptedException {
        loadEntity(1);
        // after commit entity will become detached
    }

    @Transactional
    public void testFetch(int id) {
        System.out.println("-- Loading Entities --");
        Employee employee = em.find(Employee.class, id);
        System.out.println("Employee loaded: " + employee.getName().getFname());

        // Address is lazy load, so will not be queried unless its info is needed
        System.out.println("-- Loading addresses --");
        System.out.println("City addresses loaded: " + employee.getAddresses().iterator().next().getId().getCity());

        // Benefits are lazy load
        System.out.println("-- Loading Benefits --");
        System.out.println("Benefits loaded: " + employee.getBenefits().iterator().next().getTitle());

        // User is lazy load
        System.out.println("-- Loading User --");
        System.out.println("User loaded: " + employee.getUser().getUsername());
    }

    @Transactional
    public void testCascadePersist(int id) {
        // Create a new employee and make it persist
        Employee employee = new Employee();
        Name name = new Name("Peter", "Shawn", "");
        employee.setName(name);
        employee.setAge(35);

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
        // this means that em will track all changes once transactions finish

    }

    @Transactional
    public void testCascadeRemove(int id) {
        Employee employee = em.find(Employee.class, id);
        System.out.println("Employee Loaded: " + employee.getName().getFname());
        em.remove(employee);
    }

    public void loadEntity(int id) {
        System.out.println("-- Loading entities --");
        Employee employee = em.find(Employee.class, id);
        System.out.println("Employee loaded: " + employee.getName().getFname());
    }

    @Transactional
    public void testCreateLeave(int id, LeaveType type) {
        LocalDate start = LocalDate.of(2018, 2, 13);
        LocalDate end = LocalDate.of(2018, 2, 15);
        Employee emp = em.find(Employee.class, id);
        switch (type) {
            case SICK:
                SickLeave sl = new SickLeave(emp, false, "Flu", start, end);
                em.persist(sl);
                break;

            case ANNUAL:
                AnnualLeave al = new AnnualLeave(emp, false, "Kids Affairs", start, end);
                em.persist(al);
                break;
        }
    }
}
