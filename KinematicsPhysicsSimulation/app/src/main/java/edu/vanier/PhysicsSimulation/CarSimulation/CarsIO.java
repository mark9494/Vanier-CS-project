/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.CarSimulation;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import edu.vanier.PhysicsSimulation.ProjectileMotion.Wind;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Mark
 */
public class CarsIO extends CarSimulationWindowController{

    public CarsIO(Car redCar, Car blueCar) {
       this.redCar = redCar; 
       this.blueCar = blueCar; 
    }
    
    
    public  void writeDataInFile(String filePath) {
        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filePath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // add data to csv
            String[] blueInitPosition = {"BlueInitPosition", "" + blueCar.getInitialPosition()/10};
            writer.writeNext(blueInitPosition);
            String[] blueFinPosition = {"blueFinPosition", "" + blueCar.getFinalPosition()/10 };
            writer.writeNext(blueFinPosition);
            String[] blueInitVelocity = {"blueInitVelocity", "" + blueCar.getInitialVelocity()};
            writer.writeNext(blueInitVelocity);
            String[] blueAcceleration = {"blueAcceleration", "" + blueCar.getAcceleration()};
            writer.writeNext(blueAcceleration);
            //String[] wind = {"Wind", "" + Wind.intensity, "" + Wind.angle};
            String[] redInitPosition = {"redInitPosition", "" + redCar.getInitialPosition()/10};
            writer.writeNext(redInitPosition);
            String[] redFinPosition = {"redFinPosition", "" + redCar.getFinalPosition()/10 };
            writer.writeNext(redFinPosition);
            String[] redInitVelocity = {"redInitVelocity", "" + redCar.getInitialVelocity()};
            writer.writeNext(redInitVelocity);
            String[] redAcceleration = {"redAcceleration", "" + redCar.getAcceleration()};
            writer.writeNext(redAcceleration);
            
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void readDataInFile(String filePath) throws FileNotFoundException, IOException {
        //parsing a CSV file into CSVReader class constructor
        CSVReader reader = new CSVReader(new FileReader(filePath));
        String[] nextLine;
        //reads one line at a time
        int counter = 1;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine.length == 2) {
                if (counter == 1) {
                 blueCar.setInitialPosition(Double.parseDouble(nextLine[1])); 
                }else if (counter == 2) {
                   blueCar.setFinalPosition(Double.parseDouble(nextLine[1])); 
                }else if (counter == 3) {
                    blueCar.setInitialVelocity(Double.parseDouble(nextLine[1]));
                }else if (counter == 4) {
                    blueCar.setAcceleration(Double.parseDouble(nextLine[1]));
                }else if(counter ==5){
                 redCar.setInitialPosition(Double.parseDouble(nextLine[1]));   
                }else if(counter ==6){
                 redCar.setFinalPosition(Double.parseDouble(nextLine[1]));   
                }else if(counter ==7){
                 redCar.setInitialVelocity(Double.parseDouble(nextLine[1]));   
                }else if(counter ==8){
                 redCar.setAcceleration(Double.parseDouble(nextLine[1]));   
                }
                counter++;
            }
            
            }
        }
        //System.out.println(initialVelocity + " " + rampAngle + " " + accelerationY + " " + counter + " " + Wind.intensity + " " + Wind.angle);
    }

