/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.CarSimulation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 *
 * @author antho
 */
public class CarSimulationWindowController extends Settings{
   
    @FXML
    Line h; 
    @FXML
    Pane paneMiddle;
    
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("hh");
        createAnimation();
        
        
    }
    
    public void makeLineResizable(){
        
        
        
            
       
    }
    
    public void createAnimation() {
        timeline = new Timeline(
                new KeyFrame(Duration.millis(animationDuration), e -> handleUpdateAnimation()));
        timeline.setRate(currentRate);
        timeline.setCycleCount(Timeline.INDEFINITE);

    }
    
    
    private void handleUpdateAnimation() {
        System.out.println("hello");
    }

}

    
    
    
    
    
    
   
    
    
       
       
    

