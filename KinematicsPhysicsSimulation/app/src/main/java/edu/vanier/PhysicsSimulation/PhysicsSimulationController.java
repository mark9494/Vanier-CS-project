/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation;

import com.sun.media.jfxmedia.events.MarkerEvent;
import edu.vanier.PhysicsSimulation.CarSimulation.CarSimulationWindowController;
import edu.vanier.PhysicsSimulation.Pendulum.AnimationLoader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author antho
 */
public class PhysicsSimulationController implements Initializable{

    @FXML
    Button Mark, Anthony, Ammar, Youssif;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO Add the logic to eahc button in the main window
        Mark.setOnAction((e)->{
            try {
                Stage secondWindow = new Stage();
                
                CarSimulationWindowController carWindow = new CarSimulationWindowController();
                
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/CarSimulation.fxml"));
                loader.setController(carWindow);
                Pane root = loader.load();
                Scene scene = new Scene(root, 1200.0, 1000.0);
                secondWindow.setScene(scene);
                
                secondWindow.setTitle("Car simulation");
                secondWindow.sizeToScene();
                secondWindow.show();
            } catch (IOException ex) {
                Logger.getLogger(PhysicsSimulationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        Anthony.setOnAction((e)->{
        
        });
        Ammar.setOnAction((e)->{
        
        });
        Youssif.setOnAction((e)->{
            AnimationLoader anim = new AnimationLoader(); 
        });
    }
    
}
