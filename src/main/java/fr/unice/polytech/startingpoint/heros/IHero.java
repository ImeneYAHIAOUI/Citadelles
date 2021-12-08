package fr.unice.polytech.startingpoint.heros;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.player.IAToHero;

public interface IHero {
    /**
     * Name getter
     * @return
     */
    HeroName getName();

    /**
     * Color getter
     * @return
     */
    Color getColor();

    /**
     * Rank getter
     * @return
     */
    int getRank();

    /**
     * Do the hero action
     *
     * @param information
     */
    public void doAction(IAToHero information );
}
