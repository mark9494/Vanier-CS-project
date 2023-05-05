/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.FreeFallSimulation;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 *
 * @author 2161743
 */
public class Ball extends Circle {

    private static final int RADIUS = 15;
    private double dy;

    public Ball() {
        this.setRadius(RADIUS);
        this.setFill(Color.RED);
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

}
