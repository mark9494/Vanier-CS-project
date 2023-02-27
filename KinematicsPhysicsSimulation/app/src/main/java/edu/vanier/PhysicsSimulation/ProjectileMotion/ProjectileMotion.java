/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.ProjectileMotion;

import edu.vanier.PhysicsSimulation.PhysicsSimulationController;
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
public class ProjectileMotion extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        //--> Step 1) Create the parent node of the scene graph.
        ProjectileMotionController mainController = new ProjectileMotionController();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/ProjectileMotion.fxml"));
        loader.setController(mainController);
        Pane root = loader.load();
        Scene scene = new Scene(root,1000,650);
        //-----------
        //TODO:| Create your Scene graph here.
        //-----------       
        //FIXME: ask me in class what FIXME is supposed to mean.
        //-----
        //--> Step 2) Create the scene with the specified width and height
        //          and attach the scene graph to the scene.        
        //Scene scene = new Scene(root, 300, 300);
        //--> Step 3) Load the scene into stage (window)
        stage.setScene(scene);        

        stage.setTitle("Projectile Motion");
        // Resize the stage so the size matches the scene
        stage.sizeToScene();
        //--> Step 4) Show the window.
        stage.show();
    }
    @Override
    public void stop(){
        System.out.println("Closing Application.");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
