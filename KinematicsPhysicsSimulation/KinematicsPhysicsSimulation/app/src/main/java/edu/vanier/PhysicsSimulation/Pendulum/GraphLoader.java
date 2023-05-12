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
 * @author antho
 */
public class GraphLoader extends Stage {
    public GraphLoader(){
    try{
            MakeComponents();
        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
    
    private void MakeComponents() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Graph.fxml"));
        
        loader.setController(new GraphController());
        
        Pane pane = loader.load();
        
        Scene sc = new Scene(pane);
        
        this.setScene(sc);
        
        this.show();
    } 
}
