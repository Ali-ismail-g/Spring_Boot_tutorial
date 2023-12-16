package com.dependencyInjection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Shift+F9 to start debugging your code. We have set one breakpoint
        ApplicationContext container = new ClassPathXmlApplicationContext("applicationContext.xml");
        Shape circle = container.getBean("circle",Shape.class);
        Shape square = container.getBean("square",Shape.class);
        System.out.println("drawing circle in 2d with area: "+circle.drawShape(3.0));
        System.out.println("------------------------");
        System.out.println("drawing square in 2d with area: "+square.drawShape(3.0));
    }
}