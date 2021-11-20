package fr.unice.polytech.startingpoint;


import fr.unice.polytech.startingpoint.heros.*;
import fr.unice.polytech.startingpoint.player.*;
import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.core.*;
import fr.unice.polytech.startingpoint.output.*;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Citadelle {
    private DistrictDeck districtDeck;
    private ArrayList<IPlayer> players;
    private HeroDeck heroes;
    private int round;
    Comparator compare;

    /**
     * Main method of the game
     */
    public void game(int numberOfplayers){
        //Initialization
        compare = new Comparator();
        districtDeck = new DistrictDeck(Initialization.districtList());
        players = new ArrayList<IPlayer>();
        heroes = Initialization.heroeList();
        round = 1;
        int NumberOfBuiltDistrict=0;

        for(int i=1;i<numberOfplayers+1;i++){
            players.add(new IA("Player"+i));
        }

        players.forEach(player -> {
            player.getDistrict(districtDeck.giveDistrict(4));
            player.setDeck(districtDeck);
        });

         Random rand = new Random();
        IPlayer playerWithCrown= players.get(rand.nextInt(numberOfplayers));
        playerWithCrown.setCrown();

        //Rounds
        while(NumberOfBuiltDistrict < 8){
            // Choose hero
            Collections.sort(players,new PlayerCrownComparator());
            Collections.reverse(players);

            playerWithCrown.unSetCrown();

            for(IPlayer player: players){
                player.HaveTheListOfHeroes(heroes);
                player.chooseHero();
                IHero hero =player.getRole();
                if(hero.getName()==HeroName.King ){
                    player.setCrown();
                    playerWithCrown=player;
                }
                heroes.remove(hero);
            }

            NumberOfBuiltDistrict = this.maxDistrictObtained();

            players.forEach(player -> {
                // Choose between Districts or Gold
                player.doAction();

            });



            // Hero passif
            //orderTheListOfPlayersAccordingToTheirCharacterCard
            compare.playerComp(players);


            //players.forEach(player -> player.activateHero());

            Display.round(players,round);
            heroes = Initialization.heroeList();
            round ++;
        }

        compare.gameComp(players);
        GameResult result = compare.getResult();

        Display.displayResult(result);
    }

    /**
     * Returns the maximum number of district among all players
     * @return int
     */
    public int maxDistrictObtained(){
        int max = players.stream().mapToLong(player -> player.getBuiltDistricts().stream().count()).mapToInt(player -> (int) player).filter(player -> player >= 0).max().orElse(0);

        return max;
    }


} 