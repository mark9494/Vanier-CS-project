/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.Pendulum;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Slider;

/**
 *
 * @author Youssif
 */
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PendulumIO {

    Node circle;
    Slider gravitySlider;
    Slider massSlider;
    CheckBox DampingCheckBox;
    Control[] controllers;
    double circleX, circleY, gravityVal, massVal;
    boolean isChecked;
    
    public PendulumIO(){
        
    }
    
    public PendulumIO( Node circle, Control ... controllers) {
        this.circle = circle;
        this.controllers = controllers;
        for (int i = 0; i < this.controllers.length; i++) {
            if (this.controllers[i] instanceof Slider) {
                if (this.controllers[i].getId().equals("massSlider")) {
                    this.massSlider = (Slider)this.controllers[i];
                } else if (this.controllers[i].getId().equals("gravitySlider")) {
                    this.gravitySlider = (Slider)this.controllers[i];
                }
            } else if (this.controllers[i] instanceof CheckBox) {
                    DampingCheckBox = (CheckBox) this.controllers[i];
            }
        }
    }

    public void writeDataInFile(String filePath) {
        circleX = this.circle.getTranslateX();
        circleY = this.circle.getTranslateY();
        massVal = this.massSlider.getValue();
        gravityVal = this.gravitySlider.getValue();
        isChecked = this.DampingCheckBox.isSelected();
        File file = new File(filePath);
        try {
            
            FileWriter outputfile = new FileWriter(file);
            
            CSVWriter writer = new CSVWriter(outputfile);
            
            String[] circleXPos = {"Circle x position: " , String.valueOf(circleX)};
            
            String[] circleYPos = {"Circle y position: " , String.valueOf(circleY)};
            
            writer.writeNext(circleXPos);
            
            writer.writeNext(circleYPos);
            
            String[] gravityValue = {"Gravity Value: " , "" + gravityVal};
            
            String[] massValue = {"Mass Value: " ,String.valueOf(massVal)};
            
            String[] Damping = {"is Damping Activated: ", "" + isChecked};
                    
            writer.writeNext(gravityValue);
            
            writer.writeNext(massValue);
            
            writer.writeNext(Damping);
            
            writer.close();
        }catch(Exception e){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("improper file");
            a.setContentText("Choose a proper file to save the settings");
            a.showAndWait();
        
        }
        
        
    }

    public void readDataInFile(String filePath) throws FileNotFoundException, IOException {
        //parsing a CSV file into CSVReader class constructor
        CSVReader reader = new CSVReader(new FileReader(filePath));
        String[] nextLine;
        //reads one line at a time
        int count = 1;
        while ((nextLine = reader.readNext()) != null) {
            switch (count) {
                case 1:
                    circleX = (Double.parseDouble(nextLine[1]));
                    break;
                case 2:
                    circleY = (Double.parseDouble(nextLine[1]));
                    break;
                case 3:
                    gravityVal = (Double.parseDouble(nextLine[1]));
                    break;
                case 4:
                    massVal = (Double.parseDouble(nextLine[1]));
                    break;
                case 5:
                    isChecked = (Boolean.valueOf(nextLine[1]));
                    break;
                default:
                    break;
            }
            
            count ++;
        }
    }
}
