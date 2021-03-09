package com.st121334.midterm2021.controller;

import com.st121334.midterm2021.model.Address;
import com.st121334.midterm2021.model.Employee;
import com.st121334.midterm2021.model.User;
import com.st121334.midterm2021.service.AddressService;
import com.st121334.midterm2021.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    @GetMapping
    public ModelAndView user_home() {
        List<User> allUsers = userService.findAllUsers();
        ModelAndView mv = new ModelAndView("user_index.jsp");
        mv.addObject("users", allUsers);
        return mv;
    }

    @GetMapping("/showAddress")
    public ModelAndView showAddress(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<Address> addresses = user.getEmp().getAddresses();

        ModelAndView mv = new ModelAndView("show_address.jsp");
        mv.addObject("user", user);
        mv.addObject("addresses", addresses);

        //System.out.println(user.getEmp().getAddresses());

        return mv;
    }

    @GetMapping("/addAddress")
    public ModelAndView showAddAddressForm() {
        return new ModelAndView("add_address.jsp");
    }

    @Transactional
    @PostMapping("/addAddress")
    public ModelAndView addAddress(Address address, Principal principal) {

        User user = userService.findByUsername(principal.getName());
        Employee emp = user.getEmp();
        // Save Address here //TODO: Implement here
        emp.addAddress(address);

        userService.save(user);

        return new ModelAndView("redirect:/user/showAddress");
    }

    @GetMapping("/editAddress/{addressId}")
    public String showEditAddressForm(@PathVariable("addressId") int id) {



        return "edit_address.jsp";
    }

    @PostMapping("/editAddress")
    public String editAddress(Address address) {
        return "edit_address.jsp";
    }

    @PostMapping("/deleteAddress/{addressId}")
    public String deleteAddress(@PathVariable("addressId") int addressId) {
        return "redirect:/user/showAddress";
    }

    @PostMapping("/showInfo")
    public ModelAndView showUserInfo() {


        return new ModelAndView("show_info.jsp");
    }

    @GetMapping("/editInfo/{userId}")
    public ModelAndView showEditInfoForm() {

        return new ModelAndView("edit_info.jsp");

    }

    @PostMapping("/editInfo")
    public ModelAndView editUserInfo() {

        return new ModelAndView("redirect:/user/show_info");
    }

}
