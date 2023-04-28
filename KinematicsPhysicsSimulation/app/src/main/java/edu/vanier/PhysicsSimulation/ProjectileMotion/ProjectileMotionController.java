/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.ProjectileMotion;

import edu.vanier.PhysicsSimulation.PhysicsSimulationController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 *
 * @author antho
 */
public class ProjectileMotionController extends ProjectileMotionSettings {
    
    @FXML
    public void initialize() {
        disableButtons(false, true);
        menuBar.getMenus().remove(2);
        MenuItem save = new MenuItem("Save");
        menuBar.getMenus().get(0).getItems().add(save);
        wind = new Wind();
        ramp = new Ramp();
        ramp.setTranslateY(25);
        ramp.setTranslateX(100);
        landingArea = new LandingArea();
        ball = new Ball();
        setBallDefaultLocation();
        pane.getChildren().addAll(landingArea, ball, ramp);
        createAnimation();
        timelineRectangleAndBall.play();
        timelinePaneResize.play();
        landingArea.randomSpawn(pane.getWidth() - landingArea.getWidth(), (ramp.
                getTranslateX() + ramp.getWIDTH()), pane.getHeight());
    }
    
    public void setBallDefaultLocation() {
        ball.setTranslateX(ramp.setCornerX() + ball.getRadius());
        ball.setTranslateY(ramp.getCornerY() - ball.getRadius());
    }
    
    public void createAnimation() {
        animationDuration = 10;
        currentRate = 5;
        
        timelineRectangleAndBall = new Timeline(
                new KeyFrame(Duration.millis(animationDuration),
                        e -> handleUpdateAnimation()));
        timelineRectangleAndBall.setRate(currentRate);
        timelineRectangleAndBall.setCycleCount(Timeline.INDEFINITE);
        
        timelinePaneResize = new Timeline(
                new KeyFrame(Duration.millis(animationDuration),
                        e -> handlePaneResizeAffects()));
        timelinePaneResize.setRate(currentRate);
        timelinePaneResize.setCycleCount(Timeline.INDEFINITE);
    }
    
    private void disableButtons(boolean reset, boolean begin) {
        btnBegin.setDisable(begin);
        btnReset.setDisable(reset);
    }
    
    @FXML
    public void handleHomeButton() {
        PhysicsSimulationController.projectileMotion.close();
    }
    
    @FXML
    public void handleMouseHoverInfo() {
        System.out.println("info.");
    }
    
    @FXML
    public void handleBegin() {
        disableButtons(true, true);
        timelineRectangleAndBall.stop();
        generateParameters();
        double currentRateBall = 5000;
        
        timelineBall = new Timeline(
                new KeyFrame(Duration.seconds(100),
                        e -> handleUpdateAnimationBall()));
        timelineBall.setRate(currentRateBall);
        timelineBall.setCycleCount(Timeline.INDEFINITE);
        timelineBall.play();
        lblTime.setText("" + df.format(time));
        lblPosition.setText("" + df.format(finalPosition));
    }
    
    @FXML
    public void handleResetButton() {
        removeWinAnnouncement();
        disableButtons(true, false);
        setBallDefaultLocation();
        resetParameters();
        moveRectangleAndBall();
        timelineRectangleAndBall.play();
        
        landingArea.randomSpawn(pane.getWidth() - landingArea.getWidth(),
                ramp.getTranslateX() + ramp.getWIDTH(), pane.getHeight());
    }
    
    private void handleUpdateAnimation() {
        handleWindProperties();
        moveRectangleAndBall();
        ball.setRotate(0);
    }
    
    private void handleUpdateAnimationBall() {
        ball.setRotate(ball.getRotate() - Math.PI * 3);
        moveBallX();
        moveBallY();
        endOfMotion();
        //windAnimation();
        ballInLandingArea();
    }
    
    public void handleWindProperties() {
        
        if (!CBoxWind.isSelected()) {
            isWind = false;
            windBox.setOpacity(0);
            wind.setIntensity(0);
        } else if (isWind) {
            return;
        } else {
            isWind = true;
            windBox.setOpacity(100);
            wind.setAngle(wind.randomWindAngle());
            wind.setIntensity(wind.randomIntensity());
            windArrow.setRotate(-wind.getAngle() * 180 / Math.PI); //conversion to degrees
            setIntensity();
            System.out.println(wind.getAngle());
            System.out.println(wind.getIntensity());
            System.out.println(wind.getForceWindX());
            System.out.println(wind.getForceWindY());
        }
    }
    
    private void setIntensity() {
        if (wind.getIntensity() == 1) {
            windIntensity.setProgress(0.33);
            windIntensity.setStyle("-fx-accent: green;");
            
        } else if (wind.getIntensity() == 2) {
            windIntensity.setProgress(0.66);
            windIntensity.setStyle("-fx-accent: yellow;");
            
        } else {
            windIntensity.setProgress(1);
            windIntensity.setStyle("-fx-accent: red;");
            
        }
    }
    
    private void moveRectangleAndBall() {
        setRampAngle();
        ramp.setRotate(ramp.getAngle());
        ball.setTranslateX(ramp.getCornerX() + ball.getRadius());
        ball.setTranslateY(ramp.getCornerY() - ball.getRadius());
    }
    
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
    }
    
    private void resetParameters() {
        time = 0;
        finalPosition = 0;
        deltaX = 0;
        lblTime.setText("");
        lblPosition.setText("");
    }
    
    private void moveBallX() {
        ball.setTranslateX(ball.getTranslateX() + ball.getDx() + wind.
                getForceWindX());
        
    }
    
    private void moveBallY() {
        ball.setDy(ball.getDy() - accelerationY + wind.getForceWindY());
        ball.setTranslateY(ball.getTranslateY() - ball.getDy());
        
    }
    
    private void endOfMotion() {
        if (ball.getTranslateY() + ball.getRadius() >= pane.getHeight()) {
            ball.setTranslateY(pane.getHeight() - ball.getRadius());
            timelineBall.pause();
            finalPosition = ball.getTranslateX();
            return;
        }
        if (ball.getTranslateX() > pane.getWidth() || ball.getTranslateY() < 0) {
            ball.setTranslateY(pane.getHeight() - ball.getRadius());
            timelineBall.pause();
            finalPosition = ball.getTranslateX();
        }
    }
    
    private void ballInLandingArea() {
        boolean ballLanded;
        if (finalPosition != 0) {
            if (finalPosition > landingArea.getLeftX() && finalPosition < landingArea.
                    getRightX()) {
                ballLanded = true;
                disableButtons(false, true);
            } else {
                ballLanded = false;
            }
            if (ballLanded) {
                winAnnouncement();
            } else {
                System.out.println("Ball Didnt Land D:");
                disableButtons(false, true);
            }
        }
    }

    //TODO - Make Wind Properites box move with the resizing of the pane. 
    private void handlePaneResizeAffects() {
        sldInitialVelocity.setMax(pane.getWidth() / 24);
        hboxBottom.setTranslateX(pane.getWidth() / 6);
    }
    
    private void winAnnouncement() {
        System.out.println("Landed");
        winAnnouncement = new VBox();
        win = new Label();
        
        win.setTextFill(Color.GREEN);
        win.setFont(new Font(100));
        win.setText("You Won!");
        winAnnouncement.setTranslateX(pane.getWidth() / 3);
        winAnnouncement.setTranslateY(pane.getHeight() / 3);
        winAnnouncement.getChildren().add(win);
        pane.getChildren().add(winAnnouncement);
    }
    
    private void removeWinAnnouncement() {
        pane.getChildren().remove(winAnnouncement);
    }

    //private void windAnimation() {
    //    ImageView iv = new ImageView(new Image("/images/red-car.png"));
    //    iv.setTranslateX(pane.getWidth() / 2);
    //    iv.setTranslateY(pane.getHeight() / 2);
    //   pane.getChildren().add(iv);
    //}
}
