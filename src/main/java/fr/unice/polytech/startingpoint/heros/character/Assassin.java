package fr.unice.polytech.startingpoint.heros.character;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.heros.Hero;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;

public class Assassin extends Hero {
    public Assassin(){
        this.name = HeroName.Assassin;
        this.color = Color.WHITE;
        this.rank = 1;
    }
    @Override
    public void doAction(IAToHero information) {
        IPlayer playerAssigned= information.getChosenPlayer();
        if(playerAssigned!=null) {
            playerAssigned.setIsAssigned();
        }
    }
}
