package com.st121334.FinalExam2021.controller;

import com.st121334.FinalExam2021.model.Product;
import com.st121334.FinalExam2021.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class ProductController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @GetMapping
    public ModelAndView showIndexProductListing() {
        ModelAndView mv = new ModelAndView("index.jsp");

        List<Product> allProducts = productService.getAllProducts();

        mv.addObject("products", allProducts);


        return mv;
    }

    @GetMapping("/buy/{product_id}")
    public String buy(@PathVariable("product_id") int product_id,
                      @RequestParam(name = "quantity") String quantity){


        Product product = productService.findProductById(product_id);
        // UPDATE STOCK
        int newStock = product.getStock() - Integer.parseInt(quantity);
        LOGGER.info("NEW STOCK: " + newStock);

        productService.updateStock(product, newStock);

        return "redirect: /";
    }

}
