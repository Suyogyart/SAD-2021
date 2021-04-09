package com.example.Locking.controller;

import com.example.Locking.model.Product;
import com.example.Locking.repo.ProductRepo;
import com.example.Locking.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product findOne(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PutMapping
    public Product put(@RequestBody Product product) {
        return productService.save(product);
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

}
