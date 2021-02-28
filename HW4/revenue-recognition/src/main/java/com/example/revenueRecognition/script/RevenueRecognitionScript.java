package com.example.revenueRecognition.script;

import org.springframework.format.number.money.MonetaryAmountFormatter;

import javax.money.MonetaryAmount;
import java.time.LocalDate;

public interface RevenueRecognitionScript {
    MonetaryAmount recognizedRevenue(int contractId, LocalDate asOf);

    void calculateRevenueRecognitions(int contractId);

    int insertContractInformation(int productId, MonetaryAmount contractPrice, LocalDate dateSigned);

    int insertProductInformation(String name, String type);
}
