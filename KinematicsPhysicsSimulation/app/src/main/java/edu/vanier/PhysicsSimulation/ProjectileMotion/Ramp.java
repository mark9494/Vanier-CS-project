/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.ProjectileMotion;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author antho
 */
public class Ramp extends Rectangle {

    private final int WIDTH = 100;
    private final int HEIGHT = 60;
    private double cornerX;
    private double cornerY;
    private double angle; //(bounded between 20 - 55)
    private double angleRadians;

    public Ramp() {
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        this.setRotate(angle);
        this.setFill(Color.DARKSLATEGREY);
    }

    public double setCornerX() {
        cornerX = this.getTranslateX() + WIDTH;
        return cornerX;
    }

    public double setCornerY() {
        cornerY = this.getTranslateY() + sin(this.getAngleRadians()) * this.HEIGHT;
        return cornerY;
    }

    public double getCornerX() {
        setCornerX();
        return cornerX;
    }

    public double getCornerY() {
        setCornerY();
        return cornerY;
    }

    public double getAngleRadians() {
        return this.angleRadians;
    }

    public void setAngleRadians(double angle) {
        this.angleRadians = angle * Math.PI / 180;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

}
