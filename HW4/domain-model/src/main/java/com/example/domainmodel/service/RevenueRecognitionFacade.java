package com.example.domainmodel.service;

import javax.money.MonetaryAmount;
import java.time.LocalDate;

public interface RevenueRecognitionFacade {
    MonetaryAmount recognizedRevenue(int contractId, LocalDate asOf);

    void calculateRevenueRecognitions(int contractId);

    int insertContractInformation(
            int productId, MonetaryAmount contractPrice, LocalDate dateSigned);

    int insertProductInformation(String name, String type);
}
