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
    protected static Ball ball;
    protected static LandingArea landingArea;
    public static Timeline timelineRectangleAndBall;
    public static Timeline timelineBall;
    public static Timeline timelinePaneResize;
    protected static double cannonAngle;
    protected static double accelerationY;
    protected static double initialVelocity;
    protected static final DecimalFormat df = new DecimalFormat("0.00");
    protected static boolean isWind;
    protected static String defaultBackgroundFilePath = "/images/background2.jpg";
    protected static boolean changeBackground = false;
    public static Timer timer;
    protected VBox winAnnouncement, loseAnnouncement;
    protected Label win, lose;
    protected Cannon cannon;
    protected int currentRate;
    protected double animationDuration;
    protected double initVelocityX, initVelocityY;
    protected double finalVelocityX, finalVelocityY;
    protected double time;
    protected double deltaY, deltaX, finalPosition;
    protected double windStrength;
    protected Wind wind;
    protected File loadSave, newSave;

    /**
     * Sets the default background of the pane where the animation occurs.
     */
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

    /**
     * Retrieves the slider information for the initial velocity and gives it to
     * a variable.
     */
    public void setInitialVelocity() {
        initialVelocity = sldInitialVelocity.getValue();
    }

    /**
     * Retrieves the slider information for the acceleration y and gives it to a
     * variable.
     */
    public void setAccelerationY() {
        accelerationY = sldAccelerationY.getValue();
    }

    /**
     * Retrieves the slider information for the cannon angle, converts it to
     * radians and gives it to a variable.
     */
    public void setCannonAngle() {
        cannonAngle = sldRampAngle.getValue();
        cannon.setAngle(cannonAngle);
        cannon.setAngleRadians(cannonAngle);
    }

    /**
     * Continuously updates the background, but only runs if the background has
     * changed, then just returns.
     */
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

    /**
     * Formula to set velocity X according to cannons angle and its initial
     * speed.
     */
    protected void setVelocityX() {
        initVelocityX = initialVelocity * cos(-cannon.getAngleRadians());
        finalVelocityX = initVelocityX;
    }

    /**
     * Formula to set initial velocity Y according to the cannons angle and its
     * initial speed.
     */
    protected void setVelocityY() {
        initVelocityY = initialVelocity * sin(-cannon.getAngleRadians());
    }

    /**
     * Kinematic Formula to retrieve the time of the sped up simulation.
     */
    protected void setTime() {
        finalVelocityY = Math.pow(initVelocityY, 2) + 2 * accelerationY * deltaY;
        finalVelocityY = -Math.sqrt(finalVelocityY);
        time = -(finalVelocityY - initVelocityY) / accelerationY;
    }

    /**
     * Kinematic Formula to retrieve the final X position.
     */
    protected void setDeltaX() {
        deltaX = initVelocityX * time;
    }

    protected void setDeltaY(double deltaYt) {
        deltaY = deltaYt;
    }

}
