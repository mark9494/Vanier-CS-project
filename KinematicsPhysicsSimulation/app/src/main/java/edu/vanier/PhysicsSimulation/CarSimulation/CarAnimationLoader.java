/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.CarSimulation;

import edu.vanier.PhysicsSimulation.PhysicsSimulationController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author 2125881
 */
public class CarAnimationLoader extends Stage {

    public static Scene scene;

    /**
     * calls the method that creates the simulation window
     */
    public CarAnimationLoader() {
        try {
            MakeComponents();
        } catch (IOException ex) {
            Logger.getLogger(PhysicsSimulationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * created the stage for the simulation window to be created
     * also reads the FXML file made in scene builder
     * @throws Exception
     */
    private void MakeComponents() throws Exception {
        CarSimulationWindowController carWindow = new CarSimulationWindowController();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/CarSimulation.fxml"));
        loader.setController(carWindow);
        Pane root = loader.load();
        scene = new Scene(root, 894, 600);
        PhysicsSimulationController.carSimulation.setScene(scene);
        PhysicsSimulationController.carSimulation.setTitle("Car simulation");
        PhysicsSimulationController.carSimulation.sizeToScene();
        PhysicsSimulationController.carSimulation.show();
    }
}
