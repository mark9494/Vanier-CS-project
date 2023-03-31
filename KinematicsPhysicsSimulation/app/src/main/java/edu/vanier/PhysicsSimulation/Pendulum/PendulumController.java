/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.Pendulum;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Youssif
 */
@Getter
@Setter
public class PendulumController implements Initializable{
    @FXML
    private Pane Base, animationPane;
    
    @FXML
    private Canvas canvas;
    
    @FXML
    private Slider massSlider, dampingSlider, gravitySlider, lengthSlider, amplitudeSlider;
    
    @FXML
    private Button playBtn, stopBtn, pauseBtn, graphBtn;
    
    @FXML
    private Separator vSeparator, hSeparator;

    @FXML
    private Circle circle;
    
    private GraphicsContext gc;
    
    private double currentAngle;
    private double maxAngle;
    private double length;
    private double mass;
    private int damping;
    private double gravity;
    private double angularFrequency;
    private double duration;
    
    /**
     * This method is to help us draw the string that holds the bob.
     * Every time its position is updated the method clears everything on the 
     * canvas and draw a new line.
     * @param gc 
     */
    private void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        double startY = circle.getTranslateY();
        double startX = circle.getTranslateX();
        double endY = canvas.getHeight()/2 - length;
        double endX = canvas.getWidth()/2 ;
        
        gc.strokeLine(startX, startY, endX, endY);
    }
    private Polyline createPath() {
        currentAngle = 0.1 * maxAngle;
        double thisAngle = currentAngle;
        Polyline polyline = new Polyline();
        Double[] pointsList = new Double[(int)(maxAngle / currentAngle) *2];
        pointsList[0] = (circle.getTranslateX());
        pointsList[1] = (circle.getTranslateY());
        int i = 0;
        double displacementonX = circle.getTranslateX();
        double displacementonY = circle.getTranslateY();
        while(currentAngle <= maxAngle){
            displacementonX = displacementonX + length * Math.sin(thisAngle);
            displacementonY = displacementonY + length * (1 - Math.cos(thisAngle));
            pointsList[i] = (displacementonX);
            pointsList[i + 1] = (displacementonY);
            System.out.println("Max Angle:" + maxAngle + " Current Angle: "+ currentAngle + "displacementonX: " + displacementonX + ", displacementonY: " + displacementonY);
            currentAngle = currentAngle + thisAngle;
            i = i+2;
        }
        polyline.getPoints().addAll(pointsList);
        return polyline;
    }
    public void setSlider(){
        lengthSlider.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                setLength(lengthSlider.getValue());
                draw(gc);
                if(damping == 0){
                    setAngularFrequency(Math.sqrt(gravity / length));
                }else{// TODO: Setup the Angular Frequency when there is damping}
                } 
            }
        });
        massSlider.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                setMass(massSlider.getValue());
            }
        });
        gravitySlider.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                setGravity(gravitySlider.getValue());
                if(damping == 0){
                    setAngularFrequency(Math.sqrt(gravity / length));
                }else{// TODO: Setup the Angular Frequency when there is damping}
                } 
            }
        });
        dampingSlider.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                // TODO: Setup the Angular Frequency when there is damping
                setDamping((int)dampingSlider.getValue());
            }
        });
        amplitudeSlider.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                setMaxAngle(Math.PI * amplitudeSlider.getValue()/180);
            }
        });
    }
    private void disableSlider(boolean b) {
        lengthSlider.setDisable(b);
        massSlider.setDisable(b);
        dampingSlider.setDisable(b);
        gravitySlider.setDisable(b);
        amplitudeSlider.setDisable(b);
    }
    private void setValues(){
        maxAngle = Math.PI * amplitudeSlider.getValue()/180;
        length = lengthSlider.getValue();
        mass = massSlider.getValue();
        damping = (int) dampingSlider.getValue();
        gravity = gravitySlider.getValue();
        angularFrequency = Math.sqrt(gravity / length);
        duration = 2;
    }
    public void setBtns(Animation animation){
        playBtn.setOnAction((e) -> {
            if (pauseBtn.isDisable() == true) {
                animation.playFrom(Duration.ONE);
                pauseBtn.setDisable(false);
            } else {
                animation.play();
                
            }
            pauseBtn.setDisable(false);
            stopBtn.setDisable(false);
            disableSlider(true);
        });

        pauseBtn.setOnAction((e) -> {
            animation.pause();
        });

        stopBtn.setOnAction((e) -> {
            animation.stop();
            pauseBtn.setDisable(true);
            disableSlider(false);
        });
    }
    
    public void circleProperties(){
        
        circle.translateXProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                draw(gc);
                
            }
            
        });
    }
    
    public Animation animate(Polyline path, int numbCycles, boolean isAutoReverse, double duration){
        PathTransition animation = new PathTransition();
        animation.setCycleCount(numbCycles);
        animation.setAutoReverse(isAutoReverse);
        animation.setDuration(Duration.seconds(duration));
        animation.setPath(path);
        animation.setNode(circle);
        animation.setOnFinished((event) -> {
            System.out.println("Animation done");
        });
        return animation;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Adjusting the enviornment and the nodes:
        animationPane.setLayoutX(0);
        animationPane.setLayoutY(0);
        canvas.setLayoutX(animationPane.getLayoutX());
        canvas.setLayoutY(animationPane.getLayoutY());
        
        
        circle.setTranslateX(canvas.getWidth()/2);
        circle.setTranslateY(canvas.getHeight()/2);
        
        gc  = canvas.getGraphicsContext2D();
        
        
        //Setting up the sliders to get the inputted data
        setSlider();
        
        setValues();
        draw(gc);
        toString();
        //Setting up the buttons
        playBtn.setDisable(false);
        pauseBtn.setDisable(true);
        stopBtn.setDisable(true);
        System.out.println(toString());
        
        playBtn.setOnAction((e)->{
            animate(createPath(), 2, true, duration).play();   
        });
        
        circleProperties();
    }

    @Override
    public String toString() {
        return "PendulumController{" + "currentAngle=" + currentAngle + ", maxAngle=" + maxAngle + ", length=" + length + ", mass=" + mass + ", damping=" + damping + ", gravity=" + gravity + ", angularFrequency=" + angularFrequency + ", duration=" + duration + '}';
    }
    
}