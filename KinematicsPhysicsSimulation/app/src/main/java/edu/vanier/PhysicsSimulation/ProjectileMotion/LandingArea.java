/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.ProjectileMotion;

import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class LandingArea extends Rectangle {

    static final int INIT_WIDTH = 100;
    static final double INIT_HEIGHT = 35;

    public LandingArea() {
        this.setWidth(this.INIT_WIDTH);
        this.setHeight(this.INIT_HEIGHT);
        Image landingArea = new Image("/images/landingArea.png");
        this.setFill(new ImagePattern(landingArea));
    }

    public void randomSpawn(double maxX, double minX, double height) {
        this.setTranslateY(height - INIT_HEIGHT);
        Random r = new Random();
        double randomValue = minX + (maxX - minX) * r.nextDouble();
        this.setTranslateX(randomValue);
    }

    public double getLeftX() {
        return this.getTranslateX();
    }

    public double getRightX() {
        return this.getTranslateX() + INIT_WIDTH;
    }
}
