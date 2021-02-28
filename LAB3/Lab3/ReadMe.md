# Lab 3: ORM, Hibernate, JPA, and Spring

## Caching
* when we load some objects, we won't load twice, we will have performance issues.

## Relationships

## Cascade
* if we change one object, are we also changing object relating to it

## Lazy and Eager load
* when you have parent-child, when you load parent, will you also load the child
* but if u load whole object, its eager.
* if things are really big, we wont do eager

## Transient

## EmbeddedId
* My primary id will have many fields, not only one field.
* Primary key of multiple fields

## MapsId
* Eg: If we have one to one relationship like User -> Employee, one user will have only one employee
* when I create id for user, not to create id for employee

## Inheritances
* Implementing inheritances in relational database
* Only the concrete classes in the table

## Case Study (Employee)

## Dependencies
### Basic
### Extra
#### hibernate-ehcache
Install same version as hibernate-core
#### ehcache
* Gives 2nd level caching
* 1st level caching we don't have to do anything, springboot handles
* 1st level caching will not persist across sessions/transactions
#### Difference
**hibernate-ehcache** is just an interface that allows ehcache to work properly

## Setup Application Properties

## Cache configruation
### sharedCache=enable selective
only model we specify can be cached
### use 2nd level cache
true

### Creating cache file
#### DiskStore
#### defaultCache
#### cache name

## Models
### User
* ManyToMany -> mapped by -> creates intermediate table
* OneToMany -> mapped by -> creates foreign key
### Address
#### EmbeddedId
* Like a composite key
* Anything that is embeddedId will be inserted into the same table
### AddressId
* Make this Embeddable
* implements serializable -> this object can be sent through some message channels
so that it can be decomposed into bytes and be the fields in the parent table
* 1L -> 1 with Long data type
* all will be the primary key, a composite key

### Leave
* It is abstract class
* @Inheritance means put all the inherited types and abstract types need to be put into the same table
* Single_Table strategy means leave will not have table but sickleave and annuallive will have.
* Need to have only one Id in the parent side
* DiscriminatorColumn we have all in same column, so we need a column that will specify some identifier which will tell us
whether it is a sick leave or annual leave.
* create own discriminator like "Leave Type"
#### Sick Leave
* Discriminator Value -> what needs to be put in the field of discriminator column
* No need to put @Inheritance in child

#### Annual Leave

#### Leae Type Enum

### Benefit
* Many to Many Set is better
* mapped by benefits (benefits is variable inside emp)

### Name
* Embeddable -> will be in the same table
* Not a composite key

### Employee
* Region = employee, find employee name in ehcache.xml
* 
