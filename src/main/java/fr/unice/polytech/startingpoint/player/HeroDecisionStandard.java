package fr.unice.polytech.startingpoint.player;

import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.heros.HeroName;

import java.util.ArrayList;
import java.util.List;

public class HeroDecisionStandard {
    /*public double probaScore(IPlayer players){
        return players.getScore()*100 + players.getBuiltDistricts().size()*10;
    }

    public boolean heroPresentInTheList(HeroDeck heroes, HeroName heroName){
        return heroes.stream().map(hero -> hero.getName()).anyMatch(name -> name == heroName);
    }


    public void attack(List<HerosChoice> thoughtPath, HeroDeck heroes){ // LEVEL 2

    }

    public void heroDecision(IPlayer ia, List<IPlayer> players, HeroDeck heroes){ // LEVEL 1
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

        List<HerosChoice> thoughtPath = new ArrayList<HerosChoice>();
        thoughtPath.add(HerosChoice.IChooseAHero);

        if(choise <= myProba)
            defense(thoughtPath,heroes);
        attack(thoughtPath,heroes);
    }

    public void defense(IPlayer ia, List<HerosChoice> thoughtPath,HeroDeck heroes){ // LEVEL 2
        float needGold = 0; // Merchent, King
        float exchangeDistrict = 0; // Magicien
        float buildTwoDistrict = 0; // Architect

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
            needGold(thoughtPath,heroes);
        }else if(choise <= needGold + exchangeDistrict){
            thoughtPath.add(HerosChoice.IWantToChangeTheDistricts);
            thoughtPath.add(HerosChoice.SoIChooseTheMagician);
            ia.thoughtPathList = thoughtPath;

            for(int i = 0 ; i < heroes.size(); i++){
                if(heroes.get(i).getName() == HeroName.Magician){
                    ia.setRole(heroes.get(i));
                    heroes.remove(i);
                    break;
                }
            }
        }
    }

    public void needGold(List<HerosChoice> thoughtPath, HeroDeck heroes){ // LEVEL 3
        int yellow = 0;
        int green = 0;

        int nbColor = 2;

        IDistrict district = null;

        for(int i = 0; i < this.getBuiltDistricts().size(); i++){
            district = this.getBuiltDistricts().get(i);

            switch (district.getColor()){
                case YELLOW:
                    if(heroes.stream().map(hero -> hero.getName()).anyMatch(name -> name == HeroName.King))
                        yellow ++;
                    break;
                case GREEN:
                    if(heroes.stream().map(hero -> hero.getName()).anyMatch(name -> name == HeroName.Merchant))
                        green ++;
                    break;
            }
        }

        if(yellow < green) {
            thoughtPath.add(HerosChoice.SoIChooseTheKing);
            this.thoughtPathList = thoughtPath;

            for(int i = 0 ; i < heroes.size(); i++){
                if(heroes.get(i).getName() == HeroName.King){
                    this.setRole(heroes.get(i));
                    heroes.remove(i);
                    break;
                }
            }
        }else{
            thoughtPath.add(HerosChoice.SoIChooseTheMerchant);
            this.thoughtPathList = thoughtPath;

            for(int i = 0 ; i < heroes.size(); i++){
                if(heroes.get(i).getName() == HeroName.Merchant){
                    this.setRole(heroes.get(i));
                    heroes.remove(i);
                    break;
                }
            }
        }

    }*/
}
