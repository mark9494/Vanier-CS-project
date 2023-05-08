/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.ProjectileMotion;

import java.io.File;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProjectileMotionSettings {

    @FXML
    protected Pane motionPane;

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

    protected static Stage editorStage;
    protected VBox winAnnouncement, loseAnnouncement;
    protected Label win, lose;
    protected Cannon cannon;
    protected static Ball ball;
    protected static LandingArea landingArea;
    public static Timeline timelineRectangleAndBall;
    public static Timeline timelineBall;
    public static Timeline timelinePaneResize;
    protected int currentRate;
    protected double animationDuration;
    protected static double rampAngle;
    protected static double accelerationY;
    protected static double initialVelocity;
    protected double initVelocityX, initVelocityY;
    protected double finalVelocityX, finalVelocityY;
    protected double time;
    protected double deltaY, deltaX, finalPosition;
    protected static final DecimalFormat df = new DecimalFormat("0.00");
    protected double windStrength;
    protected static boolean isWind;
    protected Wind wind;
    protected File loadSave, newSave;
    protected static String defaultBackgroundFilePath = "/images/background2.jpg";
    protected static boolean changeBackground = false;
    public static Timer timer;

    public void setDefaultBackGround() {
        Image image = new Image("/images/background2.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true));
        motionPane.setBackground(new Background(backgroundImage));
    }

    public void setInitialVelocity() {
        initialVelocity = sldInitialVelocity.getValue();
    }

    public void setAccelerationY() {
        accelerationY = sldAccelerationY.getValue();
    }

    public void setRampAngle() {
        rampAngle = sldRampAngle.getValue();
        cannon.setAngle(rampAngle);
        cannon.setAngleRadians(rampAngle);
    }

    public void updateBackground() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!changeBackground) {
                    changeBackground = false;
                    return;
                }
                Image image = new Image(defaultBackgroundFilePath);
                BackgroundImage backgroundImage = new BackgroundImage(
                        image,
                        BackgroundRepeat.NO_REPEAT, // repeat X
                        BackgroundRepeat.NO_REPEAT, // repeat Y
                        BackgroundPosition.CENTER, // position
                        new BackgroundSize(100, 100, true, true, true, true));
                motionPane.setBackground(new Background(backgroundImage));
            }
        }, 0, 200);
    }

    protected void setVelocityX() {
        initVelocityX = initialVelocity * cos(-cannon.getAngleRadians());
        finalVelocityX = initVelocityX;
    }

    protected void setVelocityY() {
        initVelocityY = initialVelocity * sin(-cannon.getAngleRadians());
    }

    protected void setTime() {
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

    protected void setDeltaX() {
        deltaX = initVelocityX * time;
    }

    protected void setDeltaY(double deltaYt) {
        deltaY = deltaYt;
    }

}
