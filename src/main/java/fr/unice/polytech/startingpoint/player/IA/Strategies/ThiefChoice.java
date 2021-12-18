package fr.unice.polytech.startingpoint.player.IA.Strategies;

import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.player.IA.Bots;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.Utils;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;

import java.util.List;

public class ThiefChoice {


    /**
     * this methode regroupes the choices made by the IA after choosing thief
     * it targets the player with most amount of gold, it trys to guess it's role
     * and then stocks the player with that role in the information object
     */
    public void ThiefChoice(IAToHero infos){
        IHero Hero = null;
        String RealChosenPlayer = null;
        HeroName chosenHero = null;
        String chosenPlayer;
        int CardNumber;
        List<IDistrict> builtDistricts;
        int maxGold;
        maxGold = Utils.searchForMaxGold(infos);
        List<String> players=infos.getPlayersName();
        List<Integer> gold= infos.getGold();
        if(((IA)infos.getCurrentPlayer()).bot.equals(Bots.random)){
            List<HeroName> heroes = List.of(HeroName.Magician, HeroName.King, HeroName.Bishop, HeroName.Merchant, HeroName.Architect, HeroName.Condottiere);
            chosenHero = heroes.stream().findAny().orElse(null);
        }
        else if(!gold.isEmpty()) {
            chosenPlayer = findPlayerWithMaxGold(infos);
            CardNumber = infos.getCardCount().get(players.indexOf(chosenPlayer));
            builtDistricts = infos.getBuiltDistricts().get(players.indexOf(chosenPlayer));
            chosenHero = Utils.guessHero(CardNumber, maxGold, builtDistricts, HeroName.Thief, infos.getVisibleHeroes());
        }
        Hero = Utils.findChosenHero(chosenHero,infos);
        if(Hero != null){
            RealChosenPlayer = players.get(infos.getHeros().indexOf(Hero));
        }
        infos.setChosenPlayer(RealChosenPlayer);
    }


    /**
     * it's always more intresting to target the player with most amount of gold
     * when you're thief
     * this methode finds the player with most amount of gold for the thief to target
     */
    public String findPlayerWithMaxGold(IAToHero infos){
        List<String> players=infos.getPlayersName();
        List<Integer> gold= infos.getGold();
        int maxGold= Utils.searchForMaxGold(infos);
        int maxPlayerId = gold.indexOf(maxGold);
        IPlayer player =infos.getPlayers().get(maxPlayerId);
        gold.set(maxPlayerId,0);
        maxGold= (Utils.searchForMaxGold(infos));
        maxPlayerId = gold.indexOf(maxGold);
        return players.get(maxPlayerId);
    }


}
