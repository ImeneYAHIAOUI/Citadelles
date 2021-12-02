package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.List;

public class BonusPoint {
    /**
     * Method to get end-of-game bonus points.
     * @param playerList
     */
    public void obtainBonus(List<IPlayer> playerList){
        this.bonusForFirst(playerList);
        this.bonusWithColor(playerList);
    }

    /**
     * ø + 4 for the first player who placed his eighth quarter
     * ø + 2 for other players with eight quarters.
     * @param playerList
     */
    private void bonusForFirst(List<IPlayer> playerList){
        for(int i = 0; i < playerList.size(); i++){
            if(i == 0){
                playerList.get(i).addBonusScore(4);
            }else if(playerList.get(i).getBuiltDistricts().size() == 8){
                playerList.get(i).addBonusScore(2);
            }
        }
    }

    /**
     * ø + 3 if the city includes quarters of the five different colors.
     * @param playerList
     */
    private void bonusWithColor(List<IPlayer> playerList){
        playerList.forEach(player -> {
            int val = 0;

            if(player.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.YELLOW))
                val ++;
            if(player.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.RED))
                val ++;
            if(player.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.BLUE))
                val ++;
            if(player.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.PURPLE))
                val ++;
            if(player.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.GREEN))
                val ++;

            if(val == 5){
                player.addBonusScore(3);
            }
        });
    }
}
