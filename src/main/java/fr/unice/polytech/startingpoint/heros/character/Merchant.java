package fr.unice.polytech.startingpoint.heros.character;


import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.Treasure;
import fr.unice.polytech.startingpoint.heros.Hero;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.Information;



public class Merchant extends Hero {
    public Merchant(){
        this.name = HeroName.Merchant;
        this.color = Color.GREEN;
        this.rank = 6;
    }

    @Override
    public void doAction(Information information) {
        Treasure treasure=information.getTreasure();
        IPlayer player=information.getCurrentPlayer();
        player.addGold(treasure.removeGold(1));
        player.getBuiltDistricts().forEach(district -> {
            if (district.getColor() == this.color) {
                player.addGold(treasure.removeGold(1));
            }
        });

    }
}
