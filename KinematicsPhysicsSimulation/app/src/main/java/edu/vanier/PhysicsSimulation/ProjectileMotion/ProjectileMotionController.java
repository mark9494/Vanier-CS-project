/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.ProjectileMotion;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.util.Duration;

/**
 *
 * @author antho
 */
public class ProjectileMotionController extends ProjectileMotionSettings {

    @FXML
    public void initialize() {
        int initranslateX = 500;
        
        ramp = new Ramp();
        ramp.setTranslateY(25);
        ramp.setTranslateX(300);
        landingArea = new LandingArea(50, 50);
        ball = new Ball();
        ball.setTranslateX(ramp.getCornerX());
        ball.setTranslateY(ramp.getCornerY());
        landingArea.setTranslateX(initranslateX);
        pane.getChildren().addAll(landingArea, ball, ramp);
        createAnimation();
    }

    public void createAnimation() {
        animationDuration = 10;
        currentRate = 5;
        timeline = new Timeline(
                new KeyFrame(Duration.millis(animationDuration), e -> handleUpdateAnimation()));
        timeline.setRate(currentRate);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    @FXML
    public void handleHomeButton() {
        System.out.println("Going Back...");
    }

    @FXML
    public void handleMouseHoverInfo() {
        System.out.println("info.");
    }

    @FXML
    public void handleBegin() {
        timeline.play();
        generateParameters();
    }

    private void handleUpdateAnimation() {
        ball.setTranslateX(ball.getTranslateX() + 1);
    }

    //TODO: 
    private void generateParameters() {
        setInitialVelocity();
        setAccelerationY();
        setRampAngle();
        setVelocityX();
        setVelocityY();
        setTime();
        setDeltaX();
        //.....
    }
}
