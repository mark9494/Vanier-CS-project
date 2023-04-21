/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.ProjectileMotion;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.text.DecimalFormat;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.QuadCurve;

/**
 *
 * @author antho
 */
public class ProjectileMotionSettings {

    @FXML
    protected Pane pane;

    @FXML
    protected Button btnHome, btnReset, btnBegin;

    @FXML
    protected ImageView infoCircle;

    @FXML
    protected Label lblTime, lblPosition;

    @FXML
    protected Slider sldInitialVelocity, sldRampAngle, sldAccelerationY;

    @FXML
    protected HBox hboxBottom;

    @FXML
    protected CheckBox CBoxWind;

    @FXML
    protected VBox windBox;

    @FXML
    protected ImageView windArrow;
    
    @FXML
    protected ProgressBar windIntensity;
    
    @FXML
    protected MenuBar menuBar;
    
    protected VBox winAnnouncement;
    protected Label win;
    protected Ramp ramp;
    protected Ball ball;
    protected LandingArea landingArea;
    protected Timeline timelineRectangleAndBall, timelineBall, timelinePaneResize;
    protected int currentRate;
    protected double animationDuration;
    protected double rampAngle, accelerationY;
    protected double initialVelocity, initVelocityX, initVelocityY;
    protected double finalVelocityX, finalVelocityY;
    protected double time;
    protected double deltaY, deltaX, finalPosition;
    protected static final DecimalFormat df = new DecimalFormat("0.00");
    protected double windStrength;
    protected boolean isWind;
    protected Wind wind;

    public void setInitialVelocity() {
        initialVelocity = sldInitialVelocity.getValue();
    }

    public void setAccelerationY() {
        accelerationY = sldAccelerationY.getValue();
    }

    public void setRampAngle() {
        rampAngle = sldRampAngle.getValue();
        ramp.setAngle(rampAngle);
        ramp.setAngleRadians(rampAngle);
    }

    //1
    protected void setVelocityX() {
        initVelocityX = initialVelocity * cos(-ramp.getAngleRadians());
        finalVelocityX = initVelocityX;
    }

    //2
    protected void setVelocityY() {
        initVelocityY = initialVelocity * sin(-ramp.getAngleRadians());
    }

    //3
    protected void setTime() {
        // 0 =   1/2 (accelerationY)(time^2) + (initVelocityY)(time) - deltaY
        double a = 0.5 * (accelerationY);
        double b = initVelocityY;
        double c = -deltaY;
        double underRoot = (b * b) - (4 * a * c);
        double sqrt = Math.sqrt(underRoot);
        double time1 = (-b + sqrt) / (2 * a);

        // conditional operation
        time = (time1 > 0)
                ? time1
                : (-b - sqrt) / (2 * a);
    }

    //4
    protected void setDeltaX() {
        deltaX = initVelocityX * time;
        //finalPosition = ball.getTranslateX() + deltaX;
    }

    protected void setDeltaY(double deltaYt) {
        deltaY = deltaYt;
    }

}
