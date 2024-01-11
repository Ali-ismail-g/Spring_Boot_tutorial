package com.registerApp.registerForm.dao;

import com.registerApp.registerForm.model.EmployeeModel;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

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
    private Statement statement;
    private PreparedStatement preparedStatement;

    @PostConstruct
    public void connectToDB(){
        try{
           // Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,username,password);
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
    public EmployeeModel save(EmployeeModel employee) {
        int rowAffected = 0;
        System.out.println("employee bj from database: "+ employee.getUsername());
        try {
            String INSERT_QUERY = "INSERT INTO employee (username,email,password,confirmPassword) VALUES(?,?,?,?)";
            preparedStatement = con.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, employee.getUsername());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setString(3, employee.getPassword());
            preparedStatement.setString(4, employee.getConfirmPassword());
            rowAffected = preparedStatement.executeUpdate();
            System.out.println("affected rows: "+rowAffected);
        }catch (Exception e){
            System.out.println(e);
        }
        if (rowAffected > 0)
            System.out.println(employee.getUsername()+" has been saved successfully !!");
        return employee;
    }
}
