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
public class Ball extends Circle{
    private static final int RADIUS = 10;
    
    public Ball(){
        this.setRadius(RADIUS);
        this.setFill(Color.RED);
    }
}
