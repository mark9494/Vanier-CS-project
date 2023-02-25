package edu.vanier.PhysicsSimulation;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This is a JavaFX project template to be used for creating GUI applications.
 * JavaFX 18 is already linked to this project in the build.gradle file.
 * @link: https://openjfx.io/javadoc/18/
 * @see: Build Scripts/build.gradle
 * @author Sleiman Rabah.
 */
public class PhysicsSimulation extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //--> Step 1) Create the parent node of the scene graph.
        PhysicsSimulationController mainController = new PhysicsSimulationController();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("ClassName"));
        loader.setController(mainController);
        Pane root = loader.load();
        Scene scene = new Scene(root,600,370);
        //-----------
        //TODO:| Create your Scene graph here.
        //-----------       
        //FIXME: ask me in class what FIXME is supposed to mean.
        //-----
        //--> Step 2) Create the scene with the specified width and height
        //          and attach the scene graph to the scene.        
        //Scene scene = new Scene(root, 300, 300);
        //--> Step 3) Load the scene into stage (window)
        //stage.setScene(scene);        

        stage.setTitle("KinematicPhysicsSimulation");
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