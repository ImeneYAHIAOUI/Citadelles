package fr.unice.polytech.startingpoint.core;

import au.com.bytecode.opencsv.CSVReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
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
        if(Files.exists(Paths.get("./src/test/resources/save/result.csv"))){
            File file = new File("./src/test/resources/save/result.csv");
            file.delete();

        }

    }
    @Test
    void simulation1(){

        sim=new Simulation(1,"./src/test/resources/save/result.csv");
        sim.Simulation(level);
        assertTrue(Files.exists(Paths.get("./src/test/resources/save/result.csv")));
        try {
            CSVReader reader = new CSVReader(new FileReader("./src/test/resources/save/result.csv"), ',', '"', 1);
            //Read all rows at once
            String[] Row1 = reader.readNext();
            assertTrue(Row1[0].equals("SIMULATION1"));
            String[] Row2= reader.readNext();
            assertEquals(14,Row2.length);
            assertEquals(Integer.parseInt(Row1[0])+Integer.parseInt(Row1[2])+Integer.parseInt(Row1[4]),1000);
            assertEquals(Integer.parseInt(Row1[7])+Integer.parseInt(Row1[9])+Integer.parseInt(Row1[11]),1000);
        }catch(Exception e){

        }
    }
    @Test
    void simulation2(){
        sim=new Simulation(2,"./src/test/resources/save/result1.csv");
        sim.Simulation(level);
        assertTrue(Files.exists(Paths.get("./src/test/resources/save/result1.csv")));
        try {
            CSVReader reader = new CSVReader(new FileReader("./src/test/resources/save/result1.csv"), ',', '"', 1);
            //Read all rows at once
            String[] Row1 = reader.readNext();
            System.out.println(Row1[0]);

            assertTrue(Row1[0].equals("SIMULATION2"));
            String[] Row2= reader.readNext();
            assertEquals(14,Row2.length);


            assertEquals(Integer.parseInt(Row1[0])+Integer.parseInt(Row1[2])+Integer.parseInt(Row1[4]),1000);
            assertEquals(Integer.parseInt(Row1[7])+Integer.parseInt(Row1[9])+Integer.parseInt(Row1[11]),1000);
        }catch(Exception e) {

        }
    }
    @Test
    void Write(){

        sim=new Simulation(1,"./src/test/resources/save/result2.csv");

        sim.Write(sim.statisticsToWrite(1,2,3,4,5,4,5,2));
        try {
            CSVReader reader = new CSVReader(new FileReader("./src/test/resources/save/result2.csv"), ',', '"', 1);
            //Read all rows at once
            List<String[]> Row1 = reader.readAll();

            String[] header = {"1","0,1","2","0,2","3",
                    "0,3","4","5","0,5","4","0,4"
                    ,"5","0,5","2"};
            String[] header1 = {"SIMULATION1"};
            assertEquals(Arrays.toString(header1),Arrays.toString(Row1.get(0)));

            assertEquals(Arrays.toString(header),Arrays.toString(Row1.get(Row1.size()-1)));

        }catch(Exception e){

        }

    }
    @Test
    void oldsatictics() {
        if(Files.exists(Paths.get("./src/test/resources/save/result3.csv"))){
            File file=new File("./src/test/resources/save/result3.csv");
            file.delete();
        }
        sim = new Simulation(1, "./src/test/resources/save/result3.csv");

        String[] record = sim.oldStatistics();
        String[] header = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
        assertEquals(Arrays.toString(header), Arrays.toString(record));
        sim.Write(sim.statisticsToWrite(1, 2, 3, 4, 5, 4, 5, 2));
        String[] record1 = sim.oldStatistics();
        String[] header1 = {"1", "0,1", "2", "0,2", "3", "0,3", "4", "5", "0,5", "4", "0,4", "5", "0,5", "2"};
        assertEquals(Arrays.toString(header1), Arrays.toString(record1));
    }
    @Test
    void statisticsToWrite(){
        if(Files.exists(Paths.get("./src/test/resources/save/result4.csv"))){
            File file=new File("./src/test/resources/save/result4.csv");
            file.delete();
        }
        sim=new Simulation(1,"./src/test/resources/save/result4.csv");

        String[] record =sim.statisticsToWrite(1,2,3,4,5,6,7,8);
        String[] record1= {"1", "0,1", "2", "0,2", "3"
                , "0,3", "4", "5", "0,5", "6", "0,6", "7", "0,7", "8"};
        assertEquals(Arrays.toString(record),Arrays.toString(record1));
        sim.Write(record);
        sim.Write(record1);

        String[] record2 =sim.statisticsToWrite(8,2,4,4,5,2,7,10);
        assertEquals(Arrays.toString(record2),Arrays.toString(new String[]{"4", "0,45","2", "0,2", "3", "0,35", "4", "5","0,5","4","0,4", "7", "0,7", "9"}));
        sim.Write(record2);
        String[] record3 =sim.statisticsToWrite(8,2,4,4,5,2,7,10);

        assertEquals(Arrays.toString(record3),Arrays.toString(new String[]{"6", "0,62","2", "0,2", "3", "0,38", "4", "5","0,5","3","0,3", "7", "0,7", "9"}));



    }

}



