package vehicleSystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import vehicleSystem.dao.DataBaseOperations;
import vehicleSystem.models.Vehicle;
@Component
@Scope("prototype")
public class PlaneController implements VehicleController {
    private DataBaseOperations dataBaseOperations;
    @Autowired
    public void setDataBaseOperations(DataBaseOperations dataBaseOperations) {
        this.dataBaseOperations = dataBaseOperations;
    }

    @Override
    public void saveToDatabase(Vehicle vehicle) {
        dataBaseOperations.save(vehicle);
        System.out.println("Vehicle of type "+vehicle+" has been saved..");
    }


}
