package fr.unice.polytech.startingpoint.player.Strategies;
import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.cards.Treasure;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.Information;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class DrawOrGetGoldStrategies {

    public void drawOrGetPieces1(DistrictDeck deck, Treasure treasure, Information info, Predicate<IDistrict> isAffordable){
        List<IDistrict> hand = info.getCurrentPlayer().getHand();
        if(hand.size()>0){
            if( hand.stream().noneMatch(isAffordable)){
                NoAffordableCardsChoice(deck,treasure,info);
            }
            else{
                ChoiceBasedOnCardNumbers(deck,treasure,info);
            }
        }
        else{
            draw1(deck,info,2,1);
        }
    }



    public void draw1(DistrictDeck deck, Information info, int drawnNum,int chosenNum){
        List<IDistrict> drawnDistricts = deck.giveDistrict(drawnNum);
        List<IDistrict> keptList =  chooseDistrictsBasedOnAffordability(drawnDistricts,chosenNum,deck);
        IPlayer player = info.getCurrentPlayer();
        player.getDistrict(keptList);
        info.setDraw();
    }



    public void getGold(Treasure treasure, Information info, int amount){
        IPlayer player = info.getCurrentPlayer();
        int giveGold=treasure.removeGold(amount);
        player.addGold(giveGold);
        info.setGetGold();
    }

    public void NoAffordableCardsChoice(DistrictDeck deck,Treasure treasure,Information info){
        List<IDistrict> hand = info.getCurrentPlayer().getHand();
        int gold = info.getCurrentPlayer().getGold();
        if(hand.stream().anyMatch(district -> district.getPrice()<=gold+2 )) {
            getGold(treasure,info,2);
        }
        else{
            draw1(deck,info,2,1);
        }
    }

    public  void ChoiceBasedOnCardNumbers(DistrictDeck deck,Treasure treasure,Information info){
        List<IDistrict> hand = info.getCurrentPlayer().getHand();
        if(hand.size()<3) {
            draw1(deck,info,2,1);
        }
        else{
            getGold(treasure,info,2);
        }
    }




    public List<IDistrict> chooseDistrictsBasedOnAffordability(List<IDistrict> districtList,int chosenNum,DistrictDeck deck){
       List<IDistrict> keptList = new ArrayList<>();
       IDistrict keptDistrict;
       while (chosenNum>0) {
           keptDistrict = districtList.stream().min(Comparator.comparingInt(IDistrict::getPrice)).get();
           keptList.add(keptDistrict);
           districtList.remove(keptDistrict);
           chosenNum--;
       }

       deck.addDistricts(districtList);
       return keptList;
    }



}
