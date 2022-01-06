package fr.unice.polytech.startingpoint;

import fr.unice.polytech.startingpoint.heros.*;
import fr.unice.polytech.startingpoint.player.*;
import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.core.*;
import fr.unice.polytech.startingpoint.output.*;
import fr.unice.polytech.startingpoint.player.IA.*;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Citadelles {
    private DistrictDeck districtDeck;
    private List<IPlayer> players;
    private List<IPlayer> playersHeroRank;
    private HeroDeck heroes;
    private Treasure treasure;
    Comparator compare;
    private IAToHero information;
    private IAToWonder wonderInformation;
    private Controller controller;
    private Level level;
    IHero hiddenCard;
    List<IHero> visibleFront;
    int numberOfPlayers;

    public Citadelles(Level level) {
        this.level = level;
    }

    /**
     * Main method of the game
     */
    public List<IPlayer> game(int SimulationOrNot){
        // ========================================================
        //                      Initialization
        // ========================================================


        compare = new Comparator();
        districtDeck = new DistrictDeck(Initialization.districtList());
        players = new ArrayList<>();
        controller=new Controller();
        treasure = new Treasure(Initialization.treasureOfTheGame());
        BonusPoint bonusPoint = new BonusPoint();
        Display.initLogger(this.level);

        int round = 1;
        Random rand = new Random();

        Display.hello();

        if(SimulationOrNot==0) {

            // ========================================================
            //                  Players creation
            // ========================================================
            players = Initialization.numberOfPlayers();
        }else if(SimulationOrNot==1){

            players = Initialization.playersSimulation1();



        }else{
            players=Initialization.playersSimulation2();
        }
        List<IPlayer> players1=List.copyOf(players);

        heroes = Initialization.heroeList(players.size());




        // ========================================================
        //                Distribution of districts
        // ========================================================

        players.forEach(player -> {
            player.getDistrict(districtDeck.giveDistrict(4));
            player.addGold(  treasure.removeGold(2));
        });

        // ========================================================
        //              Random AI who takes the crown
        // ========================================================

        IPlayer playerWithCrown= players.get(rand.nextInt(players.size()));
        playerWithCrown.setCrown();

        // ========================================================
        //              Circular list for the choice of heroes
        // ========================================================

        CircularList circularListPlayer = new CircularList(players);
        List<IPlayer> circularList = circularListPlayer.getRotatePlayerList();

        // ========================================================
        //                   Citadelles loop
        // ========================================================

        while(!controller.endTheGame(players.size())){

            // ========================================================
            //                      Hero choice
            // ========================================================
            controller.setBuiltDistricts();
            HeroDeckManagement();
            Display.displayVisibleHeroes(visibleFront);
            Display.displayHiddenHero(hiddenCard);
            Display.smallBar("HERO CHOICE");
            for(int i = 0; i < circularListPlayer.size(); i++){
                if(i==6) heroes.add(hiddenCard);
                circularListPlayer.get(i).chooseHero(heroes, rand, circularList);
            }
            Display.displayHeroChoice(circularListPlayer.getRotatePlayerList(), round);
            Display.smallBar("HERO ACTION");

            this. playersHeroRank= compare.playerComp(players);

            this.playersHeroRank.forEach(player -> {
                information = new IAToHero();
                wonderInformation = new IAToWonder();
                information.setVisibleHeroes(visibleFront);
                if(!controller.isAssassinated(player)){

                    // ========================================================
                    //                     Hero action
                    // ========================================================

                    if(controller.isStolenPerson(player)){
                        controller.GiveGoldToTheThief();
                    }
                    controller.changeMagicSchoolColor(player,wonderInformation);
                    player.activateHero(players,districtDeck,treasure,information);

                    // ========================================================
                    //              Choose between gold or district
                    // ========================================================

                    player.drawOrGetPieces(districtDeck,treasure,information,wonderInformation);

                    // ========================================================
                    //          Build or not build? That is the question.
                    // ========================================================

                    player.doAction(treasure,information);
                    controller.addTOBuiltDistricts(information.getBuiltDistrict());
                    controller.update(players,treasure,districtDeck,wonderInformation);
                   Display.displayAction(information);
                    Display.displayNoDecisionNeededWonders(player);
                    Display.displayDecisionNeededWonders(wonderInformation);


                }
            });
            Display.smallBar("RESULT");

            Display.round(playersHeroRank);
            //Display.smallBar();

            // ========================================================
            //                     This is the End ?
            // ========================================================

            controller.resetMagicSchoolColor(playersHeroRank);
            circularListPlayer.findPlayerWithCrown();
            heroes = Initialization.heroeList(players.size());
            round++;
        }
        bonusPoint.obtainBonus(playersHeroRank);
        Display.displayBonusPoints(playersHeroRank);
        wonderInformation = new IAToWonder();
        controller.changeMiracleCourtColor(playersHeroRank,wonderInformation);
        controller.changeWonderValue(playersHeroRank,wonderInformation);
        compare.gameComp(playersHeroRank);

        GameResult result = compare.getResult();
        Display.displayDecisionNeededWonders(wonderInformation);
        Display.displayValueIncreaseWonders(playersHeroRank);
        Display.displayResult(result);
        return players1;

    }

    int NumberOfVisibleHeroes(){
        if (numberOfPlayers == 4)  return 2;
        else if (numberOfPlayers == 5) return  1;
        else return 0;
    }
    public List<IPlayer> getPlayers(){
        return players;
    }
    void HeroDeckManagement(){
        Collections.shuffle(heroes);
        hiddenCard = heroes.get(0);
        visibleFront = new HeroDeck();
        heroes.remove(hiddenCard);
        int numberOfVisibleHeroes = NumberOfVisibleHeroes();
        while (numberOfVisibleHeroes>0){
            int i = 1;
            IHero visibleHero = heroes.get(0);
            while(visibleHero.getName() == HeroName.King){
                visibleHero = heroes.get(i);
                i++;
            }
            visibleFront.add(visibleHero);
            heroes.remove(visibleHero);
            numberOfVisibleHeroes--;
        }
    }

}