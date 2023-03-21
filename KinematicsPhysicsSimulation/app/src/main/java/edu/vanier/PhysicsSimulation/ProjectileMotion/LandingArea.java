/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.ProjectileMotion;

import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author antho
 */
public class LandingArea extends Rectangle {

    private static final int INIT_WIDTH = 50;
    private static final double INIT_HEIGHT = 4;

    public LandingArea() {
        this.setWidth(this.INIT_WIDTH);
        this.setHeight(this.INIT_HEIGHT);
        this.setFill(Color.GREENYELLOW);
    }

    public void randomSpawn(double maxX, double minX, double height) {
        this.setTranslateY(height);
        Random r = new Random();
        double randomValue = minX + (maxX - minX) * r.nextDouble();
        this.setTranslateX(randomValue);
    }

}
