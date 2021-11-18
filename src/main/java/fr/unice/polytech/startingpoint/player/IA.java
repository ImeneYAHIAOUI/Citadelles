package fr.unice.polytech.startingpoint.player;
import fr.unice.polytech.startingpoint.heros.IHero;

import java.util.*;
public class IA extends Player{
    /**
     *
     * @param playerName the IA object is constructed the same way as a Player object,
     *                   so we also only need the name of the player here.
     */

    public IA(String playerName){
        super(playerName);
    }

    /**
     * this method chooses the hero for the bot based on the information it's given
     * it's random based for now
     */

    @Override
    public void chooseHero() {
        Random rand = new Random();
        int roleIndex = rand.nextInt(this.HeroList.size());
        this.setRole(roleIndex);
    }

    /**
     *
     * empty for now :)
     */

    @Override
    public void activateHero(IHero hero) {
        switch (hero.getName()){
            case Merchant, King -> hero.doAction(this.builtDistricts,this);
        }
    }

    /**
     * this method chooses what move to make for the bot based on the information it's given
     * it's random based for now
     */

    @Override
    public void doAction() {
        Random rand = new Random();
        int index = rand.nextInt(1);
        this.buildDistrict(index);
    }
}
