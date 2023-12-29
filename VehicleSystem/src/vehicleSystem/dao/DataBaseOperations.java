package vehicleSystem.dao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vehicleSystem.models.Orders;
import vehicleSystem.models.Type;
import vehicleSystem.models.Vehicle;

import java.sql.*;
import java.util.ArrayList;

@Component
@Scope("singleton")
@Repository
@Setter
@Getter
public class DataBaseOperations {
    @Value("${database.url}")
    private String url;

    @Value("${database.username}")
    private String username;

    @Value("${database.password}")
    private String password;

    @Autowired
    ApplicationContext context;

    private Connection con = null;
    private Statement statement;
    private PreparedStatement preparedStatement;
    @PostConstruct
    public void connectToDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con =DriverManager.getConnection(url,username,password);
            System.out.println("---------------connecting to database with properties------------------");
            System.out.println(url + " " + username + " " + password);
            System.out.println("the connection to DB is established ......");
        }catch(Exception e){ System.out.println(e);}
    }
    @PreDestroy
    public void disconnectFromDB(){

        try {
            System.out.println("the connection to DB is ended ......");
            System.out.println("Bye!!!");
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    @Transactional
    public Vehicle save(Vehicle vehicle) {
        try {
            String INSERT_QUERY = "INSERT INTO vehicle (brand,type) "+"VALUES(?,?)";
            preparedStatement = con.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, vehicle.getBrand());
            preparedStatement.setString(2, vehicle.getType().toString());

            int rowAffected = preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println(vehicle.getType()+" has been saved successfully !!");
            return vehicle;
   }
   public void getVehicleById(Vehicle vehicle){
       try {
           String SELECT_QUERY = "SELECT * FROM vehicle WHERE id ='"+vehicle.getId()+"'";
           preparedStatement = con.prepareStatement(SELECT_QUERY);
           ResultSet result = preparedStatement.executeQuery(SELECT_QUERY);
           while(result.next()){
               int id = result.getInt("id");
               String brand = result.getString("brand");
               Type type = Type.valueOf(result.getString("type"));
               vehicle.setId(id);
               vehicle.setBrand(brand);
               vehicle.setType(type);
               System.out.println("ID: "+id+" brand: "+brand+" type "+type);
           }
       }catch (Exception e){
           System.out.println(e);
       }
   }
    @Transactional
    public int update(Vehicle vehicle){
        int affectedRows = 0;
        try{
            String UPDATE_QUERY = "UPDATE vehicle SET brand = ? WHERE id ='"+vehicle.getId()+"'";

            preparedStatement = con.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1,vehicle.getBrand());
            affectedRows = preparedStatement.executeUpdate();
            System.out.println("affectedRows "+affectedRows);

        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println(vehicle.getType()+" has been updated successfully !!");
        return affectedRows;
    }
    @Transactional
    public int delete(Vehicle vehicle){
        int affectedRows = 0;
        try{
            String DELETE_QUERY = "DELETE FROM vehicle WHERE id ='"+vehicle.getId()+"'";
            preparedStatement = con.prepareStatement(DELETE_QUERY);
            affectedRows = preparedStatement.executeUpdate();
            System.out.println("affectedRows "+affectedRows);
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println(vehicle.getType()+" has been deleted successfully !!");
        return affectedRows;
    }

    public ArrayList<Orders> getOrderDetails(){
        ArrayList<Orders> allOrders = new ArrayList<>();
        try {
            String SELECT_QUERY = "SELECT * FROM orders INNER JOIN vehicle ON vehicle.id= orders.vehicleId";
            preparedStatement = con.prepareStatement(SELECT_QUERY);
            ResultSet result = preparedStatement.executeQuery(SELECT_QUERY);

            while(result.next()){
                Orders myOrder = new Orders(result.getInt("id"),result.getString("orderDate"),result.getString("price"),result.getInt("vehicleId"));
                allOrders.add(myOrder);
                System.out.println("ID: "+myOrder.getId()+" OrderDate: "+myOrder.getOrderDate()+" price: "+myOrder.getPrice()+" VehicleID: "+myOrder.getVehicleId());
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return allOrders;
    }

}
