# HW 4 - Revenue Recognition

>**Simple Revenue Recognition Principle**
> 
> * **Revenue** is said to be **earned when product is exchanged**, 
    but not when cash is exchanged.
> * If cash is paid in advance and product is exchanged later, then it is **Deferred Revenue**.
> * If cash is paid later and product is exchanged first, then it is **Accrued Revenue**.

> [**Martin Fowler's Revenue Recognition Problem**: Domain Logic Patterns](http://lorenzo-dee.blogspot.com/2015/08/domain-logic-patterns.html)


## General Dependencies
1. **Spring Web MVC** for web interfaces.
2. **H2** for in-memory database.
3. **Tomcat Jasper** to load and render JSP pages.
4. **Jackson Dataformat** to allow our RESTful API to work with XML files.
5. **JSTL** for using c-taglib directives in JSP
6. **Lombok** for making things easier for getters and setters.

## Additional Dependencies
1. [Java Money API](https://mvnrepository.com/artifact/org.javamoney/moneta): API for representing, transporting, and performing
   comprehensive calculations with Money and Currency.
   
2. [Moneta](https://mvnrepository.com/artifact/org.javamoney/moneta): Implements Java Money API.

## Revenue Recognition Script