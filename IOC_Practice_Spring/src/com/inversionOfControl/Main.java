package com.inversionOfControl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Shift+F9 to start debugging your code. We have set one breakpoint
        //Container definition
        ApplicationContext container = new ClassPathXmlApplicationContext("applicationContext.xml");

        //retrieve beans from container
        //Shape shape = new Circle(); -> this was the old way before using spring with libs

        Shape circle = container.getBean("circle",Shape.class);//use id of bean in the first parameter and type of circle or square that type shape (interface)
        Shape square = container.getBean("square",Shape.class);
        System.out.println("Area of Circle is: "+circle.getArea(3));
        System.out.println("-----------------------");
        System.out.println("Area of Square is: "+square.getArea(3));
    }
}