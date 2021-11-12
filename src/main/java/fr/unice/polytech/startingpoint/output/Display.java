package fr.unice.polytech.startingpoint.output;

import fr.unice.polytech.startingpoint.player.Player;

import java.util.*;

public class Display {


    private GameResult result;

    public Display(GameResult result) {
        this.result = result;

        List<Player> ranking = result.getRanking();

        System.out.println("Winner : " + ranking.get(0)+"\n");

        int i = 1;
        for (Player p: ranking) {
            String s = switch (i){
                case 1 -> "st";
                case 2 -> "nd";
                case 3 -> "rd";
                default -> "th";
            };
            System.out.println(i+s+" place : "+p+" -> "+p.getScore()+" points.");
            i++;
            }
    }
}