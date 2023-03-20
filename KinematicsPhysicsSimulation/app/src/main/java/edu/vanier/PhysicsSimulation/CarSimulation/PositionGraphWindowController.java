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
     XYChart.Series series = new XYChart.Series(); 
    
     series.getData().add(new XYChart.Data(df.format(blueCarPositionGraph[0][1])+"", Double.parseDouble((df.format(blueCarPositionGraph[0][0])))));
     series.getData().add(new XYChart.Data(df.format(blueCarPositionGraph[1][1])+"", Double.parseDouble((df.format(blueCarPositionGraph[1][0])))));
     series.getData().add(new XYChart.Data(df.format(blueCarPositionGraph[2][1])+"", Double.parseDouble((df.format(blueCarPositionGraph[2][0])))));
     series.getData().add(new XYChart.Data(df.format(blueCarPositionGraph[3][1])+"", Double.parseDouble((df.format(blueCarPositionGraph[3][0])))));
     series.getData().add(new XYChart.Data(df.format(blueCarPositionGraph[4][1])+"", Double.parseDouble((df.format(blueCarPositionGraph[4][0])))));
     series.getData().add(new XYChart.Data(df.format(blueCarPositionGraph[5][1])+"", Double.parseDouble((df.format(blueCarPositionGraph[5][0])))));
     series.getData().add(new XYChart.Data(df.format(blueCarPositionGraph[6][1])+"", Double.parseDouble((df.format(blueCarPositionGraph[6][0])))));
     series.getData().add(new XYChart.Data(df.format(blueCarPositionGraph[7][1])+"", Double.parseDouble((df.format(blueCarPositionGraph[7][0])))));
     series.getData().add(new XYChart.Data(df.format(blueCarPositionGraph[8][1])+"", Double.parseDouble((df.format(blueCarPositionGraph[8][0])))));
     series.getData().add(new XYChart.Data(df.format(blueCarPositionGraph[9][1])+"", Double.parseDouble((df.format(blueCarPositionGraph[9][0])))));

     XYChart.Series series2 = new XYChart.Series();

     series2.getData().add(new XYChart.Data(df.format(redCarPositionGraph[0][1])+"", Double.parseDouble((df.format(redCarPositionGraph[0][0])))));
     series2.getData().add(new XYChart.Data(df.format(redCarPositionGraph[1][1])+"", Double.parseDouble((df.format(redCarPositionGraph[1][0])))));
     series2.getData().add(new XYChart.Data(df.format(redCarPositionGraph[2][1])+"", Double.parseDouble((df.format(redCarPositionGraph[2][0])))));
     series2.getData().add(new XYChart.Data(df.format(redCarPositionGraph[3][1])+"", Double.parseDouble((df.format(redCarPositionGraph[3][0])))));
     series2.getData().add(new XYChart.Data(df.format(redCarPositionGraph[4][1])+"", Double.parseDouble((df.format(redCarPositionGraph[4][0])))));
     series2.getData().add(new XYChart.Data(df.format(redCarPositionGraph[5][1])+"", Double.parseDouble((df.format(redCarPositionGraph[5][0])))));
     series2.getData().add(new XYChart.Data(df.format(redCarPositionGraph[6][1])+"", Double.parseDouble((df.format(redCarPositionGraph[6][0])))));
     series2.getData().add(new XYChart.Data(df.format(redCarPositionGraph[7][1])+"", Double.parseDouble((df.format(redCarPositionGraph[7][0])))));
     series2.getData().add(new XYChart.Data(df.format(redCarPositionGraph[8][1])+"", Double.parseDouble((df.format(redCarPositionGraph[8][0])))));
     series2.getData().add(new XYChart.Data(df.format(redCarPositionGraph[9][1])+"", Double.parseDouble((df.format(redCarPositionGraph[9][0])))));


       System.out.println(blueCarPositionGraph[0][1]);     
//       System.out.println(blueCarPositionGraph[1][0]);     
//       System.out.println(blueCarPositionGraph[2][0]);     
//       System.out.println(blueCarPositionGraph[3][0]);     
//       System.out.println(blueCarPositionGraph[4][0]);     
//       System.out.println(blueCarPositionGraph[5][0]);     
//       System.out.println(blueCarPositionGraph[6][0]);
//       System.out.println(blueCarPositionGraph[7][0]);
//       System.out.println(blueCarPositionGraph[8][0]);
//       System.out.println(blueCarPositionGraph[9][0]);
       
     blueLineChart.getData().addAll(series);
     redLineChart.getData().addAll(series2);
   // createAnimationToUpdateData();
  //  graphTimeline.play();  
    }
   
    public PositionGraphWindowController(Car blueCar, Car redCar, double[][] blueCarPoints, double[][] redCarPoints){
        
       this.blue = blueCar;
       this.red = redCar;
       this.redCarPositionGraph = redCarPoints;
       this.blueCarPositionGraph = blueCarPoints;   
    }
    
    
//    private void createAnimationToUpdateData(){
//        
//       graphTimeline = new Timeline(
//              new KeyFrame(Duration.millis(100), e -> updateGraph()));
//        graphTimeline.setRate(1);
//        graphTimeline.setCycleCount(Timeline.INDEFINITE); 
//    }
    
    
//   private void updateGraph() { // TODO : fix graphs not working properly
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
    
    
    

