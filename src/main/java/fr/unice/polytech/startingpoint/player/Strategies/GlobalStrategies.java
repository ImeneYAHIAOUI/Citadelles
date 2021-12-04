package fr.unice.polytech.startingpoint.player.Strategies;

import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.cards.Treasure;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.Information;

import java.util.ArrayList;
import java.util.List;

public class GlobalStrategies{
    static public int searchForMaxNumberOfCards(Information infos){
        List<Integer> cardNumbers = infos.getCardCount();
        int maxCardNumber = cardNumbers.stream().max(Integer::compare).get();
        return maxCardNumber;
    }
    static public void draw(DistrictDeck deck, Information info, int num){
        IPlayer player = info.getCurrentPlayer();
        player.getDistrict(deck.giveDistrict(num));
        info.setDraw();
    }
    static public void getGold(Treasure treasure, Information info, int amount){
        IPlayer player = info.getCurrentPlayer();
        int giveGold=treasure.removeGold(amount);
        player.addGold(giveGold);
        info.setGetGold();
    }
    static public List<IDistrict> searchForDoubles(List<IDistrict> hand, List<IDistrict> districtList){
        List<IDistrict> doubles = new ArrayList<>();
        hand.forEach(district -> {
            if(districtList.stream().anyMatch(d -> d.getDistrictName().equals(district.getDistrictName())
                    && ! d.equals(district) && doubles.stream().noneMatch(d2 -> d2.getDistrictName().equals(district.getDistrictName())))){
                doubles.add(district);
            }
        });
        return doubles;
    }
    static public void NoAffordableCardsChoice(DistrictDeck deck,Treasure treasure,Information info){
        List<IDistrict> hand = info.getCurrentPlayer().getHand();
        int gold = info.getCurrentPlayer().getGold();
        if(hand.stream().anyMatch(district -> district.getPrice()<=gold+2 )) {
            getGold(treasure,info,2);
        }
        else{
            draw(deck,info,1);
        }
    }
    static public void ChoiceBasedOnCardNumbers(DistrictDeck deck,Treasure treasure,Information info){
        List<IDistrict> hand = info.getCurrentPlayer().getHand();
        if(hand.size()<3) {
            draw(deck,info,1);
        }
        else{
            getGold(treasure,info,2);
        }
    }


}
