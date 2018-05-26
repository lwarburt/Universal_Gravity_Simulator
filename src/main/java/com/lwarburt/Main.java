package com.lwarburt;

import processing.core.PApplet;

import java.util.Scanner;


public class Main extends PApplet {
    public static final double G = 6.67 * (Math.pow(10, -11));         //Universal Gravitational Constant
    public static final double DELTA_T = 2.0;                          //Time Step
    public static final double T_MAX = 500000.0 * DELTA_T;             //Amount of time
    public static final double BODY_MASS = 5.98 * (Math.pow(10, 24));  //Mass of Earth = 5.98 * (Math.pow(10, 24))
    public static final double BODY_RADIUS = 6371000;                  //Radius of Earth = 6.37 * (Main.pow(10, 6)) (meters)

    private static final double X_MAX = 5 * (Main.pow(10, 7));
    private static final double Y_MAX = 5 * (Main.pow(10, 7));
    public static final int X_RES = 1500;
    public static final int Y_RES = 1500;
    private static double xPos = 0;
    private static double yPos = 0;
    private static double xPix;
    private static double yPix;
    private static double xScale = X_RES/X_MAX;
    private static double yScale = Y_RES/Y_MAX;
    public static Scanner s = new Scanner(System.in);
    public static double vInitial;
    public static Vector2d velocity = new Vector2d(0,0f);


    public static void main(String[] args) {                                //Here comes the Disco
        System.out.print("Height of satellite above Earth (m): ");
        yPos = -s.nextDouble() - BODY_RADIUS;
        //yPos = -400000 - BODY_RADIUS;       //ISS Data
        //for loop added here later for multiple satellite support - Testing
        System.out.print("Initial horizontal velocity (m/sec): ");
        double vInitial = s.nextDouble();
        //vInitial = 7666;                      //ISS Data - Testing
        velocity.setAngle(vInitial, 0);
        PApplet.main("com.lwarburt.Main");
    }

    public static double findCenter(){
        return Math.atan2((float) (0-yPos),(float) (0-xPos));
    }

    @Override
    public void settings() {
        size(X_RES, Y_RES);
    }

    @Override
    public void draw() {
        //background(0);
        ellipse(width/2, height/2, (float) BODY_RADIUS * (float) xScale, (float) BODY_RADIUS * (float) yScale);
        //for (int i = 0; i <= T_MAX/DELTA_T; i++ ){
            System.out.println(velocity);
            double distance = sqrt((sq((float) xPos) + sq((float) yPos)));
            double force = (G * (BODY_MASS)/ sq((float) distance));
            double angle = findCenter();
            Vector2d acceleration = new Vector2d(force, (float) angle);
            acceleration.scale(DELTA_T);
            velocity.add(acceleration);
            velocity.scale(DELTA_T);
            xPos = velocity.getI() + xPos;
            yPos = velocity.getJ() + yPos;
        velocity.scale(1 / DELTA_T);
            xPix = xPos * xScale + width / 2;
            yPix = yPos * yScale + height / 2;
            ellipse((float) xPix, (float) yPix, 4, 4);
        //if (distance <= BODY_RADIUS) {
        // System.out.println("The object hit the Celestial body");

                //break;
        //}
    }

}
