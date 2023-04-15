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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
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
    private Slider massSlider, dampingSlider, gravitySlider;
    
    @FXML
    private Button playBtn, stopBtn, pauseBtn, graphBtn;
    
    @FXML
    private Separator vSeparator, hSeparator;

    @FXML
    private Circle circle;
    private double initialCircleX;
    private double initialCircleY;
    
    private GraphicsContext gc;
    
    private double maxAngle;
    private double length;
    private double mass;
    private int damping;
    private double gravity;
    private double angularFrequency;
    private double duration;
    
    private final EventHandler<MouseEvent> eventMousePressed = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            circle.setCursor(Cursor.CLOSED_HAND);
        }
    };
    
    private final EventHandler<MouseEvent> eventMouseDragged = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            double x = (event.getSceneX() - circle.getRadius()*2) - (canvas.getWidth()/1.5-100);
            double y = event.getSceneY() - canvas.getHeight()/2;
            double placement = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) );
            if(placement<=300 && placement>=100 && (event.getSceneY() - canvas.getHeight()/2) >= 0){
            circle.setTranslateX(event.getSceneX() - circle.getRadius()*2);
            circle.setTranslateY(event.getSceneY());
            setLength(placement);
            setMaxAngle(((Math.abs(Math.atan(x/y)))*180)/Math.PI);
            }
        }
    };
    
    private final EventHandler<MouseEvent> eventMouseReleased = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
           circle.setCursor(Cursor.OPEN_HAND);
        }
    };
    private final EventHandler<MouseEvent> eventMouseEnteredTarget = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
           circle.setCursor(Cursor.OPEN_HAND);
        }
    };
    
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
        //double endY = canvas.getHeight()/1.5 - length;
        //double endX = canvas.getWidth()/2 ;
        //double endY = initialCircleY - length;
        //double endX = initialCircleX ;
        double endY = canvas.getHeight()/2;
        double endX = canvas.getWidth()/1.5 -100;
        
        gc.strokeLine(startX, startY, endX, endY);
    }
    /**
     * This method is used to create a path that the pendulum will follow
     * @return PolyLine 
     */
  
    private Polyline createPath() {
        double RADIUS = length;
        double START_ANGLE = Math.toDegrees(Math.atan((canvas.getHeight() / 2 - circle.getTranslateY()) / ((canvas.getWidth() / 1.5 - 100) - circle.getTranslateX())));
        double ARC_ANGLE = 90 - Math.abs(START_ANGLE);
        System.out.println("Start angle: " + START_ANGLE);
        System.out.println("Arc angle: " + ARC_ANGLE);
        double startX = canvas.getWidth() / 1.5 - 100;
        double startY = canvas.getHeight() / 2;
        Polyline polyline = new Polyline();
        if (START_ANGLE > 0) {
            for (int i = 1; i < 2 * ARC_ANGLE; i++) {
                double angle = Math.toRadians(i + START_ANGLE);
                double x = startX + RADIUS * Math.cos(angle);
                double y = startY + RADIUS * Math.sin(angle);
                polyline.getPoints().addAll(x, y);
            }
        } else if (START_ANGLE < 0) {
            for (int i = 1; i < 2 * ARC_ANGLE; i++) {
                double angle = Math.toRadians(i + Math.abs(START_ANGLE));
                double x = startX - RADIUS * Math.cos(angle);
                double y = startY + RADIUS * Math.sin(angle);
                polyline.getPoints().addAll(x, y);
            }
        }
        return polyline;
    }
    
    
    private void clickAndDrag() {
            circle.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, eventMouseEnteredTarget);
            
            //When its closed and pressed make it drags
            //Adding 2 eventHandlers here
            circle.addEventHandler(MouseEvent.MOUSE_PRESSED, eventMousePressed);
            
            circle.addEventHandler(MouseEvent.MOUSE_DRAGGED, eventMouseDragged);
            
            //Animate the position of the rectangle to the position of the mouse when released
            circle.addEventHandler(MouseEvent.MOUSE_RELEASED, eventMouseReleased);       
    }
    public void setSlider(){
        
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
        
    }
    private void disableSlider(boolean b) {
        
        massSlider.setDisable(b);
        dampingSlider.setDisable(b);
        gravitySlider.setDisable(b);
    }
    private void setValues(){
        maxAngle = 90;
        length = 100;
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
    /**
     * This method is used only to create a path animation for the pendulum
     * @param path this path is taken from the creatPath() method
     * @param numbCycles
     * @param isAutoReverse
     * @param duration
     * @return Animation animation
     */
    public Animation animate(Polyline path, int numbCycles, boolean isAutoReverse, double duration){
        PathTransition animation = new PathTransition();
        animation.setCycleCount(/*numbCycles*/Animation.INDEFINITE);
        animation.setAutoReverse(isAutoReverse);
        animation.setDuration(Duration.seconds(duration));
        animation.setPath(path);
        animation.setNode(circle);
        animation.setInterpolator(Interpolator.EASE_BOTH);
        animation.setOnFinished((event) -> {
            System.out.println("Animation finished");
        });
        return animation;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Adjusting the enviornment and the nodes:
        Base.setLayoutX(0);
        Base.setLayoutY(0);
        animationPane.setLayoutX(0);
        animationPane.setLayoutY(0);
        
        canvas.setLayoutX(animationPane.getLayoutX());
        canvas.setLayoutY(animationPane.getLayoutY()); 
        
        
        
        circle.setTranslateX(canvas.getWidth()/1.5);
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
        clickAndDrag();
        playBtn.setOnAction((e)->{
            Polyline path = createPath();
            animate(path, 2, true, duration).play(); 
            animationPane.getChildren().add(path);
        });
        
        circleProperties();
    }

    @Override
    public String toString() {
        return "PendulumController{" + ", maxAngle=" + maxAngle + ", length=" + length + ", mass=" + mass + ", damping=" + damping + ", gravity=" + gravity + ", angularFrequency=" + angularFrequency + ", duration=" + duration + '}';
    }
    
}