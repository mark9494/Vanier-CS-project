/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.CarSimulation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author antho
 */
public class Settings {
    
    
    protected static final DecimalFormat df = new DecimalFormat("0.00");
    
    
    @FXML
    protected Line top;
    
    @FXML
    protected Line line1, line2, line3, line4, line5, line6, line7, line8;
    
    @FXML
     protected Slider redInitialPositionSlider, redFinalPositionSlider,blueInitialPositionSlider, blueFinalPositionSlider, blueInitialVelocitySlider, blueAccelerationSlider, redInitialVelocitySlider, redAccelerationSlider;
    
    @FXML
     protected Button startBtn, stopBtn, resetBtn, submitBtn, positionGraphBtn, velocityGraphButton;
    
    @FXML
    protected Line bottom;

    @FXML
    protected Pane middlePane;
    
    @FXML
    protected Pane bottomPane;
    
    @FXML
    protected Text bluePositionLabel, blueVelocityLabel, blueAccelerationLabel, blueTimeLabel;
    @FXML
    protected Text redPositionLabel, redVelocityLabel, redAccelerationLabel, redTimeLabel;
    
    protected ArrayList<Line> dottedLines;
    protected ArrayList<Line> allLines;
    protected ArrayList<Rectangle> cars;
    
    protected double[][] redCarPositionGraph;
    protected double[][] blueCarPositionGraph;
    
    protected Car blueCar;
    protected Car redCar;
    protected Timeline timeline; 
    protected Timeline timelineToUpdateSliders; 
  
    protected double animationDuration = 50;
    protected double currentRate = 10;
    
    protected double animationDuration2 = 100;
    protected double currentRate2 = 1;      // the second animation uses these two variables because it showcases the livestats slower whcih makes them readable to the user
  
    protected Timeline graphTimeline;
    
    
}
