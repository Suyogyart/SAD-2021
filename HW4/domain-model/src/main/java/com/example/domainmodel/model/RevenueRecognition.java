package com.example.domainmodel.model;

import lombok.Getter;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Getter
@Entity
public class RevenueRecognition {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="recognizedOn", nullable=false)
    private LocalDate recognizedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contract contract;

    @Column(name="amount", precision=10, scale=2)
    private BigDecimal amount_;

    @Transient
    private MonetaryAmount amount;

    @Transient
    private String currencyCode = Contract.CURRENCY.getCurrencyCode();

    public RevenueRecognition(
            Contract contract, LocalDate recognizedOn, MonetaryAmount amount) {
        if (contract == null) {
            throw new IllegalArgumentException("Contract cannot be null");
        }
        if (recognizedOn == null) {
            throw new IllegalArgumentException("Recognized date cannot be null");
        }
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        this.contract = contract;
        this.recognizedOn = recognizedOn;
        this.amount = amount;
    }

    public boolean isRecognizableBy(LocalDate asOf) {
        LocalDate recognizedOn = getRecognizedOn();
        return asOf.isAfter(recognizedOn)
                || asOf.isEqual(recognizedOn);
    }

    @PrePersist
    protected void onPrePersist() {
        currencyCode = amount.getCurrency().getCurrencyCode();
        amount_ = amount.getNumber()
                .numberValue(BigDecimal.class)
                .setScale(amount.getCurrency().getDefaultFractionDigits(),
                        RoundingMode.HALF_EVEN);
    }

    @PostLoad
    protected void onPostLoad() {
        this.amount =
                Monetary.getDefaultAmountFactory()
                        .setNumber(amount_)
                        .setCurrency(currencyCode)
                        .create();
    }

    protected RevenueRecognition() { /* as needed by JPA/ORM */ }

}