package fr.unice.polytech.startingpoint.player.IA.Strategies;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.player.IA.HerosChoice;
import fr.unice.polytech.startingpoint.player.IA.NiceNastyBot;
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
    public IHero heroDecision(IPlayer ia, List<IPlayer> players, HeroDeck heroes, List<HerosChoice> thoughtPath, Random rand){
        // Enum to know the AI thought path
        thoughtPath.add(HerosChoice.IChooseAHero);

        // Get a list of players without the AI doing the action
        List<IPlayer> playerList = listModification(ia,players);

        // Calculate my score plus the highest score of other players
        double myProScore =  probaScore(ia);
        double enemyWithThHighestScore = highestEnemyScore(players);

        // Nice or bad strategy bot
        float coeff = 0.5F;

        if(ia.niceNastyStrategy == NiceNastyBot.NICE_BOT){
            enemyWithThHighestScore *= coeff;
        }else if(ia.niceNastyStrategy == NiceNastyBot.NASTY_BOT){
            myProScore *= coeff;
        }

        // If no hero for the attack, then we put 0 in the probability of doing such an action
        if(!isHeroForAttackPresent(heroes)) {
            thoughtPath.add(HerosChoice.ThereAreNoMoreHeroesAttacking);
            enemyWithThHighestScore = 0;
        }

        if(!isHeroForDefencePresent(heroes)) {
            thoughtPath.add(HerosChoice.ThereAreNoMoreHeroesDefence);
            myProScore = 0;
        }

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
        // Si j'ai 4 / 6 double par exemple, je change?
        List<IDistrict> haveOnlyDuplicates = IA.searchForDoubles(ia.getBuiltDistricts(),ia.getHand());
        if(haveOnlyDuplicates.size() == ia.getHand().size() && heroPresentInTheList(heroes,HeroName.Magician) && ia.getHand().size() > 0){
            thoughtPath.add(HerosChoice.IOnlyHaveDuplicates);
            thoughtPath.add(HerosChoice.SoIChooseTheMagician);
            return heroes.chooseHero(HeroName.Magician); // END
        }

        // The choice according to the probabilities
        if(choise <= myProba)
            return defense(players,ia, thoughtPath,heroes, rand); // LEVEL 2
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
        thoughtPath.add(HerosChoice.IDecideToAttack);
        IPlayer ennemy = null;
        IHero hero = null;
        int heroRandom = 0;
        boolean findHeroRandom = false;

        // I find the player with the highest score
        ennemy = this.ennemyWithHighestScore(players);
        int numberOfBuiltDistrict=ennemy.getBuiltDistricts().size();

        // Account of its resources to make proba
        if(ennemy.getGold() > 3 && heroPresentInTheList(heroes, HeroName.Thief)){
            thoughtPath.add(HerosChoice.SoIchooseTheThief);
            hero = heroes.chooseHero(HeroName.Thief); // END

        }else if(numberOfBuiltDistrict<6 && heroPresentInTheList(heroes,HeroName.Assassin)){
            thoughtPath.add(HerosChoice.SoIChooseTheAssassin);
            hero = heroes.chooseHero(HeroName.Assassin);

        }else if(numberOfBuiltDistrict>=6 && heroPresentInTheList(heroes,HeroName.Condottiere)){
            thoughtPath.add(HerosChoice.SoIchooseTheCondottiere);
            hero = heroes.chooseHero(HeroName.Condottiere);

        }else{
            thoughtPath.add(HerosChoice.SoIChooseAHeroAtRandom);
            while (!findHeroRandom) {
                heroRandom = rand.nextInt(3);

                if (heroRandom == 0 && heroPresentInTheList(heroes, HeroName.Thief)) {
                    thoughtPath.add(HerosChoice.SoIchooseTheThief);
                    hero = heroes.chooseHero(HeroName.Thief); // END
                    findHeroRandom = true;
                }

                else if (heroRandom == 1 && heroPresentInTheList(heroes, HeroName.Assassin)) {
                    thoughtPath.add(HerosChoice.SoIChooseTheAssassin);
                    hero = heroes.chooseHero(HeroName.Assassin); // END
                    findHeroRandom = true;
                }

                else if (heroRandom == 2 && heroPresentInTheList(heroes, HeroName.Condottiere)) {
                    thoughtPath.add(HerosChoice.SoIchooseTheCondottiere);
                    hero = heroes.chooseHero(HeroName.Condottiere); // END
                    findHeroRandom = true;
                }



            }
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
    private IHero defense(List<IPlayer>players,IPlayer ia, List<HerosChoice> thoughtPath,HeroDeck heroes, Random rand){
        thoughtPath.add(HerosChoice.IDecideToDefend);
        IHero hero = null;
        int heroRandom = 0;
        boolean findHeroRandom = false;

        int limit =differenceBetweenTheCheapestCardAndMyGold(ia);

        if(heroPresentInTheList(heroes,HeroName.Magician) && limit > 2 && ia.getHand().size() > 0){
            thoughtPath.add(HerosChoice.IWantToChangeTheDistricts);
            thoughtPath.add(HerosChoice.SoIChooseTheMagician);
            hero =heroes.chooseHero(HeroName.Magician); // END

        }else if(architectCanBuy2OrMoreCards(ia) && heroPresentInTheList(heroes,HeroName.Architect)){
            thoughtPath.add(HerosChoice.ICanBuildSeveralDistrict);
            thoughtPath.add(HerosChoice.SoIChooseTheArchitect);
            hero = heroes.chooseHero(HeroName.Architect);
        }else if(isHeroForNeedGoldPresent(heroes)){
            hero = needGold(players,ia, thoughtPath, heroes);

        }else{
            rand = new Random();
            thoughtPath.add(HerosChoice.SoIChooseAHeroAtRandom);
            while (!findHeroRandom) {
                heroRandom = rand.nextInt(2);
                if (heroRandom == 0 && heroPresentInTheList(heroes, HeroName.Magician)) {
                    thoughtPath.add(HerosChoice.SoIChooseTheMagician);
                    hero = heroes.chooseHero(HeroName.Magician); // END
                    findHeroRandom = true;
                }

                else if (heroRandom == 1 && heroPresentInTheList(heroes, HeroName.Architect)) {
                    thoughtPath.add(HerosChoice.SoIChooseTheArchitect);
                    hero = heroes.chooseHero(HeroName.Architect); // END
                    findHeroRandom = true;
                }
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
    private IHero needGold(List<IPlayer>players,IPlayer ia, List<HerosChoice> thoughtPath, HeroDeck heroes){
        thoughtPath.add(HerosChoice.INeedGold);

        int yellow = 0;
        int green = 0;
        int blue = 0;
        int red=0;
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
        if(heroPresentInTheList(heroes,HeroName.Condottiere))
            red = districtColorCount(ia,Color.RED);

        // Count the number of gold that I can potentially steal from a player

        if(heroPresentInTheList(heroes,HeroName.Thief))
            stolenGold = potentiallyStolenGold(players);

        // Find the most interesting amount

        max = Math.max(Math.max(yellow,green),Math.max(Math.max(blue,stolenGold),red));
        if(max == 0) {
            thoughtPath.add(HerosChoice.SoIChooseAHeroAtRandom); // END
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

        }else if(red == max) {
            thoughtPath.add(HerosChoice.SoIchooseTheCondottiere);
            hero = heroes.chooseHero(HeroName.Condottiere); // END

        }

        return hero;
    }

    //==========================================================================================================
    //                                             FUNCTIONS
    //==========================================================================================================

    /**
     *
     * @param player
     * @return
     */
    private boolean architectCanBuy2OrMoreCards(IPlayer player){
        boolean response = false;

        ArchitectChoice architectChoice = new ArchitectChoice();
        List<IDistrict> testList = architectChoice.choiceDistrictsAtBuild(player);

        if(testList.size() >= 2) response = true;

        return response;
    }

    /**
     * Counter of Point
     * @param player
     * @return
     */
    private float countDistrictMediumPoint(IPlayer player){
        float val = 0;

        for(int i = 0; i < player.getBuiltDistricts().size(); i++){
            val += player.getBuiltDistricts().get(i).getPrice();
        }

        if(player.getBuiltDistricts().size() < 0)
            val /= player.getBuiltDistricts().size();

        return val;
    }

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
    private List<IPlayer> listModification(IPlayer ia, List<IPlayer> playerList){
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
    private int differenceBetweenTheCheapestCardAndMyGold(IPlayer ia){
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
    private int valueOfTheMostExpensive(IPlayer ia){
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
        if(heroPresentInTheList(heroes,HeroName.Condottiere)) return true;
        return false;
    }

    /**
     * Check if there are heroes left for the needGold choice
     * @param heroes
     * @return
     */
    private boolean isHeroForDefencePresent(HeroDeck heroes){
        if(heroPresentInTheList(heroes,HeroName.King)) return true;
        if(heroPresentInTheList(heroes,HeroName.Merchant)) return true;
        if(heroPresentInTheList(heroes,HeroName.Bishop)) return true;
        if(heroPresentInTheList(heroes,HeroName.Thief)) return true;
        if(heroPresentInTheList(heroes,HeroName.Condottiere)) return true;
        if(heroPresentInTheList(heroes,HeroName.Magician)) return true;
        if(heroPresentInTheList(heroes,HeroName.Architect)) return true;
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
        if(heroPresentInTheList(heroes,HeroName.Condottiere)) return true;
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