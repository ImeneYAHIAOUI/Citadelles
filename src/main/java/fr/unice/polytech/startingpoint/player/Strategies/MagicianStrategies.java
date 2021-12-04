package fr.unice.polytech.startingpoint.player.Strategies;

import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.Information;

import java.util.List;

public class MagicianStrategies {

    static public void exchangeEmptyHand(Information infos, int maxCardNumber) {
        List<String> playerNames = infos.getPlayersName();
        String chosenPlayer;
        chosenPlayer = playerNames.stream().filter(name -> infos.getCardCount().get(playerNames.indexOf(name)) == maxCardNumber).findAny().orElse(null);
        infos.setChosenPlayer(chosenPlayer);
    }
    static public void exchangeUnaffordableHand(Information infos,int maxCardNumber){
        String chosenPlayer;
        List<String> playerNames = infos.getPlayersName();
        IPlayer player = infos.getCurrentPlayer();
        chosenPlayer = playerNames.stream().filter(name-> infos.getGold().get(playerNames.indexOf(name)) <= player.getGold()+2)
                .filter(name-> infos.getCardCount().get(playerNames.indexOf(name)) >= player.getHand().size()).findAny().orElse(null);
        if(chosenPlayer != null ) infos.setChosenPlayer(chosenPlayer);

    }
    static public void exchangeHandWithDoubles(List<IDistrict> chosenCards,List<IDistrict> doubles1, List<IDistrict> doubles2, Information infos,int maxCardNumber){
        IPlayer player = infos.getCurrentPlayer();
        if(doubles2.size() >0 && doubles2.size() == player.getBuiltDistricts().size()) {
            exchangeUnaffordableHand(infos,maxCardNumber);
        }
        else {
            chosenCards.addAll(doubles1);
            chosenCards.addAll(doubles2);
        }
    }

    static public void exchangeUnaffordableCards(List<IDistrict> chosenCards, Information info){
        IPlayer player = info.getCurrentPlayer();
        for (IDistrict district : player.getHand()) {
            if (!district.isWonder() && district.getPrice() > player.getGold() + 2) {
                chosenCards.add(district);
            }
        }
    }


}

