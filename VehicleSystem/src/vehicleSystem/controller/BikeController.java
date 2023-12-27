package vehicleSystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import vehicleSystem.dao.DataBaseOperations;
import vehicleSystem.models.Vehicle;
@Component
@Scope("prototype")
public class BikeController implements VehicleController{

    @Autowired
    public DataBaseOperations dataBaseOperations;

    @Override
    public void saveToDatabase(Vehicle vehicle) {
        dataBaseOperations.save(vehicle);
        System.out.println("Vehicle of type "+vehicle+" has been saved..");
    }


}
