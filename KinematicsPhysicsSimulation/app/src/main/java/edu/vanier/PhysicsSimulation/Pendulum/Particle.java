package edu.vanier.PhysicsSimulation.Pendulum;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Particle {
    private static final double MAX_ATTRACT_DISTANCE = 250;
    private static final double FORCE_CONSTANT = 5000;
    double x;
    double y;
    Color color;

    double originalX;
    double originalY;

    Particle(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;

        originalX = x;
        originalY = y;
    }

    void update(Point2D circlePosition) {
        double distance = circlePosition.distance(x, y);

        if (distance > MAX_ATTRACT_DISTANCE) {
            x = originalX;
            y = originalY;
        } else {
            Point2D vector = circlePosition.subtract(x, y);
            double scaledLength = FORCE_CONSTANT * 1 / distance;
            vector = vector.normalize().multiply(scaledLength);

            x = originalX + vector.getX();
            y = originalY + vector.getY();
        }
    }

}
