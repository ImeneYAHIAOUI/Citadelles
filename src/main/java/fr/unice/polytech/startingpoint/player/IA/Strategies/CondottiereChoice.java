package fr.unice.polytech.startingpoint.player.IA.Strategies;

import fr.unice.polytech.startingpoint.cards.DistrictName;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.player.IA.Bots;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;
import fr.unice.polytech.startingpoint.player.IPlayer;
import java.util.List;

public class CondottiereChoice {

    /**
     *
     * @param information
     * @param gold
     * @param currentPlayer
     */
    private void makeRandomChoice(IAToHero information , int gold,IPlayer currentPlayer){
        int index;
        IDistrict district = null;
        String RandomPlayer = information.getPlayersName().stream().findAny().get();
        index = information.getPlayersName().indexOf(RandomPlayer);
        district = information.getBuiltDistricts().get(index).stream()
                .filter(card -> card.getPrice() <= gold && card.getDistrictName() != DistrictName.DONGEON)
                .findAny().orElse(null);
        if (district != null) {
            currentPlayer.setCardDestroyedByCondottiere(district);
            information.setChosenPlayer(RandomPlayer);
        }
    }

    /**
     * Find district to attack
     * @param scores
     * @param currentPlayer
     * @param information
     * @param gold
     * @return
     */
    private IDistrict findDistrict(List<Integer> scores, IPlayer currentPlayer, IAToHero information, int gold){
        int index;
        IDistrict district = null;

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

        return district;
    }

    /**
     *
     * @param information
     */
    public void  makeChoice(IAToHero information) {
        IPlayer currentPlayer = information.getCurrentPlayer();
        int gold = currentPlayer.getGold();
        List<Integer> scores = information.getScores();
        IDistrict district = null;

        if (((IA) information.getCurrentPlayer()).bot.equals(Bots.random)) {
            this.makeRandomChoice(information,gold, currentPlayer);
        } else {
            district = this.findDistrict(scores,currentPlayer,information,gold);

            //on choisit de détruire un quartier à une seule piece
            if (district == null) {
                this.destroyDistrictAt1Gold(information,currentPlayer);
            }
        }
    }

    /**
     *
     * @param information
     * @param currentPlayer
     */
    private void destroyDistrictAt1Gold(IAToHero information, IPlayer currentPlayer){
        int index;
        IDistrict district = null;

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