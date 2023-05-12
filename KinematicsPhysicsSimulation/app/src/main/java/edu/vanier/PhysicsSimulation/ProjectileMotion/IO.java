/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.ProjectileMotion;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IO extends ProjectileMotionSettings {

    public static void writeDataInFile(String filePath) {
        File file = new File(filePath);
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);

            // add data to csv
            String[] initVelocity = {"InitialVelocity", "" + initialVelocity};
            writer.writeNext(initVelocity);
            String[] angle = {"Angle", "" + rampAngle};
            writer.writeNext(angle);
            String[] acceleration = {"Acceleration", "" + accelerationY};
            writer.writeNext(acceleration);
            String[] landingAreaData = {"LandingArea", "" + landingArea.getTranslateX()};
            writer.writeNext(landingAreaData);
            String[] wind = {"Wind", "" + Wind.intensity, "" + Wind.angle};
            writer.writeNext(wind);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readDataInFile(String filePath) throws FileNotFoundException, IOException {

        CSVReader reader = new CSVReader(new FileReader(filePath));
        String[] nextLine;

        int counter = 1;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine.length == 2) {
                if (counter == 1) {
                    initialVelocity = Double.parseDouble(nextLine[1]);
                }
                if (counter == 2) {
                    rampAngle = Double.parseDouble(nextLine[1]);
                }
                if (counter == 3) {
                    accelerationY = Double.parseDouble(nextLine[1]);
                }
                if (counter == 4) {
                    landingArea.setTranslateX(Double.parseDouble(nextLine[1]));
                }
                counter++;
            }

            if (nextLine.length == 3) {
                Wind.intensity = Integer.parseInt((nextLine[1]));
                Wind.angle = Double.parseDouble((nextLine[2]));
                if (Wind.intensity == 0) {
                    isWind = false;
                } else {
                    isWind = true;
                }
            }

        }
    }

}
