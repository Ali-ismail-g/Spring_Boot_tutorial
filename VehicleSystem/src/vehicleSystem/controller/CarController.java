package vehicleSystem.controller;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vehicleSystem.dao.DataBaseOperations;
import vehicleSystem.models.Car;
import vehicleSystem.models.Orders;
import vehicleSystem.models.Vehicle;

import java.util.ArrayList;

@Component
@Scope("prototype")
public class CarController implements VehicleController{
    public DataBaseOperations dataBaseOperations;
    @Autowired
    public CarController(DataBaseOperations dataBaseOperations) {
        this.dataBaseOperations = dataBaseOperations;
    }

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
