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
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
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
    public static AnimationLoader anim;
    @FXML
    private Pane pane; 
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        projectileMotion.initOwner(PhysicsSimulation.primaryStage);
        carSimulation.initOwner(PhysicsSimulation.primaryStage);
        setDefaultBackGround();
        pane.widthProperty().addListener((obs, oldVal, newVal) -> {
            resizeBtnsHorizontal();
            

        });
        
        pane.heightProperty().addListener((obs, oldVal, newVal) -> {
            resizeBtnsVertical();
         
        });
        
        

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
            anim = new AnimationLoader();
        });
    }

    
    private void resizeBtnsHorizontal(){
        
        Ammar.setLayoutX((pane.getWidth()/7.40));
        Youssif.setLayoutX(pane.getWidth()/1.65);
        Mark.setLayoutX(pane.getWidth()/7.40);
        Anthony.setLayoutX(pane.getWidth()/1.65);  
    }
    
    
    private void resizeBtnsVertical(){
        Ammar.setLayoutY((pane.getHeight()/3));
        Youssif.setLayoutY(pane.getHeight()/3);
        Mark.setLayoutY(pane.getHeight()/1.3);
        Anthony.setLayoutY(pane.getHeight()/1.3);  
    }
    public void setDefaultBackGround() {
        Image image = new Image("/images/background.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true));
        pane.setBackground(new Background(backgroundImage));
    }
    
}
