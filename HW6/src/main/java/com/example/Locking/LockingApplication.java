package com.example.Locking;

import com.example.Locking.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootApplication
public class LockingApplication {

	static HttpClient client = HttpClient.newHttpClient();
	static ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(LockingApplication.class, args);

		// POST
		insert("Microsoft Powerpppointt", 499);

		// GET
		String jsonPowerPoint = get(1);
		Product pro = mapper.readValue(jsonPowerPoint, Product.class);
		pro.setName("Powerpppoint");

		String jsonPowerPoint2 = get(1);
		Product pro2 = mapper.readValue(jsonPowerPoint2, Product.class);
		pro2.setName("Powerpoint");

		// PUT (the version will increment automatically)
		update(convertObjectToJSON(pro));

		// PUT (this will raise an error because the version is different)
		update(convertObjectToJSON(pro2));

	}


	// Send Request
	private static String sendRequest(HttpRequest request) throws IOException, InterruptedException {
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}

	// Method to Map object to JSON
	private static String makeJSON(String name, int price) {

		return "{" +
				"\"name\":\"" + name + "\"," +
				"\"price\": \"" + price + "\"" +
				"}";
	}

	// Method to create JSON from Java Object
	private static String convertObjectToJSON(Product product) throws JsonProcessingException {
		return mapper.writeValueAsString(product);
	}


	// Method to invoke (GET)
	private static String get(int id) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8080/products/" + id))
				.setHeader("User-Agent", "Java 11 HttpClient SRT Bot")
				.build();

		String requestStr = sendRequest(request);

		System.out.println("Get request: " + requestStr);
		return requestStr;
	}


	// Method for Insert (POST)
	private static void insert(String name, int price) throws IOException, InterruptedException {
		// JSON formatted data
		String json = makeJSON(name, price);

		HttpRequest request = HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(json))
				.uri(URI.create("http://localhost:8080/products"))
				.setHeader("User-Agent", "Java 11 HttpClient SRT Bot")
				.header("Content-Type", "application/json")
				.build();
		System.out.println("Inserted: " + sendRequest(request));
	}


	// Method for Update (PUT)
	private static void update(String jsonString) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.PUT(HttpRequest.BodyPublishers.ofString(jsonString))
				.uri(URI.create("http://localhost:8080/products/"))
				.setHeader("User-Agent", "Java 11 HttpClient SRT Bot")
				.header("Content-Type", "application/json")
				.build();
		System.out.println("Put Request " + sendRequest(request));
	}

}
