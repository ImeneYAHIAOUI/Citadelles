package fr.unice.polytech.startingpoint.player;

import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.heros.IHero;

import java.util.ArrayList;
import java.util.List;

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
     *
     * @param ia
     * @param players
     * @param heroes
     * @param thoughtPath
     * @return
     */
    public IHero heroDecision(IA ia, List<IPlayer> players, HeroDeck heroes, List<HerosChoice> thoughtPath ){ // LEVEL 1
        double myProScore =  probaScore(ia);
        double enemyWithThHighestScore = 0;

        double val = 0;
        for(int i = 0; i < players.size() ;i++){
            val = probaScore(players.get(i));
            if(enemyWithThHighestScore < val){
                enemyWithThHighestScore = val;
            }
        }

        if(!heroPresentInTheList(heroes, HeroName.Assassin))
            enemyWithThHighestScore = 0;

        double total = myProScore + enemyWithThHighestScore;

        float myProba = (float) (myProScore / total);
        float enemyProba = (float) (enemyWithThHighestScore / total);

        float choise = (float) (Math.random() * ( 1 - 0 ));


        thoughtPath.add(HerosChoice.IChooseAHero);

        if(choise <= myProba)
            return defense(ia, thoughtPath,heroes);
        return attack(thoughtPath,heroes);
    }

    public IHero attack(List<HerosChoice> thoughtPath, HeroDeck heroes){ // LEVEL 2
        return null;
    }

    public IHero defense(IA ia, List<HerosChoice> thoughtPath,HeroDeck heroes){ // LEVEL 2
        float needGold = 0; // Merchent, King
        float exchangeDistrict = 0; // Magicien
        float buildTwoDistrict = 0; // Architect
        IHero hero = null;

        int val = ia.getHand().get(0).getPrice();
        for(int i = 0; i < ia.getHand().size(); i++){
            if(ia.getHand().get(i).getPrice() > needGold)
                needGold = ia.getHand().get(i).getPrice();
            if(ia.getHand().get(i).getPrice() < val)
                val = ia.getHand().get(i).getPrice();
        }

        exchangeDistrict = val - ia.getGold();
        if(!heroPresentInTheList(heroes, HeroName.Magician) || exchangeDistrict < 0)
            exchangeDistrict = 0;

        float total = exchangeDistrict + needGold;

        needGold = needGold / total;
        exchangeDistrict = exchangeDistrict / total;

        float choise = (float) (Math.random() * ( 1 - 0 ));

        if(choise <= needGold) {
            thoughtPath.add(HerosChoice.INeedGold);
            hero =  needGold(ia,thoughtPath,heroes);
        }else if(choise <= needGold + exchangeDistrict){
            thoughtPath.add(HerosChoice.IWantToChangeTheDistricts);
            thoughtPath.add(HerosChoice.SoIChooseTheMagician);

            for(int i = 0 ; i < heroes.size(); i++){
                if(heroes.get(i).getName() == HeroName.Magician){
                    hero = heroes.get(i);
                    heroes.remove(i);
                    break;
                }
            }
        }
        return hero;
    }

    public IHero needGold(IA ia, List<HerosChoice> thoughtPath, HeroDeck heroes){ // LEVEL 3
        int yellow = 0;
        int green = 0;
        IHero hero = null;

        int nbColor = 2;

        IDistrict district = null;

        for(int i = 0; i < ia.getBuiltDistricts().size(); i++){
            district = ia.getBuiltDistricts().get(i);

            switch (district.getColor()){
                case YELLOW:
                    if(heroes.stream().map(h -> h.getName()).anyMatch(name -> name == HeroName.King))
                        yellow ++;
                    break;
                case GREEN:
                    if(heroes.stream().map(h -> h.getName()).anyMatch(name -> name == HeroName.Merchant))
                        green ++;
                    break;
            }
        }

        if(yellow < green) {
            thoughtPath.add(HerosChoice.SoIChooseTheKing);

            for(int i = 0 ; i < heroes.size(); i++){
                if(heroes.get(i).getName() == HeroName.King){
                    hero = heroes.get(i);
                    heroes.remove(i);
                    break;
                }
            }
        }else{
            thoughtPath.add(HerosChoice.SoIChooseTheMerchant);

            for(int i = 0 ; i < heroes.size(); i++){
                if(heroes.get(i).getName() == HeroName.Merchant){
                    hero = heroes.get(i);
                    heroes.remove(i);
                    break;
                }
            }
        }
        return hero;
    }
}
