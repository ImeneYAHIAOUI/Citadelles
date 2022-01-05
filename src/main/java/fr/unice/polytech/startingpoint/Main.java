package fr.unice.polytech.startingpoint;


import fr.unice.polytech.startingpoint.core.Simulation;
import java.util.logging.Level;


public class Main {
    public static void main(String... args) {
        Level level = Level.FINEST; // Pour tout voir
        //Level level = Level.FINER; // Pour voir juste les stats
        Citadelles citadelle = new Citadelles(level);
        citadelle.game(0);
        /*µSimulation simulation=new Simulation(1);
        simulation.Simulation(level);
        simulation=new Simulation(2);
        simulation.Simulation(level);
        simulation.showResult(level);*/
    }
}