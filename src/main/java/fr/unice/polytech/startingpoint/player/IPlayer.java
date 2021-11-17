package fr.unice.polytech.startingpoint.player;

import fr.unice.polytech.startingpoint.Heros.IHero;

/**
 * The citadelle class needs these three methods to function.
 * When we creat a player class, whether it's for a human or a bot,
 * by implementing this interface, we would be obligated to also implement
 * its method. This way we won't need to rework the citadelle class
 */

public interface IPlayer {
    /**
     * as the name indicates, this method chooses the role of the player for the round
     */
    void chooseHero();

    /**
     * each hero has a function, if the player chooses to, they can activate it by using
     * this method
     * @param hero by having the hero in the parameter, the method will know what function
     * to activate
     */
    void activateHero(IHero hero);

    /**
     * this method is responsible for choosing and implementing the players move.
     * getting 2 gold pieces or drawing two district cards
     * choosing what district to build? (maybe another method will take care of that, but it most
     * likely will be called here)
     */
    void doAction();


}
