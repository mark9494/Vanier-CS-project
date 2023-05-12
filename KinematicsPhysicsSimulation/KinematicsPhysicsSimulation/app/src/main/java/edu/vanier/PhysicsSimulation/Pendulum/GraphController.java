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

    int currentposition;
    
    int timeInSec;
    
    @FXML
    private LineChart<?, ?> positionGraph;
    
    @FXML
    private CategoryAxis timeAxis;

    @FXML
    private NumberAxis positionAxis;
    
    XYChart.Series series = new XYChart.Series();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), event -> generateData()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
        series.setName("Data");
        positionAxis.setLabel("Time");
        timeAxis.setLabel("Position");
        positionGraph.getData().add(series);
        
    }

    private void generateData() {
        setCurrentPosition((int) PendulumController.getCurrentPosition());
        series.getData().add(new XYChart.Data(String.valueOf(timeInSec),getCurrentPosition()));
        if(series.getData().size() > MAXIMUM_NUMBER_OF_POINTS){
            series.getData().remove(0);
        }
        timeInSec++;
    }

    public Integer getCurrentPosition() {
        return currentposition;
    }
    
    public void setCurrentPosition(int currentposition){
        this.currentposition = currentposition;
    }
    
}
