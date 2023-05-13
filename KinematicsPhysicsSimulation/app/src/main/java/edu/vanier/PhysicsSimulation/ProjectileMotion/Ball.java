package edu.vanier.PhysicsSimulation.ProjectileMotion;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Ball extends Circle {

    private static final int RADIUS = 15;
    private double dx, dy;

    /**
     * Default Constructor which sets the default image and radius.
     */
    public Ball() {
        this.setRadius(RADIUS);
        Image basketball = new Image("/images/basketball.png");
        this.setFill(new ImagePattern(basketball));
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

}
