package fr.unice.polytech.startingpoint.heros;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.Information;

public class Thief extends Hero{
    public Thief(){
        this.name = HeroName.Thief;
        this.color = Color.PURPLE;
        this.rank = 2;
    }
    @Override
    public void doAction(Information information) {
        if(information.getChosenPlayer()!= null) {
            IPlayer currentPlayer = information.getCurrentPlayer();
            IPlayer chosenPlayer = information.getChosenPlayer();
            if(chosenPlayer.getHeroRank()!=1 && !chosenPlayer.getIsAssigned()){
                chosenPlayer.setStolenPerson();
                chosenPlayer.setStolenBy(currentPlayer);
            }
        }

    }

}
