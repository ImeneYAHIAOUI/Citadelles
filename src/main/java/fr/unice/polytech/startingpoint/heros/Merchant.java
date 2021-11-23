package fr.unice.polytech.startingpoint.heros;


import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.Information;



public class Merchant extends Hero{
    public Merchant(){
        this.name = HeroName.Merchant;
        this.color = Color.GREEN;
        this.rank = 6;
    }

    @Override
    public void doAction(Information information) {
        IPlayer player=information.getCurrentPlayer();
        player.addGold(1);
        player.getBuiltDistricts().forEach(district -> {
            if(district.getColor() == this.color) {
                information.getCurrentPlayer().addGold(1);
            }
        });
    }
}
