package fr.unice.polytech.startingpoint.heros;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.District;
import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.Information;

import java.util.ArrayList;
import java.util.List;

public class Magician extends Hero{
    public Magician(){
    this.name = HeroName.Magician;
    this.color = Color.PURPLE;
    this.rank = 3;
    }

    @Override
    public void doAction(Information information) {
        IPlayer player = information.getCurrentPlayer();
        IPlayer playerChosen = information.getChosenPlayer();
        List<IDistrict> hand = new ArrayList<>(player.getHand());
        if (playerChosen != null || information.getChosenCards().size()>0) {
            if (playerChosen == null) {
                int numberCards = information.getChosenCards().size();
                DistrictDeck districtDeck =information.getDeck();
                List<IDistrict> cards = districtDeck.giveDistrict(numberCards);
                player.getDistrict(cards);
                player.getHand().removeAll(information.getChosenCards());
                districtDeck.addAll(information.getChosenCards());
            } else {
                player.setHand(playerChosen.getHand());
                playerChosen.setHand(hand);
            }
        }
    }
}
