package vehicleSystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import vehicleSystem.dao.DataBaseOperations;
import vehicleSystem.models.Orders;
import vehicleSystem.models.Vehicle;

import java.util.ArrayList;

@Component
@Scope("prototype")
public class BikeController implements VehicleController{

    @Autowired
    public DataBaseOperations dataBaseOperations;

    @Override
    public void saveVehicle(Vehicle vehicle) {
        dataBaseOperations.save(vehicle);
    }

    @Override
    public void getVehicleById(Vehicle vehicle) {
        dataBaseOperations.getVehicleById(vehicle);
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
        dataBaseOperations.update(vehicle);
    }
    @Override
    public void deleteVehicle(Vehicle vehicle) {
        dataBaseOperations.delete(vehicle);
    }

    @Override
    public ArrayList<Orders> getOrdersDetails() {
        ArrayList<Orders> orders = dataBaseOperations.getOrderDetails();
        return orders;
    }
}
