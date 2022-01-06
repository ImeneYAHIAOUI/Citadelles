package fr.unice.polytech.startingpoint.core;

import au.com.bytecode.opencsv.CSVReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class SimulationTest {
    Simulation sim;
    FileWriter file;
    Level level ;// Pour voir juste les stats

    @BeforeEach
    void setup(){
        level = Level.FINER;

        try{
            this.file=new FileWriter("./src/test/resources/save/result.csv",false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    void simulation1(){
        sim=new Simulation(1);
        sim.setFile(file);
        sim.Simulation(level);
        try {
            CSVReader reader = new CSVReader(new FileReader("./src/test/resources/save/result.csv"), ',', '"', 1);
            //Read all rows at once
            String[] Row1 = reader.readNext();
            assertEquals(14,Row1.length);

            assertEquals(Integer.parseInt(Row1[0])+Integer.parseInt(Row1[2])+Integer.parseInt(Row1[4]),1000);
            assertEquals(Integer.parseInt(Row1[7])+Integer.parseInt(Row1[9])+Integer.parseInt(Row1[11]),1000);
        }catch(Exception e){
        }

    }
    @Test
    void simulation2(){
        sim=new Simulation(2);
        sim.setFile(file);
        sim.Simulation(level);

        assertTrue(Files.exists(Paths.get("./src/test/resources/save/result.csv")));
        try {
            CSVReader reader = new CSVReader(new FileReader("./src/test/resources/save/result.csv"), ',', '"', 1);
            //Read all rows at once
            String[] Row1 = reader.readNext();
            assertEquals(14,Row1.length);

            assertEquals(Integer.parseInt(Row1[0])+Integer.parseInt(Row1[2])+Integer.parseInt(Row1[4]),1000);
            assertEquals(Integer.parseInt(Row1[7])+Integer.parseInt(Row1[9])+Integer.parseInt(Row1[11]),1000);
        }catch(Exception e){

        }
    }
    @Test
    void Write(){
        sim=new Simulation(1);
        sim.setFile(file);
        sim.Write(sim.statisticsToWrite(1,2,3,4,5,4,5,2,"./src/test/resources/save/result.csv"));
        try {
            CSVReader reader = new CSVReader(new FileReader("./src/test/resources/save/result.csv"), ',', '"', 1);
            //Read all rows at once
            String[] Row1 = reader.readNext();

            String[] header = {"1","0,1","2","0,2","3",
                    "0,3","4","5","0,5","4","0,4"
                    ,"5","0,5","2"};
            assertEquals(Arrays.toString(header),Arrays.toString(Row1));
            List<String[]> Row2 = reader.readAll();
            String[] Row3 = Row2.get(Row2.size()-1);
            String[] header1= {"1", "0,1", "2", "0,2", "3", "0,3", "4", "5", "0,5", "4", "0,4", "5", "0,5", "2"};
            assertEquals(Arrays.toString(header1),Arrays.toString(Row3));
            Row1 = reader.readNext();
            assertEquals(14,Row1.length);
        }catch(Exception e){

        }

    }
    @Test
    void oldsatictics(){
        sim=new Simulation(1);
        sim.setFile(file);
        String[] record =sim.oldStatistics("./src/test/resources/save/result.csv");
        String[] header={"0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
        assertEquals(Arrays.toString(header),Arrays.toString(record));
        sim.Write(sim.statisticsToWrite(1,2,3,4,5,4,5,2,"./src/test/resources/save/result.csv"));
        String[] record1 =sim.oldStatistics("./src/test/resources/save/result.csv");
        String[] header1= {"1", "0,1", "2", "0,2", "3", "0,3", "4", "5", "0,5", "4", "0,4", "5", "0,5", "2"};
        assertEquals(Arrays.toString(header1),Arrays.toString(record1));

    }
    @Test
    void statisticsToWrite(){
        sim=new Simulation(1);
        sim.setFile(file);
        String[] record =sim.statisticsToWrite(1,2,3,4,5,6,7,8,"./src/test/resources/save/result.csv");
        String[] record1= {"1", "0,1", "2", "0,2", "3", "0,3", "4", "5", "0,5", "6", "0,6", "7", "0,7", "8"};
        assertEquals(Arrays.toString(record),Arrays.toString(record1));
        sim.Write(new String[]{"4", "0,5", "2", "0,25", "3", "0,35", "4", "5", "0,6", "4", "0,4", "7", "0,7", "8"});
        String[] record2 =sim.statisticsToWrite(8,2,4,4,5,2,7,10,"./src/test/resources/save/result.csv");
        assertEquals(Arrays.toString(record2),Arrays.toString(new String[]{"8", "0,8", "2", "0,2", "4", "0,4", "4", "5", "0,5", "2", "0,2", "7", "0,7", "10"}));


    }

}



