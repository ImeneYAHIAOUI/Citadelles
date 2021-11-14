package fr.unice.polytech.startingpoint.player;
import fr.unice.polytech.startingpoint.Heros.HeroDeck;
import fr.unice.polytech.startingpoint.Heros.IHero;

import java.util.*;
public class IA {
    private Player player;

    public IA(Player player){
        this.player = player;
        chooseRole();
    }
    public Player getPlayer() {
        return player;
    }


    public void move(){
        Random rand = new Random();
        int index = rand.nextInt(1);
        player.buildDistrict(index);
    }

    public void chooseRole(){
        Random rand = new Random();
        HeroDeck heroes = player.getHeroes();
        int roleIndex = rand.nextInt(heroes.size());
        player.setRole(roleIndex);
        heroes.remove(player.getRole());
        player.getRole().doAction(player);

    }

}
