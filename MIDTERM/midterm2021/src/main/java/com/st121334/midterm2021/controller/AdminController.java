package com.st121334.midterm2021.controller;

import com.st121334.midterm2021.helper.LocalDateConverter;
import com.st121334.midterm2021.model.*;
import com.st121334.midterm2021.service.AddressService;
import com.st121334.midterm2021.service.EmployeeService;
import com.st121334.midterm2021.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    AddressService addressService;

    LocalDateConverter converter = new LocalDateConverter();

    @GetMapping
    public ModelAndView admin_home() {
        List<User> allUsers = userService.findAllUsers();
        ModelAndView mv = new ModelAndView("admin_index.jsp");
        mv.addObject("users", allUsers);
        return mv;
    }

    @GetMapping("/register")
    public ModelAndView showRegisterForm() {
        Map<String, Object> map = new HashMap<>();
        map.put("levels", Level.values());
        map.put("roles", UserRoleType.values());
        ModelAndView mv = new ModelAndView("register_employee.jsp", "data", map);
        return mv;
    }

    @Transactional
    @PostMapping("/register")
    public ModelAndView register(User user, Address address, Employee employee) {

        //System.out.println(user.getUsername() + "has been Registered");

        //System.out.println(address.getCity());

        userService.save(user, address, employee);

        ModelAndView mv = new ModelAndView("redirect:/admin");
        return mv;
    }

    @GetMapping("/edit/{userId}")
    public ModelAndView showEditForm(@PathVariable("userId") int userId) {
        User user = userService.findById(userId);
        Employee employee = user.getEmp();

        Map<String, Object> map = new HashMap<>();
        map.put("employee", employee);
        map.put("levels", Level.values());

        ModelAndView mv = new ModelAndView("edit_employee.jsp", "data", map);
        return mv;
    }

    @Transactional
    @PostMapping("/edit")
    public ModelAndView editEmployee(Employee emp) {

        User user = userService.findById(emp.getId());

        Employee employee = user.getEmp();
        employee.setName(emp.getName());
        employee.setLevel(emp.getLevel());
        employee.setBaseSalary_(emp.getBaseSalary_());

        //User user = employee.getUser();
        user.setEmp(employee);
        //employeeService.save(emp);
        userService.save(user);

        return new ModelAndView("redirect:/admin");
    }

    @PostMapping("/delete/{userId}")
    public ModelAndView delete(@PathVariable("userId") int userId, Principal principal) {

        User user = userService.findById(userId);
        if ((user != null) && (!user.getUsername().equals(principal.getName()))) {
            userService.delete(user);
        }
        return new ModelAndView("redirect:/admin");
    }

    @GetMapping("/calculateSalary")
    public String calculateSalary() {
        return "calculate_salary.jsp";
    }

}
