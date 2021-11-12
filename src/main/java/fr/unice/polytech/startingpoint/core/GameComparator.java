package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.output.GameResult;
import fr.unice.polytech.startingpoint.player.*;

import java.util.*;

public class GameComparator {
    private List<Player> players;


    public GameComparator(List<Player> players){
        this.players = players;
        Collections.sort(players,new PlayerScoreComparator());
        Collections.reverse(players);
    }
    public List<Player> getSortedPlayers(){
       return players;
    }
    public GameResult getResult(){
        return new GameResult(players);
    }
    
  






}
