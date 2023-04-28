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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 2161743
 */
public class IO {

    public static void writeDataInFile(String filePath) {
        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filePath);

        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // create a List which contains String array
            List<String[]> data = new ArrayList<String[]>();
            data.add(new String[]{"Name", "Class", "Marks"});
            data.add(new String[]{"Aman", "10", "620"});
            data.add(new String[]{"Suraj", "10", "630"});
            writer.writeAll(data);

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void readDataInFile(String filePath) throws
            FileNotFoundException, IOException {
        //parsing a CSV file into CSVReader class constructor
        ArrayList<String> values = new ArrayList<String>();
        CSVReader reader = new CSVReader(new FileReader(filePath));
        String[] nextLine;
        //reads one line at a time
        while ((nextLine = reader.readNext()) != null) {
            for (String token : nextLine) {
                values.add(token);
                System.out.print(token);
            }
            System.out.print("\n");
        }
    }
}
