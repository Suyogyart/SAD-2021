# HW6: Optimistic and Pessimistic Locking

### Optimistic Locking
Optimistic Locking does not lock out any resources. Instead, it resolves conflict when it finds one.  It uses @version to keep track of any possible inconsistent states of objects.

### Pessimistic Locking
Pessimistic Locking locks out resources. Write lock and read lock are the two most common locks. In read lock, any other transactions can read but not write. On the other hand, in write lock, no other transactions can read nor write. Spring implements pessimistic locking using @Lock at the repository query.

## Dependencies
1. **Lombok** - for reducing boilerplate code
2. **JPA** - for repository pattern
3. **H2** - in-memory database (feel free to use others)
4. **Spring Web** - provides controllers and MVC support

## Application Properties

```text
spring.h2.console.enabled=true
spring.datasource.platform=h2
spring.datasource.url=jdbc:h2:mem:oplock;LOCK_TIMEOUT=1000
```

`LOCK_TIMEOUT` is specified to 1000ms.  This is informing h2 that or our pessimistic locking, the lock wait time is 1 second.  If the lock wait time is beyond 1 second and is blocking another instance, it shall raise an exception