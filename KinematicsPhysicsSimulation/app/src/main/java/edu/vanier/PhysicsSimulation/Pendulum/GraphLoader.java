/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.Pendulum;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Youssif
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
    GraphController controller = new GraphController();
    private void MakeComponents() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Graph.fxml"));
        
        loader.setController(controller);
        
        Pane pane = loader.load();
        
        Scene sc = new Scene(pane);
        
        this.setScene(sc);
        
        this.setOnCloseRequest(handle());
        
    } 

    private EventHandler<WindowEvent> handle() {
        return new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                controller.stopAnimations();
            }
        };
    }
}
