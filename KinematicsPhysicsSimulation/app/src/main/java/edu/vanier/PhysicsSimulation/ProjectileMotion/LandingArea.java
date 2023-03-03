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
public class LandingArea extends Rectangle {

    private static final int INIT_WIDTH = 2;
    private static final int INIT_HEIGHT = 10;

    public LandingArea (double width, double height) {
        this.setWidth(width);
        this.setHeight(height);
        this.setFill(Color.GREENYELLOW);
    }

    public void randomSpawn(double maxX, double minX, double height){
        //set height to pane.getHeight when calling method 
    }

}
