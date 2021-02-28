package com.example.revenueRecognition.controller;

import com.example.revenueRecognition.script.RevenueRecognitionScript;
import com.example.revenueRecognition.tableDataGateway.ContractTableDataGateway;
import com.example.revenueRecognition.tableDataGateway.ProductTableDataGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    RevenueRecognitionScript script;

    @Autowired
    ProductTableDataGateway productGateway;

    @Autowired
    ContractTableDataGateway contractGateway;

    @RequestMapping(path = "/")
    public ModelAndView home() {
        List<Object> products = new ArrayList<>();
        ResultSet rs = productGateway.findAll();

        try {
            while (rs.next()) {
                Object[] arr = new Object[3];

                arr[0] = rs.getInt("id");
                arr[1] = rs.getString("name");
                arr[2] = rs.getString("type");
                products.add(arr);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Map<String, Object> allObjectsMap = new HashMap<String, Object>();
        allObjectsMap.put("products", products);

        ModelAndView mv = new ModelAndView();
        mv.addAllObjects(allObjectsMap);

        mv.setViewName("home.jsp");
        return mv;
    }

    @RequestMapping(path = "/check")
    public ModelAndView calculateRevenueRecognition() {
        List<Object> contracts = new ArrayList<>();
        ResultSet rs = contractGateway.findAll();

        try {
            while (rs.next()) {
                Object[] arr = new Object[4];

                arr[0] = rs.getInt("id");
                arr[1] = rs.getInt("product_id");
                arr[2] = rs.getString("revenue");
                arr[3] = rs.getString("datesigned");
                contracts.add(arr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Map<String, Object> allObjectsMap = new HashMap<String, Object>();
        allObjectsMap.put("contracts", contracts);

        ModelAndView mv = new ModelAndView();
        mv.addAllObjects(allObjectsMap);

        mv.setViewName("checkrr.jsp");
        return mv;
    }
}
