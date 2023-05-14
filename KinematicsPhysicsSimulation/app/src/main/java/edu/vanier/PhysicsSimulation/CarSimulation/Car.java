/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.CarSimulation;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author antho
 */
public class Car extends Rectangle {

    private double initialPosition;
    private double currentPosition;
    private double finalPosition;
    private double initialVelocity;
    private double currentVelocity;
    private double finalVelocity;
    private double acceleration;
    private double time;
    private double finalTime;
    private double graphDisplacement;
    private double graphVelocity;

    /**
     * gives default value for the blue car object being instantiated.
     *
     * @param layoutX : Horizontal position of car.
     * @param layoutY : Vertical position of car.
     * @param color : color of car.
     */
    public Car(int layoutX, int layoutY, String color) {
        setWidth(51);
        setHeight(24);
        this.setLayoutX(layoutX);
        this.setLayoutY(layoutY);
        Image blueCar = new Image("/images/blue-car.png", false);
        setFill(new ImagePattern(blueCar));
    }

    /**
     * gives default value for the red car object being instantiated.
     *
     * @param layoutX : Horizontal position of car.
     * @param layoutY : Vertical position of car.
     */
    public Car(int layoutX, int layoutY) {
        this.setWidth(51);
        this.setHeight(24);
        this.setLayoutX(layoutX);
        this.setLayoutY(layoutY);
        Image redCar = new Image("/images/red-car.png", false);
        this.setFill(new ImagePattern(redCar));
    }
    
    /**
     * constructor used for test cases
     */
    public Car(){        
    }

    /**
     * calculates the velocity of the car at the current moment.
     *
     * @param displacement : current displacement of car used to find current
     * velocity.
     * @return : The current velocity of the car.
     */
    public double calculateCurrentVelocity(double displacement) {
        if (initialVelocity == 0 && acceleration != 0) {
            initialVelocity = 0.1;// otherwise the car won't move  
        }
        double velocitySquared = Math.pow(initialVelocity, 2) + 2 * acceleration * displacement;
        currentVelocity = Math.sqrt(velocitySquared);
        return currentVelocity;
    }

    /**
     * calculates the current time using the current displacement
     *
     * @param displacement : current displacement of the car
     * @return : the current time of the car at the current position.
     */
    public double calculateCurrentTime(double displacement) {
        time = (2 * displacement) / (this.initialVelocity + this.currentVelocity);
        return time;
    }

    /**
     * calculate the overall time the car needs to reach its final position
     *
     * @param finalDisplacement : the full displacement of the car from start to
     * finish
     * @param finalVelocity : the velocity of the car the moment it arrives to
     * its final position
     * @return : returns final time
     */
    public double calculateFinalTime(double finalDisplacement, double finalVelocity) {
        return finalTime = (2 * finalDisplacement) / (initialVelocity + finalVelocity);
    }

    /**
     * given the time, the method uses the initial velocity and acceleration to
     * find the displacement of the car
     *
     * @param time : time at which the displacement needs to be calculated
     * @return :displacement at a specific time
     */
    public double calculateGraphDisplacement(double time) {
        return graphDisplacement = (initialVelocity * time)
                + (0.5 * acceleration * Math.pow(time, 2));
    }

    /**
     * calculates the velocity at which the car will reach its final position
     *
     * @param finalDisplacement
     * @return : final velocity the car will reach during the simulation
     */
    public double calculateFinalVelocity(double finalDisplacement) {
        double velocitySquared = Math.pow(initialVelocity, 2) + 2 * acceleration * finalDisplacement;
        finalVelocity = Math.sqrt(velocitySquared);
        return finalVelocity;
    }

    /**
     * uses the initial position and the current position of the car to find its
     * current displacement
     *
     * @return : returns the displacement at the current moment
     */
    public double calculateCurrentDisplacement() {
        currentPosition = getTranslateX() / 10 - initialPosition / 10;
        return currentPosition;
    }

    /**
     * uses initial and final position to calculate the distance the car will
     * travel (final displacement)
     *
     * @return : returns the calculated final displacement
     */
    public double calculateFinalDisplacement() {
        return (finalPosition / 10) - (initialPosition / 10);
    }

    public double getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(double initialPosition) {
        this.initialPosition = initialPosition;
    }

    public double getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(double currentPosition) {
       this.currentPosition = currentPosition;
    }

    public double getFinalPosition() {
        return finalPosition;
    }

    public void setFinalPosition(double finalPosition) {
        this.finalPosition = finalPosition;
    }

    public double getInitialVelocity() {
        return initialVelocity;
    }

    public void setInitialVelocity(double initialVelocity) {
        this.initialVelocity = initialVelocity;
    }

    public double getCurrentVelocity() {
        return currentVelocity;
    }

    public void setCurrentVelocity(double currentVelocity) {
        this.currentVelocity = currentVelocity;
    }

    public double getFinalVelocity() {
        return finalVelocity;
    }

    public void setFinalVelocity(double finalVelocity) {
        this.finalVelocity = finalVelocity;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

}
