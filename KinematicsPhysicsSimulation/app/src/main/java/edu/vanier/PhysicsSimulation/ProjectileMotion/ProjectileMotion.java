/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.ProjectileMotion;

import edu.vanier.PhysicsSimulation.PhysicsSimulationController;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 *
 * @author antho
 */
public class ProjectileMotion {

    public ProjectileMotion() throws Exception {
        //IO.writeDataInFile("C:\\Users\\antho\\Desktop\\data.csv");
        //IO.readDataInFile("C:\\Users\\antho\\Desktop\\data.csv");
        start();
    }

    public void start() throws Exception {
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
   
    public static void main(String[] args) {
        launch(args);
    }

}
