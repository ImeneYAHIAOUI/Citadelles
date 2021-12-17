package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.player.IPlayer;
import java.util.List;

/*
 * This class stores the information of the choice of the IPlayer to do the actions in the wonders.
 */
public class IAToWonder {
    IDistrict choosenCardOfLaboratory;
    IDistrict choosenCardOfCemetry;
    List<IDistrict> hand;
    Treasure treasure;
    DistrictDeck deck;
    private Color choosenColorOfMiracleCourt;
    Color choosenColorOfMagicSchool;
    IPlayer player;
    IDistrict card;

    public IDistrict getCard() {
        return card;
    }

    public void setCard(IDistrict card) {
        this.card = card;
    }

    /**
     * Add the current player
     *
     * @param player
     */
    public void setplayer(IPlayer player) {
        this.player = player;
    }

    public void setInformationForLaboratory(Treasure tresor, IDistrict card) {
        this.choosenCardOfLaboratory = card;
        this.treasure = tresor;
    }


    /**
     * Return the player
     *
     * @return
     */
    public IPlayer getplayer() {
        return this.player;
    }

    /**
     * reuperation du deck
     *
     * @param deck
     */
    public void setdistrictdeck(DistrictDeck deck) {
        this.deck = deck;
    }

    /**
     * Return the district deck
     *
     * @return
     */
    public DistrictDeck getdistrictdeck() {
        return this.deck;
    }

    /**
     * The neighborhood chosen by AI to remove from hand
     *
     * @return
     */
    public IDistrict getChoosenCardOfLaboratory() {
        return choosenCardOfLaboratory;
    }


    /**
     * Recovery of the treasury that manages the accounts
     *
     * @param treasure
     */
    public void setTreasure(Treasure treasure) {
        this.treasure = treasure;
    }

    /**
     * Return the treasure
     *
     * @return
     */
    public Treasure getTreasure() {
        return this.treasure;
    }

    /**
     * Return the parameter for givedistrict method in Manufacture to assign 3 district to the player's hand
     *
     * @return
     */
    public List<IDistrict> getattributeHand() {
        return deck.giveDistrict(3);
    }

    /**
     * The color chosen by the ai to change the color of the wonder the court of miracles
     *
     * @param color
     */
    public void setInformationForMiracleCourt(Color color) {
        this.choosenColorOfMiracleCourt = color;
    }
    public Color getChoosenColorOfMiracleCourt(){
        return choosenColorOfMiracleCourt;
    }
    public void setInformationForMagicSchool(Color color) {
        this.choosenColorOfMagicSchool = color;

    }
    public Color getChoosenColorOfMagicSchool(){
        return choosenColorOfMagicSchool;
    }
        public void setInformationForCemetry(IDistrict choosenCard){
        this.choosenCardOfCemetry=choosenCard;

    }
}

