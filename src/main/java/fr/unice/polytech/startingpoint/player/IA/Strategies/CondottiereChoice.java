package fr.unice.polytech.startingpoint.player.IA.Strategies;

import fr.unice.polytech.startingpoint.cards.DistrictName;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;
import fr.unice.polytech.startingpoint.player.IPlayer;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CondottiereChoice {

    public void  makeChoice(IAToHero information) {
        IPlayer currentPlayer = information.getCurrentPlayer();
        int gold = currentPlayer.getGold();
        List<Integer> scores = information.getScores();
        int index;
        IDistrict district = null;
        if (((IA) information.getCurrentPlayer()).bot.equals("random")) {
            String RandomPlayer = information.getPlayersName().stream().findAny().get();
            index = information.getPlayersName().indexOf(RandomPlayer);
            district = information.getBuiltDistricts().get(index).stream()
                    .filter(card -> card.getPrice() <= gold && card.getDistrictName() != DistrictName.DONGEON)
                    .findAny().orElse(null);
            if (district != null) {
                currentPlayer.setCardDestroyedByCondottiere(district);
                information.setChosenPlayer(RandomPlayer);
            }
        } else {
            for (int score : scores) {
                index = scores.indexOf(score);
                if (score > currentPlayer.getScore() && information.getBuiltDistricts().get(index).size() >= 6) {
                    district = information.getBuiltDistricts().get(index).stream()
                            .filter(card -> card.getPrice() <= gold && card.getDistrictName() != DistrictName.DONGEON)
                            .findAny().orElse(null);
                    if (district != null) {
                        currentPlayer.setCardDestroyedByCondottiere(district);
                        information.setChosenPlayer(information.getPlayersName().get(index));
                        break;
                    }
                }
            }

            //on choisit de détruire un quartier à une seule piece
            if (district == null) {
                for (List<IDistrict> districts : information.getBuiltDistricts()) {
                    index = information.getBuiltDistricts().indexOf(districts);
                    district = districts.stream()
                            .filter(card -> card.getPrice() == 1)
                            .findAny().orElse(null);
                    if (district != null) {
                        currentPlayer.setCardDestroyedByCondottiere(district);
                        information.setChosenPlayer(information.getPlayersName().get(index));
                        break;
                    }

                }
            }
        }
    }
}
