package com.annotations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ApplicationContext container = new ClassPathXmlApplicationContext("applicationContext.xml");

        Circle circle = container.getBean("circleBean",Circle.class);//use id of bean in the first parameter and type of circle or square that type shape (interface)
        Square square = container.getBean("square",Square.class);
        circle.setH(3);
        System.out.println("radiu is equal: "+circle.getH());
        System.out.println("Area of Circle is: "+circle.getArea(3));
        System.out.println("-----------------------");
        System.out.println("Area of Square is: "+square.getArea(3));
    }
}