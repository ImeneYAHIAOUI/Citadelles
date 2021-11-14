package fr.unice.polytech.startingpoint.player;
import fr.unice.polytech.startingpoint.Heros.IHero;

import java.util.*;
public class IA {
    private Player player;

    public IA(Player player){
        this.player = player;
    }
    public Player getPlayer() {
        return player;
    }


    public void move(){
        Random rand = new Random();
        int size = player.getHand().size();

        int roleIndex = rand.nextInt(2);

        player.setRole(roleIndex);

        player.getRole().doAction(player);

        int index = rand.nextInt(1);
        player.buildDistrict(index);
    }

}
