package edu.vanier.PhysicsSimulation;

import edu.vanier.PhysicsSimulation.Pendulum.AnimationLoader;
import edu.vanier.PhysicsSimulation.Pendulum.testLoader;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class PhysicsSimulation extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        PhysicsSimulationController mainController = new PhysicsSimulationController();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/WelcomeWindow.fxml"));
        loader.setController(mainController);
        Pane root = loader.load();
        Scene scene = new Scene(root,600,370);
        stage.setScene(scene);        
        stage.setTitle("KinematicPhysicsSimulation");
        stage.sizeToScene();
        //stage.show();
        AnimationLoader anim = new AnimationLoader();
        //testLoader test = new testLoader();
    }
    
    @Override
    public void stop(){
        System.out.println("Closing Application.");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}