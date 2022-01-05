package fr.unice.polytech.startingpoint.core;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class SimulationTest {
    Simulation sim;
    FileWriter file;
    @BeforeEach
    void setup(){
        file=mock(FileWriter.class);
        try{
            this.file=new FileWriter("./src/test/resources/save/result.csv", true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    void simulation1(){
        sim=new Simulation(1);
        sim.setFile(file);
        //sim.Simulation();
        try {
            CSVReader reader = new CSVReader(new FileReader("./src/test/resources/save/result.csv"), ',', '"', 1);
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
        sim=new Simulation(2);
        assertTrue(Files.exists(Paths.get("./src/test/resources/save/result.csv")));
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

}



