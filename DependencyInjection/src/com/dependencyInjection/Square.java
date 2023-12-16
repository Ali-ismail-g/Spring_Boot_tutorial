package com.dependencyInjection;

public class Square implements Shape{
    private double l;
    private DrawShape3d draw3d;

    public DrawShape3d getDraw3d() {
        return draw3d;
    }
    //setter injection
    public void setDraw3d(DrawShape3d draw3d) {
        this.draw3d = draw3d;
    }

    public double getL() {
        return l;
    }

    public void setL(double l) {
        this.l = l;
    }

    @Override
    public double drawShape(double l) {
        draw3d.draw();
        return l*l;
    }
}
