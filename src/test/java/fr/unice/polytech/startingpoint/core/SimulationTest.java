package fr.unice.polytech.startingpoint.core;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.io.FileWriter;


public class SimulationTest {
    Simulation sim;
    FileWriter file;
    @BeforeEach
    void setup(){
        sim= new Simulation(2);
        file=mock(FileWriter.class);
        sim.setFile(file);
    }
    @Test
    void simulation1(){
        Simulation simulation1= spy(sim);
    }


}
