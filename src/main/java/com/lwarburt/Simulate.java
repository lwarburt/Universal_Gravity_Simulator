package com.lwarburt;

public class Simulate {
    private final double G = 6.67 * (Math.pow(10, -11));         //Universal Gravitational Constant
    private double bodyMass;
    private double deltaT;
    private Vector2d velocity;
    private double distance;

    private double xPos;
    private double yPos;

    public Simulate(Vector2d velocity, double bodyMass, double deltaT, double xPos, double yPos) {
        this.velocity = velocity;
        this.bodyMass = bodyMass;
        this.deltaT = deltaT;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Simulate(Vector2d velocity, double deltaT, double xPos, double yPos) {
        this.velocity = velocity;
        this.bodyMass = 5.972 * Math.pow(10, 24);       //Earth default
        this.deltaT = deltaT;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void set(Vector2d velocity, double deltaT, double xPos, double yPos) {
        this.velocity = velocity;
        this.bodyMass = 5.972 * Math.pow(10, 24);       //Earth default
        this.deltaT = deltaT;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void set(Vector2d velocity, double bodyMass, double deltaT, double xPos, double yPos) {
        this.velocity = velocity;
        this.bodyMass = bodyMass;
        this.deltaT = deltaT;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void simulate() {
        distance = Math.sqrt(Math.pow(xPos, 2) + Math.pow(yPos, 2));
        double force = (G * (bodyMass) / Math.pow(distance, 2));
        double angle = findCenter();
        Vector2d acceleration = new Vector2d(force, (float) angle);
        acceleration.scale(deltaT);
        velocity.add(acceleration);
        velocity.scale(deltaT);
        xPos = velocity.getI() + xPos;
        yPos = velocity.getJ() + yPos;
        velocity.scale(1 / deltaT);
    }

    public static boolean isAlmost(double a, double b, double acceptance) {         //Static Tolerance checker
        return Math.abs(a - b) <= acceptance;
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public double getDistance() {
        return distance;
    }

    public void setDeltaT(double deltaT) {
        this.deltaT = deltaT;
    }

    public Vector2d getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2d velocity) {
        this.velocity = velocity;
    }

    public void setBodyMass(double bodyMass) {
        this.bodyMass = bodyMass;
    }

    private double findCenter() {
        return Math.atan2((float) (0 - yPos), (float) (0 - xPos));
    }

    public boolean samePoint(double yPosI, double radius) {
        return isAlmost(getxPos(), 0, 10000) && (isAlmost(getyPos(), yPosI - radius, 100000));
    }
}
