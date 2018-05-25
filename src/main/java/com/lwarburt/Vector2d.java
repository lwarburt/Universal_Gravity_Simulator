package com.lwarburt;

import java.lang.Math;

public class Vector2d{


    private double i;
    private double j;

    public Vector2d(double i, double j){
        this.i = i;
        this.j = j;
    }

    public Vector2d(double magnitude, float directionDegrees){
        this.j = magnitude * Math.sin((double) directionDegrees);
        this.i = magnitude * Math.cos((double) directionDegrees);
    }

    public void setAngle(double magnitude, float directionDegrees){
        this.j = magnitude * Math.sin((double) directionDegrees);
        this.i = magnitude * Math.cos((double) directionDegrees);
    }

    public void add(Vector2d addend){
        this.i += addend.i;
        this.j += addend.j;
    }

    public void scale(double scalar){
        this.i *= scalar;
        this.j *= scalar;
    }

    public double getDirection(){
         return Math.toDegrees(Math.atan2(this.j, this.i));
    }

    public double getI() {
        return i;
    }

    public double getJ() {
        return j;
    }

    public void setI(double i) {
        this.i = i;
    }

    public void setJ(double j) {
        this.j = j;
    }

    @Override
    public String toString() {
        return this.i + ", " + this.j;
    }
}
