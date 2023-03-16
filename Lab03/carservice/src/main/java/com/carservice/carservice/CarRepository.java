package com.carservice.carservice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByCarId(Long carId);
    List<Car> findAll();
}
