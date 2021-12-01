package fr.unice.polytech.startingpoint.player;
import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.cards.Treasure;
import fr.unice.polytech.startingpoint.heros.HeroDeck;

import fr.unice.polytech.startingpoint.heros.HeroName;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class IA extends Player{
    Predicate<IDistrict> isAffordable = district -> district.getPrice()<=gold;
    static BiFunction<Integer ,Integer,Integer > calculScore=(score, nbBuiltCard)->  100*score+10*nbBuiltCard;

    List<HerosChoice> thoughtPathList;
    /**
     *
     * @param playerName the IA object is constructed the same way as a Player object,
     *                   so we also only need the name of the player here.
     */

    public IA(String playerName){
        super(playerName);
        thoughtPathList = new ArrayList<HerosChoice>();
    }

    public double probaScore(IPlayer players){
        return players.getScore()*100 + players.getBuiltDistricts().size()*10;
    }

    public boolean heroPresentInTheList(HeroDeck heroes, HeroName heroName){
        return heroes.stream().map(hero -> hero.getName()).anyMatch(name -> name == heroName);
    }

    /**
     * this method chooses the hero for the bot based on the information it's given
     * it's random based for now
     */
    @Override
    public void chooseHero(HeroDeck heroes, int roleIndex) { // LEVEL 1
        if (roleIndex < 0 || roleIndex> heroes.size()){
            throw new RuntimeException("Invalide value");
        }
        this.setRole(heroes.get(roleIndex));
        heroes.remove(role);
/*
        double myProScore = this.getScore()*100 + this.getBuiltDistricts().size()*10;
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

 */
    }

    public void attack(List<HerosChoice> thoughtPath,HeroDeck heroes){ // LEVEL 2

    }

    public void defense(List<HerosChoice> thoughtPath,HeroDeck heroes){ // LEVEL 2
        float needGold = 0; // Merchent, King
        float exchangeDistrict = 0; // Magicien
        float buildTwoDistrict = 0; // Architect

        int val = this.getHand().get(0).getPrice();
        for(int i = 0; i < this.getHand().size(); i++){
            if(this.getHand().get(i).getPrice() > needGold)
                needGold = this.getHand().get(i).getPrice();
            if(this.getHand().get(i).getPrice() < val)
                val = this.getHand().get(i).getPrice();
        }

        exchangeDistrict = val - this.getGold();
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
            this.thoughtPathList = thoughtPath;

            for(int i = 0 ; i < heroes.size(); i++){
                if(heroes.get(i).getName() == HeroName.Magician){
                    this.setRole(heroes.get(i));
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

    }


    @Override
    public void activateHero(List<IPlayer> players, DistrictDeck districtDeck, Treasure treasure) {
        Information info=new Information();
        switch (role.getName()){
            case Merchant -> {
                info.setInformationForMerchant(this,treasure);
                role.doAction(info);
            }
            case King-> {
                        info.setInformationForKing(this,players,treasure);
                        role.doAction(info);
                    }
            case Magician ->
                    {
                info.setInformationForMagician(players,this, districtDeck);
                magicienChoice(info);

                role.doAction(info);
                }
            case Assassin -> {
                info.setInformationForAssassin(players,this);
                AssassinChoice(info);
                role.doAction(info);
            }

            }
        }
        public void AssassinChoice(Information infos){
            String chosenPlayer;
            int scoreMax;
            int scoreplayer;
            List<List<IDistrict>> cardsBuilt = infos.getBuiltDistricts();
            List<Integer> scores = infos.getScores();
            List<String> playerNames = infos.getPlayersName();
            chosenPlayer=playerNames.get(0);
            scoreplayer= calculScore.apply(scores.get(0),cardsBuilt.get(0).size());
            scoreMax=scoreplayer;
            for(int i=1;i<playerNames.size();i++){
                scoreplayer = calculScore.apply(scores.get(i),cardsBuilt.get(i).size());
                if(scoreMax<scoreplayer){
                    chosenPlayer=playerNames.get(i);
                    scoreMax=scoreplayer;
                }
            }


            infos.setChosenPlayer(chosenPlayer);
        }
        public void magicienChoice(Information infos) {
            List<Integer> cardNumbers = infos.getCardCount();
            List<String> playerNames = infos.getPlayersName();
            int maxCardNumber = cardNumbers.stream().max(Integer::compare).get();
            List<IDistrict> doubles = new ArrayList<>();

            hand.forEach(district -> {
                if(hand.stream().anyMatch(d -> d.getDistrictName().equals(district.getDistrictName())
                && ! d.equals(district) && doubles.stream().noneMatch(d2 -> d2.getDistrictName().equals(district.getDistrictName())))){
                    doubles.add(district);
                }
            });

            List<IDistrict> chosenCards = new ArrayList<>();
            String chosenPlayer;
            if(hand.size() == 0){
                chosenPlayer = playerNames.stream().filter(name -> infos.getCardCount().get(playerNames.indexOf(name)) == maxCardNumber).findAny().orElse(null);
                infos.setChosenPlayer(chosenPlayer);
            }
            else if (hand.stream().noneMatch(IDistrict::isWonder) && hand.stream().noneMatch(isAffordable)){

                chosenPlayer = playerNames.stream().filter(name-> infos.getGold().get(playerNames.indexOf(name)) <= gold+2)
                        .filter(name-> infos.getCardCount().get(playerNames.indexOf(name)) >= hand.size()).findAny().orElse(null);

                if(chosenPlayer != null ) infos.setChosenPlayer(chosenPlayer);

            }

            else {
                if(doubles.size()>0) {
                    chosenCards.addAll(doubles);
                }
                for (IDistrict district : hand){
                    if(! district.isWonder() && district.getPrice() > gold+2){
                        chosenCards.add(district);
                    }
                }


            }
            infos.setChosenCards(chosenCards);
        }


    /**
     * this method chooses what move to make for the bot based on the information it's given
     * it's random based for now
     */

    @Override
    public void doAction(Treasure treasure) {
        if(hand.stream().anyMatch(isAffordable)){
            IDistrict chosenDistrict = hand.stream().filter(isAffordable).findAny().get();
            buildDistrict(chosenDistrict);
            treasure.addToTreasure(chosenDistrict.getPrice());

        }
        else return;
    }

    @Override
    public void drawOrGetPieces(DistrictDeck deck, Treasure treasure){
        Boolean treasureNotEmpty=treasure.getPieces()>=2;
        if(hand.size()>0){
            if( hand.stream().noneMatch(isAffordable)){
                if(hand.stream().anyMatch(district -> district.getPrice()<=gold+2 )&& treasureNotEmpty) {

                    addGold(2);
                    treasure.removeGold(2);
                }
                else{
                    getDistrict(deck.giveDistrict(1));
                }
            }
            else{
                if(hand.size()<3) {
                    getDistrict(deck.giveDistrict(1));
                }
                else{
                    if(treasureNotEmpty){
                        addGold(2);
                        treasure.removeGold(2);

                    }
                }
            }
        }
        else{
            getDistrict(deck.giveDistrict(1));
        }
    }
}
