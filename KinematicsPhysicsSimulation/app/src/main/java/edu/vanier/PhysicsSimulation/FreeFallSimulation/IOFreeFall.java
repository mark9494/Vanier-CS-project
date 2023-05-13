package edu.vanier.PhysicsSimulation.FreeFallSimulation;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IOFreeFall extends FreeFallController {

    public IOFreeFall() {
    }

    /**
     * Creates and writes into a csv file to store data of the simulation.
     *
     * @param filePath : file path to store the csv file
     */
    public void writeDataInFile(String filePath) {
        File file = new File(filePath);
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);

            // add data to csv
            String[] height = {"Height", "" + initialHeight};
            writer.writeNext(height);
            String[] acceleration = {"AccelerationY", "" + accelerationY};
            writer.writeNext(acceleration);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the data from the csv file and sets the variables.
     *
     * @param filePath : file path to retrieve csv file
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void readDataInFile(String filePath) throws FileNotFoundException, IOException {
        CSVReader reader = new CSVReader(new FileReader(filePath));
        String[] nextLine;

        int counter = 1;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine.length == 2) {
                if (counter == 1) {
                    FreeFallSettings.initialHeight = Double.parseDouble(nextLine[1]);
                    System.out.println(initialHeight);
                }
                if (counter == 2) {
                    FreeFallSettings.accelerationY = Double.parseDouble(nextLine[1]);
                    System.out.println(accelerationY);
                }
                counter++;
            }
        }
    }
}
