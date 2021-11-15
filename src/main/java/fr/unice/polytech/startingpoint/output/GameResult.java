package fr.unice.polytech.startingpoint.output;

import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.*;

public class GameResult {
    private List<IA> ranking;

     public GameResult(List<IA> ranking) {
        this.ranking = ranking;
    }

    public List<IA> getRanking() {
        return ranking;
    }

}
