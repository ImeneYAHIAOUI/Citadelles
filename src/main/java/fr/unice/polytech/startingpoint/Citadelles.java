package fr.unice.polytech.startingpoint;


import fr.unice.polytech.startingpoint.heros.*;
import fr.unice.polytech.startingpoint.player.*;
import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.core.*;
import fr.unice.polytech.startingpoint.output.*;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Citadelles {
    private DistrictDeck districtDeck;
    private List<IPlayer> players;
    private List<IPlayer> playersHeroRank;
    private CircularList circularListPlayer;
    private HeroDeck heroes;
    private BonusPoint bonusPoint;
    private Treasure treasure;
    private int round;
    Comparator compare;
    private IAToHero information;
    private Controller controller;
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
        controller=new Controller();
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

        while(!controller.endTheGame()){

            // ========================================================
            //                      Hero choice
            // ========================================================
            controller.setBuiltDistricts();
            for(int i = 0; i < this.circularListPlayer.size(); i++){
                this.circularListPlayer.get(i).chooseHero(heroes,rand,players);
            }
            Display.displayHeroChoice(this.circularListPlayer.getRotatePlayerList(),round);

            this.playersHeroRank = compare.playerComp(players);

            this.playersHeroRank.forEach(player -> {
                information = new IAToHero();
                if(!controller.isAssassinated(player)){

                    // ========================================================
                    //                     Hero action
                    // ========================================================

                    if(controller.isStolenPerson(player)){
                        controller.GiveGoldToTheTief();
                    }
                    controller.changeMagicSchoolColor(player);
                    player.activateHero(players,districtDeck,treasure,information);

                    // ========================================================
                    //              Choose between gold or district
                    // ========================================================

                    player.drawOrGetPieces(districtDeck,treasure,information);

                    // ========================================================
                    //          Build or not build? That is the question.
                    // ========================================================

                    player.doAction(treasure,information);
                    controller.addTOBuiltDistricts(information.getBuiltDistrict());
                    controller.update(players,treasure,districtDeck );
                    Display.displayAction(information);
                }
            });

            Display.round(players);

            // ========================================================
            //                     This is the End ?
            // ========================================================

            controller.resetMagicSchoolColor(players);
            this.circularListPlayer.findPlayerWithCrown();
            heroes = Initialization.heroeList();
            round ++;
        }

        bonusPoint.obtainBonus(players);
        compare.gameComp(players);
        controller.changeMiracleCourtColor(players);

        GameResult result = compare.getResult();
        Display.displayResult(result);
    }
}