package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.Comparator;

public class PlayerHeroRankComparator implements Comparator<IPlayer> {

    /**
     *
     * @param p1
     * @param p2
     * @return
     */
    @Override
    public int compare(IPlayer p1, IPlayer p2) {
        return Integer.compare(p2.getTheHeroRank(), p1.getTheHeroRank());
    }
}
