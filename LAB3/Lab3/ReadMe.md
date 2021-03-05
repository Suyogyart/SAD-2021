# Take Home Tasks

### a. Changing cache time
> Set the employee cache to 20 seconds, 
> and run the test again. Look at testCache.
> What happens? Document your findings.

**Answer:**

_**What was happening BEFORE when `timeToIdleSeconds="5"`?**_
* First, `testFetch()` gets employee, so it hits SQL Query.
  

* Then, on calling `testCache()`, it loaded from cache, as `5 SECONDS` was still not passed.
* Then it `SLEPT for 10 SECONDS`.
  

* By this time, idle time has passed and no request is made, so the `CACHE INVALIDATES`.
  

* So, when it runs `testCache()`, the SQL Query is made again, and the `IDLE TIME is RESET to 5 SECONDS`.
  

* On next several calls of `testCache()`, it is still under 5 seconds time frame. So employee is loaded from the cache.
  

* It again sleeps for 10 seconds, when the idle time again passes. So SQL Query is made again.


I changed `timeToIdleSeconds = 20` and `timeToLiveSeconds = 100`. 
```xml
    <cache name="employee"
           maxEntriesLocalHeap="10000"
           eternal="false"
           timeToIdleSeconds="20"
           timeToLiveSeconds="100">
        <persistence strategy="localTempSwap" />
    </cache>
```

_**What is happening here?**_
* First, Employee is loaded by `testFetch()` method, so it makes SQL Query.
    * Now the employee is added to the cache.
    * The `20 SECONDS IDLE` countdown has begun.
    

* Then, `testCache()` method is called.
    * Here, Employee is loaded from the cache.
    

* Now, code `SLEEP FOR 10 SECONDS`
    * After this 10 seconds, we still have employee in the cache.
    * The remaining time will be more or less 10 seconds.
    

* Again, `testCache()` method is called several times.
    * Still, the cache is not yet invalidated, employee is still there.
    * At this point, `IDLE TIME` will reset to `20 SECONDS` again.
    * Countdown again begins from `20 SECONDS`.
    

* Now again, code `SLEEP FOR 10 SECONDS`
    * The remaining time will still be like 10 seconds more or less, 
      as idle time has been reset after being called.
    

* So, when `testCache()` is called again
    * Employee is still in the cache, so loads from cache without making SQL Query.

_**Console output after setting Employee cache to 20 seconds**_

![](img/console_a.png)



### b. Make foreign key of Employee in User Table
> Attempt to change the code, so that User table has 
> the foreign key of Employee.

**Answer:**

**Employee BEFORE has `USER_ID` as foreign key**

![](img/a_h2_before.PNG)

**User BEFORE**

![](img/a_h2_user_before.PNG)

After changing the code.

**User NOW has `EMP_ID` as foreign key**

![](img/a_h2_user_after.png)

**Employee NOW**

![](img/a_h2_employee_after.png)



### c. `Cascade.REMOVE` vs `orphanRemoval = true`
> Research and discuss the difference between cascade.REMOVE and orphanRemoval=true 
> (use your own words)

**Answer:**

#### CascadeType.REMOVE
This property deletes the child entities when its parent is deleted. In our coding assignment, we have


#### orphanRemoval = true

### d. Remove `FetchType.Lazy` and test
> Remove lazy load from addresses and benefits, run the testFetch function. 
> What happens? Document your findings.


### e. Run `testCascadeRemove` and `testCascadePersist` after removing some properties
> Remove cascade = cascadeType.ALL and orphanRemoval = true from benefits and addresses, 
> run the testCascadeRemove and testCascadePersist function. What happens?  
> Document your findings.


### f. Remove `@Transactional`
> Attempt to remove @Transactional from any of the methods defined 
> in the TestService.java. There are some errors. Explain why such an error happens.


## g. Write `JUnit tests`
> **Coding**: Transform my main program test into unit test.


## h. Extend app to apply leave and approve leave
> **Coding**: Attempt to extend the app so that user can apply sick leave or annual leave 
> (do not make any fancy thing, simply add leave), and admin can approve leave.
