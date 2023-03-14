/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.CarSimulation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.util.Duration;

/**
 *
 * @author Mark
 */
public class PositionGraphWindowController extends CarSimulationWindowController {
    
    @FXML
    private LineChart<?, ?> blueLineChart;

    @FXML
    private CategoryAxis blueX;

    @FXML
    private NumberAxis blueY;

    @FXML
    private LineChart<?, ?> redLineChart;

    @FXML
    private CategoryAxis redX;

    @FXML
    private NumberAxis redY;

   
   
    
    private Car blue;
    private Car red;
   @FXML
    public void initialize() {
        
    createAnimationToUpdateData();
  //  graphTimeline.play();
     
       
    }
   
    public PositionGraphWindowController(Car blueCar, Car redCar){
       this.blue = blueCar;
       this.red = redCar;
    }
    
    
    private void createAnimationToUpdateData(){
        
       graphTimeline = new Timeline(
              new KeyFrame(Duration.millis(100), e -> updateGraph()));
        graphTimeline.setRate(1);
        graphTimeline.setCycleCount(Timeline.INDEFINITE); 
    }
    
    
   private void updateGraph() { // TODO : fix graphs not working properly
//       double time =blueCar.getTime();
//       
//       for(int i =10;i>0; i--){
//           XYChart.Series series = new XYChart.Series(); 
//     series.getData().add(new XYChart.Data(df.format(blue.getTime())+"", Double.parseDouble((df.format(red.getTranslateX()/10)))));
//     blueLineChart.getData().addAll(series);
//       }
       //if(blue.getTranslateX()<blue.getFinalPosition()- blue.getWidth()-10 && blue.getTranslateX()!=blue.getInitialPosition()){
//     XYChart.Series series = new XYChart.Series(); 
//     series.getData().add(new XYChart.Data(Double.parseDouble(df.format(blue.getTime()))+"", Double.parseDouble((df.format(red.getTranslateX()/10)))));
//     blueLineChart.getData().addAll(series);
    //   }
       
//     if(red.getTranslateX()<red.getFinalPosition()- red.getWidth()-10 && red.getTranslateX()!=red.getInitialPosition()){
//     XYChart.Series series2 = new XYChart.Series(); 
//     series2.getData().add(new XYChart.Data((df.format(red.getTime()))+"", Double.parseDouble(df.format(red.getTranslateX()/10))));
//     redLineChart.getData().addAll(series2);
//       }
    } 
    
    
    
}
