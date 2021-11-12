package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.player.Player;

import java.util.Comparator;

public class PlayerScoreComparator implements Comparator<Player> {


    @Override
    public int compare(Player player1, Player player2) {
        return Integer.compare(player1.getScore(), player2.getScore());
    }







}
