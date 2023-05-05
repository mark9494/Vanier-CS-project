/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.FreeFallSimulation;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author antho
 */
public class FreeFallSettings {

    @FXML
    protected Button btnHome;
    @FXML
    protected ImageView homeImgView;
    @FXML
    protected Pane motionPane;
    @FXML
    protected Slider sliderSpeed;
    
    protected Timeline timelineFreeFall;
    protected Ball ball;

}
