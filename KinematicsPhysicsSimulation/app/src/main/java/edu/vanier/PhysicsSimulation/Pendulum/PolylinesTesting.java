/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.Pendulum;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 *
 * @author Youssif
 */
public class PolylinesTesting implements Initializable {

    @FXML
    Pane animationPane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
// Parameters for the pendulum animation
     final double LENGTH = 200; // Length of the pendulum arm
     final double MASS = 20; // Mass of the pendulum bob
     final double GRAVITY = 9.81; // Acceleration due to gravity
     final double DAMPING = 0.1; // Damping factor for the pendulum
    
        
        // Create the pendulum components
        Circle bob = new Circle(LENGTH, 0, MASS, Color.BLACK);
        Line arm = new Line(0, 0, LENGTH, 0);
        arm.setStrokeWidth(3);
        arm.setStroke(Color.BLACK);
        Text massText = new Text(LENGTH - MASS, MASS + 20, "m = " + MASS);
        Text dampingText = new Text(LENGTH - MASS, MASS + 40, "b = " + DAMPING);
        Text gravityText = new Text(LENGTH - MASS, MASS + 60, "g = " + GRAVITY);
        
        // Add the pendulum components to a group
        Group group = new Group(bob, arm, massText, dampingText, gravityText);
        
        // Create a rotate transition for the pendulum bob
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(2), bob);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(Timeline.INDEFINITE);
        rotateTransition.setAutoReverse(false);
        
        // Set up the animation timeline
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        
        // Add a keyframe to the timeline
        timeline.getKeyFrames().add(
                new javafx.animation.KeyFrame(Duration.millis(20), event -> {
                    double theta = Math.toRadians(bob.getRotate());
                    double angularVelocity = rotateTransition.getCurrentRate();
                    double acceleration = -GRAVITY / LENGTH * Math.sin(theta) - DAMPING / MASS * angularVelocity;
                    double deltaTheta = angularVelocity * 0.02;
                    rotateTransition.setRate(rotateTransition.getRate() + acceleration * 0.02);
                    bob.setRotate(bob.getRotate() + deltaTheta);
                })
        );
        
        // Start the animation
        timeline.play();
        rotateTransition.play();
        animationPane.getChildren().add(group);
        
        // Set up the scene
       // Scene scene = new Scene(group, 2 * LENGTH, 2 * LENGTH + 80);
        
    }
}
/*    
    @FXML
    Pane pane, animationPane;

    @FXML
    Canvas canvas;
    
    @FXML
    Circle circle;
    
    @FXML
    Button playBtn;
    
    private GraphicsContext gc;
    
    private final EventHandler<MouseEvent> eventMousePressed = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            circle.setCursor(Cursor.CLOSED_HAND);
            //rect.setLayoutX(amplitude);
            //rect.setLayoutX(rect.translateXProperty().getValue());
            //rect.setTranslateX(rect.translateXProperty().getValue());
        }
    };
    
    private final EventHandler<MouseEvent> eventMouseDragged = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            circle.setTranslateX(event.getSceneX());
            circle.setTranslateY(event.getSceneY());
            /*
            drawMovingSpring(0);
            System.out.println(rect.getLayoutX());
            amplitude = (rect.getLayoutX() - centralLine.getLayoutX())/50;
            SimulationSHM shm = new SimulationSHM();
            Animation shmHandled = shm.bringToCenter(rect, amplitude, mass, damping, springStiffness, centralLine.getLayoutX());
            animate(shmHandled);*/
  /*      }
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
    
    private void clickAndDrag() {
            circle.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, eventMouseEnteredTarget);
            
            //When its closed and pressed make it drags
            //Adding 2 eventHandlers here
            circle.addEventHandler(MouseEvent.MOUSE_PRESSED, eventMousePressed);
            
            circle.addEventHandler(MouseEvent.MOUSE_DRAGGED, eventMouseDragged);
            
            //Animate the position of the rectangle to the position of the mouse when released
            circle.addEventHandler(MouseEvent.MOUSE_RELEASED, eventMouseReleased);       
    }
    
    
    private void draw(GraphicsContext gc){
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        double startY = circle.getTranslateY();
        double startX = circle.getTranslateX();
        //double endY = canvas.getHeight()/1.5 - length;
        //double endX = canvas.getWidth()/2 ;
        //double endY = initialCircleY - length;
        //double endX = initialCircleX ;
        double endY = canvas.getHeight()/2;
        double endX = canvas.getWidth()/1.5 - 100;
        
        gc.strokeLine(startX, startY, endX, endY);
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
         
        
        
        
        animationPane.setLayoutX(0);
        animationPane.setLayoutY(0);
        canvas.setLayoutX(animationPane.getLayoutX());
        canvas.setLayoutY(animationPane.getLayoutY()); 
        
        
        
        circle.setTranslateX(canvas.getWidth()/1.5);
        circle.setTranslateY(canvas.getHeight()/2);
        
        
        gc  = canvas.getGraphicsContext2D();
        
        double length = 100;
        double maxAngle = Math.PI ;
        double currentAngle = 0.05 * maxAngle;
        double thisAngle = currentAngle;
        
        draw(gc);
        circle.setTranslateX((canvas.getWidth()/1.5-100) + length * Math.cos(Math.PI/4));
        circle.setTranslateY(canvas.getHeight()/2 + length * Math.sin(Math.PI/4));
        
        double RADIUS = length;
        double START_ANGLE = Math.toDegrees(Math.atan((canvas.getHeight()/2 - circle.getTranslateY())/((canvas.getWidth()/1.5-100)-circle.getTranslateX())));
        double ARC_ANGLE = 90 - START_ANGLE;

        double startX = canvas.getWidth()/1.5-100;
        double startY = canvas.getHeight()/2;
        Polyline polyline = new Polyline();
        for (int i = 1; i <2* ARC_ANGLE; i++) {
            double angle = Math.toRadians(i + START_ANGLE);
            double x = startX + RADIUS * Math.cos(angle);
            double y = startY + RADIUS * Math.sin(angle);
            polyline.getPoints().addAll(x, y);
        }
        
        animationPane.getChildren().addAll(polyline);



        
        circle.translateXProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                draw(gc);
                
            }
            
        });
    
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
        //pane.getChildren().add(line);
        
        PathTransition anim = new PathTransition();
        anim.setInterpolator(Interpolator.EASE_BOTH);
        anim.setPath(line);
        anim.setNode(circle);
        anim.setDuration(Duration.seconds(1));
        anim.setCycleCount(Animation.INDEFINITE);
        anim.setAutoReverse(true);
        clickAndDrag();
        playBtn.setOnAction((e)->{
           anim.play(); 
        });
        
                        
    }*/
