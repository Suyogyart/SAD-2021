package com.example.Lab1.controller;

import com.example.Lab1.dao.EmployeeDao;
import com.example.Lab1.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao dao;

    private static final String HOME_SCREEN = "home.jsp";
    private static final String ADD_EMPLOYEE_SCREEN = "addEmployee.jsp";
    private static final String EDIT_EMPLOYEE_SCREEN = "editEmployee.jsp";

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView(HOME_SCREEN);
        // Show all users list in homepage sorted by name
        List<Employee> employeeList = dao.getEmployeeSortedByName();
        mv.addObject("employeeList", employeeList);
        System.out.println("Listing Employees\n\n" + employeeList + "\n\n");
        return mv;
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ModelAndView list(Employee emp) {
        ModelAndView mv = new ModelAndView(HOME_SCREEN);
        // Show all users list in homepage sorted by name
        List<Employee> employeeList = dao.getEmployeeSortedByName();
        mv.addObject("employeeList", employeeList);
        System.out.println("Listing Employees\n\n" + employeeList + "\n\n");
        return mv;
    }

    // Add Employee Screen
    @RequestMapping(path="/add", method = RequestMethod.GET)
    public String showAddForm() {
        return ADD_EMPLOYEE_SCREEN;
    }

//    // Edit Employee Screen
//    @RequestMapping(path="/edit", method = RequestMethod.GET)
//    public String showEditForm() {
//        return EDIT_EMPLOYEE_SCREEN;
//    }

    // Edit Employee Screen
    @RequestMapping(path="/edit/{eid}", method = RequestMethod.GET)
    public ModelAndView showEditForm(@PathVariable("eid") int eid) {
        ModelAndView mv = new ModelAndView(EDIT_EMPLOYEE_SCREEN);
        Employee emp = dao.getOne(eid);
        mv.addObject("employee", emp);
        return mv;
    }

    // Add Employee to DB
    @RequestMapping(path="/employee", method = RequestMethod.POST)
    public ModelAndView addNewEmployee(Employee emp) {
        ModelAndView mv = new ModelAndView("addResult.jsp");
        dao.save(emp);
        System.out.println("Employee added. Showing employee details");

        mv.addObject("emp", emp);
        return mv;
    }

//    // Edit or Add Employee to DB
//    @RequestMapping(path="/employee", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    @ResponseBody
//    public String editOrAddEmployee(Employee emp) {
//       dao.save(emp);
//        System.out.println("Employee updated. Returning to Homepage");
//       return HOME_SCREEN;
//    }

    // Edit or Add Employee to DB
    @RequestMapping(path="/employee", method = RequestMethod.PUT)
    public ModelAndView editOrAddEmployee(Employee emp) {
        ModelAndView mv = new ModelAndView("editResult.jsp");
        dao.save(emp);

        System.out.println("Employee updated. Returning to Homepage");
        mv.addObject("emp", emp);
        return mv;
    }

    // Delete Employee from DB
    @RequestMapping(path="/employee/{eid}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("eid") int eid) {
        Employee emp = dao.getOne(eid);
        dao.delete(emp);
        return "Deleted Succesfully";
    }
}