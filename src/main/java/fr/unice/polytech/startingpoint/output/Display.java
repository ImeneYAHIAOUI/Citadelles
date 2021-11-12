package fr.unice.polytech.startingpoint.output;

import fr.unice.polytech.startingpoint.player.Player;

import java.util.ArrayList;

public class Display {


    private GameResult result;

    public Display(GameResult result) {
        this.result = result;
        ArrayList<Player> players = result.getresult();
        System.out.println("the winner is  " + players.get(0));
        int i;
        for (i = 0; i < players.size(); i++) {
                System.out.println(players.get(i) + "'s position is" + players.get(i) + "est" + (i + 1));
            }





    }
}