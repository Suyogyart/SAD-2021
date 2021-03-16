package com.example.HW5.service;

import com.example.HW5.model.Car;
import com.example.HW5.repo.CarRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CarServiceImpl implements CarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    CarRepo carRepo;

    @Override
    @Async
    public CompletableFuture<List<Car>> saveCars(final InputStream inputStream) throws Exception {
        final long start = System.currentTimeMillis();
        List<Car> cars = parseCSVFile(inputStream);
        LOGGER.info("Saving a list of cars of size {} records", cars.size());
        cars = carRepo.saveAll(cars);
        LOGGER.info("Elapsed time: {}", (System.currentTimeMillis() - start));
        return CompletableFuture.completedFuture(cars);
    }

    // accepts a multlipart file as inputstream,
    // parses it
    // and stores the data in database
    private List<Car> parseCSVFile(final InputStream inputStream) throws Exception {
        final List<Car> cars = new ArrayList<>();
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line=br.readLine()) != null) {
                final String[] data = line.split(";");
                final Car car = new Car(data[0], data[1], data[2]);
                cars.add(car);
            }
            return cars;
        } catch (final IOException e) {
            LOGGER.error("Failed to parse CSV file {}", e);
            throw new Exception("Failed to parse CSV file {}", e);
        }


    }

    // Reads the data from the database
    @Override
    @Async
    public CompletableFuture<List<Car>> getAllCars() throws InterruptedException {
        LOGGER.info("Request to get a list of cars");
        final List<Car> cars = carRepo.findAll();
        Thread.sleep(10000L);
        return CompletableFuture.completedFuture(cars);
    }
}
