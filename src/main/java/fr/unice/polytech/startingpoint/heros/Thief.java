package fr.unice.polytech.startingpoint.heros;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.Information;

public class Thief extends Hero{
    public Thief(){
        this.name = HeroName.Thief;
        this.color = Color.WHITE;
        this.rank = 2;
    }
    @Override
    public void doAction(Information information) {
        IPlayer chosenPlayer = information.getChosenPlayer();
        if(chosenPlayer!= null) {
            IPlayer currentPlayer = information.getCurrentPlayer();
            if(chosenPlayer.getHeroRank()!=1 && !chosenPlayer.getIsAssigned()){
                chosenPlayer.setStolenPerson();
                chosenPlayer.setStolenBy(currentPlayer);
            }
        }

    }

}
