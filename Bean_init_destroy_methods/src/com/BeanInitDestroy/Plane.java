package com.BeanInitDestroy;

import com.BeanInitDestroy.config.DatabaseOperations;

public class Plane implements Vehicle{
    public String brand;
    public DatabaseOperations databaseOperations;

    public DatabaseOperations getDatabaseOperations() {
        return databaseOperations;
    }
    //setter injection
    public void setDatabaseOperations(DatabaseOperations databaseOperations) {
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
