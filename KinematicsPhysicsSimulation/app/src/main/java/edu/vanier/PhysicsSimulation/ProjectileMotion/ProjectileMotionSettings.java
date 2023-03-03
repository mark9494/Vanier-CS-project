/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.ProjectileMotion;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author antho
 */
public class ProjectileMotionSettings {
    @FXML
    protected Pane pane;
    @FXML
    protected Button btnHome;
    @FXML
    protected ImageView infoCircle;
    @FXML
    protected Label initialVelocity, angleRamp, accelerationY;

}
