/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.ProjectileMotion;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author antho
 */
public class Ramp extends Rectangle {

    private static final int INIT_WIDTH = 100;
    private static final int INIT_HEIGHT = 75;

    public Ramp() {
        this.setWidth(INIT_WIDTH);
        this.setHeight(INIT_HEIGHT);
        this.setRotate(45);
        this.setFill(Color.BLUE);
    }
}
