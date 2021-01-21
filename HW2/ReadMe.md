## Setting up Spring Boot and running the first application

#### Spring Boot
Spring Boot is an amazing framework for building web applications. Applications could be backend or full stack applications. It is very popular framework nowadays and we can easily code using languages like Java and Kotlin.

#### Why Spring Boot?
Spring Boot is very flexible and supports tons of different modules and gives everything we need to build an application. If we need security, we can use **security modules** available, we can use **logging integrations**, we can connect to every kind of **databases** like MongoDB, Postgres, Neo4j, MySQL, and so on. It also has modules for **metrics**, i.e. checking how our application is behaving in production. It is **production ready**, we can build **microservices**, add built in **dependency injections** and **multiple configurations**. Furthermore, it has a **great community** and is **easy to learn**.

#### Integrated Development Environments (IDE) Options
1. [IntelliJ Community Edition](https://www.jetbrains.com/idea/download/)
2. [Visual Studio Code](https://code.visualstudio.com/)

#### Spring Initializr
Go to [https://start.spring.io/](https://start.spring.io/) to get started with the Spring Boot application. We can configure our project in the way we like. It allows us to select the programming language to use among Java, Kotlin and Groovy; Maven and Gradle Project; Spring Boot version to use; and configure project metadata like package names, packaging type (Jar used mostly), Java versions, project names, etc. Most importantly, it allows us to pick the dependencies that our project needs. There exist a list of dependencies and we can find almost anything we want to use in our projects.

Click on **Generate** and we will get a zip file containing our starter project with the configurations chosen. We can modify those later as well. Now, project is ready to go and we open it in our favorite IDE.

#### Important folders in project structure
* `src\main\java`
  Consists of most of the Java code, models, business logics structured in more folders as required. It comes up with a barebone Spring Boot application where we can start coding. 

* `src\main\resources`
  Important file in this folder is *application.properties*. This is where we configure all the properties of our application and environment-specific properties such as parameters and credentials of our database connections.

* `src\main\static` `src\main\templates`
  Our frontend code such as working with HTML, CSS, JavaScript, reside here.

* `src\main\test`
  Here, we put all our testing code such as unit tests.

#### Managing Dependencies
In the root directory of the project, we can find `pom.xml` file. It is used to manage different dependencies in the project. We can see project metadata like Java version used, project names; dependencies like spring web, databases to use, testing, etc.

When we modify this file, add or remove dependencies, our project needs to be reloaded. 
1. **Right-click** on `pom.xml` file, 
2. **Select** `Maven` and 
3. **Click** on `Reload project`.

We can also get the shortcuts popped-out somewhere in the IDE, after we change the dependency file, where we can reload our project with just a simple click.

#### Starting Spring Boot application
Open the Java file inside `src\main\java` which contains the `main method` and run the method, then our application starts with bunch of texts in the logger below. One of the important log message is:
* `Tomcat started on port(s): 8080 (http)...`
  This means we have a web server up and running and if we try to hit our webserver in port 8080, we will get the endpoints we have implemented in our codes.

We can view our application in any web browser in the address [http://localhost:8080/](http://localhost:8080/). Make sure we have a Tomcat server running on port 8080 for this to work.

#### Spring Web MVC
It is a Java framework which is used to build web applications. It follows the Model-View-Controller (MVC) design pattern.

#### Useful Resources
1. [Spring Initializr](https://start.spring.io/)
2. [Core Spring Framework Annotations](https://springframework.guru/spring-framework-annotations/)
3. [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
4. [Testing Spring Boot RESTful Services](https://springframework.guru/testing-spring-boot-restful-services/)
5. [Getting Started Guides](https://spring.io/guides)
