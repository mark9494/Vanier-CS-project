/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.Pendulum;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;

/**
 *
 * @author antho
 */
public class GraphController implements Initializable{
    
    public static final int MAXIMUM_NUMBER_OF_POINTS = 20;

    int currentxposition;
    
    int currentyposition;
    
    int timeInSec;
    
    @FXML
    private LineChart<?, ?> positionGraph;
    
    @FXML
    private CategoryAxis timeAxis;

    @FXML
    private NumberAxis positionAxis;
    
    XYChart.Series seriesX = new XYChart.Series();
    XYChart.Series seriesY = new XYChart.Series();
    
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), event -> generateData()));
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
        seriesX.setName("X Postion");
        
        seriesY.setName("Y Position");
        
        positionAxis.setLabel("Time");
        
        timeAxis.setLabel("Position");
        
        positionGraph.getData().addAll(seriesX, seriesY);
        
    }

    private void generateData() {
        setCurrentXPosition((int) PendulumController.getCurrentXPosition());
        setCurrentYPosition((int) PendulumController.getCurrentYPosition());
        seriesX.getData().add(new XYChart.Data(String.valueOf(timeInSec),getCurrentXPosition()));
        seriesY.getData().add(new XYChart.Data(String.valueOf(timeInSec),getCurrentYPosition()));
        if(seriesX.getData().size() > MAXIMUM_NUMBER_OF_POINTS){
            seriesX.getData().remove(0);
            seriesY.getData().remove(0);
        }
        timeInSec++;
    }

    public Integer getCurrentXPosition() {
        return currentxposition;
    }
    public Integer getCurrentYPosition() {
        return currentyposition;
    }
    public void setCurrentXPosition(int currentposition){
        this.currentxposition = currentposition;
    }
    public void setCurrentYPosition(int currentposition){
        this.currentyposition = currentposition;
    }
    public void stopAnimations(){
        timeline.stop();
    }
}
