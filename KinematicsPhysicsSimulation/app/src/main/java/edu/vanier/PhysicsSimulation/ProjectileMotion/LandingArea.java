package edu.vanier.PhysicsSimulation.ProjectileMotion;

import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class LandingArea extends Rectangle {

    static final int INIT_WIDTH = 100;
    static final double INIT_HEIGHT = 35;

    /**
     * Constructor setting the length, width and image to the landing area.
     */
    public LandingArea() {
        this.setWidth(this.INIT_WIDTH);
        this.setHeight(this.INIT_HEIGHT);
        Image landingArea = new Image("/images/landingArea.png");
        this.setFill(new ImagePattern(landingArea));
    }

    /**
     * Randomly sets a position on the bottom of the pane for the user to aim at
     * a different location each time.
     *
     * @param maxX Maximum distance where the ball can land
     * @param minX Minimum distance where the ball can land
     * @param height Y coordinate it will placed
     */
    public void randomSpawn(double maxX, double minX, double height) {
        this.setTranslateY(height - INIT_HEIGHT);
        Random r = new Random();
        double randomValue = minX + (maxX - minX) * r.nextDouble();
        this.setTranslateX(randomValue);
    }

    /**
     * Gives the starting point of the landing area.
     *
     * @return Left part x component
     */
    public double getLeftX() {
        return this.getTranslateX();
    }

    /**
     * Gives the ending point of the landing area.
     *
     * @return right part x component
     */
    public double getRightX() {
        return this.getTranslateX() + INIT_WIDTH;
    }
}
