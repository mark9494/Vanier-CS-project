/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.ProjectileMotion;

import edu.vanier.PhysicsSimulation.PhysicsSimulationController;
import static edu.vanier.PhysicsSimulation.ProjectileMotion.ProjectileMotionSettings.timelinePaneResize;
import static edu.vanier.PhysicsSimulation.ProjectileMotion.ProjectileMotionSettings.timelineRectangleAndBall;
import static edu.vanier.PhysicsSimulation.ProjectileMotion.ProjectileMotionSettings.timer;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class ProjectileMotion {

    public ProjectileMotion() throws Exception {
        start();
    }

    public void start() throws Exception {
        PhysicsSimulationController.projectileMotion.setOnCloseRequest(e -> projectileMotionClosed());
        ProjectileMotionController mainController = new ProjectileMotionController();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(
                "/fxml/ProjectileMotion.fxml"));
        loader.setController(mainController);
        Pane root = loader.load();
        Scene scene = new Scene(root, 1000, 800);
        PhysicsSimulationController.projectileMotion.setScene(scene);
        PhysicsSimulationController.projectileMotion.setTitle(
                "Projectile Motion");
        PhysicsSimulationController.projectileMotion.sizeToScene();
        PhysicsSimulationController.projectileMotion.show();
    }

    public void projectileMotionClosed() {
        timelineRectangleAndBall.stop();
        timelinePaneResize.stop();
        timer.cancel();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
