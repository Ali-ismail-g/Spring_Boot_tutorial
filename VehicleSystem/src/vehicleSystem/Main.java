package vehicleSystem;

import vehicleSystem.config.Config;
import vehicleSystem.controller.BikeController;
import vehicleSystem.controller.CarController;
import vehicleSystem.controller.PlaneController;
import vehicleSystem.models.Bike;
import vehicleSystem.models.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vehicleSystem.models.Plane;
import vehicleSystem.models.Type;


public class Main {
    public static void main(String[] args) {
        //ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        System.out.println("---------car bean -------------");
        Car car = context.getBean("car", vehicleSystem.models.Car.class);
        car.setBrand("AUDI");
        car.setType(Type.Car);
        car.setId(2);
        CarController carController = context.getBean("carController", vehicleSystem.controller.CarController.class);
        //carController.saveVehicle(car);
        //carController.updateVehicle(car);
        //carController.deleteVehicle(car);
        carController.getVehicleById(car);
        //carController.getOrdersDetails();

        System.out.println("---------plane bean -------------");
        Plane plane = context.getBean("plane", vehicleSystem.models.Plane.class);
        //plane.setType(Type.Plane);
        //plane.setBrand("J21");
        plane.setId(6);
        PlaneController planeController = context.getBean("planeController", vehicleSystem.controller.PlaneController.class);
        //planeController.saveVehicle(plane);
        planeController.getVehicleById(plane);
        //planeController.updateVehicle(plane);
        //planeController.deleteVehicle(plane);


        System.out.println("---------bike bean -------------");
        Bike bike = context.getBean("bike", vehicleSystem.models.Bike.class);
        //bike.setType(Type.Bike);
        //bike.setBrand("chinese");
        bike.setId(7);
        BikeController bikeController = context.getBean("bikeController", vehicleSystem.controller.BikeController.class);
        //bikeController.saveVehicle(bike);
        bikeController.getVehicleById(bike);
        //bikeController.updateVehicle(bike);
        //bikeController.deleteVehicle(bike);




        //((AnnotationConfigApplicationContext)context).close();
        context.close();
    }
}