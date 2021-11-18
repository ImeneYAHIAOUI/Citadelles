package fr.unice.polytech.startingpoint.heros;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.District;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.List;

public class Merchant extends Hero{
    public Merchant(){
        this.name = HeroName.Merchant;
        this.color = Color.GREEN;
        this.rank = 6;
    }

    @Override
    public void doAction(List<District> districts, IPlayer player) {
        //player.goldWon(1);
        districts.forEach(district -> {
            if(district.getColor() == this.color) {
                //player.goldWon(1);
            }
        });
    }
}
