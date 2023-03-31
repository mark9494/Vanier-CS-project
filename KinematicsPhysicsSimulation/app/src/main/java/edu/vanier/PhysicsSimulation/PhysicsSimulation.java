package edu.vanier.PhysicsSimulation;

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
        primaryStage.setScene(scene);
        primaryStage.setTitle("KinematicPhysicsSimulation");
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    @Override
    public void stop() {
        System.out.println("Closing Application.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
