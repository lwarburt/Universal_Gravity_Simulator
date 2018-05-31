package com.lwarburt;

public class Body {
    protected String name;
    protected double mass;
    protected double radius;
    protected float r;
    protected float g;
    protected float b;

    public Body(String name, double mass, double radius, float r, float g, float b) {
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public double getWindow() {

        return radius * 10;
    }

    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return ("The planet is " + name + ", with a mass of: " + mass + " and a radius of: " + radius);
    }

    public float getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getB() {
        return b;
    }

}



