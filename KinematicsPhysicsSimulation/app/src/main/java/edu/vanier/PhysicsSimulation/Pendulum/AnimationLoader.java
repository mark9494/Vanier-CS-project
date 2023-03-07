/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.Pendulum;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Youssif
 */
public class AnimationLoader extends Stage{
    public AnimationLoader(){
        try{
            MakeComponents();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void MakeComponents() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PendulumTab.fxml"));
        
        loader.setController(new PendulumController());
        
        Pane pane = loader.load();
        
        Scene sc = new Scene(pane);
        
        this.setScene(sc);
        
        this.show();
    }
    
    
}
