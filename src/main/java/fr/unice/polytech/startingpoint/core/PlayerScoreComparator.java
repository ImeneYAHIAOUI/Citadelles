package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.Comparator;

public class PlayerScoreComparator implements Comparator<IA> {

    /** comparing players score
     *
     * @param p1
     * @param p2
     *
     */
    @Override
    public int compare(IA p1, IA p2){
        return  Integer.compare(p1.getScore(),p2.getScore());
    }

}
