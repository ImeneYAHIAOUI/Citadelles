package fr.unice.polytech.startingpoint.player;
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
        int index = rand.nextInt(2);
        player.buildDistrict(index);
    }

}
