package fr.unice.polytech.startingpoint.core;
import fr.unice.polytech.startingpoint.output.GameResult;
import fr.unice.polytech.startingpoint.player.*;
import java.util.*;

public class GameComparator {
    private List<IA> players;

    /** sorting of IA list  in a decreasing way as to have the player with the highest score at index 0
     *
     * @param players
     *
     */
    public GameComparator(List<IA> players){
        this.players = players;
        Collections.sort(players,new PlayerScoreComparator());
        Collections.reverse(players);
    }
    public List<IA> getSortedPlayers(){
       return players;
    }
    public GameResult getResult(){
        return new GameResult(players);
    }
    


}
