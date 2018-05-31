package com.lwarburt;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Testing extends PApplet {
    private static final double DELTA_T = .1;                            //Time Step (lower numbers = more processing, but more accuracy
    private static final double T_MAX = 4000000.0 * DELTA_T;            //Total time before quitting computation

    private static final double X_MAX = CelestialBodies.EARTH.getWindow();  //8.5 times planet radius
    private static final double Y_MAX = CelestialBodies.EARTH.getWindow();
    private static final int X_RES = 1500;                              //Resolution of window
    private static final int Y_RES = 1500;
    private static double xScale = X_RES / X_MAX;
    private static double yScale = Y_RES / Y_MAX;
    private static double radius = CelestialBodies.EARTH.radius;
    private static Simulate sim = new Simulate();

    public static void main(String[] args) {
        PApplet.main("com.lwarburt.Testing");
    }

    @Override
    public void settings() {
        size(X_RES, Y_RES);
    }

    @Override
    public void draw() {
        noLoop();
        //background(40, 40, 40);
        background(200, 200, 200);
        fill(0, 153, 51);
        ellipse(width / 2, height / 2, (float) radius * (float) xScale * 2, (float) radius * (float) yScale * 2);
        noFill();
        Scanner s = new Scanner(System.in);                                               //Here comes the Disco
        System.out.print("Height of satellite above the planets surface (m): ");          //Maybe too much Disco
        double initialYPos = -s.nextDouble();
        sim.setBodyMass(CelestialBodies.EARTH.mass);                        //Sets all base values for sim
        sim.setRadius(CelestialBodies.EARTH.radius);
        sim.setDeltaT(DELTA_T);
        double max = 0;
        double min = 0;
        List<Point> points = new ArrayList<>();
        Point p = new Point(0, 0, 0);

        for (int n = 0; n <= 0; n++) {
            System.out.print("Initial horizontal velocity (m/sec): ");
            Vector2d velocity = new Vector2d(s.nextDouble(), 0);
            sim.setVelocity(velocity);                              //sets simulation initial velocity
            sim.setyPos(initialYPos - radius);                      //Resets Y Position
            sim.setxPos(0);                                         //Resets X Position
            for (int i = 0; i <= T_MAX / DELTA_T; i++) {
                sim.simulate();                                     //Simulates motion by one time step
                double xPix = sim.getxPos() * xScale + X_RES / 2;   //Plots x,y points -- To own method?
                double yPix = sim.getyPos() * yScale + Y_RES / 2;
                p.set(xPix, yPix, sim.getVelocity().getMagnitude());
                points.add(p);
                if (sim.getVelocity().getMagnitude() >= max) {
                    max = sim.getVelocity().getMagnitude();
                }
                if (sim.getVelocity().getMagnitude() <= min) {
                    min = sim.getVelocity().getMagnitude();
                }
                if (sim.endOrbit(initialYPos, i)) {                   //Tests if/how the orbit should end
                    System.out.printf(" in %f minutes \n", i * DELTA_T / 60);
                    break;
                }
            }

        }
        for (int i = 0; i <= points.size() - 1; i++) {
            stroke(lerpColor(0xff0000, 0x0000ff, (float) (((points.get(i).getMag()) - min) / max)));
            fill(lerpColor(0xff0000, 0x0000ff, (float) (((points.get(i).getMag()) - min) / max)));
            ellipse((float) points.get(i).getX(), (float) points.get(i).getY(), 5, 5);
        }
    }
}
