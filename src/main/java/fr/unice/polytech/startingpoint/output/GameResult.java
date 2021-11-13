package fr.unice.polytech.startingpoint.output;

import fr.unice.polytech.startingpoint.player.Player;

import java.util.*;

public class GameResult {
    private List<Player> ranking;

    public GameResult(List<Player> ranking) {
        this.ranking = ranking;
    }

    public List<Player> getRanking() {
        return ranking;
    }

}
