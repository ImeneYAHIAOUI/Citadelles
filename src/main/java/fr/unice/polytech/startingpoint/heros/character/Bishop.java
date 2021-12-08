package fr.unice.polytech.startingpoint.heros.character;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.heros.Hero;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;

public class Bishop extends Hero {
    public Bishop(){
        this.name = HeroName.Bishop;
        this.color = Color.BLUE;
        this.rank = 5;
    }
    @Override
    public void doAction(IAToHero information) {
        Treasure treasure=information.getTreasure();
        IPlayer player=information.getCurrentPlayer();
        player.getBuiltDistricts().forEach(district -> {
            if(district.getColor() == this.color ) {
                player.addGold( treasure.removeGold(1));
            }
        });
    }
}
