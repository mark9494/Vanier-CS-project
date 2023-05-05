/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.FreeFallSimulation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author antho
 */
public class FreeFallController extends FreeFallSettings {
    
    @FXML
    public void initialize() {
        ball = new Ball();
        System.out.println("hi");
        motionPane.getChildren().add(ball);
        ball.setTranslateX(220);
        ball.setTranslateY(+15);
        createAnimation();
    }
    
    @FXML
    public void handleHomeButton() {
        System.out.println("home button");
        FreeFallLoader.secondWindow.close();
        
    }
    
    public void createAnimation() {
        int animationDuration = 15;
        int currentRate = 4;
        
        timelineFreeFall = new Timeline(
                new KeyFrame(Duration.millis(animationDuration),
                        e -> handleUpdateAnimation()));
        timelineFreeFall.setRate(currentRate);
        timelineFreeFall.setCycleCount(Timeline.INDEFINITE);
    }
    
    public void handleUpdateAnimation() {
        ball.setTranslateY(ball.getTranslateY() + ball.getDy());
    }
    
    @FXML
    public void handlePlay() {
        timelineFreeFall.play();
        ball.setDy(sliderSpeed.getValue());
    }
    
}
