package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.player.Player;

import java.util.Comparator;

public class PlayerScoreComparator implements Comparator<Player> {


    @Override
    public int compare(Player p1, Player p2){
        return  Integer.compare(p1.getScore(),p2.getScore());
    }

}
