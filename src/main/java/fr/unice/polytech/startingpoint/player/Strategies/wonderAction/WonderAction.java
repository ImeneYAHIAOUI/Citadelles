package fr.unice.polytech.startingpoint.player.Strategies.wonderAction;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.ArrayList;
import java.util.List;

public class WonderAction {
    infoaction info ;
    Manufacture manufacture=new Manufacture();

    public WonderAction(){
        this.info = new infoaction();
    }
    public void applyLibrary(IA player,List<IDistrict> cards){
        IDistrict wonder=player.getBuiltDistricts().stream()
                .filter(district -> district.isWonder() && district.getDistrictName()== DistrictName.LIBRARY)
                .findAny().orElse(null);
        if(wonder!=null){
            info.setplayer(player);
            info.setChosenCards(cards);
            ((IWonder )wonder).doAction(info);
        }
    }
    public void applyDongeon(){
    }

    public void applyManufacture(IA player,DistrictDeck deck,Treasure tresor) {
        int i;
        info.setTreasure(tresor);
        info.setplayer(player);
        info.setdistrictdeck(deck);
        IDistrict wonder = player.getBuiltDistricts().stream()
                .filter(district -> district.isWonder() && district.getDistrictName() == DistrictName.MANUFACTURE)
                .findAny().orElse(null);
        if (wonder != null & player.getGold() >= 3) {
            int s = 0;
            int c = 0;
           for (i = 0; i < player.getHand().size(); i++) {
               if (player.getHand().get(i).getPrice() >= 3) {
                    s = s + 1;
                } else c = c + 1;}
            if ( s > c || player.getHand().size()== 0)
            { manufacture.doAction(info);}

        }
        }


    public void applyMiracleCourt(IA player,Color choosencolor){
        info.setplayer(player);
        info.setchoosencolor(choosencolor);
        IDistrict wonder = player.getHand().stream()
                .filter(district -> district.isWonder() && district.getDistrictName() == DistrictName.LACOURDESMIRACLES)
                .findAny().orElse(null);
    /**    if(wonder!=null){
            int i;
            for (i = 0; i < player.getBuiltDistricts().size(); i++){
                if(player.getBuiltDistricts().get(i).getColor()!={

                }
            }**/



        }

    }
