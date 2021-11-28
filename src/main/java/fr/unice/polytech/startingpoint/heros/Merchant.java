package fr.unice.polytech.startingpoint.heros;


import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.Treasure;
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
        Treasure treasure=information.getTreasure();
        IPlayer player=information.getCurrentPlayer();
        int pieces=treasure.getPieces();
        if(pieces>1){
            player.addGold(1);
            treasure.removeGold(1);
        }

        player.getBuiltDistricts().forEach(district -> {
            if(district.getColor() == this.color && pieces>=1) {
                player.addGold(1);
                treasure.removeGold(1);

            }
        });
    }
}
