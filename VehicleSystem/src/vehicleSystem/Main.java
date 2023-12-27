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
        car.setBrand("BMW");
        car.setType(Type.Car);
        car.setId(1);
        CarController carController = context.getBean("carController", vehicleSystem.controller.CarController.class);
        System.out.println("carController obj "+carController);
        carController.saveToDatabase(car);

        System.out.println("---------plane bean -------------");
        Plane plane = context.getBean("plane", vehicleSystem.models.Plane.class);
        plane.setBrand("F16");
        plane.setType(Type.Plane);
        plane.setId(1);
        PlaneController planeController = context.getBean("planeController", vehicleSystem.controller.PlaneController.class);
        System.out.println("planeController obj "+planeController);
        planeController.saveToDatabase(plane);

        System.out.println("---------bike bean -------------");
        Bike bike = context.getBean("bike", vehicleSystem.models.Bike.class);
        bike.setBrand("Jaguar");
        bike.setType(Type.Bike);
        bike.setId(1);
        BikeController bikeController = context.getBean("bikeController", vehicleSystem.controller.BikeController.class);
        BikeController bikeController2 = context.getBean("bikeController", vehicleSystem.controller.BikeController.class);
        System.out.println("bikeController obj "+bikeController);
        System.out.println("bikeController obj2 "+bikeController2);
        bikeController.saveToDatabase(bike);


        //((AnnotationConfigApplicationContext)context).close();
        context.close();
    }
}