package com.example.designpatterns.model.adapter;

public class CsvFormatter implements CsvFormattable {

    @Override
    public String formatCsvText(String text) {
        String formattedText = text.replace(".", ",");
        return formattedText;
    }
}
