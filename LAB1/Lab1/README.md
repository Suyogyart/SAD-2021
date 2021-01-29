## LAB 1: RESTful APIs, MVC with Spring Boot

_**Objective:** To be able to implement basic CRUD operations on the database system using RESTful APIs in Spring Boot._


### Pre-requisites
1. Spring Boot installation
2. Postman for RESTful calls

### Dependencies Installed
The required dependencies can be downloaded from [MVN Repository](https://mvnrepository.com) website.
1. **Spring Web MVC** for web interfaces
2. **H2** for in-memory database
3. **Spring JPA** for common boilerplate SQL queries for Java
4. **Tomcat Jasper** to load and render JSP pages
5. **Jackson Dataformat** to allow our RESTful API to work with XML files
6. **Spring Dev Tools** for faster development

### Model Description
![](img/employee.png)

Here are the key concepts in Employee model to know about:

* `Employee` class is marked by `@Entity` annotation to make it recognized as a `TABLE` in the h2-database.

* `Gender` is declared as an `enum`. `@Enumerated` annotation is used to identify variable as `ENUM`. This enum stores `STRING` type data. Other type can be `ORDINAL` (numeric) in the order it is defined.

* `@Id` denotes that `eid` should be made the primary key in `Employee` table.
  
* `@GeneratedValue` means that employee id need not be provided manually, but is incremented automatically

* `@Transient` says that we don't need a separate column in database because it is an derived attribute, i.e. it can be calculated from the values already stored in the database.

_**Note:** We need to make sure that all the variable names declared inside the entity should have lowercased names. In my case, camelCase didn't work too._

### Static SQL Queries for initial configuration
`data.sql` file inside `resources` directory consists of database populating queries.

![](img/sql.png)



### Major CRUD Operations and Endpoints Verification

**Using *ModelAndView* class**
We can instantiate an object of `ModelAndView` class and add an object to it. We can specify in it's constructor regarding which view to return. This class allows to send data along with model data.
![](img/model-and-view.png)

The contents of `home.jsp` file, which is basically an HTML form.

Once the request is completed, we can then show the details and work with using the model we have passed before.

![](img/view-user.png)


**Using *Postman***
1. Open **h2-console** in web browser.
2. Open **Postman** and start creating and sending requests.

**Get all users using CrudRepository**
![](img/get-users.png)
Notice how crud repository returns Java objects in its response.

**Get all users using JpaRepository**
![](img/get-usersJPA.png)
JPA repository returns well formatted JSON/XML objects.

---

**Get user by id using CrudRepository**
![](img/get-user-by-id.png)

**Get user by id using JpaRepository**
![](img/get-userJPA-by-id.png)

---

**Add new user**
![](img/add-user.png)
Here we do `POST` request on `/userJPA` endpoint with JSON object in the request body. The JPA repository maps this JSON object into respective model and saves it into the database.

![](img/add-user-console.png)

---

**Update user if exists, Create if does't exist**
![](img/update-user.png)
Here we do `PUT` request on `/userJPA` endpoint with JSON object in the request body. The JPA repository maps this JSON object into respective model and updates it if its Id value does exist already in the database, otherwise saves as a new record into the database.

![](img/update-user-console.png)

---

**Delete user**
![](img/delete-user.png)
Here we perform `DELETE` request on `/userJPA/{uid}` endpoint where {uid} refers to the id of object to be deleted.

![](img/delete-user-console.png)

---

_**Note:** Notice how we can route all the requests related to one kind of entity using a single endpoint with change in request methods so easily with Repository interfaces in Spring Boot._