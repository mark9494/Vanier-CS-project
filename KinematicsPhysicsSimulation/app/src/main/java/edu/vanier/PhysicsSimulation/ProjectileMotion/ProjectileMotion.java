/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.ProjectileMotion;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author antho
 */
public class ProjectileMotion extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ProjectileMotionController mainController = new ProjectileMotionController();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/ProjectileMotion.fxml"));
        loader.setController(mainController);
        Pane root = loader.load();
        Scene scene = new Scene(root, 1000, 650);
        stage.setScene(scene);
        stage.setTitle("Projectile Motion");
        stage.sizeToScene();
        stage.show(); 
    }

    @Override
    public void stop() {
        System.out.println("Closing Application.");
    }

    public static void main(String[] args) {
        launch(args);
        ProjectileMotionController pm = new ProjectileMotionController();
    }

}
