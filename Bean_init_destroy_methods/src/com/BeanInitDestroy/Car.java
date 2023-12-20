package com.BeanInitDestroy;

import com.BeanInitDestroy.config.DatabaseOperations;

public class Car implements Vehicle{
    public String brand;
    public DatabaseOperations databaseOperations;
    //constructor injection
    public Car(DatabaseOperations databaseOperations) {
        this.databaseOperations = databaseOperations;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public void saveToDatabase(Vehicle vehicle) {
        databaseOperations.save(vehicle);
    }

    @Override
    public String getBrand() {
        return "brand of the car is "+ brand;
    }
}
