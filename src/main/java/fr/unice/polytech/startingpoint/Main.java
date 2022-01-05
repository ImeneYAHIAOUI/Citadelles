package fr.unice.polytech.startingpoint;


import fr.unice.polytech.startingpoint.core.Simulation;
import fr.unice.polytech.startingpoint.output.Display;
import fr.unice.polytech.startingpoint.output.TerminalFormatter;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String... args) {
        //Level level = Level.FINEST; // Pour tout voir
        Level level = Level.FINER; // Pour voir juste les stats
        Citadelles citadelle = new Citadelles(level);
        citadelle.game(0);
        Simulation simulation=new Simulation(1);
        simulation.Simulation(level);
        simulation=new Simulation(2);
        simulation.Simulation(level);
        simulation.showResult(level);
    }
}