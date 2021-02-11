## Lab 2: Spring Security (GUIDE)

### Create project with dependencies

### Configure Proxy Mail in Mail Trap and add configurations
```
Host: smtp.mailtrap.io
Port: 25 or 465 or 587 or 2525
Username: <your username>
Password: <your password>
Auth: PLAIN, LOGIN and CRAM-MD5
TLS: Optional (STARTTLS on all ports)
```


### Create Models
User and Roles with lots of annotations (Explain)
   
### Annotations

@Email
@Column
@Transient
@Email
@NotBlank

### Create DAOs
UserJPADao

RoleJPADao

### Add Data SQL

Spring Security Expects prefix "ROLE_"


### Notes
@Controller -> Singleton
@Component -> Singleton
@Service -> Singleton
@Entity -> Not Singleton


### Services
Userservice- Add people, find people
EmailService - Mail server
 Who uses com.example.Lab2_Guide.service?
CONTROLLERS

Instead of putting logic inside the controller, we organize them to com.example.Lab2_Guide.service

### User Controller
@Valid
To apply all the defaultvalidations we do
We need "BindingResult"

To add custom validators


@Principal
How do I know who is the current user in the current session initially
This object knows Principal

we can simply inject it


