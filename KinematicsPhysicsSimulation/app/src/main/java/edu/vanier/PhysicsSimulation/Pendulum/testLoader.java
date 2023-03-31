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
public class testLoader extends Stage{
    public testLoader(){
        try{
            MakeComponents();
        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
    
    private void MakeComponents() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/test.fxml"));
        
        loader.setController(new PolylinesTesting());
        
        Pane pane = loader.load();
        
        Scene sc = new Scene(pane);
        
        this.setScene(sc);
        
        this.show();
    } 
}
