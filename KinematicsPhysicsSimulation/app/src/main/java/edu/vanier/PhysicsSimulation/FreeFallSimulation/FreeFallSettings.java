/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.FreeFallSimulation;
import java.io.File;
import java.text.DecimalFormat;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class FreeFallSettings {

    @FXML
    protected Button btnHome;
    @FXML
    protected ImageView homeImgView;
    @FXML
    protected Pane motionPane;
    @FXML
    protected Slider sldHeight;
    @FXML
    protected Slider sldAccelerationY;
    @FXML
    protected Rectangle building;
    @FXML
    protected Button btnReset;
    @FXML
    protected Button btnStart;
    @FXML
    protected Label lblTime;
    @FXML
    protected Label lblFinalVelocity;
    @FXML
    protected MenuBar menuBar;
    
    protected IOFreeFall fileHandler;
    
    protected File loadSave, newSave;
    protected static final DecimalFormat df = new DecimalFormat("0.00");
    protected Timeline timelineFreeFall, timelineInitializeBall;
    protected Ball ball;
    protected double speed, accelerationY, initialHeight, finalVelocity, time;

    public void setDefaultBackGround() {
        Image image = new Image("/images/cityBackground.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true));
        motionPane.setBackground(new Background(backgroundImage));
    }

    public double returnFinalVelocity(double accelerationY, double height) {
        double finalVelocity = 2 * accelerationY * height;
        finalVelocity = Math.sqrt(finalVelocity);
        return finalVelocity;
    }

    public double returnTime(double finalVelocity, double accelerationY, double initialVelocity) {

        finalVelocity = Math.pow(initialVelocity, 2) + 2 * accelerationY * building.getHeight();
        finalVelocity = -Math.sqrt(finalVelocity);
        System.out.println(finalVelocity);
        time = -(finalVelocity - initialVelocity) / accelerationY;
        return time;
    }
}
