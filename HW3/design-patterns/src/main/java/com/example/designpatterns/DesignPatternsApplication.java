package com.example.designpatterns;

import com.example.designpatterns.model.adapter.*;
import com.example.designpatterns.model.facade.Facade;
import com.example.designpatterns.model.observer.Observer;
import com.example.designpatterns.model.observer.Topic;
import com.example.designpatterns.model.observer.TopicSubscriber;
import com.example.designpatterns.model.state.Swordman;
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
		System.out.println("\n");
		statePatternTest();
		System.out.println("\n");
		observerPatternTest();
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

	private static void statePatternTest() {
		// State Pattern Test
		Swordman sm = new Swordman();
		System.out.println("Character's initial state: ");
		sm.printStates();

		sm.increaseAttack(4);
		sm.printStates();

		sm.speedUp(3);
		sm.printStates();

		sm.increaseDefense(1);
		sm.printStates();

		sm.speedUp(2);
		sm.printStates();

		System.out.println("Character's present final state: ");
		sm.printStates();
	}

	private static void observerPatternTest() {
		// create subject
		Topic politics = new Topic();
		Topic sports = new Topic();

		// create observers
		Observer ob1 = new TopicSubscriber("Political CNN");
		Observer ob2 = new TopicSubscriber("General BBC");
		Observer ob3 = new TopicSubscriber("Sports CNN");

		// register observers to the subject
		politics.register(ob1);
		politics.register(ob2);
		sports.register(ob3);

		// attach observer to subject;
		ob1.setSubject(politics);
		ob2.setSubject(politics);
		ob3.setSubject(sports);

		// check if any update is available
		ob1.update();

		// now send message to subject
		politics.postMessage("Prime Minister election!");
		sports.postMessage("Manchester City Wins");
	}

}
