package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.player.*;

import java.util.*;

public class comparator {
    private ArrayList<Player> players;

    public comparator(ArrayList<Player> players){
        this.players = players;
        Collections.sort(players,Collections.reverseOrder());

    }

    public ArrayList<Player> getPlayers(){
        return players;
    }






}
