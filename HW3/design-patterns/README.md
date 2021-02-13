## HW3: Design Patterns and Spring Boot (GUIDE)

---

### 1. BUILDER PATTERN (CREATIONAL)
#### Advantages
* Avoids use of long constructors
* Allows creating objects step by step
* Encapsulates growing list of attributes

#### Without using Lombok
Creating User 1

![](img/builder/builder_getUser1.png)

GET Request for User 1 from Postman
![](img/builder/builder_getUser1_postman.png)

Structure of User with just properties
![](img/builder/user.png)

#### With Lombok
Structure of User with @Builder
![](img/builder/user_builder.png)

Structure of User with @Builder and @Data
![](img/builder/user_lombok.png)

GET Request for User 2 from Postman
![](img/builder/builder_lombok_getUser2_postman.png)


---

### 2. FACTORY PATTERN (CREATIONAL)

#### Advantages
* Hiding creation logic
* Control creation login only in one place (DRY Principle)
* Adding subclasses require very less change in main class
* Highly reusable

#### When to use?
* When creation logic is complex and want to hide complexity behind
* Not sure which class will be used later
* When class is only chosen at runtime

#### Creating Interface
![](img/factory/interface.png)

#### Implementing Interface
Concrete classes implement the interface and has to implement all the required methods
![](img/factory/concreteAdmin.png)
![](img/factory/concreteMember.png)

#### Creating RoleFactory
Object creation logic will be handled here. The factory produces required objects.
Role factory is annotated with `@Component`. This is a class level annotation that
tells that this class can be `Autowired` later for dependency injection.

![](img/factory/roleFactory.png)

#### Creating Controller
Here we implement a `PostMapping` to set name and check access level using
path variables.
![](img/factory/factoryController.png)

#### Testing POST request with Postman
![](img/factory/admin.png)
![](img/factory/member.png)

---

### 3. ADAPTER PATTERN (STRUCTURAL)
#### Advantages
* Makes multiple interfaces compatible
* Allows two unrelated interfaces work together without changing their existing code
* Uses single `Adapter class` to join functionalities of independent or incompatible interfaces / classes

#### Creating TextFormattable Interface
![](img/adapter/TextFormattableInterface.png)

#### Creating CsvFormattable Interface
![](img/adapter/csvFormattableInterface.png)

#### Concrete Classes that implement the respective interfaces
![](img/adapter/newlineFormatter.png)
![](img/adapter/csvFormatter.png)

#### Creating CsvAdapter
Here we make CsvFormatter compatible with TextFormattable interface. This is achieved by creating
`CsvAdapterImpl` class that implements `TextFormattable` and takes in `CsvFormattable` object in its constructor.
![](img/adapter/csvAdapterImpl.png)

#### Testing Adapter Pattern
![](img/adapter/adapterTest.png)
The `NewLineFormatter` formats the lines into new lines.
Next, we create a `CsvFormatter` and pass it to the `CsvAdapter` which implements `TextFormattable` interface.

---

### 4. FACADE PATTERN (STRUCTURAL)

#### Advantages
* Makes client free from internal service dependencies
* Allows changing of internals without affecting the client
* Makes testing easier

Here we use Facade Design Pattern to print different types of report
for different departments.

#### Creating SalesReportHelper and LogisticsReportHelper

These classes will have their own implementation of their methods. The implementation could be like establishing
database connection, they may have to connect to their own databases, or generating reports
according to their specific needs.

![](img/facade/saleHelper.png)
![](img/facade/logisticHelper.png)


#### Making use of Java Enums (Type Safe Practice)
Java enums help us in creating useful cases instead of using String literals directly. This
ensures that we do not make any typo errors while doing stuff.

![](img/facade/typesafeEnums.png)

#### Facade Implementation
Here `Facade` class provides encapsulation and makes it easier for client to use the internal services
without having to know their logical implementations.

The client will only know about `Facade`, but not its internal logics.

This `generateReport()` is a method in `Facade` class, that accepts Service type, Report Type and Date
and hence handles logic for different services.

![](img/facade/facadeImpl.png)

#### Using Facade to generate different reports
![](img/facade/facadeUse.png)

Here the client uses `Facade` to generate the appropriate reports.

---

### 5. STATE DESIGN PATTERN (BEHAVIORAL)
#### Advantages
* Useful when maintaining an entity that contains multiple transition states 
* Avoids coupling between client and state changes
* Encourages developing of abstract class or interface, and defining concrete class that implement abstract
interface or abstract class for each state
  
Here we explore state changes in player attributes in attack, defense and agility

#### Creating Interface
Here we create state interface where we declare methods for changing the state of object that implements this interface
![](img/state/stateInterface.png)

#### Create class that implements State interface
![](img/state/swordman.png)

#### Creating an object and invoking methods from the State interface
![](img/state/stateTest.png)

#### Results after applying different states to the class
![](img/state/characterOutput.png)

---

### 6. OBSERVER PATTERN (BEHAVIORAL)
#### Advantages
* Allows to observe a subject and get notified when subject sends update.
* Reduces coupling

#### Creating Subject interface
This interface is responsible for registering, unregistering and notifying observers.
It also gets update for an observer from the subject

![](img/observer/subjectInterface.png)

#### Creating Observer interface
This interface is implemented by those concrete classes who subscribe to the specific subject
![](img/observer/observerInterface.png)

#### Creating concrete class
Topic is a concrete class which implements subject. Here Topic is a subject and upon its creation,
new empty list is initialized to store observers
![](img/observer/topic.png)

#### Creating an observer named TopicSubscriber that observes Topic as subject
![](img/observer/topicSubscriber.png)

#### TopicSubscriber can post messages and notify observers
![](img/observer/postMessage.png)

#### Creating Topic and making observers observe / subscribe to different topics
![](img/observer/observerTest.png)

#### Result: Gets message only when observer is registered and message is posted by the Subjects
![](img/observer/result.png)
  
---



