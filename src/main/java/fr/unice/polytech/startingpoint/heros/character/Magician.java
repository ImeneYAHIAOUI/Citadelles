package fr.unice.polytech.startingpoint.heros.character;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.heros.Hero;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.IAToHero;

import java.util.ArrayList;
import java.util.List;

public class Magician extends Hero {
    public Magician(){
    this.name = HeroName.Magician;
    this.color = Color.WHITE;
    this.rank = 3;
    }

    @Override
    public void doAction(IAToHero information) {
        IPlayer player = information.getCurrentPlayer();
        IPlayer chosenPlayer = information.getChosenPlayer();
        List<IDistrict> hand = new ArrayList<>(player.getHand());
        if (chosenPlayer != null || information.getChosenCards().size()>0) {
            if (chosenPlayer == null) {
                int numberCards = information.getChosenCards().size();
                DistrictDeck districtDeck =information.getDeck();
                List<IDistrict> cards = districtDeck.giveDistrict(numberCards);
                player.getDistrict(cards);
                player.getHand().removeAll(information.getChosenCards());
                districtDeck.addDistricts(information.getChosenCards());
            } else {
                player.setHand(chosenPlayer.getHand());
                chosenPlayer.setHand(hand);
            }
        }
    }
}

