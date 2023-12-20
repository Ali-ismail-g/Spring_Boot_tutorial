package com.BeanScope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ApplicationContext container = new ClassPathXmlApplicationContext("applicationContext.xml");
        Shape circle = container.getBean("circle",Shape.class);
        Shape circle2 = container.getBean("circle",Shape.class);
        Shape square = container.getBean("square",Shape.class);
        Shape square2 = container.getBean("square",Shape.class);
        System.out.println("drawing circle in 2d with area: "+circle.drawShape(3.0)+" "+circle);
        System.out.println("drawing circle in 2d with area: "+circle2.drawShape(3.0)+" "+circle2);
        System.out.println("------------------------");
        System.out.println("drawing square in 3d with area: "+square.drawShape(3.0)+" "+square);
        System.out.println("drawing square in 3d with area: "+square2.drawShape(3.0)+" "+square2);
    }
}