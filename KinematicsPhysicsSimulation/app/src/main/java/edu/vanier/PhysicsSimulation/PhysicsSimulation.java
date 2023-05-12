package edu.vanier.PhysicsSimulation;


import edu.vanier.PhysicsSimulation.CarSimulation.Settings;
import edu.vanier.PhysicsSimulation.ProjectileMotion.ProjectileMotionSettings;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PhysicsSimulation extends Application {

    public static Stage primaryStage = new Stage();

    @Override
    public void start(Stage stage) throws Exception {
        PhysicsSimulationController mainController = new PhysicsSimulationController();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/WelcomeWindow.fxml"));
        loader.setController(mainController);
        Pane root = loader.load();
        Scene scene = new Scene(root, 600, 370);
        stage.setScene(scene);
        stage.setTitle("KinematicPhysicsSimulation");
        stage.sizeToScene();
        stage.show();
    }

//TODO: Close all timelines here.
    @Override
    public void stop() {       
        try{      
        System.out.println("Closing Application.");
        ProjectileMotionSettings.timelineRectangleAndBall.stop();
        ProjectileMotionSettings.timelinePaneResize.stop();
        ProjectileMotionSettings.timer.cancel();
        Settings.timeline.stop();
        Settings.timelineToUpdateSliders.stop();
        
        }catch(Exception e){
            
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
