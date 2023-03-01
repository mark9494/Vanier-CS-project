/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation;

import com.sun.media.jfxmedia.events.MarkerEvent;
import edu.vanier.PhysicsSimulation.Pendulum.AnimationLoader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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
