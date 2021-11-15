package fr.unice.polytech.startingpoint;


import fr.unice.polytech.startingpoint.Heros.HeroDeck;
import fr.unice.polytech.startingpoint.player.*;
import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.core.*;
import fr.unice.polytech.startingpoint.output.*;


import java.util.ArrayList;


public class Citadelle {
    private DistrictDeck districtDeck;
    private ArrayList<IA> players;
    private HeroDeck heroes;
    private int round;

    /**
     * Main method of the game
     */
    public void game(int numberOfplayers){
        //Initialization
        districtDeck = new DistrictDeck(Initialization.districtList());
        players = new ArrayList<IA>();
        heroes = Initialization.heroeList();
        round = 0;
        int NumberOfBuiltDistrict=0;

        for(int i=1;i<numberOfplayers+1;i++){
            players.add(new IA(new Player("Player"+i)));
        }

        //Rounds
        while(NumberOfBuiltDistrict < 4){
            players.forEach(player -> {
                player.getDistrict(districtDeck.giveDistrict(1));
                player.
            });

            //heroes = Initialization.heroeList();
            for(Player player : players){

                //player.setHeroes(heroes);
                IA iaOfPlayer=new IA(player);
                iaOfPlayer.move();

                NumberOfBuiltDistrict=Math.max(NumberOfBuiltDistrict,player.getBuiltDistricts().size());

            }

            Display.displayRound(players,round);
            round ++;
        }


        GameComparator compare=new GameComparator(players);

        GameResult result = compare.getResult();

        System.out.println(Display.displayResult(result));
    }
} 