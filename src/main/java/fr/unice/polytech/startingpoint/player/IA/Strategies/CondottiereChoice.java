package fr.unice.polytech.startingpoint.player.IA.Strategies;

import fr.unice.polytech.startingpoint.cards.DistrictName;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;
import fr.unice.polytech.startingpoint.player.IPlayer;
import java.util.List;

public class CondottiereChoice {

    public void makeChoice(IAToHero information){
        IPlayer currentPlayer=information.getCurrentPlayer();
        int gold=currentPlayer.getGold();
        List<Integer> scores=information.getScores();
        int index=-1;
        for (int score:scores){
            if(score>currentPlayer.getScore()&& information.getBuiltDistricts().get(scores.indexOf(score)).size()>=6){
                    index=scores.indexOf(score);
                    break;
            }
        }
        if(index>=0){
          IDistrict district =information.getBuiltDistricts().get(index).stream().filter(card->card.getPrice()<=gold && card.getDistrictName()!= DistrictName.DONGEON).findAny().orElse(null);
            if(district!=null){
                currentPlayer.setCardDestroyedByCondottiere(district);
                information.setChosenPlayer(information.getPlayersName().get(index));            }
        }
    }
}