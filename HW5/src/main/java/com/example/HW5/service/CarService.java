package com.example.HW5.service;

import com.example.HW5.model.Car;
import org.springframework.scheduling.annotation.Async;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CarService {

    @Async
    CompletableFuture<List<Car>> saveCars(InputStream inputStream) throws Exception;

    @Async
    CompletableFuture<List<Car>> getAllCars() throws InterruptedException;
}
