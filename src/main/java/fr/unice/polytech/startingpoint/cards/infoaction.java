package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.ArrayList;
import java.util.List;

public class infoaction {
    public District Districtremove;
    List<IDistrict> hand;
    Treasure treasure;
    IPlayer player1;
    List<District> buildDistrict;
    Enum Color;
    Enum Districtname;
    List<IDistrict> giveDistrict;
    District district;
     List<IDistrict> buildlist=new ArrayList<>();
    private Color color;
    DistrictDeck districtdeck;
    IPlayer player;


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
    /** le quartier choisit pour enlever de la main **/
    public District getDistrictremove() { return this.Districtremove;}
    public void  setDistrictremove(District Districtremove ) { this.Districtremove=Districtremove;}
    public void setTreasure(Treasure treasure ){
        this.treasure=treasure;
    }

    public List<IDistrict> getattributeHand() {
       this.districtdeck = new DistrictDeck(Initialization.districtList());
        return districtdeck.giveDistrict(3);
    }
     public List<IDistrict> getbuilddisctrict(){
        infoaction info =new infoaction();
      buildlist =info.getplayer().getBuiltDistricts();
      return buildlist;

    }
    public void setchoosencolor( Color color) {
        this.color=color;
    }
     public Color getchoosencolor(){
        return this.color;
    }
}
