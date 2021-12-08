package fr.unice.polytech.startingpoint.player.Strategies;
import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DrawOrGetGoldStrategies {
    /**
     *this methodes regroups all the choises made by the IA
     * when choosing to draw or get gold
     */
    public void drawOrGetPieces1(DistrictDeck deck, Treasure treasure, IAToHero info, Predicate<IDistrict> isAffordable){
        List<IDistrict> hand = info.getCurrentPlayer().getHand();
        int numberOfDistrictChosen = 0;
        int numberOfDistrictDistributed = 0;
        List<IDistrict> doubles = IA.searchForDoubles(hand,info.getCurrentPlayer().getBuiltDistricts());
        List<IDistrict> nonDoubles = hand.stream().filter(d -> !doubles.contains(d)).collect(Collectors.toList());
        if(nonDoubles.size()>0 && doubles.size() < hand.size()){
            if( nonDoubles.stream().noneMatch(isAffordable)){
                NoAffordableCardsChoice(deck,treasure,info);
            }
            else{
                ChoiceBasedOnCardNumbers(deck,treasure,info);
            }
        }
        else{
            // If I have the wonder I apply its power
            // If you choose to draw cards at the start of your turn, you draw two cards and keep both.
            numberOfDistrictChosen = info.getCurrentPlayer().applyLibrary();

            //If you choose to draw cards at the start of your turn, you draw three, choose one, and discard the other two.
            numberOfDistrictDistributed = info.getCurrentPlayer().applyObservatory();

            draw1(deck,info,numberOfDistrictDistributed,numberOfDistrictChosen);
        }
    }

    /**
     *when the IA choses the draw, it usually takes 2 from deck and chooses one
     * but sometimes with wander districts we can draw or/and choose more
     *this IA preveleges cheaper cards
     */

    public void draw1(DistrictDeck deck, IAToHero info, int drawnNum, int chosenNum){
        List<IDistrict> drawnDistricts = deck.giveDistrict(drawnNum);
        List<IDistrict> doubles = IA.searchForDoubles(info.getCurrentPlayer().getHand(),info.getCurrentPlayer().getBuiltDistricts());
        List<IDistrict> keptList =  chooseDistrictsBasedOnAffordability(drawnDistricts,chosenNum,deck,doubles);
        IPlayer player = info.getCurrentPlayer();
        player.getDistrict(keptList);
        info.setDraw();
    }


    /**
     *this methode handle the destrebution of gold from the tresury in
     */
    public void getGold(Treasure treasure, IAToHero info, int amount){
        IPlayer player = info.getCurrentPlayer();
        int giveGold=treasure.removeGold(amount);
        player.addGold(giveGold);
        info.setGetGold();
    }

    /**
     * this methode handle the IA choice in the case of having a hand with no affordable cards
     */
    public void NoAffordableCardsChoice(DistrictDeck deck, Treasure treasure, IAToHero info){
        List<IDistrict> hand = info.getCurrentPlayer().getHand();
        int gold = info.getCurrentPlayer().getGold();
        List<IDistrict> doubles = IA.searchForDoubles(hand,info.getCurrentPlayer().getBuiltDistricts());
        if(hand.stream().filter(d -> !doubles.contains(d)).anyMatch(d -> d.getPrice()<=gold+2)){
            getGold(treasure,info,2);
        }
        else{
            draw1(deck,info,2,1);
        }
    }

    /**if the IA does have affordable hands, it chooses to draw only if it only has
     * to cards or less*/
    public  void ChoiceBasedOnCardNumbers(DistrictDeck deck, Treasure treasure, IAToHero info){
        List<IDistrict> hand = info.getCurrentPlayer().getHand();
        if(hand.size()<3) {
            draw1(deck,info,2,1);
        }
        else{
            getGold(treasure,info,2);
        }
    }


    /** when this IA choses to draw, it preveleges cheaper cards
     * this methode handles the choice of cards and puts back the
     * rest in deck*/

    public List<IDistrict> chooseDistrictsBasedOnAffordability(List<IDistrict> districtList,int chosenNum,DistrictDeck deck,List<IDistrict> doubles){
       List<IDistrict> keptList = new ArrayList<>();
       IDistrict keptDistrict;
        while (chosenNum>0) {
           keptDistrict = districtList.stream().min(Comparator.comparingInt(IDistrict::getPrice)).get();
           if(! doubles.contains(keptDistrict)){
               keptList.add(keptDistrict);
               districtList.remove(keptDistrict);
           }

           chosenNum--;
       }

       deck.addDistricts(districtList);
       return keptList;
    }



}
