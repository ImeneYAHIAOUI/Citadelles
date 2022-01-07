package fr.unice.polytech.startingpoint.player.IA.Strategies;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.player.IA.*;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.ArrayList;
import java.util.List;

public class AssassinChoice {

    /**
     * this methode regroupes the choices made by the IA after choosing the assassin
     * by default, it targets the most advanced player , it trys to guess its role
     * and then stocks the player with that role in the information object
     * if the player is a random bot, then the choice would be made randomly
     * if it's a builder bot, the method builderBot is called
     */


    public void AssassinChoice(IAToHero infos){
        HeroName supposedHero;
        IHero Hero;
        String RealChosenPlayer = null;
        List<String> playerNames = infos.getPlayersName();
        if(((IA)infos.getCurrentPlayer()).bot.equals(Bots.random)) {
            List<HeroName> heroes = List.of(HeroName.Thief, HeroName.Magician, HeroName.King, HeroName.Bishop, HeroName.Merchant, HeroName.Architect, HeroName.Condottiere);
            supposedHero = heroes.stream().findAny().orElse(null);
        }
        else if(((IA)infos.getCurrentPlayer()).strategyBot.equals(StrategyBot.BUILDER_BOT)){
            supposedHero = builderBotChoice(infos);
        }
        else {
            String chosenPlayer;
            List<List<IDistrict>> builtCards = infos.getBuiltDistricts();
            chosenPlayer = Utils.mostAdvancedPlayer(infos);
            List<IDistrict> playerBuiltDistricts = builtCards.get(playerNames.indexOf(chosenPlayer));
            int gold = infos.getGold().get(playerNames.indexOf(chosenPlayer));
            int cardNumber = infos.getCardCount().get(playerNames.indexOf(chosenPlayer));
            supposedHero = Utils.guessHero(cardNumber, gold, playerBuiltDistricts, HeroName.Assassin, infos.getVisibleHeroes());
        }
        Hero = Utils.findChosenHero(supposedHero, infos);
        if(Hero != null)
            RealChosenPlayer = playerNames.get(infos.getHeros().indexOf(Hero));
        infos.setChosenPlayer(RealChosenPlayer);
    }


    // ==================================================================================
    //                          builder bot strategy methods
    // ==================================================================================
    public HeroName builderBotChoice(IAToHero infos){
        HeroName targetedHero = infos.getCurrentPlayer().getTargetedHero();
        //sometimes the builder bot chooses the assassin with a hero in mind.
        // In that case, this information is stocked in the players objects attribute targetedHero
        if(targetedHero != null && !infos.getVisibleHeroes().contains(targetedHero)){
            return targetedHero;
        }
        if(Utils.currentPlayerIsAhead(infos) && possibleHeroAboutToWin(infos).equals(HeroName.Condottiere)){
            return HeroName.Condottiere;
        }
        if(infos.getCurrentPlayer().getHand().size()>3){
            return HeroName.Magician;
        }
        if(enrichmentRisk(infos) && possibleHeroAboutToWin(infos).equals(HeroName.Thief)){
            return HeroName.Thief;
        }
        return possibleHeroAboutToWin(infos);
    }


    /**
     *this method looks for the most advanced player and guesses its role
     */

    public HeroName possibleHeroAboutToWin(IAToHero infos){
        List<List<IDistrict>> builtCards = infos.getBuiltDistricts();
        List<String> playerNames = infos.getPlayersName();
        String mostAdvancedPlayer = Utils.mostAdvancedPlayer(infos);
        int index = playerNames.indexOf(mostAdvancedPlayer);
        return Utils.guessHero(infos.getCardCount().get(index),infos.getGold().get(index), builtCards.get(index),HeroName.Assassin,infos.getVisibleHeroes());
    }

    /**
     *this methode verifies if there is a big chance that the thief will get a lot of gold
     */
    public boolean enrichmentRisk(IAToHero infos) {
        int maxGold = Utils.searchForMaxGold(infos);
        if (maxGold < 4) {
            return false;
        }
        double numberOfRichPlayers = infos.getScores().stream().filter(score -> score >= 4).count();
        return numberOfRichPlayers > 1;
    }
}
