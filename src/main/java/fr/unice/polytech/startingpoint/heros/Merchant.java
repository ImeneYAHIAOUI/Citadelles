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
        //player.goldWon(1);
        IPlayer player=information.getCurrentPlayer();

        player.getBuiltDistricts().forEach(district -> {
            if(district.getColor() == this.color) {
                //player.goldWon(1);
            }
        });
    }
}
