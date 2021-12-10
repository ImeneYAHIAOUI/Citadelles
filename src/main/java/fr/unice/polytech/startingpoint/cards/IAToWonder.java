package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.player.IPlayer;
import java.util.List;

/*
 * This class stores the information of the choice of the IPlayer to do the actions in the wonders.
 */
public class IAToWonder {
    public IDistrict Districtremove;
    List<IDistrict> hand;
    Treasure treasure;
    DistrictDeck deck;
    private Color color;
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
     * @param player
     */
    public void setplayer(IPlayer player){
            this.player=player;
    }

    /**
     * Return the player
     * @return
     */
    public IPlayer getplayer(){
        return this.player;
    }

    /**
     * reuperation du deck
     * @param deck
     */
    public void setdistrictdeck(DistrictDeck deck){
        this.deck=deck;
    }

    /**
     * Return the district deck
     * @return
     */
    public DistrictDeck getdistrictdeck() {
        return this.deck;
    }

    /**
     * The neighborhood chosen by AI to remove from hand
     * @return
     */
    public IDistrict getDistrictremove() {
        return this.Districtremove;
    }

    /**
     * District withdrawn
     * @param Districtremove
     */
    public void setDistrictremove(IDistrict Districtremove ) {
        this.Districtremove =  Districtremove;
    }

    /**
     * Recovery of the treasury that manages the accounts
     * @param treasure
     */
    public void setTreasure(Treasure treasure ){
        this.treasure=treasure;
    }

    /**
     * Return the treasure
     * @return
     */
    public Treasure getTreasure(){
        return this.treasure;
    }

    /**
     * Return the parameter for givedistrict method in Manufacture to assign 3 district to the player's hand
     * @return
     */
    public List<IDistrict> getattributeHand() {
        return deck.giveDistrict(3);
    }

    /**
     * The color chosen by the ai to change the color of the wonder the court of miracles
     * @param color
     */
    public void setchoosencolor( Color color) {
        this.color=color;
    }

    /**
     * Return the choosen color
     * @return
     */
     public Color getchoosencolor(){
        return this.color;
    }
}

