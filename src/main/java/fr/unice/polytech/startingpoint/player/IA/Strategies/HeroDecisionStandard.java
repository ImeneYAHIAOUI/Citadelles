package fr.unice.polytech.startingpoint.player.IA.Strategies;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.player.IA.HerosChoice;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HeroDecisionStandard {

    //==========================================================================================================
    //                                             LEVEL 1
    //==========================================================================================================

    /**
     * Hero chosen by the AI
     * @param ia
     * @param players
     * @param heroes
     * @param thoughtPath
     * @return
     */
    public IHero heroDecision(IA ia, List<IPlayer> players, HeroDeck heroes, List<HerosChoice> thoughtPath, Random rand){
        // Enum to know the AI thought path
        thoughtPath.add(HerosChoice.IChooseAHero);

        // Get a list of players without the AI doing the action
        List<IPlayer> playerList = listModification(ia,players);

        // Calculate my score plus the highest score of other players
        double myProScore =  probaScore(ia);
        double enemyWithThHighestScore = highestEnemyScore(players);

        // If no hero for the attack, then we put 0 in the probability of doing such an action
        if(!isHeroForAttackPresent(heroes))
            enemyWithThHighestScore = 0;

        // Random choice based on probability
        double total = myProScore + enemyWithThHighestScore;

        float myProba = 0;
        float enemyProba = 0;
        float choise = 0;

        if(total > 0) {
            myProba = (float) (myProScore / total);
            enemyProba = (float) (enemyWithThHighestScore / total);
            choise = rand.nextFloat() * (1 - 0);
        }else{
            myProba = 1;
            total = 1;
        }

        // If I have only duplicates in my hand, I take magician
        List<IDistrict> haveOnlyDuplicates = IA.searchForDoubles(ia.getBuiltDistricts(),ia.getHand());
        if(haveOnlyDuplicates.size() == ia.getHand().size() && heroPresentInTheList(heroes,HeroName.Magician) && ia.getHand().size() > 0){
            thoughtPath.add(HerosChoice.IOnlyHaveDuplicates);
            thoughtPath.add(HerosChoice.SoIChooseTheMagician);
            return heroes.chooseHero(HeroName.Magician); // END
        }

        // The choice according to the probabilities
        if(choise <= myProba)
            return defense(playerList,ia, thoughtPath,heroes,rand); // LEVEL 2
        return attack(thoughtPath,heroes,rand,playerList); // LEVEL 2
    }

    //==========================================================================================================
    //                                             LEVEL 2
    //==========================================================================================================

    /**
     * Find a hero to harm a player
     * @param thoughtPath
     * @param heroes
     * @return
     */
    private IHero attack(List<HerosChoice> thoughtPath, HeroDeck heroes, Random rand, List<IPlayer> players){
        thoughtPath.add(HerosChoice.IDecidetoAttack);

        double goldWin = 0;
        double numberOfDistrict = 0;
        double gold = 0;
        IPlayer ennemy = null;
        double enemyWithThHighestScore = 0;
        int blue = 0;
        int green = 0;
        int yellow = 0;
        double max = 0;
        IHero hero = null;

        // I find the player with the highest score

        ennemy = this.ennemyWithHighestScore(players);

        // Account of its resources to make proba

        if(heroPresentInTheList(heroes,HeroName.Assassin)) {
            numberOfDistrict = (double) ennemy.getBuiltDistricts().size() / (double) 8;
        }if(heroPresentInTheList(heroes,HeroName.Thief))
            gold = (double)ennemy.getGold() / (double) 6;

        // Find the greatest value the enemy can earn with a hero

        if(heroPresentInTheList(heroes,HeroName.King))
            yellow = districtColorCount(ennemy,Color.YELLOW);
        if(heroPresentInTheList(heroes,HeroName.Merchant))
            green = districtColorCount(ennemy,Color.GREEN);
        if(heroPresentInTheList(heroes,HeroName.Bishop))
            blue = districtColorCount(ennemy,Color.BLUE);

        if(heroPresentInTheList(heroes,HeroName.Assassin))
            max = Math.max(Math.max(yellow,green),blue);

        //Proba

        double total = max + gold + numberOfDistrict;
        double assassinChoice = 0;
        double thiefChoice = 0;
        if(total > 0) {
            assassinChoice = (numberOfDistrict + max) / total;
            thiefChoice = (gold) / total;
        }else{
            if(heroPresentInTheList(heroes,HeroName.Assassin)) {
                thoughtPath.add(HerosChoice.SoIChooseTheAssassin);
                hero = heroes.chooseHero(HeroName.Assassin);
            }
            if(heroPresentInTheList(heroes,HeroName.Thief)){
                thoughtPath.add(HerosChoice.SoIchooseTheThief);
                hero = heroes.chooseHero(HeroName.Thief);
            }
        }

        // Choice

        float choice = rand.nextFloat() * ( 1 - 0 );

        if(choice < assassinChoice){
            thoughtPath.add(HerosChoice.SoIChooseTheAssassin);
            hero = heroes.chooseHero(HeroName.Assassin);
        }
        else if( choice < assassinChoice+thiefChoice){
            thoughtPath.add(HerosChoice.SoIchooseTheThief);
            hero = heroes.chooseHero(HeroName.Thief);
        }


        return hero;
    }

    /**
     * Find a hero to build his defense or advance in the construction of his city
     * @param ia
     * @param thoughtPath
     * @param heroes
     * @return IHero
     */
    private IHero defense(List<IPlayer>players,IA ia, List<HerosChoice> thoughtPath,HeroDeck heroes, Random rand){
        float needGold = 0; // Merchent, King
        float exchangeDistrict = 0; // Magicien
        //float buildTwoDistrict = 0; // Architect
        IHero hero = null;

        // Score for merchent's, king's choice
        needGold = this.valueOfTheMostExpensive(ia);

        // Score for magician's choice
        if(!heroPresentInTheList(heroes, HeroName.Magician))
            exchangeDistrict = 0;
        else
            exchangeDistrict = this.differenceBetweenTheCheapestCardAndMyGold(ia);

        // Random choice based on probability
        float total = exchangeDistrict + needGold;
        if(total > 0) {
            needGold = needGold / total;
            exchangeDistrict = exchangeDistrict / total;
        }else{
            total = 1;
            needGold = 1;
        }
        float choise = rand.nextFloat() * ( 1 - 0 );

        // Check if there are heroes left for the needGold choice
        if(!isHeroForNeedGoldPresent(heroes)){
            needGold = 0;
            exchangeDistrict = 1;
        }

        // The choice according to the probabilities
        if(choise <= needGold) {
            hero =  needGold(players,ia,thoughtPath,heroes);  // LEVEL 3
        }else if(choise <= needGold + exchangeDistrict){
            if(heroPresentInTheList(heroes,HeroName.Magician)) {
                thoughtPath.add(HerosChoice.IWantToChangeTheDistricts);
                thoughtPath.add(HerosChoice.SoIChooseTheMagician);
                hero = heroes.chooseHero(HeroName.Magician); // END
            }else{
                thoughtPath.add(HerosChoice.SoIChooseAHeroAtRandom);
                hero =heroes.randomChoice();
            }
        }
        return hero;
    }

    //==========================================================================================================
    //                                             LEVEL 3
    //==========================================================================================================

    /**
     * Choose a hero to get gold
     * @param ia
     * @param thoughtPath
     * @param heroes
     * @return
     */
    private IHero needGold(List<IPlayer>players,IA ia, List<HerosChoice> thoughtPath, HeroDeck heroes){
        thoughtPath.add(HerosChoice.INeedGold);

        int yellow = 0;
        int green = 0;
        int blue = 0;
        int max = 0;
        int stolenGold = 0;
        IHero hero = null;
        IDistrict district = null;

        //Count the number of color cards. Choose the hero who earns the most coins

        if(heroPresentInTheList(heroes,HeroName.King))
            yellow = districtColorCount(ia,Color.YELLOW);
        if(heroPresentInTheList(heroes,HeroName.Merchant))
            green = districtColorCount(ia,Color.GREEN);
        if(heroPresentInTheList(heroes,HeroName.Bishop))
            blue = districtColorCount(ia,Color.BLUE);

        // Count the number of gold that I can potentially steal from a player

        if(heroPresentInTheList(heroes,HeroName.Thief))
            stolenGold = potentiallyStolenGold(players);

        // Find the most interesting amount

        max = Math.max(Math.max(yellow,green),Math.max(blue,stolenGold));
        if(max == 0) {
            thoughtPath.add(HerosChoice.SoIChooseAHeroAtRandom);
            return heroes.randomChoice();
        }

        // Returns the corresponding hero

        if(yellow == max) {
            thoughtPath.add(HerosChoice.SoIChooseTheKing);
            hero = heroes.chooseHero(HeroName.King); // END
        }else if(green == max){
            thoughtPath.add(HerosChoice.SoIChooseTheMerchant);
            hero = heroes.chooseHero(HeroName.Merchant); // END
        }else if(blue == max){
            thoughtPath.add(HerosChoice.SoIchooseTheBishop);
            hero = heroes.chooseHero(HeroName.Bishop); // END
        }else if(stolenGold == max){
            thoughtPath.add(HerosChoice.SoIchooseTheThief);
            hero = heroes.chooseHero(HeroName.Thief); // END
        }

        return hero;
    }

    //==========================================================================================================
    //                                             FUNCTIONS
    //==========================================================================================================

    /**
     * Return the highest score among all enemy players
     * @param players
     * @return
     */
    private double highestEnemyScore(List<IPlayer> players){
        double enemyWithThHighestScore = 0;
        double val = 0;
        for(int i = 0; i < players.size() ;i++){
            val = probaScore(players.get(i));
            if(enemyWithThHighestScore < val){
                enemyWithThHighestScore = val;
            }
        }
        return enemyWithThHighestScore;
    }

    /**
     * Copy the list of players and delete the player who is doing the action
     * @param ia
     * @param playerList
     * @return
     */
    private List<IPlayer> listModification(IA ia, List<IPlayer> playerList){
        List<IPlayer> list = new ArrayList<IPlayer>(playerList);
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getName().equals(ia.getName())){
                list.remove(i);
                break;
            }
        }
        return list;
    }

    /**
     * Difference between the cheapest card and my gold
     * @param ia
     * @return int
     */
    private int differenceBetweenTheCheapestCardAndMyGold(IA ia){
        int val = 0;

        if( ia.getHand().size() > 0) {
            val = ia.getHand().get(0).getPrice();
            for (int i = 0; i < ia.getHand().size(); i++) {
                if (ia.getHand().get(i).getPrice() < val)
                    val = ia.getHand().get(i).getPrice();
            }
            if (val - ia.getGold() < 0)
                val = 0;
        }

        return val;
    }

    /**
     * Return the value of the most expensive card
     * @param ia
     * @return
     */
    private int valueOfTheMostExpensive(IA ia){
        int needGold = 0;

        for(int i = 0; i < ia.getHand().size(); i++) {
            if (ia.getHand().get(i).getPrice() > needGold && ia.getHand().get(i).getPrice() <= ia.getGold()) {
                needGold = ia.getHand().get(i).getPrice();
            }
        }

        return needGold;
    }

    /**
     * Calculate the score to make probabilities.
     * score * 10 ^ 2 + number of cards * 10
     * @param players
     * @return
     */
    private double probaScore(IPlayer players){
        return players.getScore()*100 + players.getBuiltDistricts().size()*10;
    }

    /**
     * Check that a hero is present in the deck
     * @param heroes
     * @param heroName
     * @return
     */
    private boolean heroPresentInTheList(HeroDeck heroes, HeroName heroName){
        return heroes.stream().map(hero -> hero.getName()).anyMatch(name -> name == heroName);
    }

    /**
     * Check if there are heroes left for the needGold choice
     * @param heroes
     * @return
     */
    private boolean isHeroForNeedGoldPresent(HeroDeck heroes){
        if(heroPresentInTheList(heroes,HeroName.King)) return true;
        if(heroPresentInTheList(heroes,HeroName.Merchant)) return true;
        if(heroPresentInTheList(heroes,HeroName.Bishop)) return true;
        if(heroPresentInTheList(heroes,HeroName.Thief)) return true;
        return false;
    }

    /**
     * If heroes available for attack
     * @param heroes
     * @return
     */
    private boolean isHeroForAttackPresent(HeroDeck heroes){
        if(heroPresentInTheList(heroes,HeroName.Thief)) return true;
        if(heroPresentInTheList(heroes,HeroName.Assassin)) return true;
        // conditiere
        return false;
    }

    /**
     * Find the player with the highest score
     * @param players
     * @return
     */
    private IPlayer ennemyWithHighestScore(List<IPlayer> players){
        IPlayer player = players.get(0);
        double proba = this.probaScore(player);

        for(int i = 1; i < players.size(); i++){
            if(proba < this.probaScore(players.get(i))){
                player = players.get(i);
                proba = this.probaScore(players.get(i));
            }
        }
        return player;
    }
    /**
     * Stolen gold
     * @param players
     * @return
     */
    private int potentiallyStolenGold(List<IPlayer> players){
        int max = 0;

        for(int i = 0; i < players.size(); i++){
            if(max < players.get(i).getGold())
                max = players.get(i).getGold();
        }

        return max;
    }

    /**
     * Do the designated color district count
     * @param player
     * @param color
     * @return
     */
    private int districtColorCount(IPlayer player, Color color){
        int cpt = 0;
        IDistrict district = null;

        for(int i = 0; i < player.getBuiltDistricts().size(); i++){
            district = player.getBuiltDistricts().get(i);

            if(district.getColor() == color){
                cpt ++ ;
            }
        }

        return cpt;
    }
}