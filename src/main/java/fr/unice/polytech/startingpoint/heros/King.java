package fr.unice.polytech.startingpoint.heros;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.District;
import fr.unice.polytech.startingpoint.cards.Treasure;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.Information;

import java.util.List;

public class King extends Hero{
    public King() {
        this.name = HeroName.King;
        this.color = Color.YELLOW;
        this.rank = 4;
    }

    @Override
    public void doAction(Information information ) {
        IPlayer player=information.getCurrentPlayer();
        IPlayer crownHolder = information.getCrownHolder();
        Treasure treasure=information.getTreasure();
        crownHolder.unSetCrown();
        player.setCrown();
        player.getBuiltDistricts().forEach(district -> {
            if(district.getColor() == this.color && treasure.getPieces()>=1) {
                System.out.println("hi");
                player.addGold(1);
                treasure.removeGold(1);
            }
        });
    }
}
