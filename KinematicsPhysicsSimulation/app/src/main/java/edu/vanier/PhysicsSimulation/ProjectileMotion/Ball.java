/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.ProjectileMotion;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author antho
 */
public class Ball extends Circle {

    private static final int RADIUS = 15;
    private double dx, dy;

    public Ball() {
        //this.dy = 5;
        //this.dx = 5;
        this.setRadius(RADIUS);
        this.setFill(Color.RED);
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

}
