package fr.unice.polytech.startingpoint;

import fr.unice.polytech.startingpoint.player.Player;

public class Main {


    public static String hello() {
        return "Hello World!";
    }

    public static void main(String... args) {

        System.out.println(hello());

        Citadelle citadelle = new Citadelle();
        citadelle.game();

    }

}