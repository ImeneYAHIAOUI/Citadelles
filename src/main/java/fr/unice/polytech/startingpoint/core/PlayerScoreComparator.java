package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.Comparator;

public class PlayerScoreComparator implements Comparator<IPlayer> {

    /** comparing players score
     *
     * @param p1
     * @param p2
     *
     */
    @Override
    public int compare(IPlayer p1, IPlayer p2){
        return  Integer.compare(p1.getScore(),p2.getScore());
    }

}
