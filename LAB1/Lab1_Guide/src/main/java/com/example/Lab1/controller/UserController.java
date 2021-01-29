package com.example.Lab1.controller;

import com.example.Lab1.dao.UserDao;
import com.example.Lab1.dao.UserJPADao;
import com.example.Lab1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    UserDao dao;

    @Autowired
    UserJPADao jpaDao;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home() {
        return "home.jsp";
    }

    // Adding a user in traditional URL way
    @RequestMapping(path = "/addUser", method = RequestMethod.GET)
    public String addUser(User user) {
        dao.save(user);
        return "home.jsp";
    }

    // Getting User
    // To send data along with model data, we use ModelAndView
    @RequestMapping(path = "/getUser", method = RequestMethod.GET)
    public ModelAndView getUser(@RequestParam int uid) {
        ModelAndView mv = new ModelAndView("showUser.jsp");
        User user = dao.findById(uid).orElse(new User());
        mv.addObject(user);

        // Example how to use query defined at dao interface
        System.out.println(dao.findByNationality("Nepal"));
        System.out.println(dao.findByUidGreaterThan(101));
        System.out.println(dao.findByNationaltySortedByName("Nepal"));

        // This will render the view along with the object
        return mv;
    }

    // Getting All users using JPADao
    // To send data along with model data, we use ModelAndView
    @RequestMapping(path = "/showUsers", method = RequestMethod.GET)
    public ModelAndView getUsersJPA(Model model) {
        ModelAndView mv = new ModelAndView("showUsers.jsp");
        List<User> listOfUsers = jpaDao.findAll();
        mv.addObject(listOfUsers);

        System.out.println(listOfUsers);

        // This will render the view along with the object
        return mv;
    }

    // Implementing RESTful APIs
    // Getting all users using CrudRepository which returns java object
    // Java objects may not work well across all platforms
    @RequestMapping(path = "/users", method = RequestMethod.GET)
    @ResponseBody // This annotation is for REST methods where view will not be rendered
    public String getUsersREST() {
        return dao.findAll().toString();
    }

    // Getting all users using JPARepository which returns proper JSON or XML format
    // This one uses jackson library in maven dependencies to convert java object into json
    @RequestMapping(path = "/usersJPA", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<User> getUsersRESTJPA() {
        return jpaDao.findAll();
    }

    // Getting single user by ID using REST
    @RequestMapping(path = "/user/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public String getUserREST(@PathVariable("uid") int uid) {
        return dao.findById(uid).toString();
    }

    // Getting single user by ID using REST JPA
    @RequestMapping(path = "/userJPA/{uid}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Optional<User> getUserRESTJPA(@PathVariable("uid") int uid) {
        return jpaDao.findById(uid);
    }

    // Creating new user
    @RequestMapping(path = "/userJPA", method = RequestMethod.POST)
    @ResponseBody
    // Request Body is used so we can also send using raw format, aside from form format
    public User postUser(@RequestBody User user) {
        jpaDao.save(user);
        return user;
    }

    @RequestMapping(path = "/userJPA", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseBody
    public String saveOrUpdateUser(@RequestBody User user) {
        jpaDao.save(user);
        return "Updated";
    }

    @RequestMapping(path = "/userJPA/{uid}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteUser(@PathVariable("uid") int uid) {
        User user = jpaDao.getOne(uid);
        jpaDao.delete(user);
        return "Deleted User: " + user.getName();
    }

}
