/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.ProjectileMotion;

import static java.lang.Math.sin;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author antho
 */
public class Ramp extends Rectangle {

    private final int WIDTH = 90;
    private final int HEIGHT = 60;
    private double cornerX;
    private double cornerY;
    private double angle = 25; //(bounded between 20 - 65)

    public Ramp() {
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        this.setRotate(angle);
        this.setFill(Color.BLUE);
    }

    // TODO: getter modify values, need to be changed
    public double getCornerX() {
        cornerX = this.getTranslateX() + this.WIDTH;
        return cornerX;
    }

    public double getCornerY() {
        cornerY = sin(angle) * (WIDTH / 2);
        return cornerY;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

}
