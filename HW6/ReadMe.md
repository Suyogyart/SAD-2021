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

## Model
This is simple `Product.java` class. 

```java
@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    private String name;

    private Long price;
}
```
**@Version** annotation here assumes a column in the database exists to represent the field that the annotation is tagged to.

The JPA takes care of incrementing the version as well as all the version checking upon save.

>**Note:** We *MUST NOT* manually update or increment version by ourselves. When we manually create any object through `data.sql`, me must set the version to **0**.

## Controller
We have the controller with three mappings. 
1. `/products/{id} (GET)`: Find product by id.
2. `/products (PUT)`: Update product.
3. `/products (POST)`: Save product.

## Defining Request handlers

### Global HttpClient and ObjectMapper
```text
static HttpClient client = HttpClient.newHttpClient();
static ObjectMapper mapper = new ObjectMapper();
```
1. **HttpClient**: Make restful calls to our controller APIs
2. **ObjectMapper**: Transform JSON into Java objects.

### Method to Send HTTP requests
```text
private static String sendRequest(HttpRequest request) throws IOException, InterruptedException {
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return response.body();
}
```
**HttpClient** is used to send the request, which returns an `HttpResponse`

### JSON builder and ObjectMapper
We have a custom JSON Builder that builds JSON string from the Java object and in complement to it ObjectMapper class transforms the Java object to the JSON representation.

### HTTP Requests
```text
private static void insert(String name, int price) throws IOException, InterruptedException {
    // JSON formatted data
    String json = makeJSON(name, price);

    HttpRequest request = HttpRequest.newBuilder()
        .POST(HttpRequest.BodyPublishers.ofString(json))
        .uri(URI.create("http://localhost:8080/products"))
        .setHeader("User-Agent", "Java 11 HttpClient SRT Bot")
        .header("Content-Type", "application/json")
        .build();
        
    System.out.println("Inserted: " + sendRequest(request));
```
**HttpRequest** is a builder wrapper for creating Http requests with `.uri` setting the restful path and `.POST` as method type.

The header specifying **Content-Type**  is set to `application/json` to ensure that it will accept JSON strings.

In the similar way, `.PUT` and `.GET` methods are handled simulating the request sending behavior.

## Demonstration of Optimistic Locking
```java
public class LockingApplication {

    static HttpClient client = HttpClient.newHttpClient();
    static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(LockingApplication.class, args);

        // POST
        insert("Microsoft Powerpppointt", 499);

        // GET
        String jsonPowerPoint = get(1);
        Product pro = mapper.readValue(jsonPowerPoint, Product.class);
        pro.setName("Powerpppoint");

        String jsonPowerPoint2 = get(1);
        Product pro2 = mapper.readValue(jsonPowerPoint2, Product.class);
        pro2.setName("Powerpoint");

        // PUT (the version will increment automatically)
        update(convertObjectToJSON(pro));

        // PUT (this will raise an error because the version is different)
        update(convertObjectToJSON(pro2));

    }
}
```

- We first added some data to our H2 Database
- The we get two copies of the same instance, simulating an concurrent situation.
- The first update will pass through nicely.
- However, the second update fails, because Spring detects from the version number that is older than current one.

![](img/optimistic_lock.png)

Here we run into exception when second update happens, and we get this error.

>org.springframework.orm.ObjectOptimisticLockingFailureException: Object of class [com.example.Locking.model.Product] with identifier [1]: optimistic locking failed; nested exception is org.hibernate.StaleObjectStateException: Row was updated or deleted by another transaction





## Demonstration of Pessimistic Locking