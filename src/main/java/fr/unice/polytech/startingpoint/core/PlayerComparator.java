package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.output.GameResult;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.Collections;
import java.util.List;

public class PlayerComparator {
    private List<IPlayer> players;

    /** sorting of IA list  in a decreasing way as to have the player with the highest score at index 0
     *
     * @param players
     *
     */
    public PlayerComparator(List<IPlayer> players){
        this.players = players;
        Collections.sort(players,new PlayerHeroRankComparator());
        Collections.reverse(players);
    }
    public List<IPlayer> getSortedPlayers(){
        return players;
    }
}
