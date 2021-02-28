package com.example.domainmodel.strategy;

import com.example.domainmodel.model.Contract;

public class CompleteRecognitionStrategy implements RecognitionStrategy {

    @Override
    public void calculateRevenueRecognitions(Contract contract) {
        contract.addRevenueRecognition(
                contract.getRevenue(), contract.getDateSigned());
    }

}
