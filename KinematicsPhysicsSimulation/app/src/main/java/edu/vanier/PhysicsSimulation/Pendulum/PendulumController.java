/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.Pendulum;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;

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
    private Slider massSlider, dampingSlider, gravitySlider, lengthSlider;
    
    @FXML
    private Button playBtn, stopBtn, pauseBtn, graphBtn;
    
    @FXML
    private Separator vSeparator, hSeparator;

    @FXML
    private Circle circle;
    
    private GraphicsContext gc;
    
    private double currentAngle;
    private double maxAngle;
    
    private double length = 100;
    
    /**
     * This method is to help us draw the string that holds the bob.
     * Every time its position is updated the method clears everything on the 
     * canvas and draw a new line.
     * @param gc 
     */
    private void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        double startY = circle.getCenterY();
        double startX = circle.getCenterX() - 3;
        
        double endY = startY - length;
        double endX = startX - length*Math.sin(currentAngle);
        
        gc.strokeLine(startX, startY, endX, endY);
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        circle.setCenterX(canvas.getWidth()/2);
        circle.setCenterY(canvas.getHeight()/2);
        gc  = canvas.getGraphicsContext2D();
        draw(gc);
        lengthSlider.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                setLength(lengthSlider.getValue()+100);
                draw(gc);
            }
        });

        MoveTo moveTo = new MoveTo();
        moveTo.setX(50.0);
        moveTo.setY(50.0);

        ArcTo arcTo = new ArcTo();
        arcTo.setLargeArcFlag(true);
        arcTo.setSweepFlag(false);
        arcTo.setRadiusX(50);
        arcTo.setRadiusY(50);
        arcTo.setXAxisRotation(0);
        /*arcTo.setX(500);
        arcTo.setY(100);
        arcTo.setX(circle.getCenterX());
        arcTo.setY(circle.getCenterY() );
        arcTo.setY(100);*/
        Path arcPath = new Path();
        arcPath.getElements().addAll(new MoveTo(50, 50), arcTo);
        arcPath.setLayoutX(circle.getCenterX() - arcTo.getRadiusX()/2);
        arcPath.setLayoutY(circle.getCenterY() + arcTo.getRadiusY()/2);
        
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(1.0));
        pathTransition.setPath(arcPath);
        pathTransition.setNode(circle);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);
        
        pathTransition.play();
        /*
        //pendulum arc
        

        //transforming pendulum arc to shape
        
        arcPath.setOpacity(0.1);

        //adding all into pathtransition
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(1.0));
        pathTransition.setPath(arcPath);
        pathTransition.setNode(circle);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);

        animationPane.getChildren().add(arcPath);
        animationPane.getChildren().add(circle);
        
        pathTransition.play();
        */
    }

    
    
    
}
