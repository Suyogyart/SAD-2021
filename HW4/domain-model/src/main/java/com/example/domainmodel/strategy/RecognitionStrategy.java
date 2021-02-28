package com.example.domainmodel.strategy;

import com.example.domainmodel.model.Contract;

public interface RecognitionStrategy {
    void calculateRevenueRecognitions(Contract contract);
}
