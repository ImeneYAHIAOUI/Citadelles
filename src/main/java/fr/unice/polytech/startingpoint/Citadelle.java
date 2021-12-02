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

    /**
     * Main method of the game
     */
    public void game(int numberOfplayers){
        //Initialization
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

        for(int i=1;i<numberOfplayers+1;i++){
            players.add(new IA("Player"+i));
        }

        players.forEach(player -> {
            player.getDistrict(districtDeck.giveDistrict(4));
            player.addGold(  treasure.removeGold(2));
        });

        IPlayer playerWithCrown= players.get(rand.nextInt(numberOfplayers));
        playerWithCrown.setCrown();

        this.circularListPlayer = new CircularList(players);

        //Rounds
        while(NumberOfBuiltDistrict < 8){
            // Choose hero
            for(int i = 0; i < this.circularListPlayer.size(); i++){
                this.circularListPlayer.get(i).chooseHero(heroes,rand.nextInt(heroes.size()));
            }

            compare.playerComp(players);
            players.forEach(player -> {
                if(!player.getIsAssigned()){
                    // Hero action
                    if(player.getStolenPerson()){ //le tour du personnage volé
                        int gold=player.getGold();
                        IPlayer thief=player.getStolenBy();
                        thief.addGold(gold);
                        player.removeGold(gold);
                    }
                    player.activateHero(players,districtDeck,treasure);
                    // Choose between gold or district
                    player.drawOrGetPieces(districtDeck,treasure);
                    // Build or not build? This is the question.
                    player.doAction(treasure);
                }
            });

            Display.round(players,round);

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