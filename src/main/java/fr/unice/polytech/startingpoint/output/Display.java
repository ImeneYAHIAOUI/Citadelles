package fr.unice.polytech.startingpoint.output;

import fr.unice.polytech.startingpoint.player.Player;

import java.util.*;

public class Display {


    private GameResult result;


    public Display(GameResult result) {
        this.result = result;
    }
    public String displayResult(){

        List<Player> ranking = result.getRanking();

        String display = "Winner : " + ranking.get(0)+"\n\n";

        int i = 1;
        for (Player p: ranking) {
            String s = switch (i){
                case 1 -> "st";
                case 2 -> "nd";
                case 3 -> "rd";
                default -> "th";
            };
            display += i+s+" place : "+p+" -> "+p.getScore()+" points.\n";
            i++;
            }
        return display;
    }

}