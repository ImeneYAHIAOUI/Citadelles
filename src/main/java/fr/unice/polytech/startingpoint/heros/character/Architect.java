package fr.unice.polytech.startingpoint.heros.character;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.heros.Hero;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.List;

/*
 * The Architect draws two more district cards.
 * It can build up to three neighborhoods.
 */
public class Architect extends Hero {
    public Architect(){
        this.name = HeroName.Architect;
        this.color = Color.WHITE;
        this.rank = 7;
    }

    @Override
    public void doAction(IAToHero information) {
        IPlayer chosenPlayer = information.getCurrentPlayer();

        // Draws two more district cards
        List<IDistrict> list = information.getDeck().giveDistrict(2);
        chosenPlayer.getDistrict(list);
    }   
}
