package com.lwarburt;

import processing.core.PApplet;
import java.lang.Math;
import java.util.Scanner;

public class Testing extends PApplet {
    public static void main(String[] args) {
        PApplet.main("Testing");
    }

    public void settings() {
        super.settings();
        size(800,500);
    }
    public void draw() {
        int xScale = 1400/width;
        int xPos = 60;
        int yScale = 1200/height;
        int yPos = 50;
        double xPix = xPos*xScale + (width/2);
        System.out.println(xPix);
        double yPix = yPos*yScale + (height/2);
        System.out.println(yPix);
        stroke(0xff0000);
        strokeWeight(20);
        System.out.println((float) xPix);
        ellipse((float) xPix, (float) yPix, 4, 4);
        strokeWeight(3);
        ellipse(400 ,250,0,50);



    }

}
