package com.BeanInitDestroy.config;

import com.BeanInitDestroy.Vehicle;

public class DatabaseOperations {
    private String url;
    private String username;
    private String password;

    public DatabaseOperations() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void connectToDB(){
        System.out.println("the connection to DB is established ......");
        System.out.println("calling from connectToDB init-method .....");
    }
    public void disconnectFromDB(){
        System.out.println("the connection to DB is ended ......");
        System.out.println("Bye!!!");
    }
    public void save(Vehicle vehicle){
        System.out.println("---------------connecting to database with properties------------------");
        System.out.println(url + " " + username + " " + password);
        System.out.println("--------------%s saved to database------------------\n"+vehicle.getBrand());
    }
}
