package fr.unice.polytech.startingpoint.player.IA.Strategies;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;

import java.util.List;

public class AssassinChoice {

    /**
     * this methode regroupes the choices made by the IA after choosing the assassin
     * it targets the most advansed player , it trys to guess it's role
     * and then stocks the player with that role in the information object
     */
    public void AssassinChoice1(IAToHero infos){
        String chosenPlayer;
        IHero Hero;
        String RealChosenPlayer = null;
        List<List<IDistrict>> builtCards = infos.getBuiltDistricts();
        List<Integer> scores = infos.getScores();
        List<String> playerNames = infos.getPlayersName();
        chosenPlayer = mostAdvancedPlayer(builtCards,scores,playerNames);
        List<IDistrict> playerBuiltDistricts = builtCards.get(playerNames.indexOf(chosenPlayer));
        int gold = infos.getGold().get(playerNames.indexOf(chosenPlayer));
        int cardNumber = infos.getCardCount().get(playerNames.indexOf(chosenPlayer));
        HeroName supposedHero = IA.guessHero(cardNumber,gold,playerBuiltDistricts,HeroName.Assassin,infos.getVisibleHeroes());
        Hero = IA.findChosenHero(supposedHero,infos);
        if(Hero != null)
            RealChosenPlayer = playerNames.get(infos.getHeros().indexOf(Hero));
        infos.setChosenPlayer(RealChosenPlayer);
    }

    /**
     * the assassin targets the most advanced player, the one with most built districts
     * and most amount of gold
     */
    public String mostAdvancedPlayer(List<List<IDistrict>> cardsBuilt, List<Integer> scores,List<String> playerNames){
        String chosenPlayer = playerNames.get(0);
        int scoreMax;
        int playerScore;
        playerScore= IA.calculScore.apply(scores.get(0),cardsBuilt.get(0).size());
        scoreMax=playerScore;
        for(int i=1;i<playerNames.size();i++){
            playerScore =IA.calculScore.apply(scores.get(i),cardsBuilt.get(i).size());
            if(scoreMax<playerScore){
                chosenPlayer=playerNames.get(i);
                scoreMax=playerScore;
            }
        }
        return chosenPlayer;
    }


}
