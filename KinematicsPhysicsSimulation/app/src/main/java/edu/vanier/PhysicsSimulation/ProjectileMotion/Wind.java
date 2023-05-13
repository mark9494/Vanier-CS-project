package edu.vanier.PhysicsSimulation.ProjectileMotion;

import java.util.Random;

public class Wind {

    static int intensity;
    static double angle;

    /**
     * Default constructor, sets intensity to zero.
     */
    public Wind() {
        this.intensity = 0;
    }

    /**
     * Generates a random angle between 0 and 2Pi for the wind vector
     *
     * @return random angle of wind in radians.
     */
    public double randomWindAngle() {
        double min = 0;
        double max = 2 * Math.PI;
        Random r = new Random();
        double windAngle = min + (max - min) * r.nextDouble();
        return windAngle;
    }

    /**
     * Generates a random number between 1-3 for the strength of the wind. 3
     * being the strongest, 1 being the weakest.
     *
     * @return integer of wind strength
     */
    public int randomIntensity() {
        int min = 1;
        int max = 3;
        int windIntensity = (int) Math.floor(Math.random() * (max - min + 1) + min);
        return windIntensity;
    }

    /**
     * Gets the x component of the wind vector.
     *
     * @return double x vector component
     */
    public double getForceWindX() {
        return this.getIntensity() * Math.cos(this.getAngle());
    }

    /**
     * Gets the y component of the wind vector.
     *
     * @return double y vector component
     */
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
