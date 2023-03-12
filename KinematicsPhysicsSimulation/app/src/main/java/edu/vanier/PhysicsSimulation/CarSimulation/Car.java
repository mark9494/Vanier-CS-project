/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.CarSimulation;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author antho
 */
public class Car extends Rectangle {
    
   public Car(int layoutX, int layoutY){
    this.setWidth(51);
    this.setHeight(24);
     this.setLayoutX(layoutX);
     this.setLayoutY(layoutY);
     this.setFill(Color.BLACK);   
   }
    
}
