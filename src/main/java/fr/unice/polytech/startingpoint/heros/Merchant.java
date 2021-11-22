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
<<<<<<< HEAD
=======
        information.getCurrentPlayer().addGold(1);
>>>>>>> 4bee8055a0dd6cd01326f349bdcb91dfad7172a0
        IPlayer player=information.getCurrentPlayer();
        player.addPieces(1);
        player.getBuiltDistricts().forEach(district -> {
            if(district.getColor() == this.color) {
                information.getCurrentPlayer().addGold(1);
            }
        });
    }
}
