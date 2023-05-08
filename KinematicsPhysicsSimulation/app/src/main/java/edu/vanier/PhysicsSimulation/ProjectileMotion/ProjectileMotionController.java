/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.ProjectileMotion;

import edu.vanier.PhysicsSimulation.PhysicsSimulationController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ProjectileMotionController extends ProjectileMotionSettings {

    @FXML
    public void initialize() {
        FileChooser fileChooser = new FileChooser();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        disableButtons(true, false);
        menuBar.getMenus().remove(2);

        MenuItem save = new MenuItem("Save Last Run");
        MenuItem openSave = new MenuItem("Open Save");
        MenuItem changeBallPicture = new MenuItem("Change Ball");
        MenuItem changeBackgroundPicture = new MenuItem("Change Background");

        menuBar.getMenus().get(0).getItems().addAll(save, openSave);
        menuBar.getMenus().get(1).getItems().addAll(changeBallPicture, changeBackgroundPicture);

        EventHandler<ActionEvent> savePressed = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                newSave = directoryChooser.showDialog(new Stage());
                IO.writeDataInFile(newSave.getPath() + "\\ProjectileMotionData.csv");
            }
        };
        EventHandler<ActionEvent> loadSaved = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                loadSave = fileChooser.showOpenDialog(new Stage());
                try {
                    IO.readDataInFile(loadSave.getPath());
                    loadVisualSettingsBack();
                } catch (IOException ex) {
                    System.out.println("File Not Read Properly. ");
                }
            }
        };
        EventHandler<ActionEvent> changeBallImage = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                EditChangesController.isBall = true;
                try {
                    openEditWindow();
                } catch (IOException ex) {
                    Logger.getLogger(ProjectileMotionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        EventHandler<ActionEvent> changeBackgroundImage = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                EditChangesController.isBall = false;
                try {
                    openEditWindow();
                } catch (IOException ex) {
                    Logger.getLogger(ProjectileMotionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        setDefaultBackGround();
        updateBackground();
        save.setOnAction(savePressed);
        openSave.setOnAction(loadSaved);
        changeBallPicture.setOnAction(changeBallImage);
        changeBackgroundPicture.setOnAction(changeBackgroundImage);
        wind = new Wind();
        cannon = new Cannon();
        cannon.setTranslateY(25);
        cannon.setTranslateX(100);
        landingArea = new LandingArea();
        ball = new Ball();
        setBallDefaultLocation();
        motionPane.getChildren().addAll(landingArea, ball, cannon);
        createAnimation();
        timelineRectangleAndBall.play();
        timelinePaneResize.play();

        int defaultPaneWidth = 980;
        int defaultPaneHeight = 200;
        landingArea.randomSpawn(defaultPaneWidth - landingArea.getWidth(),
                cannon.getTranslateX() + cannon.getWIDTH() + 25, defaultPaneHeight);
    }

    public void loadVisualSettingsBack() {
        sldInitialVelocity.setValue(initialVelocity);
        sldAccelerationY.setValue(accelerationY);
        sldRampAngle.setValue(rampAngle);
        if (isWind) {
            CBoxWind.setSelected(true);
            windBox.setOpacity(100);
            wind.setAngle(Wind.angle);
            wind.setIntensity(Wind.intensity);
            windArrow.setRotate(-Wind.angle * 180 / Math.PI);
            setIntensity();
        }
    }

    public void setBallDefaultLocation() {
        ball.setTranslateX(cannon.setCornerX() + ball.getRadius());
        ball.setTranslateY(cannon.getCornerY() - ball.getRadius() - 20);
    }

    public void createAnimation() {
        animationDuration = 15;
        currentRate = 4;

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
        wind.setIntensity(0);
        resetParameters();
        timelineRectangleAndBall.stop();
        timelinePaneResize.stop();
        timer.cancel();
    }

    @FXML
    public void handleBegin() {
        double currentRateBall = 5000;

        disableButtons(true, true);
        timelineRectangleAndBall.stop();
        generateParameters();

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
        removeWinLoseAnnouncement();
        disableButtons(true, false);
        setBallDefaultLocation();
        resetParameters();
        moveRectangleAndBall();
        timelineRectangleAndBall.play();
        landingArea.randomSpawn(motionPane.getWidth() - landingArea.getWidth(),
                cannon.getTranslateX() + cannon.getWIDTH() + 25, motionPane.getHeight());
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
        cannon.setRotate(cannon.getAngle());
        ball.setTranslateX(cannon.getCornerX() + ball.getRadius() + 20);
        ball.setTranslateY(cannon.getCornerY() - ball.getRadius() + 40);
    }

    private void generateParameters() {
        setDeltaY(motionPane.getHeight());
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
        ball.setTranslateX(ball.getTranslateX() + ball.getDx() + wind.getForceWindX());
    }

    private void moveBallY() {
        ball.setDy(ball.getDy() - accelerationY + wind.getForceWindY());
        ball.setTranslateY(ball.getTranslateY() - ball.getDy());
    }

    private void endOfMotion() {
        if (ball.getTranslateY() + ball.getRadius() >= motionPane.getHeight()) {
            ball.setTranslateY(motionPane.getHeight() - ball.getRadius());
            timelineBall.pause();
            finalPosition = ball.getTranslateX();
            return;
        }
        if (ball.getTranslateX() > motionPane.getWidth() || ball.getTranslateY() < 0) {
            ball.setTranslateY(motionPane.getHeight() - ball.getRadius());
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
                lblPosition.setText("" + df.format(ball.getTranslateX() - cannon.getCornerX()));
                winAnnouncement();
            } else {
                lblPosition.setText("" + df.format(ball.getTranslateX() - cannon.getCornerX()));
                LostAnnouncement();
                disableButtons(false, true);
            }
        }
    }

    private void handlePaneResizeAffects() {
        sldInitialVelocity.setMax(motionPane.getWidth() / 24);
        hboxBottom.setTranslateX(motionPane.getWidth() / 6);
        windBox.setTranslateX(motionPane.getWidth() - 1000);
        landingArea.setTranslateY(motionPane.getHeight() - landingArea.INIT_HEIGHT);
    }

    private void winAnnouncement() {
        winAnnouncement = new VBox();
        win = new Label();

        win.setTextFill(Color.GREEN);
        win.setFont(new Font(100));
        win.setText("Scored!");
        winAnnouncement.setTranslateX(motionPane.getWidth() / 3);
        winAnnouncement.setTranslateY(motionPane.getHeight() / 3);
        winAnnouncement.getChildren().add(win);
        motionPane.getChildren().add(winAnnouncement);
    }

    private void LostAnnouncement() {
        loseAnnouncement = new VBox();
        lose = new Label();

        lose.setTextFill(Color.RED);
        lose.setFont(new Font(100));
        lose.setText("Missed");
        loseAnnouncement.setTranslateX(motionPane.getWidth() / 3);
        loseAnnouncement.setTranslateY(motionPane.getHeight() / 3);
        loseAnnouncement.getChildren().add(lose);
        motionPane.getChildren().add(loseAnnouncement);
    }

    private void removeWinLoseAnnouncement() {
        motionPane.getChildren().remove(winAnnouncement);
        motionPane.getChildren().remove(loseAnnouncement);
    }

    private void openEditWindow() throws IOException {
        editorStage = new Stage();
        EditChangesController mainController = new EditChangesController();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/editImages.fxml"));
        loader.setController(mainController);
        Pane root = loader.load();
        Scene scene = new Scene(root);
        editorStage.initOwner(PhysicsSimulationController.projectileMotion);
        editorStage.setScene(scene);
        editorStage.setTitle("Editor");
        editorStage.sizeToScene();
        editorStage.show();
    }
}
