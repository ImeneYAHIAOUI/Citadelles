package fr.unice.polytech.startingpoint;


import fr.unice.polytech.startingpoint.player.*;
import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.core.*;
import fr.unice.polytech.startingpoint.output.*;


import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Citadelle {

    /**
     * Main method of the game
     */
    public void game(int numberOfplayers){
        DistrictDeck districtDeck = new DistrictDeck(Initialization.districtList());
        ArrayList<Player> players=new ArrayList<Player>();
        for(int i=1;i<numberOfplayers+1;i++){
            players.add(new Player(districtDeck.giveDistrict(1),"Player"+i));
        }

        for(Player player : players){

            IA iaOfPlayer=new IA(player);
            iaOfPlayer.move();
        }

        GameComparator compare=new GameComparator(players);

        GameResult result = compare.getResult();

        Display display=new Display(result);




    }
} 