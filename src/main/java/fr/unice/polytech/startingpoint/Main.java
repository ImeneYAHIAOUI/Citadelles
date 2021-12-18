package fr.unice.polytech.startingpoint;


import fr.unice.polytech.startingpoint.output.Display;

public class Main {
    public static void main(String... args) {
            Display.hello();
            Citadelles citadelle = new Citadelles();

            for(int i = 0; i < 100; i++) {
                System.out.println("\n\nNEW : " + i + "\n\n");
                citadelle.game();
            }
    }
}