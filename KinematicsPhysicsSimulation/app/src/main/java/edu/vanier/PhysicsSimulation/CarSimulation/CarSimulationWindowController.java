/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.CarSimulation;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
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
    Line line1, line2, line3, line4, line5, line6, line7;
    
    @FXML
    Rectangle blueRectangle, redRectangle;
    
    @FXML
    Line bottom;

    @FXML
    Pane middlePane;
    
    @FXML
    Pane bottomPane;
    
    ArrayList<Line> dottedLines;
    ArrayList<Line> allLines;
    ArrayList<Rectangle> cars;
    
    @FXML
    public void initialize() {
        createAnimation();

        timeline.play();
        
        dottedLines = new ArrayList<>();
        allLines = new ArrayList<>();
        cars = new ArrayList<>();
        
        cars.add(redRectangle);
        cars.add(blueRectangle);
        
        
        dottedLines.add(line1);
        dottedLines.add(line2);
        dottedLines.add(line3);
        dottedLines.add(line4);
        dottedLines.add(line5);
        dottedLines.add(line6);
        dottedLines.add(line7);
            
        top.getStartY();
        allLines.addAll(dottedLines);
        allLines.add(top);
        allLines.add(bottom);
        middlePane.widthProperty().addListener((obs, oldVal, newVal) -> {
            resizeLineHorizontal();
        });
        middlePane.heightProperty().addListener((obs, oldVal, newVal) -> {
            resizeLineVertical(middlePane.getHeight());
        });
    }

    public void resizeLineHorizontal() {
        final int increaseLineLength = 50;

        top.setStartX(middlePane.getMinWidth()- increaseLineLength);
        top.setEndX(middlePane.getWidth());
        bottom.setEndX(middlePane.getWidth());
        
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

    public void createAnimation() {
        timeline = new Timeline(
                new KeyFrame(Duration.millis(animationDuration), e -> handleUpdateAnimation()));
        timeline.setRate(currentRate);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void handleUpdateAnimation() {
        //TODO move this to the moveRectangle method
        int dx = 1;
        if(blueRectangle.getTranslateX() != middlePane.getWidth()- blueRectangle.getWidth()){
        
        blueRectangle.setTranslateX(blueRectangle.getTranslateX() + dx);
        }
        
        
    }

}
