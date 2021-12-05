package fr.unice.polytech.startingpoint.player.Strategies;

import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.heros.Hero;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.Information;

import java.util.ArrayList;
import java.util.List;

public class AssassinChoice {

    public void AssassinChoice1(Information infos){
        String chosenPlayer;
        IHero Hero;
        String RealChosenPlayer;
        List<List<IDistrict>> builtCards = infos.getBuiltDistricts();
        List<Integer> scores = infos.getScores();
        List<String> playerNames = infos.getPlayersName();
        chosenPlayer = mostAdvancedPlayer(builtCards,scores,playerNames);
        List<IDistrict> playerBuiltDistricts = builtCards.get(playerNames.indexOf(chosenPlayer));
        int maxGold = IA.searchForMaxGold(infos);
        int cardNumber = infos.getCardCount().get(playerNames.indexOf(chosenPlayer));
        HeroName supposedHero = IA.guessHero(cardNumber,maxGold,playerBuiltDistricts);
        IA.findMostObviousPlayer(chosenPlayer,supposedHero,infos,maxGold);
        Hero = IA.findChosenHero(supposedHero,infos);
        RealChosenPlayer = playerNames.get(infos.getHeros().indexOf(Hero));
        infos.setChosenPlayer(RealChosenPlayer);
        }

        public void AssassinChoice2(Information infos){
            String chosenPlayer;
            IHero Hero;
            String RealChosenPlayer;
            List<List<IDistrict>> builtCards = infos.getBuiltDistricts();
            List<Integer> scores = infos.getScores();
            List<String> playerNames = infos.getPlayersName();
            chosenPlayer = mostAdvancedPlayer(builtCards,scores,playerNames);
            List<IDistrict> playerBuiltDistricts = builtCards.get(playerNames.indexOf(chosenPlayer));
            int maxGold = IA.searchForMaxGold(infos);
            int cardNumber = infos.getCardCount().get(playerNames.indexOf(chosenPlayer));
            HeroName supposedHero = IA.guessHero(cardNumber,maxGold,playerBuiltDistricts);
            IA.findMostObviousPlayer(chosenPlayer,supposedHero,infos,maxGold);
            Hero = IA.findChosenHero(supposedHero,infos);
            RealChosenPlayer = playerNames.get(infos.getHeros().indexOf(Hero));
            infos.setChosenPlayer(RealChosenPlayer);
        }





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
