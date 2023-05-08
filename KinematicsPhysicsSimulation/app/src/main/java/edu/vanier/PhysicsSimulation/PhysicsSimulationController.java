/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation;

import edu.vanier.PhysicsSimulation.CarSimulation.CarAnimationLoader;
import edu.vanier.PhysicsSimulation.FreeFallSimulation.FreeFallLoader;
import edu.vanier.PhysicsSimulation.Pendulum.AnimationLoader;
import edu.vanier.PhysicsSimulation.ProjectileMotion.ProjectileMotion;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author antho
 */
public class PhysicsSimulationController implements Initializable {

    @FXML
    Button Mark, Anthony, Ammar, Youssif;

    public static Stage projectileMotion = new Stage();
    public static Stage carSimulation = new Stage();
    public static Stage pendulum = new Stage();
    public static Stage freeFall = new Stage();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        projectileMotion.initOwner(PhysicsSimulation.primaryStage);
        carSimulation.initOwner(PhysicsSimulation.primaryStage);
        
        //TODO Add the logic to eahc button in the main window

        Mark.setOnAction((e)->{

            CarAnimationLoader animationStage = new CarAnimationLoader();


        });
        Anthony.setOnAction((e) -> {

            try {
                ProjectileMotion animation = new ProjectileMotion();
            } catch (Exception ex) {
                Logger.getLogger(PhysicsSimulationController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        
        Ammar.setOnAction((e) -> {
            FreeFallLoader anim = new FreeFallLoader();


        });
        Youssif.setOnAction((e) -> {
            AnimationLoader anim = new AnimationLoader();
        });
    }

}
