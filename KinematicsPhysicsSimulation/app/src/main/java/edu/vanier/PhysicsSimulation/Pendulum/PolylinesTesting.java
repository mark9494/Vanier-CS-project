/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.Pendulum;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;

/**
 *
 * @author Youssif
 */
public class PolylinesTesting implements Initializable {
    
    @FXML
    Pane pane;

    @FXML
    Circle circle;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        double length = 100;
        double maxAngle = Math.PI ;
        double currentAngle = 0.05 * maxAngle;
        double thisAngle = currentAngle;
        circle.setTranslateX(300 + length);
        circle.setTranslateY(200 - length);
        
         
        Polyline line = new Polyline();
        Double[] points = new Double[(int)(maxAngle / currentAngle) * 2];
        double x;
        double y;
        int i = 0;
        int index = 0;
        while(currentAngle <= maxAngle){
            
            x = circle.getTranslateX() -  length * (1 - Math.cos(i*thisAngle));
            y = circle.getTranslateY() + length * (Math.sin(i*thisAngle));
            points[index] = x;
            points[index + 1] = y;
            i++;
            index = index + 2;
            currentAngle  = currentAngle + thisAngle;
        }
        line.getPoints().addAll(points);
        pane.getChildren().add(line);
        
        PathTransition anim = new PathTransition();
        anim.setInterpolator(Interpolator.EASE_BOTH);
        anim.setPath(line);
        anim.setNode(circle);
        anim.setDuration(Duration.seconds(1));
        anim.setCycleCount(Animation.INDEFINITE);
        anim.setAutoReverse(true);
        anim.play();
        
                
    }
    /*
    Polyline line = new Polyline();
        Double[] points = new Double[(int)(maxAngle / currentAngle) * 2];
        double x;
        double y;
        int i = 0;
        int index = 0;
        while(currentAngle <= maxAngle){
            x = circle.getTranslateX() +  length * (Math.sin(i*thisAngle));
            y = circle.getTranslateY() -  length * (1 - Math.cos(i*thisAngle));
            points[index] = x;
            points[index + 1] = y;
            i++;
            index = index + 2;
            currentAngle  = currentAngle + thisAngle;
        }
        line.getPoints().addAll(points);
        pane.getChildren().add(line);
    */
    
    
}
