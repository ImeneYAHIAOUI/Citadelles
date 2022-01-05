package fr.unice.polytech.startingpoint;


import fr.unice.polytech.startingpoint.core.Simulation;
import fr.unice.polytech.startingpoint.output.Display;

public class Main {
    public static void main(String... args) {
        Display.hello();
        Citadelles citadelle = new Citadelles();
        citadelle.game(0);
        Simulation simulation=new Simulation(1);
        simulation.Simulation();
        simulation=new Simulation(2);

        simulation.Simulation();

        simulation.showResult();
    }
}