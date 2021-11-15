package fr.unice.polytech.startingpoint;


import fr.unice.polytech.startingpoint.Heros.HeroDeck;
import fr.unice.polytech.startingpoint.player.*;
import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.core.*;
import fr.unice.polytech.startingpoint.output.*;


import java.util.ArrayList;


public class Citadelle {

    /**
     * Main method of the game
     */
    public void game(int numberOfplayers){
        DistrictDeck districtDeck = new DistrictDeck(Initialization.districtList());
        ArrayList<Player> players=new ArrayList<Player>();
        HeroDeck heroes = Initialization.heroeList();
        int NumberOfBuiltDistrict=0;
        for(int i=1;i<numberOfplayers+1;i++){
            players.add(new Player(districtDeck.giveDistrict(4),"Player"+i));
        }
        while(NumberOfBuiltDistrict<4){
            heroes = Initialization.heroeList();
            for(Player player : players){
                player.setHeroes(heroes);
                IA iaOfPlayer=new IA(player);
                iaOfPlayer.move();
                NumberOfBuiltDistrict=Math.max(NumberOfBuiltDistrict,player.getBuiltDistricts().size());
            }
        }


        GameComparator compare=new GameComparator(players);

        GameResult result = compare.getResult();

        System.out.println(Display.displayResult(result));
    }
} 