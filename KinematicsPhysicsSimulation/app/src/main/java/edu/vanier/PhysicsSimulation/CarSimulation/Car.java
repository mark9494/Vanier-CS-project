/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.CarSimulation;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
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

    public Car(int layoutX, int layoutY, String color) {

        this.setWidth(51);
        this.setHeight(24);
        this.setLayoutX(layoutX);
        this.setLayoutY(layoutY);
        Image blueCar = new Image("/images/blue-car.png", false);
        this.setFill(new ImagePattern(blueCar));
    }

    public Car(int layoutX, int layoutY) {

        this.setWidth(51);
        this.setHeight(24);
        this.setLayoutX(layoutX);
        this.setLayoutY(layoutY);
        Image redCar = new Image("/images/red-car.png", false);
        this.setFill(new ImagePattern(redCar));
    }

    public void calculateCurrentVelocity() {
        double velocitySquared = Math.pow(this.initialVelocity, 2) + 2 * this.acceleration * this.calculatecCurrentDisplacement();
        this.currentVelocity = Math.sqrt(velocitySquared);
    }

    public void calculateCurrentTime() {

        this.time = ((this.currentVelocity - this.initialVelocity) / this.acceleration);// we divide by 10 because we multiplied both initial and final position by 10 before
        
    }

    private double calculatecCurrentDisplacement() {

        return this.getTranslateX() / 10 - this.initialPosition / 10;
    }

    private double calculateFinalDisplacement() {

        return this.finalPosition / 10 - this.initialPosition / 10;
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
