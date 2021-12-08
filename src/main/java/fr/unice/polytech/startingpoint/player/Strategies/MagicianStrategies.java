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
    /**
     * In case we find ourselves with an empty hand,
    it's most intresting to target the player with most cards in hand*/
     public void exchangeWithMaxHand(Information infos, int maxCardNumber) {
        List<String> playerNames = infos.getPlayersName();
        String chosenPlayer;
        chosenPlayer = playerNames.stream().filter(name -> infos.getCardCount().get(playerNames.indexOf(name)) == maxCardNumber).findAny().orElse(null);
        infos.setChosenPlayer(chosenPlayer);
    }
    /**
     * this IA preveleges building as many districts as possible rather than build
    high value districts, so if it finds itself with a hand full of expenssive hands
    that it wouldn't be able to build even after taking 2 gold pieces, it will try to
     exchange the entire hand with a player who doesn't have as many gold pieces
     */
     public void exchangeUnaffordableHand(Information infos){
        String chosenPlayer;
        List<String> playerNames = infos.getPlayersName();
        IPlayer player = infos.getCurrentPlayer();
        chosenPlayer = playerNames.stream().filter(name-> infos.getGold().get(playerNames.indexOf(name)) <= player.getGold()+2)
                .filter(name-> infos.getCardCount().get(playerNames.indexOf(name)) >= player.getHand().size()).findAny().orElse(null);
        if(chosenPlayer != null ) infos.setChosenPlayer(chosenPlayer);

    }

    /**
     *in case we find ourselves with only double cards and/or only districts that are
     * already built, it is more interesting to exchange the entire hand,
     * or else we could exchange only the useless ones with the deck
     */
     public void exchangeHandWithDoubles(List<IDistrict> chosenCards,List<IDistrict> doubles1, List<IDistrict> doubles2, Information infos){
        IPlayer player = infos.getCurrentPlayer();
        if(doubles2.size() >0 && doubles2.size() == player.getBuiltDistricts().size()) {
            exchangeUnaffordableHand(infos);
        }
        else {
            chosenCards.addAll(doubles1);
            chosenCards.addAll(doubles2);
        }
    }

    /**
     *if we have a mix of affordable and inaffordable cards, only exchage the inaffordabble ones
     */

     public void exchangeUnaffordableCards(List<IDistrict> chosenCards, Information info){
        IPlayer player = info.getCurrentPlayer();
        for (IDistrict district : player.getHand()) {
            if (!district.isWonder() && district.getPrice() > player.getGold() + 2) {
                chosenCards.add(district);
            }
        }
    }

    /**
     * this is a methode that determins the way the magician action will be
     * used, it is an intelligence that would rather build many districts
     * with low value than few with high value
     */
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
                        infos);
            }
            if(infos.getChosenPlayer() != null){
                exchangeUnaffordableCards(chosenCards,infos);
            }
        }
        infos.setChosenCards(chosenCards);
    }










}

