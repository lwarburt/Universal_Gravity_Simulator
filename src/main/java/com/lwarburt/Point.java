package com.lwarburt;

public class Point {
    private double x;
    private double y;
    private double mag;

    public Point(double x, double y, double mag) {
        this.x = x;
        this.y = y;
        this.mag = mag;
    }

    public void set(double x, double y, double mag) {
        this.x = x;
        this.y = y;
        this.mag = mag;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getMag() {
        return mag;
    }

    public void setMag(double mag) {
        this.mag = mag;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ") Mag:" + mag;
    }
}
