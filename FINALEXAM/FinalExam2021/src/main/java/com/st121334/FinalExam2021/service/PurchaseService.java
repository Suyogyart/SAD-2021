package com.st121334.FinalExam2021.service;

import com.st121334.FinalExam2021.model.Product;
import com.st121334.FinalExam2021.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PurchaseService {

    @Autowired
    ProductRepo productRepo;


}
