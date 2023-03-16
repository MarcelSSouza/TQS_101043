package com.carservice.carservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CarController {
	@Autowired
	CarRepository carRepository;

	@GetMapping("/getAllCars")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = new ArrayList<Car>();
        carRepository.findAll().forEach(cars::add);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
    
         

	@GetMapping("/getCarById/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") Long id) {
        Optional<Car> carData = carRepository.findById(id);
        if (carData.isPresent()) {
            return new ResponseEntity<>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createCar")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        try {
            Car _car = carRepository.save(new Car(car.getMaker(), car.getModel()));
            return new ResponseEntity<>(_car, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
