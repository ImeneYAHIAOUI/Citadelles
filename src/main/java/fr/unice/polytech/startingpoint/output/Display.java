package fr.unice.polytech.startingpoint.output;

import fr.unice.polytech.startingpoint.player.Player;

import java.util.*;
import java.util.stream.*;


public class Display {


    private GameResult result;

    public Display(GameResult result) {
        this.result = result;

    }

    public String displayRank(List<Player> players) {
        String ranking;
        int rank = 1;
        ranking = "1st place : " + players.get(0) + " -> " + players.get(0).getScore() + " points.\n";
        for (int i = 1; i < players.size(); i++) {
            String s = switch (rank) {
                case 1 -> "st";
                case 2 -> "nd";
                case 3 -> "rd";
                default -> "th";
            };
            ranking += rank + s + " place : " + players.get(i) + " -> " + players.get(i).getScore() + " points.\n";

            if (players.get(i).getScore() != players.get(i - 1).getScore()) rank = i + 1;

        }
        return ranking;
    }


    public String displayWinners(List<Player> ranking){
        String winners = ""+ranking.get(0) ;
        int nbWinners = 1;

        for(int i = 1; i < ranking.size();i++){
            if (ranking.get(i).getScore() == ranking.get(0).getScore()) {
                winners += " - "+ranking.get(i);
                nbWinners++;
            }
        }
        String plural = nbWinners>1 ? "s":"";
        winners = "Winner"+plural+" : "+winners+"\n\n";
        return winners;
    }

    public String displayResult(){

        List<Player> rankedPlayers = result.getRanking();


        return displayWinners(rankedPlayers)+displayRank(rankedPlayers);
    }

}