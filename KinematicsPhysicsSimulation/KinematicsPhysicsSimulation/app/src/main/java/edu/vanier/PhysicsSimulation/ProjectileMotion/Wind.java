/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.ProjectileMotion;

import java.util.Random;

/**
 *
 * @author 2161743
 */
public class Wind {

    private int intensity;
    private double angle;

    public Wind() {
        this.intensity = 0;
    }

    public double randomWindAngle() {
        double min = 0;
        double max = 2 * Math.PI;
        Random r = new Random();
        double windAngle = min + (max - min) * r.nextDouble();
        return windAngle;
    }

    public int randomIntensity() {
        int min = 1;
        int max = 3;
        int windIntensity = (int) Math.floor(Math.random() * (max - min + 1) + min);
        return windIntensity;
    }

    public double getForceWindX() {
        return this.getIntensity() * Math.cos(this.getAngle());
    }

    public double getForceWindY() {
        return this.getIntensity() * Math.sin(this.getAngle());
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

}
