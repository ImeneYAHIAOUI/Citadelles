package fr.unice.polytech.startingpoint.player.Strategies;

import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.Information;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MagicianStrategies {

     public void exchangeWithMaxHand(Information infos, int maxCardNumber) {
        List<String> playerNames = infos.getPlayersName();
        String chosenPlayer;
        chosenPlayer = playerNames.stream().filter(name -> infos.getCardCount().get(playerNames.indexOf(name)) == maxCardNumber).findAny().orElse(null);
        infos.setChosenPlayer(chosenPlayer);
    }
    public void exchangeWithExpensiveHand(Information infos,int maxGold){
        List<String> playerNames = infos.getPlayersName();
        String chosenPlayer;
        chosenPlayer = playerNames.stream().filter(name -> infos.getGold().get(playerNames.indexOf(name)) == maxGold).findAny().orElse(null);
        infos.setChosenPlayer(chosenPlayer);
    }
     public void exchangeUnaffordableHand(Information infos){
        String chosenPlayer;
        List<String> playerNames = infos.getPlayersName();
        IPlayer player = infos.getCurrentPlayer();
        chosenPlayer = playerNames.stream().filter(name-> infos.getGold().get(playerNames.indexOf(name)) <= player.getGold()+2)
                .filter(name-> infos.getCardCount().get(playerNames.indexOf(name)) >= player.getHand().size()).findAny().orElse(null);
        if(chosenPlayer != null ) infos.setChosenPlayer(chosenPlayer);

    }
     public void exchangeHandWithDoubles(List<IDistrict> chosenCards,List<IDistrict> doubles1, List<IDistrict> doubles2, Information infos,int maxCardNumber){
        IPlayer player = infos.getCurrentPlayer();
        if(doubles2.size() >0 && doubles2.size() == player.getBuiltDistricts().size()) {
            exchangeUnaffordableHand(infos);
        }
        else {
            chosenCards.addAll(doubles1);
            chosenCards.addAll(doubles2);
        }
    }

     public void exchangeUnaffordableCards(List<IDistrict> chosenCards, Information info){
        IPlayer player = info.getCurrentPlayer();
        for (IDistrict district : player.getHand()) {
            if (!district.isWonder() && district.getPrice() > player.getGold() + 2) {
                chosenCards.add(district);
            }
        }
    }

    public void exchangeCheapCards(List<IDistrict> chosenCards, Information info){
        IPlayer player = info.getCurrentPlayer();
        for (IDistrict district : player.getHand()) {
            if (!district.isWonder() && district.getPrice() < 3) {
                chosenCards.add(district);
            }
        }
    }

    public void magicienChoice1(Information infos,Predicate<IDistrict> isAffordable) {
        int maxCardNumber = IA.searchForMaxNumberOfCards(infos);
        List<IDistrict> chosenCards = new ArrayList<>();
        List<IDistrict> hand = infos.getCurrentPlayer().getHand();
        List<IDistrict> builtDistricts = infos.getCurrentPlayer().getBuiltDistricts();
        List<IDistrict> doublesInHand = IA.searchForDoubles(hand,hand);
        if(hand.size() == 0){
            exchangeWithMaxHand(infos,maxCardNumber);
        }

        else if (hand.stream().noneMatch(IDistrict::isWonder) && hand.stream().noneMatch(isAffordable)){
            exchangeUnaffordableHand(infos);
        }
        else {
            if(doublesInHand.size()>0) {
                List<IDistrict> doublesInBuiltDistricts = IA.searchForDoubles(hand, builtDistricts);
                exchangeHandWithDoubles(chosenCards,doublesInHand,doublesInBuiltDistricts,
                        infos,maxCardNumber);
            }
            if(infos.getChosenPlayer() != null){
                exchangeUnaffordableCards(chosenCards,infos);
            }
        }
        infos.setChosenCards(chosenCards);
    }

    static void exchangeWithMaxExpensiveHand(Information infos,int maxCardNumber){
        List<String> playerNames = infos.getPlayersName();
        String chosenPlayer;
        int maxGold = IA.searchForMaxGold(infos);
        chosenPlayer = playerNames.stream().filter(p -> infos.getGold().get(playerNames.indexOf(p)) == maxGold).findAny().orElse(null);
        infos.setChosenPlayer(chosenPlayer);
    }


    public void magicienChoice2(Information infos,Predicate<IDistrict> isAffordable) {
        int maxCardNumber = IA.searchForMaxNumberOfCards(infos);
        int maxGold = IA.searchForMaxGold(infos);
        List<IDistrict> chosenCards = new ArrayList<>();
        List<IDistrict> hand = infos.getCurrentPlayer().getHand();
        List<IDistrict> builtDistricts = infos.getCurrentPlayer().getBuiltDistricts();
        List<IDistrict> doublesInHand;
        doublesInHand = IA.searchForDoubles(hand,hand);
        if(hand.size() == 0){
            exchangeWithMaxExpensiveHand(infos,maxCardNumber);
        }
        else if (hand.stream().noneMatch(IDistrict::isWonder) && hand.stream().allMatch(district -> district.getPrice()<3)){
            exchangeWithExpensiveHand(infos,maxGold);
        }
        else {
            if(doublesInHand.size()>0) {
                List<IDistrict> doublesInBuiltDistricts = IA.searchForDoubles(hand, builtDistricts);
                exchangeHandWithDoubles(chosenCards,doublesInHand,doublesInBuiltDistricts,
                        infos,maxCardNumber);
            }
            if(infos.getChosenPlayer() != null){
                exchangeCheapCards(chosenCards,infos);
            }
        }
        infos.setChosenCards(chosenCards);
    }





}

