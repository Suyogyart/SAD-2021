package com.example.Lab3;

import com.example.Lab3.model.LeaveType;
import com.example.Lab3.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Lab3Application {

	@Autowired
	static TestService ts;

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext applicationContext =  SpringApplication.run(Lab3Application.class, args);

		TestService ts = applicationContext.getBean(TestService.class);

		// Testing lazy fetch
		System.out.println("---- Testing Fetch ----");
		ts.testFetch(1);

		// Testing cache
		System.out.println("---- Testing Cache ----");
		TimeUnit.SECONDS.sleep(10); // make sure cache is cleared
		System.out.println("---- Not loaded, require query ---");
		ts.testCache();
		System.out.println("---- Already loaded ----");
		ts.testCache();
		System.out.println("---- Already loaded ----");
		ts.testCache();
		TimeUnit.SECONDS.sleep(10);
		System.out.println("---- Not Loaded, require query ----");
		ts.testCache();

		// Testing cascade persist
		System.out.println("----Testing Cascade Persist---");
		ts.testCascadePersist(1);
		System.out.println("----Try log in to H2 and try john with pwd of 1234.  See what has been persisted---");


		// Testing cascade remove
		System.out.println("----Testing Cascade Remove---");
		ts.testCascadeRemove(1);
		System.out.println("----Try log in to H2 and try john with pwd of 1234.  See what has been deleted---");


		// Testing inheritances
		System.out.println("----Testing Inheritances ---");
		System.out.println("----Adding Sick Leave for employee with emp_user_id 1---");
		ts.testCreateLeave(2, LeaveType.SICK);
		System.out.println("----Adding Annual Leave for employee with emp_user_id 1---");
		ts.testCreateLeave(2, LeaveType.ANNUAL);
		System.out.println("----Try log in to H2 and try john with pwd of 1234.  See what has been added in LEAVE table---");

	}

}
