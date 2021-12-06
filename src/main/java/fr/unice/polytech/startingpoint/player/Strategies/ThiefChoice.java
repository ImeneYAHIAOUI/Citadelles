package fr.unice.polytech.startingpoint.player.Strategies;

import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.heros.Hero;
import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.Information;

import java.util.ArrayList;
import java.util.List;

public class ThiefChoice {


    public void ThiefChoice1(Information infos){
        IHero Hero = null;
        String RealChosenPlayer = null;
        HeroName chosenHero = null;
        String chosenPlayer;
        int CardNumber;
        List<IDistrict> builtDistricts;
        int maxGold = IA.searchForMaxGold(infos);
        List<String> players=infos.getPlayersName();
        List<String> clonePlayers = new ArrayList<>(players);
        List<Integer> gold= infos.getGold();

        if(!gold.isEmpty()){
            chosenPlayer = findPlayerWithMaxGold(infos);
            CardNumber = infos.getCardCount().get(players.indexOf(chosenPlayer));
            builtDistricts = infos.getBuiltDistricts().get(players.indexOf(chosenPlayer));
            chosenHero = IA.guessHero(CardNumber,maxGold,builtDistricts);
            findMostObviousPlayer(chosenPlayer,chosenHero,infos,maxGold);
            Hero = IA.findChosenHero(chosenHero,infos);
            RealChosenPlayer = players.get(infos.getHeros().indexOf(Hero));
        }

        infos.setChosenPlayer(RealChosenPlayer);
    }
    public void ThiefChoice2(Information infos){
        IHero Hero = null;
        String RealChosenPlayer = null;
        HeroName chosenHero = null;
        String chosenPlayer;
        int CardNumber;
        List<IDistrict> builtDistricts;
        int maxGold;
        maxGold = IA.searchForMaxGold(infos);
        List<String> players=infos.getPlayersName();
        List<Integer> gold= infos.getGold();

        if(!gold.isEmpty()){
            chosenPlayer = findPlayerWithMaxGold(infos);
            CardNumber = infos.getCardCount().get(players.indexOf(chosenPlayer));
            builtDistricts = infos.getBuiltDistricts().get(players.indexOf(chosenPlayer));
            chosenHero = IA.guessHero(CardNumber,maxGold,builtDistricts);
            Hero = IA.findChosenHero(chosenHero,infos);
            RealChosenPlayer = players.get(infos.getHeros().indexOf(Hero));
        }

        infos.setChosenPlayer(RealChosenPlayer);
    }
    public String findPlayerWithMaxGold(Information infos){
        List<String> players=infos.getPlayersName();
        List<Integer> gold= infos.getGold();
        int maxGold= IA.searchForMaxGold(infos);
        int maxPlayerId = gold.indexOf(maxGold);
        IPlayer player =infos.getPlayers().get(maxPlayerId);
        while(player.getIsAssigned() && player.getRole().getName() == HeroName.Assassin){
            gold.set(maxPlayerId,0);
            maxGold= IA.searchForMaxGold(infos);
            maxPlayerId = gold.indexOf(maxGold);
            player =infos.getPlayers().get(maxPlayerId);
        }
        return players.get(maxPlayerId);

    }
    public void findMostObviousPlayer(String chosenPlayer,HeroName chosenHero, Information infos, int maxGold){
        while (chosenHero == null && maxGold >0){
            int playerIndex = infos.getPlayersName().indexOf(chosenPlayer);
            infos.getGold().set(playerIndex,0);
            maxGold= IA.searchForMaxGold(infos);
            chosenPlayer=findPlayerWithMaxGold(infos);
            playerIndex = infos.getPlayersName().indexOf(chosenPlayer);
            int cardNumber = infos.getCardCount().get(playerIndex);
            List<IDistrict> builtDistricts = infos.getBuiltDistricts().get(playerIndex);
            chosenHero = IA.guessHero(cardNumber,maxGold,builtDistricts);
        }
    }

}
