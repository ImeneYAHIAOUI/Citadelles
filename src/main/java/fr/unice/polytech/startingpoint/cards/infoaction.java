package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.Information;

import java.util.ArrayList;
import java.util.List;

public class infoaction {
    public District Districtremove;
    Boolean fiveColorMiracleCourt;
    Boolean threeManufacture;
    List<IDistrict> hand;
    Treasure treasure;
    IPlayer player1;
    List<District> buildDistrict;
    Enum Color;
    Enum Districtname;
    List<IDistrict> giveDistrict;
    District district;
    List<IDistrict> buildlist = new ArrayList<>();
    List<IDistrict> chosenCards;


    private Color color;
    DistrictDeck districtdeck = (DistrictDeck) Initialization.districtList();
    IPlayer player;
    District choice;

    public void setThreeManufacture(Boolean threeManufacture) {
        this.threeManufacture = threeManufacture;
    }

    public void setFiveColorMiracleCourt(Boolean fiveColorMiracleCourt) {
        this.fiveColorMiracleCourt = fiveColorMiracleCourt;
    }

    public Boolean getFiveColorMiracleCourt() {
        return fiveColorMiracleCourt;
    }

    public Boolean getThreeManufacture() {
        return threeManufacture;
    }

    public void setplayer(IPlayer player){
            this.player=player;
        }
        public IPlayer getplayer(){
        return this.player;
    }
     public Treasure getTreasure(){
          return this.treasure;
    }

    /** recuperation de la main**/
    List<IDistrict>  getHAND(){return this.hand;}
   public void setHAND(List<IDistrict> hand){ this.hand=hand;}
    /** le quartier choisit par IA  pour enlever de la main **/
    public District getDistrictremove() { return this.Districtremove;}
    public void  setDistrictremove(District Districtremove ) { this.Districtremove=Districtremove;}
   /** recuperation du tresor qui gere les comptes **/
    public void setTreasure(Treasure treasure ){
        this.treasure=treasure;
    }
/** retourne le parametre pour methode givedistrict dans Manufacture pour attribuer 3 district à la main du player*/
    public List<IDistrict> getattributeHand() {

       this.districtdeck = (DistrictDeck) Initialization.districtList();
        return districtdeck.giveDistrict(3);
    }
    /** build la liste des quartiers construits qu'il faut analyser pour l'action de la court des miracles **/
    public void setbuildlist(List<IDistrict> buildlist){
        this.buildlist=buildlist;
    }
     public List<IDistrict> getbuilddisctrict(){
        return this.buildlist;

    }
    /** La couleur choisit par l'ia pour changer la couleur du wonder  la court des miracles **/
    public void setchoosencolor( Color color) {
        this.color=color;
    }
     public Color getchoosencolor(){
        return this.color;
    }
/** pour l'action de l'observatoire au debut du tour le player a le droit de piocher trois district**/
    public List<IDistrict> gettriocard( ){

        List<IDistrict> districtProposal = this.districtdeck.giveDistrict(3);
        return districtProposal;

    }
    /** ca  represente les deux quartiers piocher que le player peut garder sans se defausser d'une **/
    public void setChosenCards(List<IDistrict> chosenCards){
        this.chosenCards=chosenCards;

    }

    public List<IDistrict> getChosenCards() {
        return chosenCards;
    }
/** choice represente le district que le ia decide  de garder alors que les deux autres seront remis au districtlist**/
    public void setchoice(District choice ) {
        this.choice = choice;
    }
    public District getchoice(){
        return this.choice;
    }



}

