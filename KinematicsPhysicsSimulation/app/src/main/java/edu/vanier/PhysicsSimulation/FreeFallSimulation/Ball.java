package edu.vanier.PhysicsSimulation.FreeFallSimulation;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Ball extends Circle {

    public static final int RADIUS = 20;
    private double dy;

    /**
     * Default constructor which gives the default values for the ball.
     */
    public Ball() {
        this.setRadius(RADIUS);
        this.setFill(Color.RED);
        Image ball = new Image("/images/tennisBall.png");
        this.setFill(new ImagePattern(ball));
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

}
