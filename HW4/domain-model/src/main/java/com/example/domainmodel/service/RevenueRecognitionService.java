package com.example.domainmodel.service;

import javax.money.MonetaryAmount;
import java.time.LocalDate;

public interface RevenueRecognitionService {

    MonetaryAmount recognizedRevenue(int contractId, LocalDate asOf);

    void calculateRevenueRecognitions(int contractId);

}
