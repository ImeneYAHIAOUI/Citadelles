package fr.unice.polytech.startingpoint.player.Strategies.wonderAction;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.ArrayList;
import java.util.List;

public class WonderAction {
    infoaction info = null;

    public WonderAction(){
        this.info = new infoaction();
    }
    public void applyLibrary(IA player,List<IDistrict> cards){
        IDistrict wonder=player.getHand().stream()
                .filter(district -> district.isWonder() && district.getDistrictName()== DistrictName.MANUFACTURE)
                .findAny().orElse(null);
        if(wonder!=null){
            info.setChosenCards(cards);
            ((IWonder )wonder).doAction(info);
        }
    }
    public void applyDongeon(){
    }

    public void applyManufacture(IA player,DistrictDeck deck){

    }

    public void applyMiracleCourt(IA player){

    }
}
