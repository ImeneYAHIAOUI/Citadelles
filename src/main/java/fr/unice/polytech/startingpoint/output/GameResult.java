package fr.unice.polytech.startingpoint.output;

import fr.unice.polytech.startingpoint.core.comparator;
import fr.unice.polytech.startingpoint.player.Player;

import java.util.ArrayList;

public class GameResult {


    private ArrayList<Player> players = comparator.getPlayers();


    ArrayList<Player> getresult(){
         return  players  ;


    }
}

