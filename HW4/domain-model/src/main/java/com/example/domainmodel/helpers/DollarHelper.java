package com.example.domainmodel.helpers;

import org.springframework.stereotype.Service;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import java.util.Locale;

@Service
public class DollarHelper {

    CurrencyUnit USD;

    public DollarHelper() {
        USD = Monetary.getCurrency("USD");
    }

    public MonetaryAmount dollars(Number number) {
        return Monetary.getDefaultAmountFactory()
                .setNumber(number)
                .setCurrency(USD)
                .create();
    }

    public String formatAmount(MonetaryAmount monetaryAmount) {
        MonetaryAmountFormat amountFormat =
                MonetaryFormats.getAmountFormat(
                        AmountFormatQueryBuilder
                                .of(Locale.US)
                                .set("pattern", "#0.00")
                                .build());
        return amountFormat.format(monetaryAmount);
    }
}
