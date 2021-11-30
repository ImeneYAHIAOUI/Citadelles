package fr.unice.polytech.startingpoint.player;

import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.cards.Treasure;
import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.heros.IHero;

import java.util.List;

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
    void chooseHero(HeroDeck heroes, int roleIndex);

    /**
     * each hero has a function, if the player chooses to, they can activate it by using
     * this method
     * @param players,districtDeck,info the list of players
     */
    void activateHero(List<IPlayer> players, DistrictDeck districtDeck, Treasure treasure);

    /**
     * this method is responsible for choosing and implementing the players move.
     * getting 2 gold pieces or drawing two district cards
     * choosing what district to build? (maybe another method will take care of that, but it most
     * likely will be called here)
     */
    void doAction(Treasure treasure);

    /**
     * Give the player its first district cards by adding them to hand
     * @param giveDistrict list of given district cards
     */
    void getDistrict(List<IDistrict> giveDistrict);
    void setIsAssigned();
    void unsetIsAssigned();
    boolean getIsAssigned();

    /**
     * role getter
     * @return the player's role
     */
    IHero getRole();

    /**
     * Have the list of available heroes
     * @param heroes list of available heros
     */



    /**
     * hand getter
     * @return the players hand
     */
    List<IDistrict> getHand();

    /**
     * score getter
     * @return score
     */
    int getScore();
    void setScore(int score);
    void setCrown();
    void unSetCrown();
    boolean getCrown();
    /**
     * once a king is chosen (randomly at first or based on the king role card), this method
     * is called to mark the player that has the crown with isKing
     */
    void setHand(List<IDistrict> hand);

    /**
     * Rank hero getter
     * @return
     */
    int getHeroRank();

    List<IDistrict> getBuiltDistricts();
    int getGold();
    String getName();

    void addGold(int addedValue);
    void removeGold(int removevalue);

    void drawOrGetPieces(DistrictDeck deck,Treasure treasure);

}
