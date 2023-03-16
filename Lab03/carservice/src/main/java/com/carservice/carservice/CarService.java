package com.carservice.carservice;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CarService {
    private CarRepository carRepository;

    public Car save (Car car) {
        carRepository.save(car);
        return car;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarDetails(Long id) {
        return carRepository.findById(id);
    }

    

}
