package com.BeanScope;

public class Circle implements Shape{
    private double h;
    private DrawShape2d draw2d;
    //constructor injection
    public Circle(DrawShape2d draw2d) {
        this.draw2d = draw2d;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    @Override
    public double drawShape(double h) {
        draw2d.draw();
        return Math.PI*h*h;
    }
}
