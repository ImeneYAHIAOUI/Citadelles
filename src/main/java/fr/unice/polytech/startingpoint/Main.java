package fr.unice.polytech.startingpoint;


import fr.unice.polytech.startingpoint.output.Display;
import fr.unice.polytech.startingpoint.player.IA.NiceNastyBot;

public class Main {
    public static void main(String... args) {
        Display.hello();
        Citadelles citadelle = new Citadelles();
        citadelle.game();
    }
}