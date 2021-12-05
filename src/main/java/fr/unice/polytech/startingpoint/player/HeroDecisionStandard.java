package fr.unice.polytech.startingpoint.player;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.heros.IHero;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class HeroDecisionStandard {

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
        return false;
    }

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
        // Get a list of players without the AI doing the action
        List<IPlayer> playerList = listModification(ia,players);

        // Calculate my score plus the highest score of other players
        double myProScore =  probaScore(ia);
        double enemyWithThHighestScore = highestEnemyScore(players);

        // If no hero for the attack, then we put 0 in the probability of doing such an action
        if(!heroPresentInTheList(heroes, HeroName.Assassin))
            enemyWithThHighestScore = 0;

        // Random choice based on probability
        double total = myProScore + enemyWithThHighestScore;
        float myProba = (float) (myProScore / total);
        float enemyProba = (float) (enemyWithThHighestScore / total);
        float choise = rand.nextFloat() * ( 1 - 0 );

        // Enum to know the AI thought path
        thoughtPath.add(HerosChoice.IChooseAHero);

        // The choice according to the probabilities
        if(choise <= myProba)
            return defense(players,ia, thoughtPath,heroes,rand); // LEVEL 2
        return attack(thoughtPath,heroes,rand); // LEVEL 2
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
    private List<IPlayer> listModification(IA ia, List<IPlayer> playerList){
        List<IPlayer> list = new ArrayList<IPlayer>(playerList);
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getName().equals(ia.name)){
                list.remove(i);
                break;
            }
        }
        return list;
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
    private IHero attack(List<HerosChoice> thoughtPath, HeroDeck heroes, Random rand){
        thoughtPath.add(HerosChoice.IdecidetoAttack);
        thoughtPath.add(HerosChoice.SoIChooseTheAssassin);
        return heroes.chooseHero(HeroName.Assassin);
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
        needGold = needGold / total;
        exchangeDistrict = exchangeDistrict / total;
        float choise = rand.nextFloat() * ( 1 - 0 );

        // Check if there are heroes left for the needGold choice
        if(!isHeroForNeedGoldPresent(heroes)){
            needGold = 0;
            exchangeDistrict = 1;
        }

        // The choice according to the probabilities
        if(choise <= needGold) {
            thoughtPath.add(HerosChoice.INeedGold);
            hero =  needGold(players,ia,thoughtPath,heroes);  // LEVEL 3
        }else if(choise <= needGold + exchangeDistrict){
            thoughtPath.add(HerosChoice.IWantToChangeTheDistricts);
            thoughtPath.add(HerosChoice.SoIChooseTheMagician);
            hero = heroes.chooseHero(HeroName.Magician); // END
        }
        return hero;
    }

    /**
     * Difference between the cheapest card and my gold
     * @param ia
     * @return int
     */
    private int differenceBetweenTheCheapestCardAndMyGold(IA ia){
        int val = ia.getHand().get(0).getPrice();
        for(int i = 0; i < ia.getHand().size(); i++){
            if(ia.getHand().get(i).getPrice() < val)
                val = ia.getHand().get(i).getPrice();
        }
        if(val - ia.getGold() < 0)
            val = 0;
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
            if (ia.getHand().get(i).getPrice() > needGold) {
                needGold = ia.getHand().get(i).getPrice();
                if(needGold > ia.getGold())
                    needGold = 0;
            }
        }
        return needGold;
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
        //int yellow = 0;
        //int green = 0;
        //int blue=0;
        //IHero hero = null;
        //IDistrict district = null;
        int score1=0;
        IHero heroChoice=heroes.get(0);
        int score=ia.getBuiltDistricts().stream().filter(quart-> quart.getColor()==heroes.get(0).getColor()).collect(Collectors.toList()).size();
        for(int i=1;i<heroes.size();i++){
            if(heroes.get(i).getRank()>2){
                Color color=heroes.get(i).getColor();
                score1=ia.getBuiltDistricts().stream().filter(quart-> quart.getColor()==color).collect(Collectors.toList()).size();
            }
            else{// c le voleur
                score1=players.stream().filter(player-> player.getRole().getRank()>1 && !player.getIsAssigned() && !ia.equals(player))
                        .map(player-> player.getGold()).reduce(Integer::max).get();
            }
            if(score1>score){
                heroChoice=heroes.get(i);
                score=score1;
            }

        }
        switch (heroChoice.getName()){
            case King:
                thoughtPath.add(HerosChoice.SoIChooseTheKing);
                break;
            case Merchant:
                thoughtPath.add(HerosChoice.SoIChooseTheMerchant);
                break;
            case Bishop:
                thoughtPath.add(HerosChoice.SoIchooseTheBishop);
                break;
            case Thief:
                thoughtPath.add(HerosChoice.SoIchooseTheThief);
                break;
        }
        return heroChoice;
        // Count the number of color cards. Choose the hero who earns the most coins
        //for(int i = 0; i < ia.getBuiltDistricts().size(); i++){
            //district = ia.getBuiltDistricts().get(i);
            //switch (district.getColor()){
                //case YELLOW:
                    //if(heroes.stream().map(h -> h.getName()).anyMatch(name -> name == HeroName.King))
                        //yellow ++;
                    //break;
                //case GREEN:
                    //if(heroes.stream().map(h -> h.getName()).anyMatch(name -> name == HeroName.Merchant))
                        //green ++;
                    //break;
                //case BLUE:
                    //if(heroes.stream().map(h -> h.getName()).anyMatch(name -> name == HeroName.Bishop))
                        //blue ++;
                    //break;
            //}
        //}
        //if(yellow > green) {
            //thoughtPath.add(HerosChoice.SoIChooseTheKing);
            //hero = heroes.chooseHero(HeroName.King);

        //}else{
            //thoughtPath.add(HerosChoice.SoIChooseTheMerchant);
            //hero = heroes.chooseHero(HeroName.Merchant);
        //}
        //return hero;
    }
}