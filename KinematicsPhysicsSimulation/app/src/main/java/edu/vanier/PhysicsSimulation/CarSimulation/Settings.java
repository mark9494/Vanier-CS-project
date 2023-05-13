/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.CarSimulation;

import eu.hansolo.medusa.Gauge;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
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
    protected static final DecimalFormat df2 = new DecimalFormat("0");
    protected double meetingDistance;
    protected Line meetLine;
    
    protected boolean carsMeet;
    protected Gauge blueGauge;    
    protected Gauge redGauge;
    
    @FXML
     protected BorderPane borderPane;

    @FXML
    protected Pane redGaugePane, blueGaugePane, roadPane;
    @FXML
    protected Line top;
    
    @FXML
    protected Line line1, line2, line3, line4, line5, line6, line7;
    
    @FXML
     protected Slider redInitialPositionSlider, redFinalPositionSlider,blueInitialPositionSlider, blueFinalPositionSlider, blueInitialVelocitySlider, blueAccelerationSlider, redInitialVelocitySlider, redAccelerationSlider;
    
    @FXML
     protected Button startBtn, stopBtn, resetBtn, submitBtn, positionGraphBtn, velocityGraphBtn, homeBtn;
   
    @FXML
    protected MenuBar menuBar;
    
    @FXML
    protected Line bottom;

    @FXML
    protected Pane middlePane;
    
    @FXML
    protected Pane bottomPane;
    
    @FXML
    protected Pane topPane;
    
    @FXML
    protected Label windowTitle;
    
    @FXML
    protected Text bluePositionLabel, blueVelocityLabel, blueAccelerationLabel, blueTimeLabel;
    
    @FXML
    protected Text redPositionLabel, redVelocityLabel, redAccelerationLabel, redTimeLabel;
    
    @FXML
    protected Text txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8;
    
    @FXML
    protected Text txt9, txt10, txt11, txt12, txt13, txt14, txt15, txt16, txt17, txt18;
    
    protected ArrayList<Line> dottedLines;
    protected ArrayList<Line> allLines;
    protected ArrayList<Rectangle> cars;
    protected ArrayList<Text> text;
    protected ArrayList<Pane> pane;
    protected ArrayList<Button> button;
    
    protected double[][] redCarPositionGraph;
    protected double[][] blueCarPositionGraph;
    
    protected double[][] redCarVelocityGraph;
    protected double[][] blueCarVelocityGraph;
    
    protected Car blueCar;
    protected Car redCar;
    public static Timeline timeline; 
    public static Timeline timelineToUpdateSliders; 
  
    protected double animationDuration = 50;
    protected double currentRate = 10;
    
    protected double animationDuration2 = 100;
    protected double currentRate2 = 1;     
  
    protected Timeline graphTimeline;
    
    protected File loadSave, newSave;
   
    protected CarsIO fileHandler;
}
