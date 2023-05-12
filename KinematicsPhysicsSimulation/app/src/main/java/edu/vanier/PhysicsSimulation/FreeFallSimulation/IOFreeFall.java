package edu.vanier.PhysicsSimulation.FreeFallSimulation;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.shape.Rectangle;

public class IOFreeFall extends FreeFallController {

    public IOFreeFall(Rectangle building, double acceleration) {
        this.building = building;
        this.accelerationY = acceleration;
    }
    
    
    public void writeDataInFile(String filePath) {
        File file = new File(filePath);
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);

            // add data to csv
            String[] height = {"Height", "" + building.getHeight()};
            writer.writeNext(height);
            String[] acceleration = {"AccelerationY", "" + accelerationY};
            writer.writeNext(acceleration);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readDataInFile(String filePath) throws FileNotFoundException, IOException {

        CSVReader reader = new CSVReader(new FileReader(filePath));
        String[] nextLine;

        int counter = 1;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine.length == 2) {
                if (counter == 1) {
                    initialHeight = Double.parseDouble(nextLine[1]);
                }
                if (counter == 2) {
                    accelerationY = Double.parseDouble(nextLine[1]);
                }

                counter++;
            }

        }
    }

}
