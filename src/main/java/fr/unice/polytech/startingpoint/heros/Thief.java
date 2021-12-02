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
        IPlayer currentPlayer = information.getCurrentPlayer();
        IPlayer chosenPlayer = information.getChosenPlayer();
        chosenPlayer.setStolenPerson();
        chosenPlayer.setStolenBy(currentPlayer);

    }

}
