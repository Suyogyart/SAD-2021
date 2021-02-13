package com.example.designpatterns.model.facade;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogisticsReportHelper {

    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static Connection getLogisticsDBConnection() {
        return null;
    }

    public void generatePDFReport(Date date) {
        System.out.println("Generating logistics report in PDF for " + dateFormatter.format(date));
    }

    public void generateCSVReport(Date date) {
        System.out.println("Generating logistics report in CSV format for " + dateFormatter.format(date));
    }
}
