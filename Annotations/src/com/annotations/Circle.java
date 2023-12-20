package com.annotations;

import org.springframework.stereotype.Component;

@Component("circleBean")
public class Circle implements Shape{
    private double h;
    public Circle() {}
    public Circle(double h) {
        this.h = h;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    @Override
    public double getArea(double h){
        return Math.PI*h*h;
    }
}
