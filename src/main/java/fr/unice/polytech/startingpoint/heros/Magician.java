package fr.unice.polytech.startingpoint.heros;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.District;
import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.Information;

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
        List<IDistrict> hand = player.getHand();
        if (playerChosen != null || information.getChosenCards() != null) {
            if (playerChosen == null) {
                int numberCards = information.getChosenCards().size();
                DistrictDeck districtDeck =information.getDeck();
                List<IDistrict> cards = districtDeck.giveDistrict(numberCards);
                player.setHand(cards);
                districtDeck.addAll(hand);
            } else {
                player.setHand(playerChosen.getHand());
                playerChosen.setHand(hand);
            }
        }
    }
}
