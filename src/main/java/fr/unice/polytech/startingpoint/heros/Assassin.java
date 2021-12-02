package fr.unice.polytech.startingpoint.heros;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.Information;

public class Assassin extends Hero {
    public Assassin(){
        this.name = HeroName.Assassin;
        this.color = Color.WHITE;
        this.rank = 1;
    }
    @Override
    public void doAction(Information information) {
        IPlayer playerAssigned= information.getChosenPlayer();
        if(playerAssigned!=null) {
            playerAssigned.setIsAssigned();
        }
    }
}
