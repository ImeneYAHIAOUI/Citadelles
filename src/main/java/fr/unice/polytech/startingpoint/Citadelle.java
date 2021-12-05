package fr.unice.polytech.startingpoint;


import fr.unice.polytech.startingpoint.heros.*;
import fr.unice.polytech.startingpoint.player.*;
import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.core.*;
import fr.unice.polytech.startingpoint.output.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Citadelle {
    private DistrictDeck districtDeck;
    private List<IPlayer> players;
    private List<IPlayer> playersHeroRank;
    private CircularList circularListPlayer;
    private HeroDeck heroes;
    private BonusPoint bonusPoint;
    private Treasure treasure;
    private int round;
    Comparator compare;
    private  Information information;
    /**
     * Main method of the game
     */
    public void game(int numberOfplayers){
        // ========================================================
        //                      Initialization
        // ========================================================

        compare = new Comparator();
        districtDeck = new DistrictDeck(Initialization.districtList());
        players = new ArrayList<IPlayer>();
        heroes = Initialization.heroeList();
        treasure = new Treasure(Initialization.treasureOfTheGame());
        bonusPoint = new BonusPoint();
        round = 1;
        int giveGold=0;
        int NumberOfBuiltDistrict=0;
        Random rand = new Random();
        List<IPlayer> playerListSortRang = null;

        // ========================================================
        //                  Players creation
        // ========================================================

        for(int i=1;i<numberOfplayers+1;i++){
            players.add(new IA("Player"+i));
            if(i%2 == 1){
                players.get(i-1).setBot("bot1");
            }else players.get(i-1).setBot("bot2");
        }

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

        IPlayer playerWithCrown= players.get(rand.nextInt(numberOfplayers));
        playerWithCrown.setCrown();

        // ========================================================
        //              Circular list for the choice of heroes
        // ========================================================

        this.circularListPlayer = new CircularList(players);

        // ========================================================
        //                   Citadelles loop
        // ========================================================

        while(NumberOfBuiltDistrict < 8){

            // ========================================================
            //                      Hero choice
            // ========================================================
            for(int i = 0; i < this.circularListPlayer.size(); i++){
                this.circularListPlayer.get(i).chooseHero(heroes,rand.nextInt(heroes.size()));
            }
            Display.displayHeroChoice(this.circularListPlayer.getRotatePlayerList(),round);

            this.playersHeroRank = compare.playerComp(players);
            this.playersHeroRank.forEach(player -> {
                information = new Information();
                if(!player.getIsAssigned()){

                    // ========================================================
                    //                     Hero action
                    // ========================================================

                    if(player.getStolenPerson()){ //le tour du personnage volé
                        int gold=player.getGold();
                        IPlayer thief=player.getStolenBy();
                        thief.addGold(gold);
                        player.removeGold(gold);
                        player.unSetStolenPerson();
                    }
                    player.activateHero(players,districtDeck,treasure,information);

                    // ========================================================
                    //              Choose between gold or district
                    // ========================================================

                    player.drawOrGetPieces(districtDeck,treasure,information);

                    // ========================================================
                    //          Build or not build? That is the question.
                    // ========================================================

                    player.doAction(treasure,information);
                    Display.displayAction(information);
                }
            });

            Display.round(players);

            NumberOfBuiltDistrict = this.maxDistrictObtained();
            this.circularListPlayer.findPlayerWithCrown();
            heroes = Initialization.heroeList();
            players.forEach(player -> {
                if(player.getIsAssigned()){
                    player.unsetIsAssigned();
                }
            });
            round ++;
        }
        compare.gameComp(players);
        bonusPoint.obtainBonus(players);

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