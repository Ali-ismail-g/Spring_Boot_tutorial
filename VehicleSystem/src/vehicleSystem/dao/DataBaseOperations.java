package vehicleSystem.dao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vehicleSystem.models.Vehicle;

import java.sql.Connection;
import java.sql.DriverManager;

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

    private Connection con = null;
    @PostConstruct
    public void connectToDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
          con  =DriverManager.getConnection(
                    url,username,password);
            System.out.println("the connection to DB is established ......");
            System.out.println("calling from connectToDB init-method .....");

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
    public Vehicle save(Vehicle vehicle){
        System.out.println("---------------connecting to database with properties------------------");
        System.out.println(url + " " + username + " " + password);
        return vehicle;
   }
}
