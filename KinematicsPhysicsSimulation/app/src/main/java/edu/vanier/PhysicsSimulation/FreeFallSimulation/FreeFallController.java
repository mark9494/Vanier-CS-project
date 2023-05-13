/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.FreeFallSimulation;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FreeFallController extends FreeFallSettings {

    @FXML
    public void initialize() {
        lblBuildingHeight.setTextFill(Color.WHITESMOKE);
        fileHandler = new IOFreeFall(building, accelerationY);
        building.setTranslateY(30);
        ball = new Ball();
        motionPane.getChildren().add(ball);
        createAnimation();
        timelineInitializeBall.play();
        Image basketball = new Image("/images/building2.png");
        building.setFill(new ImagePattern(basketball));
        setDefaultBackGround();
        setupMenuBar();
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

        timelineInitializeBall = new Timeline(
                new KeyFrame(Duration.millis(animationDuration),
                        e -> handleInitializeAnimation()));
        timelineInitializeBall.setRate(currentRate);
        timelineInitializeBall.setCycleCount(Timeline.INDEFINITE);
    }

    public void handleUpdateAnimation() {
        ball.setRotate(ball.getRotate() - Math.PI);
        ball.setTranslateY(ball.getTranslateY() + ball.getDy());
        endAnimation();
        moveBall();
        setTimeAndFinalVelocity();
    }

    public void handleInitializeAnimation() {
        lblBuildingHeight.setText("" + df.format(building.getHeight()) + " m");
        lblBuildingHeight.setTranslateY(building.getTranslateY()-10);
        building.setWidth(motionPane.getWidth() / 4);
        sldHeight.setMax(motionPane.getHeight() - 50);
        ball.setTranslateX(building.getWidth() - Ball.RADIUS);
        ball.setTranslateY(building.getTranslateY() - Ball.RADIUS);
        building.setTranslateY(sldHeight.getValue());
        building.setHeight(motionPane.getHeight() - building.getTranslateY());

    }

    @FXML
    public void handlePlay() {
        disableButtons(true, false);
        disableSliders(true, true);
        timelineInitializeBall.stop();
        readSliders();
        ball.setDy(1);
        ball.setTranslateX(ball.getTranslateX() + 3 * Ball.RADIUS);
        timelineFreeFall.play();
        System.out.println(initialHeight);
    }

    @FXML
    public void handleReset() {
        disableSliders(false, false);
        disableButtons(false, true);
        timelineFreeFall.stop();
        timelineInitializeBall.play();

    }

    public void endAnimation() {
        if (ball.getTranslateY() + Ball.RADIUS >= motionPane.getHeight()) {
            timelineFreeFall.stop();
            ball.setTranslateY(motionPane.getHeight() - Ball.RADIUS);
        }
    }

    public void readSliders() {
        initialHeight = sldHeight.getValue();
        accelerationY = sldAccelerationY.getValue();
    }

    public void moveBall() {
        ball.setDy(ball.getDy() + ball.getDy() * accelerationY / 400);
    }

    public void disableButtons(boolean play, boolean reset) {
        btnStart.setDisable(play);
        btnReset.setDisable(reset);
    }

    public void disableSliders(boolean slider1, boolean slider2) {
        sldHeight.setDisable(slider1);
        sldAccelerationY.setDisable(slider2);
    }

    public void setTimeAndFinalVelocity() {
        finalVelocity = -returnFinalVelocity(accelerationY, building.getHeight());
        time = returnTime(finalVelocity, accelerationY, 0);
        lblFinalVelocity.setText("" + df.format(finalVelocity));
        lblTime.setText("" + df.format(time));

    }

    private void setupMenuBar() {
        FileChooser fileChooser = new FileChooser();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        menuBar.getMenus().remove(2);
        MenuItem save = new MenuItem("Save Last Run");
        MenuItem openSave = new MenuItem("Open Save");
        menuBar.getMenus().get(0).getItems().addAll(save, openSave);
        menuBar.getMenus().get(0).getItems().remove(0);

        MenuItem darkMode = new MenuItem("Dark Mode");
        MenuItem lightMode = new MenuItem("Light Mode");
        menuBar.getMenus().get(1).getItems().remove(0);
        menuBar.getMenus().get(1).getItems().addAll(darkMode, lightMode);

        EventHandler<ActionEvent> savePressed = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                newSave = directoryChooser.showDialog(new Stage());
                fileHandler.writeDataInFile(newSave.getPath() + "\\FreeFallSim.csv");
            }
        };

        EventHandler<ActionEvent> loadSaved = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                loadSave = fileChooser.showOpenDialog(new Stage());
                try {
                    System.out.println("hiiiii");
                    fileHandler.readDataInFile(loadSave.getPath());
                    building.setHeight(initialHeight);
                    setBackSliders();
                } catch (IOException ex) {
                    System.out.println("File Not Read Properly. ");
                }
            }
        };

        EventHandler<ActionEvent> handleDark = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                darkMode();
            }
        };

        EventHandler<ActionEvent> handleLight = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                lightMode();
            }
        };
        save.setOnAction(savePressed);
        openSave.setOnAction(loadSaved);
        darkMode.setOnAction(handleDark);
        lightMode.setOnAction(handleLight);
    }

    private void darkMode() {
        topPane.setStyle("-fx-background-color: #282828");
        bottomPane.setStyle("-fx-background-color: #282828");
        lblHeight.setTextFill(Color.WHITESMOKE);
        lblTime2.setTextFill(Color.WHITESMOKE);
        lblVel.setTextFill(Color.WHITESMOKE);
        lblAcc.setTextFill(Color.WHITESMOKE);
        lblTime.setTextFill(Color.WHITESMOKE);
        lblFinalVelocity.setTextFill(Color.WHITESMOKE);
    }

    private void lightMode() {
        topPane.setStyle("-fx-background-color: #FFFFFF");
        bottomPane.setStyle("-fx-background-color: #FFFFFF");
        lblHeight.setTextFill(Color.BLACK);
        lblTime2.setTextFill(Color.BLACK);
        lblVel.setTextFill(Color.BLACK);
        lblAcc.setTextFill(Color.BLACK);
        lblTime.setTextFill(Color.BLACK);
        lblFinalVelocity.setTextFill(Color.BLACK);
    }

    public void setBackSliders() {
        sldAccelerationY.setValue(accelerationY);
        sldHeight.setValue(initialHeight);
    }

}
