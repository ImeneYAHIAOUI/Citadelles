package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.ArrayList;
import java.util.List;

public class infoaction {
    public IDistrict Districtremove;

    List<IDistrict> hand;
    Treasure treasure;

    DistrictDeck deck;
    private Color color;
    IPlayer player;



    public void setplayer(IPlayer player){
            this.player=player;
    }
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
    public DistrictDeck getdistrictdeck(){
        return this.deck;
}

    /** le quartier choisit par IA  pour enlever de la main **/

    /**
     * recuperation de la main
     * @return
     */
    List<IDistrict>  getHAND(){return this.hand;}
    public void setHAND(List<IDistrict> hand){ this.hand=hand;}
    /**
     * le quartier choisit par IA  pour enlever de la main
     * @return
     */
    public IDistrict getDistrictremove() { return this.Districtremove;}
    public void  setDistrictremove(IDistrict Districtremove ) { this.Districtremove =  Districtremove;}
    /**
     *  recuperation du tresor qui gere les comptes
     * @param treasure
     */
    public void setTreasure(Treasure treasure ){
        this.treasure=treasure;
    }
    public Treasure getTreasure(){
        return this.treasure;
    }
    /**
     * retourne le parametre pour methode givedistrict dans Manufacture pour attribuer 3 district à la main du player
     * @return
     */
    public List<IDistrict> getattributeHand() {
        return deck.giveDistrict(3);
    }



    /**
     *  La couleur choisit par l'ia pour changer la couleur du wonder  la court des miracles
     * @param color
     */
    public void setchoosencolor( Color color) {
        this.color=color;
    }
     public Color getchoosencolor(){
        return this.color;
    }





}

