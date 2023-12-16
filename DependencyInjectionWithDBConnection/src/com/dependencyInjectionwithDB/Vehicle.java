package com.dependencyInjectionwithDB;

public interface Vehicle {
    public void saveToDatabase(Vehicle vehicle);
    public String getBrand();
}
