package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.List;

public class infoaction {
    public District Districtremove;
    List<IDistrict> hand;
    IPlayer player1;
    List<District> buildDistrict;
    Enum Color;
    Enum Districtname;
    District district;
    IPlayer getplayer(){
        return this.player1;
    }

District getDistrictremove(){
        return this.Districtremove;
}
List<IDistrict>  getHAND(){return this.hand;}
District Districtremove() { return this.Districtremove;}
}
