package fr.unice.polytech.startingpoint.core;

import au.com.bytecode.opencsv.CSVReader;
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
    @BeforeEach
    void setup(){
        while(Files.exists(Paths.get("./src/main/resources/save/result.csv"))){
            File myFile = new File("./src/main/resources/save/result.csv");
            myFile.delete();
        }
    }
    @AfterAll
    static void setup1(){
        while(Files.exists(Paths.get("./src/main/resources/save/result.csv"))){
            File myFile = new File("./src/main/resources/save/result.csv");
            myFile.delete();
        }
    }

    @Test
    void simulation1(){

        assertFalse(Files.exists(Paths.get("./src/main/resources/save/result.csv")));
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
        assertFalse(Files.exists(Paths.get("./src/main/resources/save/result.csv")));
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
        assertFalse(Files.exists(Paths.get("./src/main/resources/save/result.csv")));
        Simulation.Write(1,2,3,4,5,4,5,2);
        try {
            CSVReader reader = new CSVReader(new FileReader("./src/main/resources/save/result.csv"), ',', '"', 1);
            //Read all rows at once
            String[] Row1 = reader.readNext();

            String[] header = {"PG","% PG"," PP","% PP"," PN",
                    "% PN","SC1"," PG","% PG"," PP","% PP"
                    ," PN","% PN","SC2"};
            assertEquals(Arrays.toString(header),Arrays.toString(Row1));
            String[] Row2 = reader.readNext();

            String[] header1= {"1", "0.1", "2", "0.2", "3", "0.3", "4", "5", "0.5", "4", "0.4", "5", "0.5", "2"};
            assertEquals(Arrays.toString(header1),Arrays.toString(Row2));


            Row1 = reader.readNext();
            assertEquals(14,Row1.length);
        }catch(Exception e){

        }

    }

}
