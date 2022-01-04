package fr.unice.polytech.startingpoint.core;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimulationTest {


    @Test
    void simulation1(){

        Simulation.Simulation1();
        assertTrue(Files.exists(Paths.get("./src/main/resources/save/result.csv")));

        try {
            CSVReader reader = new CSVReader(new FileReader("./src/main/resources/save/result.csv"), ',', '"', 1);
            //Read all rows at once
            String[] Row1 = reader.readNext();
            String[] header = {"PG","% PG"," PP","% PP"," PN",
                    "% PN","SC1"," PG","% PG"," PP","% PP"
                    ," PN","% PN","SC2"};
            assertEquals(Arrays.toString(header),Arrays.toString(Row1));
            Row1 = reader.readNext();
            assertEquals(14,Row1.length);
        }catch(Exception e){

        }

    }
    @Test
    void simulation2(){
        Simulation.Simulation2();
        assertTrue(Files.exists(Paths.get("./src/main/resources/save/result.csv")));
        try {
            CSVReader reader = new CSVReader(new FileReader("./src/main/resources/save/result.csv"), ',', '"', 1);
            //Read all rows at once
            String[] Row1 = reader.readNext();
            String[] header = {"PG","% PG"," PP","% PP"," PN",
                    "% PN","SC1"," PG","% PG"," PP","% PP"
                    ," PN","% PN","SC2"};
            assertEquals(Arrays.toString(header),Arrays.toString(Row1));
            Row1 = reader.readNext();
            assertEquals(14,Row1.length);
        }catch(Exception e){

        }
    }
    @Test
    void Write(){
        Simulation.Write(1,2,3,4,5,4,5,2);
        try {
            CSVReader reader = new CSVReader(new FileReader("./src/main/resources/save/result.csv"), ',', '"', 1);
            //Read all rows at once
            String[] Row1 = reader.readNext();

            String[] header = {"PG","% PG"," PP","% PP"," PN",
                    "% PN","SC1"," PG","% PG"," PP","% PP"
                    ," PN","% PN","SC2"};
            assertEquals(Arrays.toString(header),Arrays.toString(Row1));
            List<String[]> Row2 = reader.readAll();
            String[] Row3 = Row2.get(Row2.size()-1);
            String[] header1= {"1", "0.1", "2", "0.2", "3", "0.3", "4", "5", "0.5", "4", "0.4", "5", "0.5", "2"};
            assertEquals(Arrays.toString(header1),Arrays.toString(Row3));
            Row1 = reader.readNext();
            assertEquals(14,Row1.length);
        }catch(Exception e){

        }

    }

}
