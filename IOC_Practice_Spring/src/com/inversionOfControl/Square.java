package com.inversionOfControl;

public class Square implements Shape{
    private double length;
    public Square() {}
    public Square(double length) {
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public double getArea(double length){
        return length*length;
    }
}
