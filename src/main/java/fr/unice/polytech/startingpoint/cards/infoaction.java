package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.List;

public class infoaction {
    public District Districtremove;
    List<IDistrict> hand;
    Treasure treasure;
    IPlayer player1;
    List<District> buildDistrict;
    Enum Color;
    Enum Districtname;
    District district;
      void setplayer(IPlayer player1){
            this.player1=player1;
        }

        IPlayer getplayer(){
        return this.player1;
    }
    Treasure getTreasure(){
          return this.treasure;
    }

    /** recuperation de la main**/
    List<IDistrict>  getHAND(){return this.hand;}
    void setHAND(List<IDistrict> hand){ this.hand=hand;}
    /** le quartier choisit pour enlever de la main **/
    District getDistrictremove() { return this.Districtremove;}
    void  setDistrictremove(District Districtremove ) { this.Districtremove=Districtremove;}
    void setTreasure(Treasure treasure ){
        this.treasure=treasure;
    }
}
