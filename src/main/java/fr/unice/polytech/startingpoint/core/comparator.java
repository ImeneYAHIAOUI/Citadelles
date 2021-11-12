package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.output.GameResult;
import fr.unice.polytech.startingpoint.player.*;

import java.util.*;

public class comparator {
    private List<Player> players;


    public comparator(List<Player> players){
        this.players = players;
        Collections.sort(players,Collections.reverseOrder());

    }

    public GameResult getResult(){
        return new GameResult(players);
    }






}
