package fr.unice.polytech.startingpoint.player;
import fr.unice.polytech.startingpoint.Heros.Hero;
import fr.unice.polytech.startingpoint.Heros.HeroDeck;
import fr.unice.polytech.startingpoint.Heros.IHero;
import fr.unice.polytech.startingpoint.cards.District;

import java.util.*;
public class IA extends Player{

    public IA(String playerName){
        super(playerName);
    }

    @Override
    public void chooseHero(HeroDeck heroes) {
        Random rand = new Random();
        int roleIndex = rand.nextInt(this.HeroList.size());
        this.setRole(roleIndex);
        if(this.role.getName() == Hero.King)
            this.setKing();
        else
            this.unsetKing();
    }

    @Override
    public void activateHero(IHero hero) {

    }

    @Override
    public void doAction() {
        Random rand = new Random();
        int index = rand.nextInt(1);
        this.buildDistrict(index);
    }
}
