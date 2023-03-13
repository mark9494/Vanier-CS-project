/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.CarSimulation;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author antho
 */
public class CarSimulationWindowController extends Settings {
 
    @FXML
    Line top;
    
    @FXML
    Line line1, line2, line3, line4, line5, line6, line7, line8;
    
    @FXML
    Slider redInitialPositionSlider, redFinalPositionSlider,blueInitialPositionSlider, blueFinalPositionSlider, blueInitialVelocitySlider, blueAccelerationSlider, redInitialVelocitySlider, redAccelerationSlider;
    
    @FXML
     private Button startBtn, stopBtn, resetBtn, submitBtn;
    
    @FXML
    Line bottom;

    @FXML
    public Pane middlePane;
    
    @FXML
    public Pane bottomPane;
    
    ArrayList<Line> dottedLines;
    ArrayList<Line> allLines;
    ArrayList<Rectangle> cars;
    
    private Car blueCar;
    private Car redCar;
    
    
    @FXML
    public void initialize() {
   
        createAnimationToUpdateData();
        timelineToUpdateSliders.play();
        disableBtns(true, true, true,false);
        
        blueCar = new Car(5, 82, "blue"); 
        redCar = new Car(5, 122);
        
        middlePane.getChildren().addAll(blueCar,redCar);
 
        createAnimation();

        dottedLines = new ArrayList<>();
        allLines = new ArrayList<>();
        cars = new ArrayList<>();
        
        cars.add(blueCar);
        cars.add(redCar);

        dottedLines.add(line1);
        dottedLines.add(line2);
        dottedLines.add(line3);
        dottedLines.add(line4);
        dottedLines.add(line5);
        dottedLines.add(line6);
        dottedLines.add(line7);
 
        allLines.addAll(dottedLines);
        allLines.add(top);
        allLines.add(bottom);
        allLines.add(line8);
        middlePane.widthProperty().addListener((obs, oldVal, newVal) -> {
            resizeLineHorizontal();
            
        });
        middlePane.heightProperty().addListener((obs, oldVal, newVal) -> {
            resizeLineVertical(middlePane.getHeight());
        });
    }

    public void resizeLineHorizontal() {

        top.setEndX(middlePane.getWidth());
        bottom.setEndX(middlePane.getWidth());
        line8.setEndX(middlePane.getWidth());
        
        double lengthOfLine = middlePane.getWidth()/20;
        double gapBetweenLines = middlePane.getWidth()/40;
        
        for(int i = 0; i< dottedLines.size();i++){
            if(i!=0){
             dottedLines.get(i).setStartX(dottedLines.get(i-1).getEndX() +gapBetweenLines );
             dottedLines.get(i).setEndX(dottedLines.get(i).getStartX() +lengthOfLine );
            }else{
            dottedLines.get(i).setStartX(gapBetweenLines);
            dottedLines.get(i).setEndX(gapBetweenLines + lengthOfLine);     
            }
        }
    }
    
    public void resizeLineVertical(double oldHeight){
            
            if(oldHeight > middlePane.getHeight()){
                
                for(int i = 0; i < cars.size();i++){
                    
             cars.get(i).setLayoutY(cars.get(i).getLayoutY() - middlePane.getHeight()/500);
           }
        for(int i = 0; i< allLines.size();i++){
            
          allLines.get(i).setLayoutY(allLines.get(i).getLayoutY() - middlePane.getHeight()/500); 
        }
            }else{
           //TODO need to find a way to know if I should add or substract the panes height depending on the pane getting bigger or smaller
           for(int i = 0; i < cars.size();i++){
               
             cars.get(i).setLayoutY(cars.get(i).getLayoutY() + middlePane.getHeight()/500);
           }
        for(int i = 0; i< allLines.size();i++){  
            
          allLines.get(i).setLayoutY(allLines.get(i).getLayoutY() + middlePane.getHeight()/500);
        }
            }
    }

    private void createAnimation() {
        
        timeline = new Timeline(
                new KeyFrame(Duration.millis(animationDuration), e -> handleUpdateAnimation()));
        timeline.setRate(currentRate);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }
    private void createAnimationToUpdateData(){
        
       timelineToUpdateSliders = new Timeline(
                new KeyFrame(Duration.millis(animationDuration), e -> handleUpdateData()));
        timelineToUpdateSliders.setRate(currentRate);
        timelineToUpdateSliders.setCycleCount(Timeline.INDEFINITE); 
    }
    
      private void handleUpdateData() {
        
        updateSliderMax();
    }
      
    private void handleUpdateAnimation() { 
        blueCar.calculateCurrentVelocity();
        blueCar.calculateCurrentTime();
        
        //System.out.println(blueCar.getCurrentVelocity());
        redCar.calculateCurrentVelocity();
        redCar.calculateCurrentTime();

        moveCar();
    }
    
    public void updateSliderMax(){
      redInitialPositionSlider.setMax((middlePane.getWidth()/10));
      redFinalPositionSlider.setMin(redInitialPositionSlider.getValue());// this causes the final position ticks to start after the initial postion of the car
      redFinalPositionSlider.setMax(middlePane.getWidth()/10);
      
      blueInitialPositionSlider.setMax(middlePane.getWidth()/10);
      blueFinalPositionSlider.setMin(blueInitialPositionSlider.getValue());
      blueFinalPositionSlider.setMax(middlePane.getWidth()/10);
 
    }
    
    public void updateInput(){
       if(blueInitialPositionSlider.getValue() > blueInitialPositionSlider.getMax() - blueCar.getWidth()){
            blueCar.setInitialPosition((blueInitialPositionSlider.getValue()*10 - blueCar.getWidth())); // this prevents the car from going outside the pane
       }else{
         blueCar.setInitialPosition((blueInitialPositionSlider.getValue()*10));  
       }
        
        blueCar.setFinalPosition(blueFinalPositionSlider.getValue()*10);
        blueCar.setInitialVelocity(blueInitialVelocitySlider.getValue());
        blueCar.setAcceleration(blueAccelerationSlider.getValue());
        
        if(redInitialPositionSlider.getValue() > redInitialPositionSlider.getMax() - redCar.getWidth()){
           redCar.setInitialPosition(redInitialPositionSlider.getValue()*10 - redCar.getWidth()); 
        }else{
            redCar.setInitialPosition(redInitialPositionSlider.getValue()*10);
        }
        
        redCar.setFinalPosition(redFinalPositionSlider.getValue()*10);
        redCar.setInitialVelocity(redInitialVelocitySlider.getValue());
        redCar.setAcceleration(redAccelerationSlider.getValue());
        
        redCar.setTranslateX(redCar.getInitialPosition());
        blueCar.setTranslateX(blueCar.getInitialPosition()); 
    }

    private void moveCar(){

      if(blueCar.getTranslateX() < blueCar.getFinalPosition()- blueCar.getWidth()){// if I make a for loop, i wont have access to getFinalPosition (Ask teacher why)
          blueCar.setTranslateX(blueCar.getTranslateX() + blueCar.getCurrentVelocity()/15);
      }
      if(redCar.getTranslateX() < redCar.getFinalPosition() - redCar.getWidth()){// if I make a for loop, i wont have access to getFinalPosition (Ask teacher why)
          redCar.setTranslateX(redCar.getTranslateX() + redCar.getCurrentVelocity()/15);// divide by 15 because otherwise the acceleartion will be too fast
      }                                                     
    }
    
    @FXML
    private void handleStart(){
        
       timeline.play(); 
       disableBtns(true, false, true, true);
    }
    
    @FXML
    private void handleStop(){
       
       disableBtns(false,true,false, true); 
       timeline.pause();
    }
    
    @FXML
    private void handleReset(){
       
        disableBtns(true, true, true, false);
        timeline.stop();
    }
    @FXML
    private void handleSubmit(){
        
        updateInput();
        disableBtns(false, true, true, true);
        
        System.out.println(blueCar.getInitialPosition());
        System.out.println(blueCar.getFinalPosition());
        System.out.println(blueCar.getInitialVelocity());
        System.out.println(blueCar.getAcceleration());
        
    }
    
    private void disableBtns(boolean start, boolean stop, boolean reset, boolean submit){
        
      startBtn.setDisable(start);
      stopBtn.setDisable(stop);
      resetBtn.setDisable(reset); 
      submitBtn.setDisable(submit);
    }
    
    
    
    
    

}
