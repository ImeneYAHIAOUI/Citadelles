package fr.unice.polytech.startingpoint.heros;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.District;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.List;

public class King extends Hero{
    public King() {
        this.name = HeroName.King;
        this.color = Color.YELLOW;
        this.rank = 4;
    }

    @Override
    public void doAction(List<District> districts, IPlayer player) {
        player.setKing();
        districts.forEach(district -> {
            if(district.getColor() == this.color) {
                //player.goldWon(1);
            }
        });
    }
}
