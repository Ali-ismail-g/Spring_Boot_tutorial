package vehicleSystem.controller;

import vehicleSystem.models.Orders;
import vehicleSystem.models.Vehicle;

import java.util.ArrayList;

public interface VehicleController {

    public void saveVehicle(Vehicle vehicle);
    public void getVehicleById(Vehicle vehicle);
    public void updateVehicle(Vehicle vehicle);
    public void deleteVehicle(Vehicle vehicle);

    public ArrayList<Orders> getOrdersDetails();

}
