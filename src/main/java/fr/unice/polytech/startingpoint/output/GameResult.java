package fr.unice.polytech.startingpoint.output;

import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.*;

public class GameResult {
    private List<IPlayer> ranking;

     public GameResult(List<IPlayer> ranking) {
        this.ranking = ranking;
    }

    public List<IPlayer> getRanking() {
        return ranking;
    }

}
