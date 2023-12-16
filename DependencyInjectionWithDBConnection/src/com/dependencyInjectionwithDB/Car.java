package com.dependencyInjectionwithDB;

import com.dependencyInjectionwithDB.config.DatababseOperations;

public class Car implements Vehicle{
    public String brand;
    public DatababseOperations datababseOperations;
    //constructor injection
    public Car(DatababseOperations datababseOperations) {
        this.datababseOperations = datababseOperations;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public void saveToDatabase(Vehicle vehicle) {
        datababseOperations.save(vehicle);
    }

    @Override
    public String getBrand() {
        return "brand of the car is "+ brand;
    }
}
