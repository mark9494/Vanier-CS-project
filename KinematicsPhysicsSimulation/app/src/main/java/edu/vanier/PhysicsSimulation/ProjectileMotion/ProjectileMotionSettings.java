/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.ProjectileMotion;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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
    //@FXML
    //protected Label initialVelocity, angleRamp, accelerationY;
    @FXML
    protected Slider sldInitialVelocity, sldRampAngle, sldAccelerationY;
    protected Ramp ramp;
    protected Ball ball;
    protected LandingArea landingArea;
    protected Timeline timeline;
    protected int currentRate;
    protected double animationDuration;
    protected double rampAngle, accelerationY;
    protected double initialVelocity, initVelocityX, initVelocityY;
    protected double finalVelocityX, finalVelocityY;
    protected double time;
    protected double deltaY, deltaX;

    public void setInitialVelocity() {
        initialVelocity = sldInitialVelocity.getValue();
    }

    public void setAccelerationY() {
        accelerationY = sldAccelerationY.getValue();
    }

    public void setRampAngle() {
        rampAngle = sldRampAngle.getValue();
    }

    //1
    protected void setVelocityX() {
        initVelocityX = initialVelocity * (cos(rampAngle));
        finalVelocityX = initVelocityX;
    }

    //2
    protected void setVelocityY() {
        initVelocityY = initialVelocity * (sin(rampAngle));
    }

    //3
    protected void setTime() {
        // 0 =   1/2 (accelerationY)(time^2) + (initVelocityY)(time) - deltaY
    }

    //4
    protected void setDeltaX() {
        deltaX = (initVelocityX * time) + (1 / 2 * accelerationY * time * time);
    }
}
