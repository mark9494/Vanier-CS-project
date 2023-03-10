/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.CarSimulation;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 *
 * @author antho
 */
public class CarSimulationWindowController extends Settings {

    @FXML
    Line top;
    
    @FXML
    Line middle;

    @FXML
    Line bottom;

    @FXML
    Pane paneMiddle;

    @FXML
    public void initialize() {
        createAnimation();

        timeline.play();

        paneMiddle.widthProperty().addListener((obs, oldVal, newVal) -> {
            resizeLineHorizontal();
        });
        paneMiddle.heightProperty().addListener((obs, oldVal, newVal) -> {

        });
    }

    public void resizeLineHorizontal() {
        double x = paneMiddle.getWidth();

        top.setStartX(paneMiddle.getWidth() + 50);
        top.setEndX(paneMiddle.getMinWidth() - 55);

        //middle.
        bottom.setEndX(paneMiddle.getWidth() + 50);
    }

    public void createAnimation() {
        timeline = new Timeline(
                new KeyFrame(Duration.millis(animationDuration), e -> handleUpdateAnimation()));
        timeline.setRate(currentRate);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void handleUpdateAnimation() {

    }

}
