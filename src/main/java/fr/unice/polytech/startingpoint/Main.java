package fr.unice.polytech.startingpoint;


import fr.unice.polytech.startingpoint.output.Display;

public class Main {
    public static void main(String... args) {
        Display.hello();
        Citadelles citadelle = new Citadelles();
        citadelle.game();
    }
}