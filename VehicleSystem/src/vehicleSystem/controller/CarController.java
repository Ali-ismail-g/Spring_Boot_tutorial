package vehicleSystem.controller;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vehicleSystem.dao.DataBaseOperations;
import vehicleSystem.models.Car;
import vehicleSystem.models.Vehicle;
@Component
@Scope("prototype")
public class CarController implements VehicleController{
    public DataBaseOperations dataBaseOperations;
    @Autowired
    public CarController(DataBaseOperations dataBaseOperations) {
        this.dataBaseOperations = dataBaseOperations;
    }

    @Override
    public void saveToDatabase(Vehicle vehicle) {
        dataBaseOperations.save(vehicle);
        System.out.printf("Vehicle of type %s has been saved.. \n",vehicle );
    }


}
