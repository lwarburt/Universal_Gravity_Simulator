package com.lwarburt;


public class Simulate {
    private final double G = 6.67e-11;         //Universal Gravitational Constant
    private double bodyMass;
    private double deltaT;
    private Vector2d velocity;
    private double distance;
    private double radius;

    private double xPos;
    private double yPos;

    public Simulate(Vector2d velocity, double bodyMass, double deltaT, double xPos, double yPos) {
        this.velocity = velocity;
        this.bodyMass = bodyMass;
        this.deltaT = deltaT;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Simulate() {

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

    public void simulate() {                                            //Base Math
        distance = Math.sqrt(Math.pow(xPos, 2) + Math.pow(yPos, 2));    //Calculates distance from center
        double force = (G * (bodyMass) / Math.pow(distance, 2));        //Calculates force/acceleration
        double angle = findCenter();
        Vector2d acceleration = new Vector2d(force, (float) angle);     //Creates the acceleration vector
        acceleration.scale(deltaT);                                     //Scales acceleration by deltaT to get velocity
        velocity.add(acceleration);
        velocity.scale(deltaT);
        xPos = velocity.getI() + xPos;                                  //Finds new x,y positions
        yPos = velocity.getJ() + yPos;
        velocity.scale(1 / deltaT);                                //Finds 'real' velocity by undoing scaling
    }

    public boolean endOrbit(double initialYPos, int iterations) {            //Tests if/how orbit ends
        double apsis;
        if (samePoint(initialYPos, radius)) {
            apsis = getyPos();
        }

        if (getDistance() < radius) {
            double mag = getVelocity().getMagnitude();
            System.out.printf("The object hit the Celestial body with a final velocity of :%f", mag);
            apsis = 0;
            return true;
        }
        if (samePoint(initialYPos, radius) && iterations > 10000) {
            System.out.print("The object successfully orbits");
            //if (apsis != 0){
            //double eccent = Math.abs(initialYPos - apsis + BODY_RADIUS)/((initialYPos - BODY_RADIUS)+ (apsis- BODY_RADIUS));
            //System.out.printf("with an eccentricity of %f \n", (eccent));
            //}
            apsis = 0;
            return true;
        }
        return false;
    }

    public static boolean isAlmost(double a, double b, double acceptance) {         //Static Tolerance checker
        return Math.abs(a - b) <= acceptance;
    }

    private double findCenter() {                                       //Finds 0,0 from object position
        return Math.atan2((float) (0 - yPos), (float) (0 - xPos));
    }

    public boolean samePoint(double yPosI, double radius) {
        return isAlmost(getxPos(), 0, 10000) && (isAlmost(getyPos(), yPosI - radius, 100000));
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

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
