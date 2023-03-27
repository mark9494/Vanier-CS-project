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
        ramp = new Ramp();
        ramp.setTranslateY(25);
        ramp.setTranslateX(300);
        landingArea = new LandingArea();
        ball = new Ball();
        ball.setTranslateX(ramp.setCornerX() + ball.getRadius());
        ball.setTranslateY(ramp.getCornerY() - ball.getRadius());
        pane.getChildren().addAll(landingArea, ball, ramp);
        createAnimation();
        timeline.play();
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
        //timeline.play();
        landingArea.randomSpawn(pane.getWidth() - landingArea.getWidth(), ball.getTranslateX(), pane.getHeight());
        generateParameters();
    }

    private void handleUpdateAnimation() {
        //ball.setTranslateX(ball.getTranslateX() + 1);
        moveRectangleAndBall();
    }

    private void moveRectangleAndBall() {
        setRampAngle();
        ramp.setRotate(ramp.getAngle());
        ball.setTranslateX(ramp.getCornerX() + ball.getRadius());
        ball.setTranslateY(ramp.getCornerY() - ball.getRadius());
    }

    //TODO: Finish adding the rest of the methods
    private void generateParameters() {
        setDeltaY(pane.getHeight());
        setInitialVelocity();
        setAccelerationY();
        setRampAngle();
        setVelocityX();
        setVelocityY();
        setTime();
        setDeltaX();
        System.out.println(initVelocityY);
        System.out.println(deltaX);
        
    }
}
