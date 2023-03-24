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
        ramp.setTranslateX(100);
        landingArea = new LandingArea();
        ball = new Ball();
        ball.setTranslateX(ramp.setCornerX() + ball.getRadius());
        ball.setTranslateY(ramp.getCornerY() - ball.getRadius());
        pane.getChildren().addAll(landingArea, ball, ramp);
        createAnimation();
        timelineRectangleAndBall.play();
    }
    
    public void createAnimation() {
        animationDuration = 10;
        currentRate = 5;
        
        timelineRectangleAndBall = new Timeline(
                new KeyFrame(Duration.millis(animationDuration), e -> handleUpdateAnimation()));
        timelineRectangleAndBall.setRate(currentRate);
        timelineRectangleAndBall.setCycleCount(Timeline.INDEFINITE);
        
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
        
        timelineRectangleAndBall.stop();
        landingArea.randomSpawn(pane.getWidth() - landingArea.getWidth(), (ramp.getTranslateX() + ramp.getWIDTH()), pane.getHeight());
        generateParameters();
        double currentRateBall = time * 185;
        
        timelineBall = new Timeline(
                new KeyFrame(Duration.seconds(100), e -> handleUpdateAnimationBall()));
        timelineBall.setRate(currentRateBall);
        timelineBall.setCycleCount(Timeline.INDEFINITE);
        timelineBall.play();
    }
    
    private void handleUpdateAnimation() {
        moveRectangleAndBall();
    }
    
    private void handleUpdateAnimationBall() {
        moveBallX();
        moveBallY();
        endOfMotion();
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
        ball.setDy(initVelocityY);
        ball.setDx(initVelocityX);
        System.out.println(initVelocityY);
        System.out.println(initVelocityX);
        System.out.println(time);
        System.out.println(deltaX);
    }
    
    private void moveBallX() {
        ball.setTranslateX(ball.getTranslateX() + ball.getDx());
        
    }
    
    private void moveBallY() {
        ball.setDy(ball.getDy() - accelerationY);
        ball.setTranslateY(ball.getTranslateY() - ball.getDy());
        
    }
    
    private void endOfMotion() {
        if (ball.getTranslateY() + ball.getRadius() >= pane.getHeight()) {
            ball.setTranslateY(pane.getHeight() - ball.getRadius());
            timelineBall.pause();
        }
    }
    
}
