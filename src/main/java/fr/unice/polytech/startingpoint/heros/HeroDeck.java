package fr.unice.polytech.startingpoint.heros;

import java.util.ArrayList;
import java.util.Random;

public class HeroDeck extends ArrayList<IHero> {
    public HeroDeck() {
        super();
    }

    /**
     * Return the chosen hero
     * @param name
     * @return
     */
    public IHero chooseHero(HeroName name){
        IHero hero = null;
        for(int i = 0; i < this.size(); i++){
            if(this.get(i).getName() == name){
                hero = this.get(i);
                remove(i);
                break;
            }
        }
        return hero;
    }

    /**
     * Randomly choose a hero
     * @return
     */
    public IHero randomChoice(){
        if(this.size() <= 0) return null;
        Random r = new Random();
        int val = r.nextInt(this.size() - 0);
        return chooseHero(this.get(val).getName());
    }
}