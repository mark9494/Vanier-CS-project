/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.ProjectileMotion;

import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author antho
 */
public class ProjectileMotionController extends ProjectileMotionSettings {

    @FXML
    public void initialize() {
        int initranslateX = 500;
        Ramp ramp = new Ramp();
        ramp.setTranslateX(300);
        ramp.setHeight(ramp.getHeight());
        LandingArea landingArea = new LandingArea(50, 50);
        Ball ball = new Ball();
        landingArea.setTranslateX(initranslateX);
        pane.getChildren().addAll(landingArea, ball, ramp);

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
        System.out.println(pane.getHeight());
    }

}
