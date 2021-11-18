package fr.unice.polytech.startingpoint.Heros;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.District;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.List;
import java.util.Optional;

public class Merchant implements IHero{
    private Hero name;
    private Color color;

    public Merchant(){
        name = Hero.Merchant;
        color = Color.GREEN;
    }

    @Override
    public Hero getName() {
        return name;
    }

    @Override
    public Color getColor() {
        return color;
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
