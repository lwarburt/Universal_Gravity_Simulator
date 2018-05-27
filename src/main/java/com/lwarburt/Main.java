package com.lwarburt;

import processing.core.PApplet;

import java.util.Scanner;

public class Main extends PApplet {
    private static final double DELTA_T = .1;                          //Time Step
    private static final double T_MAX = 400000.0 * DELTA_T;             //Amount of time
    private static final double BODY_RADIUS = 6371000;                  //Radius of Earth = 6.37 * (Main.pow(10, 6)) (meters)
    private static final double X_MAX = 4 * (Main.pow(10, 7));
    private static final double Y_MAX = 4 * (Main.pow(10, 7));
    private static final int X_RES = 1500;
    private static final int Y_RES = 1500;
    private static double xScale = X_RES/X_MAX;
    private static double yScale = Y_RES/Y_MAX;
    private static Vector2d velocity = new Vector2d(0, 0d);
    private static Simulate sim = new Simulate(velocity, 0, 0, 0);
    private static double initialYPos;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);                                 //Here comes the Disco
        System.out.print("Height of satellite above Earth (m): ");          //Maybe too much Disco
        initialYPos = -s.nextDouble();
        sim.setBodyMass(5.972 * Math.pow(10, 24));
        sim.setDeltaT(DELTA_T);
        PApplet.main("com.lwarburt.Main");
    }

    @Override
    public void settings() {
        size(X_RES, Y_RES);
    }

    @Override
    public void draw() {
        noLoop();
        Scanner s = new Scanner(System.in);
        ellipse(width / 2, height / 2, (float) BODY_RADIUS * (float) xScale * 2, (float) BODY_RADIUS * (float) yScale * 2);
        double apsis = 0;
        for (int n = 0; n <= 4; n++) {
            System.out.print("Initial horizontal velocity (m/sec): ");
            velocity.setAngle(s.nextDouble(), 0);
            sim.setVelocity(velocity);
            sim.setyPos(initialYPos - BODY_RADIUS);
            sim.setxPos(0);
            int i = 0;
            while (i <= T_MAX / DELTA_T) {
                sim.simulate();
                double xPix = sim.getxPos() * xScale + X_RES / 2;
                double yPix = sim.getyPos() * yScale + Y_RES / 2;
                ellipse((float) xPix, (float) yPix, 3, 3);

                if (sim.samePoint(initialYPos, BODY_RADIUS)) {
                    apsis = sim.getyPos();
                }

                if (sim.getDistance() < BODY_RADIUS) {
                    double mag = sim.getVelocity().getMagnitude();
                    System.out.printf("The object hit the Celestial body with a final velocity of :%f \n", mag);
                    apsis = 0;
                    break;
                }
                if (sim.samePoint(initialYPos, BODY_RADIUS) && i > 5000) {
                    System.out.println("The object successfully orbits");
                    //if (apsis != 0){
                    //double eccent = Math.abs(initialYPos - apsis + BODY_RADIUS)/((initialYPos - BODY_RADIUS)+ (apsis- BODY_RADIUS));
                    //System.out.printf("with an eccentricity of %f \n", (eccent));
                    //}
                    apsis = 0;
                    break;
                }
                i++;
            }
        }
    }
}
