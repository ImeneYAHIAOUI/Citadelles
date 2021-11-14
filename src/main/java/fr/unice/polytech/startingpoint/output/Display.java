package fr.unice.polytech.startingpoint.output;

import fr.unice.polytech.startingpoint.player.Player;

import java.util.List;


public class Display {


     private GameResult result;

    public Display(GameResult result) {
        this.result = result;


    }


    public String displayRank(List<Player> players) {
        StringBuilder ranking;
        int rank = 1;
        ranking = new StringBuilder("1st place : " + players.get(0) + " -> " + players.get(0).getScore() + " points.\n");
        for (int i = 1; i < players.size(); i++) {
            if (players.get(i).getScore() != players.get(i - 1).getScore()) {
                rank = i + 1;
                String s = switch (rank) {
                    case 1 -> "st";
                    case 2 -> "nd";
                    case 3 -> "rd";
                    default -> "th";

                };
                ranking.append(rank).append(s).append(" place : ").append(players.get(i)).append(" -> ").append(players.get(i).getScore()).append(" points.\n");


            }



        }
        return ranking.toString();
    }
    public String displayWinners(List<Player> ranking){
        StringBuilder winners = new StringBuilder("" + ranking.get(0));
         int nbWinners = 1;

        for(int i = 1; i < ranking.size();i++){
            if (ranking.get(i).getScore() == ranking.get(0).getScore()) {
                winners.append(" - ").append(ranking.get(i));
                nbWinners++;
            }
        }
        String plural = nbWinners>1 ? "s":"";
        winners = new StringBuilder("Winner" + plural + " : " + winners + "\n\n");
        return winners.toString();
    }

    public String displayResult(){

        List<Player> rankedPlayers = result.getRanking();


        return displayWinners(rankedPlayers)+displayRank(rankedPlayers);
    }

}