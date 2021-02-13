package com.example.designpatterns;

import com.example.designpatterns.model.adapter.*;
import com.example.designpatterns.model.facade.Facade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class DesignPatternsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesignPatternsApplication.class, args);

		adapterPatternTest();
		System.out.println("\n");
		facadePatternTest();
	}

	private static void adapterPatternTest() {
		// Adapter Pattern Test
		String testString = " Formatting line 1. Formatting line 2. Formatting line 3.";
		TextFormattable newLineFormatter = new NewLineFormatter();
		String resultString = newLineFormatter.formatText(testString);
		System.out.println(resultString);

		CsvFormattable csvFormatter = new CsvFormatter();
		TextFormattable csvAdapter = new CsvAdapterImpl(csvFormatter);
		String resultCsvString = csvAdapter.formatText(testString);
		System.out.println(resultCsvString);
	}

	private static void facadePatternTest() {
		// Facade Pattern Test
		Date date = new Date();

		// Generate Sale PDF report and Logistics CSV report using Facade
		Facade.generateReport(Facade.Types.SALE, Facade.ReportTypes.PDF, date);
		Facade.generateReport(Facade.Types.LOGISTIC, Facade.ReportTypes.CSV, date);
	}

}
