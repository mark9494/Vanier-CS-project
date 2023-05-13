package edu.vanier.PhysicsSimulation.ProjectileMotion;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Cannon extends Rectangle {

    private final int WIDTH = 100;
    private final int HEIGHT = 60;
    private double cornerX;
    private double cornerY;
    private double angle; //(bounded between 20 - 55)
    private double angleRadians;

    /**
     * Constructor which sets the size of the cannon, rotates it to default
     * angle, and sets the cannon image.
     */
    public Cannon() {
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        this.setRotate(angle);
        Image basketball = new Image("/images/Cannon.png");
        this.setFill(new ImagePattern(basketball));
    }

    /**
     * Sets the corner of the rectangle x value
     */
    public void setCornerX() {
        cornerX = this.getTranslateX() + cos(this.getAngleRadians() * 2 / 3) * this.HEIGHT;
    }

    /**
     * Sets the corner of the rectangle y value
     */
    public void setCornerY() {
        cornerY = this.getTranslateY() + sin(this.getAngleRadians()) * this.HEIGHT;
    }

    /**
     * Gets the bottom right corner x coordinate of the rectangle.
     *
     * @return double bottom corner x coordinate of the rectangle
     */
    public double getCornerX() {
        setCornerX();
        return cornerX;
    }

    /**
     * Gets the bottom right corner y coordinate of the rectangle.
     *
     * @return double bottom corner y coordinate of the rectangle
     */
    public double getCornerY() {
        setCornerY();
        return cornerY;
    }

    public double getAngleRadians() {
        return this.angleRadians;
    }

    /**
     * converts the angle from degrees to radians.
     *
     * @param angle angle in degrees.
     */
    public void setAngleRadians(double angle) {
        this.angleRadians = angle * Math.PI / 180;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

}
