package com.example.HW5.controller;

import com.example.HW5.model.Car;
import com.example.HW5.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@RestController
@RequestMapping("api/car")
public class CarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarService carService;

    @RequestMapping(method = RequestMethod.POST,
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity uploadFile(@RequestParam(value = "files")MultipartFile[] files) {

        try {
            for (final MultipartFile file : files) {
                carService.saveCars(file.getInputStream());
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @RequestMapping(method = RequestMethod.GET,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    //@ResponseBody
    public ResponseEntity getAllCars() throws InterruptedException {

        CompletableFuture<List<Car>> cars1 = carService.getAllCars();
        CompletableFuture<List<Car>> cars2 = carService.getAllCars();
        CompletableFuture<List<Car>> cars3 = carService.getAllCars();
        CompletableFuture.allOf(cars1, cars2, cars3).join();
        return ResponseEntity.status(HttpStatus.OK).build();

//        return carService.getAllCars().<ResponseEntity>thenApply(ResponseEntity::ok)
//                .exceptionally(handleGetCarFailure);
    }

    private static Function<Throwable, ? extends ResponseEntity<List<Car>>> handleGetCarFailure = throwable ->  {
        LOGGER.error("Failed to read records: {}", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };

}
