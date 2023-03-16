package com.carservice.carservice;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carId;
    @Column(name = "maker")
    private String maker;
    @Column(name = "model")
    private String model;

    public Car() {
    }

    public Car( String maker, String model) {
        this.maker = maker;
        this.model = model;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Long getCarId() {
        return carId;
    }

    public String getMaker() {
        return maker;
    }

    public String getModel() {
        return model;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car [carId=" + carId + ", maker=" + maker + ", model=" + model + "]";
    }
}
