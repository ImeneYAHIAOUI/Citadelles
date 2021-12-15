package fr.unice.polytech.startingpoint.heros.character;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.heros.Hero;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.List;

public class Condottiere extends Hero {
    public Condottiere() {
        this.name = HeroName.Condottiere;
        this.color = Color.RED;
        this.rank = 8;
    }

    @Override
    public void doAction(IAToHero information){
        Treasure treasure=information.getTreasure();
        IPlayer player=information.getCurrentPlayer();
        IDistrict card=player.getCardDestroyedByCondottiere();
        if(card!=null){
            treasure.addToTreasure(card.getPrice()-1);
            player.removeGold(card.getPrice()-1);
            information.getDeck().getDistrictList().add(card);
            information.getChosenPlayer().getBuiltDistricts().remove(card);
            information.setChosenCards(List.of(card));
            player.setCardDestroyedByCondottiere(null);
        }
        player.getBuiltDistricts().forEach(district -> {
            if (district.getColor() == this.color) {
                player.addGold(treasure.removeGold(1));
            }
        });
    }
}
