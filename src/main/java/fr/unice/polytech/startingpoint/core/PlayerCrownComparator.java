package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.Comparator;

public class PlayerCrownComparator implements Comparator<IPlayer> {
    /**
     *
     * @param p1
     * @param p2
     * @return
     */
    @Override
    public int compare(IPlayer p1, IPlayer p2) {
        return Boolean.compare(p2.getCrown(), p1.getCrown());
    }
}
