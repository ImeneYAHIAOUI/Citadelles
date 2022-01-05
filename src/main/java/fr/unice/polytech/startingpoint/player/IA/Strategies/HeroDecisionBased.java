package fr.unice.polytech.startingpoint.player.IA.Strategies;

/*
LES BATISSEURS
Ce type de joueurs va prendre régulièrement les personnages générant le plus de ressources et jouer régulièrement
- Marchand + quartiers verts
- Architecte
- JE prends toujours le roi avec des quartiers jaune, en prenant l'architecte/marchand quand le roi n'est plus disponible
A tedance à battre les opportunistes quand les aggressifs sont peu présents
Pesonnages préférés : marhcand, architecte, roi
 */

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.player.CircularList;
import fr.unice.polytech.startingpoint.player.IA.HerosChoice;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;
import fr.unice.polytech.startingpoint.player.IA.Utils;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.List;

public class HeroDecisionBased {
    /**
     * https://www.trictrac.net/forum/sujet/citadelles-charte-citadelles-de-base
     */

    private IHero hero;

    /**
     * @param ia
     * @param heroes
     * @param thoughtPath
     */

    public IHero heroChoice(IPlayer ia, HeroDeck heroes, List<HerosChoice> thoughtPath, List<IPlayer> players) {
        int numberOfDistrict = this.howManyDistrictBuild(players,ia);
        IHero hero = null;
        ia.setTargetedHero(null);
        ia.setChosenPlayer(null);
        thoughtPath.add(HerosChoice.IChooseAHero);
        if(numberOfDistrict > 6 || ia.getBuiltDistricts().size() > 6){
            thoughtPath.add(HerosChoice.WithLastRoundStrategy);
            IAToHero information = new IAToHero();
            information.setInformationForAssassinOrThief(players,ia,null);
            hero = lastRoundStrategy(heroes,players,information,thoughtPath); //this.lastRoundStrategy();
        }
        else if(numberOfDistrict < 6){
            thoughtPath.add(HerosChoice.WithNormalStrategy);
            hero = this.normalStrategy(ia, heroes, thoughtPath);
        }else {
            thoughtPath.add(HerosChoice.WithPenultimateRoundStrategy);
            hero = this.penultimateRoundStrategy(players, ia, heroes, thoughtPath);
        }

        return hero;
    }

    // ===============================================================================================================
    //
    //                                              NORMAL STRATEGY
    //
    // ===============================================================================================================

    /**
     *
     * @param ia
     * @param heroes
     * @param thoughtPath
     * @return
     */
    private IHero normalStrategy(IPlayer ia, HeroDeck heroes, List<HerosChoice> thoughtPath){
        int yellow = 0;
        int green = 0;

        //Can I build more than one district?
        boolean architect = this.architectCanBuy2OrMoreCards(ia);

        // If yes, archi,
        if (architect == true && this.heroPresentInTheList(heroes, HeroName.Architect)) {
            thoughtPath.add(HerosChoice.ICanBuildSeveralDistrict);
            thoughtPath.add(HerosChoice.SoIChooseTheArchitect);
            this.hero = heroes.chooseHero(HeroName.Architect); // END
        } else {
            // Else, choice between king, merchant or random
            yellow = this.colorCount(Color.YELLOW, ia.getBuiltDistricts());
            green = this.colorCount(Color.GREEN, ia.getBuiltDistricts());

            if (this.isKingChoice(green, yellow, heroes)) { // I choose the king
                thoughtPath.add(HerosChoice.INeedGold);
                thoughtPath.add(HerosChoice.SoIChooseTheKing);
                this.hero = heroes.chooseHero(HeroName.King); // END
            } else if (this.isMerchantChoice(green, yellow, heroes)) { // I choose the merchant
                thoughtPath.add(HerosChoice.INeedGold);
                thoughtPath.add(HerosChoice.SoIChooseTheMerchant);
                this.hero = heroes.chooseHero(HeroName.Merchant); // END
            } else if (yellow == green && (this.heroPresentInTheList(heroes, HeroName.Merchant) || this.heroPresentInTheList(heroes, HeroName.King))) {
                if (this.heroPresentInTheList(heroes, HeroName.King)) {
                    thoughtPath.add(HerosChoice.INeedGold);
                    thoughtPath.add(HerosChoice.SoIChooseTheKing);
                    this.hero = heroes.chooseHero(HeroName.King); // END
                } else {
                    thoughtPath.add(HerosChoice.INeedGold);
                    thoughtPath.add(HerosChoice.SoIChooseTheMerchant);
                    this.hero = heroes.chooseHero(HeroName.Merchant); // END
                }
            } else { // I choose a random hero
                thoughtPath.add(HerosChoice.ThereAreNoMoreHeroesDefence);
                thoughtPath.add(HerosChoice.SoIChooseAHeroAtRandom);
                this.hero = heroes.randomChoice(); // END
            }
        }

        return this.hero;

    }

    // ===============================================================================================================
    //
    //                                              PENULTIMATE ROUND STRATEGY
    //
    // ===============================================================================================================

    /**
     *
     * @param players
     * @param ia
     * @param heroes
     * @param thoughtPath
     * @return
     */
    private IHero penultimateRoundStrategy(List<IPlayer> players, IPlayer ia, HeroDeck heroes ,List<HerosChoice> thoughtPath){
        /*
        Un des joueurs est sur le point de construire son avant-dernier quartier : ( 5/7 ou 6/8 )
        - Je dois prendre (dans l’ordre) le Roi, l’Assassin, le Condottiere et l’Evêque.
        */

        if(heroPresentInTheList(heroes, HeroName.King)){
            thoughtPath.add(HerosChoice.INeedGold);
            thoughtPath.add(HerosChoice.SoIChooseTheKing);
            this.hero = heroes.chooseHero(HeroName.King); // END
        }
        else if(heroPresentInTheList(heroes, HeroName.Assassin)){
            thoughtPath.add(HerosChoice.IDecideToAttack);
            thoughtPath.add(HerosChoice.SoIChooseTheAssassin);
            this.hero = heroes.chooseHero(HeroName.Assassin); // END
        }
        else if(heroPresentInTheList(heroes, HeroName.Bishop)){
            thoughtPath.add(HerosChoice.INeedGold);
            thoughtPath.add(HerosChoice.SoIchooseTheBishop);
            this.hero = heroes.chooseHero(HeroName.Bishop); // END
        }
        else if(heroPresentInTheList(heroes, HeroName.Condottiere)){
            thoughtPath.add(HerosChoice.INeedGold);
            thoughtPath.add(HerosChoice.SoIchooseTheCondottiere);
            this.hero = heroes.chooseHero(HeroName.Condottiere); // END
        }else{
            // I choose a random hero
            thoughtPath.add(HerosChoice.ThereAreNoMoreHeroesDefence);
            thoughtPath.add(HerosChoice.SoIChooseAHeroAtRandom);
            this.hero = heroes.randomChoice(); // END
        }
        return hero;
    }

    // ===============================================================================================================
    //
    //                                              LAST ROUND STRATEGY
    //
    // ===============================================================================================================

    private IHero lastRoundStrategy(HeroDeck heroes,List<IPlayer> players,IAToHero information,List<HerosChoice> thoughtPath){
        if(Utils.currentPlayerIsAhead(information)){
            thoughtPath.add(HerosChoice.ImAboutToWin);
            return MostAheadPlayerStrategy(heroes,thoughtPath);
        }
        thoughtPath.add(HerosChoice.SomeoneIsAboutToWin);
        String mostAdvancedPlayerName = Utils.mostAdvancedPlayer(information);

        IPlayer mostAdvancedPlayer = players.stream().filter(p -> p.getName().equals(mostAdvancedPlayerName)).findFirst().orElse(null);
        int mostAdvancedPlayerPosition = players.indexOf(mostAdvancedPlayer);
        if(heroPresentInTheList(heroes,HeroName.Bishop) &&  heroPresentInTheList(heroes,HeroName.Assassin) && heroPresentInTheList(heroes,HeroName.Condottiere)) {
            thoughtPath.add(HerosChoice.AllUsefulHeroesAreAvailable);
            return firstCaseStrategy(players,information, thoughtPath,heroes,mostAdvancedPlayerPosition);
        }
         if (!heroPresentInTheList(heroes,HeroName.Bishop) ){
            thoughtPath.add(HerosChoice.BishopIsNotAvailable);
            return SecondCaseStrategy(players,information, thoughtPath,heroes,mostAdvancedPlayerPosition);
        }
        if(!heroPresentInTheList(heroes,HeroName.Assassin)){
            thoughtPath.add(HerosChoice.AssassinIsNotAvailable);
            return fourthCaseStrategy(players,information,thoughtPath,heroes,mostAdvancedPlayerPosition);
        }
        if(! heroPresentInTheList(heroes,HeroName.Condottiere)){
            thoughtPath.add(HerosChoice.CondottiereIsNotAvailable);
            return thirdCaseStrategy(players,information, thoughtPath,heroes,mostAdvancedPlayerPosition);
        }
        if(! heroPresentInTheList(heroes,HeroName.Assassin)){
            thoughtPath.add(HerosChoice.AssassinIsNotAvailable);
            return fourthCaseStrategy(players,information,thoughtPath,heroes,mostAdvancedPlayerPosition);
        }
        thoughtPath.add(HerosChoice.AllUsefulHeroesAreNotAvailable);
        thoughtPath.add(HerosChoice.SoIChooseAHeroAtRandom);
        return heroes.randomChoice();
    }

    private IHero MostAheadPlayerStrategy(HeroDeck heroes,List<HerosChoice> thoughtPath) {
        thoughtPath.add(HerosChoice.IDecideToProtectMyself);
        if (heroPresentInTheList(heroes, HeroName.Assassin)) {
            thoughtPath.add(HerosChoice.SoIChooseTheAssassin);
            return heroes.chooseHero(HeroName.Assassin);
        }
        if (heroPresentInTheList(heroes,HeroName.Bishop)) {
            thoughtPath.add(HerosChoice.SoIchooseTheBishop);
            return heroes.chooseHero(HeroName.Bishop);
        }
        if (heroPresentInTheList(heroes,HeroName.Condottiere)) {
            thoughtPath.add(HerosChoice.SoIchooseTheCondottiere);
            return heroes.chooseHero(HeroName.Condottiere);
        }
        thoughtPath.add(HerosChoice.ThereAreNoMoreHeroesDefence);
        thoughtPath.add(HerosChoice.SoIChooseAHeroAtRandom);
        return heroes.randomChoice();
    }
    private IHero firstCaseStrategy(List<IPlayer> players, IAToHero information,List<HerosChoice> thoughtPath,HeroDeck heroes,int mostAdvancedPlayerPosition) {
        IPlayer currentPlayer = information.getCurrentPlayer();
        int currentPlayerPosition =players.indexOf(currentPlayer);
        if (mostAdvancedPlayerPosition < currentPlayerPosition) {
            thoughtPath.add(HerosChoice.PossibleWinnerIsBeforeMe);
            thoughtPath.add(HerosChoice.SoIChooseAHeroAtRandom);
            return heroes.randomChoice();
        }
        thoughtPath.add(HerosChoice.PossibleWinnerIsAfterMe);
        thoughtPath.add(HerosChoice.IDecideToBlockWinner);
        if (currentPlayerPosition %2==0) {
            if (heroPresentInTheList(heroes, HeroName.Condottiere)) {
                thoughtPath.add(HerosChoice.SoIchooseTheCondottiere);
                return heroes.chooseHero(HeroName.Condottiere);
            }
            thoughtPath.add(HerosChoice.AssassinIsNotAvailable);
            thoughtPath.add(HerosChoice.SoIChooseAHeroAtRandom);
            return heroes.randomChoice();
        } else {
            if (heroPresentInTheList(heroes, HeroName.Assassin)) {
                thoughtPath.add(HerosChoice.SoIChooseTheAssassin);
                information.getCurrentPlayer().setTargetedHero(HeroName.Bishop);
                return heroes.chooseHero(HeroName.Assassin);
            } else {
                thoughtPath.add(HerosChoice.AssassinIsNotAvailable);
                thoughtPath.add(HerosChoice.SoIChooseAHeroAtRandom);
            }
        } return heroes.randomChoice();
    }
    private IHero SecondCaseStrategy(List<IPlayer> players, IAToHero information,List<HerosChoice> thoughtPath,HeroDeck heroes,int mostAdvancedPlayerPosition){
        IPlayer currentPlayer = information.getCurrentPlayer();
        int currentPlayerPosition = players.indexOf(currentPlayer);
        if (mostAdvancedPlayerPosition<currentPlayerPosition) {
            thoughtPath.add(HerosChoice.PossibleWinnerIsBeforeMe);
            thoughtPath.add(HerosChoice.SoIChooseAHeroAtRandom);
            return heroes.randomChoice();
        }
        thoughtPath.add(HerosChoice.PossibleWinnerIsAfterMe);
        thoughtPath.add(HerosChoice.IDecideToBlockWinner);
        if(currentPlayerPosition%2==0){
            if( heroPresentInTheList(heroes,HeroName.Assassin)) {
                thoughtPath.add(HerosChoice.SoIChooseTheAssassin);
                return heroes.chooseHero(HeroName.Assassin);
            }
            thoughtPath.add(HerosChoice.AssassinIsNotAvailable);
            thoughtPath.add(HerosChoice.SoIChooseAHeroAtRandom);
            return heroes.randomChoice();
        }
        else  {
            if(heroPresentInTheList(heroes,HeroName.Condottiere)) {
                thoughtPath.add(HerosChoice.SoIchooseTheCondottiere);
                return heroes.chooseHero(HeroName.Condottiere);
            }else{
                thoughtPath.add(HerosChoice.CondottiereIsNotAvailable);
                thoughtPath.add(HerosChoice.SoIChooseAHeroAtRandom);
                return heroes.randomChoice();
            }
        }
    }

    private IHero thirdCaseStrategy(List<IPlayer> players, IAToHero information,List<HerosChoice> thoughtPath,HeroDeck heroes,int mostAdvancedPlayerPosition){
        IPlayer currentPlayer = information.getCurrentPlayer();
        int currentPlayerPosition = players.indexOf(currentPlayer);
        if (mostAdvancedPlayerPosition<currentPlayerPosition) {
            thoughtPath.add(HerosChoice.PossibleWinnerIsBeforeMe);
            thoughtPath.add(HerosChoice.SoIChooseAHeroAtRandom);
            return heroes.randomChoice();
        }
        thoughtPath.add(HerosChoice.PossibleWinnerIsAfterMe);
        thoughtPath.add(HerosChoice.IDecideToBlockWinner);
        if(currentPlayerPosition%2 == 0){
            if( heroPresentInTheList(heroes,HeroName.Assassin)) {
                thoughtPath.add(HerosChoice.SoIChooseTheAssassin);
                int cardNumber = players.get(1).getHand().size();
                if (cardNumber > 3) {
                    information.getCurrentPlayer().setTargetedHero(HeroName.Magician);
                }
                return heroes.chooseHero(HeroName.Assassin);
            }
            thoughtPath.add(HerosChoice.AssassinIsNotAvailable);
            thoughtPath.add(HerosChoice.SoIChooseAHeroAtRandom);
            return heroes.randomChoice();
        }
        else {
            if(heroPresentInTheList(heroes,HeroName.Magician)) {
                thoughtPath.add(HerosChoice.SoIChooseTheMagician);
                information.getCurrentPlayer().setChosenPlayer(players.get(mostAdvancedPlayerPosition));
                return heroes.chooseHero(HeroName.Magician);
            }else{
                thoughtPath.add(HerosChoice.MagicianIsNotAvailable);
                thoughtPath.add(HerosChoice.SoIChooseAHeroAtRandom);
                return heroes.randomChoice();
            }
        }
    }

    private IHero fourthCaseStrategy(List<IPlayer> players,IAToHero information,List<HerosChoice> thoughtPath,HeroDeck heroes,int mostAdvancedPlayerPosition){
        IPlayer currentPlayer = information.getCurrentPlayer();
        int currentPlayerPosition = players.indexOf(currentPlayer);
        if (mostAdvancedPlayerPosition<currentPlayerPosition) {
            thoughtPath.add(HerosChoice.PossibleWinnerIsBeforeMe);
            thoughtPath.add(HerosChoice.SoIChooseAHeroAtRandom);
            return heroes.randomChoice();
        }
        thoughtPath.add(HerosChoice.PossibleWinnerIsAfterMe);
        thoughtPath.add(HerosChoice.IDecideToBlockWinner);
        if(currentPlayerPosition%2 == 0){
            if(heroPresentInTheList(heroes,HeroName.Condottiere)){
                thoughtPath.add(HerosChoice.SoIchooseTheCondottiere);
                information.getCurrentPlayer().setChosenPlayer(players.get(mostAdvancedPlayerPosition));
                return heroes.chooseHero(HeroName.Condottiere);
            }else{
                thoughtPath.add(HerosChoice.CondottiereIsNotAvailable);
                thoughtPath.add(HerosChoice.SoIChooseAHeroAtRandom);
                return heroes.randomChoice();
            }
        }
        else{
            if(heroPresentInTheList(heroes,HeroName.Bishop)) {
                thoughtPath.add(HerosChoice.SoIchooseTheBishop);
                return heroes.chooseHero(HeroName.Bishop);
            }else{
                thoughtPath.add(HerosChoice.BishopIsNotAvailable);
                thoughtPath.add(HerosChoice.SoIChooseAHeroAtRandom);
                return heroes.randomChoice();

            }
        }
    }


    // ===============================================================================================================
    //
    //                                                   FUNCTIONS
    //
    // ===============================================================================================================

    /**
     *
     * @param players
     * @return
     */
    private int howManyDistrictBuild(List<IPlayer> players, IPlayer ia){
        int count = 0;
        int memo = 0;

        for(int i = 0; i < players.size(); i++){
            if(!players.get(i).equals(ia))
                memo = players.get(i).getBuiltDistricts().size();
            if(memo > count)
                count = memo;
        }

        return count;
    }



    /**
     *
     * @param player
     * @return
     */
    private boolean architectCanBuy2OrMoreCards(IPlayer player){
        boolean response = false;

        ArchitectChoice architectChoice = new ArchitectChoice();
        List<IDistrict> testList = architectChoice.choiceDistrictsAtBuild(player);

        if(testList.size() >= 2) {
            int count = 0;

            for(int i = 0; i < testList.size();i++){
                count += testList.get(i).getPrice();
            }

            if(count >= 6)
                response = true;
        }

        return response;
    }

    /**
     * Check that a hero is present in the deck
     * @param heroes
     * @param heroName
     * @return
     */
    private boolean heroPresentInTheList(HeroDeck heroes, HeroName heroName){
        return heroes.stream().map(hero -> hero.getName()).anyMatch(name -> name == heroName);
    }

    /**
     * Color count
     * @param color
     * @param districtDeck
     * @return
     */
    private int colorCount(Color color, List<IDistrict> districtDeck){
        int count = 0;

        for(int i = 0; i < districtDeck.size(); i++){
            if(districtDeck.get(i).getColor() == color){
                count ++;
            }
        }

        return count;
    }

    /**
     *
     * @param green
     * @param yellow
     * @param heroes
     * @return
     */
    private boolean isKingChoice(int green, int yellow, HeroDeck heroes){
        boolean reponse = false;

        // If yellow bigger than green, and the king is present
        if (yellow > green && this.heroPresentInTheList(heroes, HeroName.King))
            reponse = true;

        // If green and bigger than yellow, and we don't have the merchant but the kings
        if(!this.heroPresentInTheList(heroes, HeroName.Merchant) && this.heroPresentInTheList(heroes, HeroName.King) && yellow < green)
            reponse = true;

        return reponse;
    }

    /**
     *
     * @param green
     * @param yellow
     * @param heroes
     * @return
     */
    private boolean isMerchantChoice(int green, int yellow, HeroDeck heroes){
        boolean reponse = false;

        // If green bigger than yellow, and the merchant is present
        if (yellow < green && this.heroPresentInTheList(heroes, HeroName.Merchant))
            reponse = true;

        // If yellow and bigger than green, and we don't have the king but the merchant
        if(!this.heroPresentInTheList(heroes, HeroName.King) && this.heroPresentInTheList(heroes, HeroName.Merchant) && yellow > green)
            reponse = true;

        return reponse;
    }
}