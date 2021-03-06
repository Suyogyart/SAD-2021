package com.example.Lab3.controller;

import com.example.Lab3.model.*;
import com.example.Lab3.repo.EmployeeRepo;
import com.example.Lab3.repo.LeaveRepo;
import com.example.Lab3.service.LeaveService;
import com.example.Lab3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepo empRepo;

    @Autowired
    LeaveService leaveService;

    @Autowired
    UserService userService;

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable("id") int id) {
        Employee emp = empRepo.findById(id).orElse(null);

        return emp;
    }

    @GetMapping("/leave")
    public ModelAndView showLeaveForm(ModelAndView mv, Principal principal) {
        List<LeaveType> leaveTypes = new ArrayList<>();
        leaveTypes.add(LeaveType.ANNUAL);
        leaveTypes.add(LeaveType.SICK);

        Employee currentEmployee = userService.findByUsername(principal.getName()).getEmp();
        List<Leave> leaves = currentEmployee.getLeaves();

        //ModelAndView mv = new ModelAndView("leave_form.jsp");
        // For display
        mv.addObject("leaves", leaves);


        // For form
        LocalDate dateNow = LocalDate.now();
        LocalDate maxSickLeaveDate = dateNow.plusDays(30);
        LocalDate maxAnnualLeaveDate = dateNow.plusDays(15);

        mv.addObject("leaveTypes", LeaveType.values());
        mv.addObject("minDate", dateNow.toString());
        mv.addObject("maxSickLeaveDate", maxSickLeaveDate.toString());
        mv.addObject("maxAnnualLeaveDate", maxAnnualLeaveDate.toString());
        mv.setViewName("leave_form.jsp");
        return mv;
    }

    @Transactional
    @PostMapping("/leave")
    public String applyLeave(Principal principal,
                           @RequestParam("leaveType") String leaveType,
                           @RequestParam("start") String start,
                           @RequestParam("end") String end,
                             @RequestParam("remarks") String remarks) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Employee employee = userService.findByUsername(principal.getName()).getEmp();
        LocalDate startDate = LocalDate.parse(start, formatter);
        LocalDate endDate = LocalDate.parse(end, formatter);
        LeaveType type = LeaveType.valueOf(leaveType);
        switch (type) {
            case SICK:
                SickLeave sl = new SickLeave(employee, false, remarks, startDate, endDate);
                leaveService.save(sl);
                break;

            case ANNUAL:
                AnnualLeave al = new AnnualLeave(employee, false, remarks, startDate, endDate);
                leaveService.save(al);
                break;
        }

        return "redirect:/employees/leave";
    }

    @Transactional
    @PostMapping("/leave/delete/{id}")
    public String deleteLeave(@PathVariable("id") int id) {
        leaveService.deleteById(id);

        return "redirect:/employees/leave";
    }

}
