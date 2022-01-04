package fr.unice.polytech.startingpoint.player.IA;

import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.heros.IHero;

import java.util.ArrayList;
import java.util.List;

public abstract class Utils {

    public static void func(){
        System.out.println("ok");
    }

    /**
     * searching for the maximum number of built districts per player can be useful for multiple heros
     *like the magician, so this static methode can be used in all the hero Strategies classes in case the information is needed
     */
    static public int searchForMaxNumberOfCards(IAToHero infos){
        List<Integer> cardNumbers = infos.getCardCount();
        int maxCardNumber = cardNumbers.stream().max(Integer::compare).get();
        return maxCardNumber;
    }

    /**
     *same as the searchForMaxNumberOfCards, many heros may need to know the maximum amount of gold
     * possessed by a player in order to make thier choices, this methode gives this information
     */
    public static int searchForMaxGold(IAToHero infos){
        List<Integer> gold = infos.getGold();
        int maxGold =  gold.stream().max(Integer::compare).get();
        return  maxGold;
    }

    /**
     * this methode search for doubles in two hands, it's useful for the magician and for
     * choosing whether to draw or get gold
     */
    public static List<IDistrict> searchForDoubles(List<IDistrict> hand, List<IDistrict> districtList){
        List<IDistrict> doubles = new ArrayList<>();
        for(IDistrict district : hand){
            for(IDistrict district2 : districtList){
                if(district2.getDistrictName().equals(district.getDistrictName()) && !district2.equals(district)){
                    doubles.add(district2);
                    break;
                }
            }
        }
        return doubles;
    }

    /**
     * the thief and the assassin need to choose a hero to kill/steal from,
     * so once they target a player, they need to  guess what hero they chose,
     * for that they will need this information:
     * @param CardNumber number of cards in the players hand
     * @param gold amount of gold
     * @param builtDistricts list of districts built by the player
     * @param guessingHero the role of the player who's guessing
     * @return if the IA manages to guess the targeted players role, this methode
     * returns it, otherwise it returns a null
     */
    public static HeroName guessHero(int CardNumber, int gold, List<IDistrict> builtDistricts, HeroName guessingHero, List<HeroName> visibleHeros){
        if (CardNumber<2 && !visibleHeros.contains(HeroName.Magician)) return HeroName.Magician;
        if(gold > 4 && !visibleHeros.contains(HeroName.Architect)) return HeroName.Architect;
        if(gold < 1 && guessingHero != HeroName.Thief && !visibleHeros.contains(HeroName.Thief)) return HeroName.Thief;
        int green = 0;
        int blue = 0;
        int yellow = 0;
        int red = 0;
        List<HeroName> colorHeroes = List.of(HeroName.Merchant,HeroName.Bishop,HeroName.King,HeroName.Condottiere);
        for (IDistrict district : builtDistricts) {
            switch (district.getColor()) {
                case GREEN -> green++;
                case BLUE -> blue++;
                case YELLOW -> yellow++;
                case RED -> red++;
            }
        }
        List<Integer> colorValues = List.of(green,blue,yellow,red);
        int maxValue = colorValues.stream().max(Integer::compare).orElse(null);
        HeroName guess = colorHeroes.get(colorValues.indexOf(maxValue));
        if (maxValue>0 && !visibleHeros.contains(guess)) return guess;
        else return randomGuess(guessingHero,visibleHeros);
    }
    public static HeroName randomGuess(HeroName guessingHero,List<HeroName> visibleHeroes){
        //on ne met pas l'assassin dans cette liste car il ne pas choisir lui même et on le voleur ne peut pas le choisir non plus
        List<HeroName> allHeros = new ArrayList<>();
        allHeros.add(HeroName.Merchant);
        allHeros.add(HeroName.Bishop);
        allHeros.add(HeroName.Thief);
        allHeros.add(HeroName.King);
        allHeros.add(HeroName.Condottiere);
        allHeros.add(HeroName.Magician);
        allHeros.add(HeroName.Architect);
        //on enlève les heroes visibles
        allHeros.removeAll(visibleHeroes);
        allHeros.remove(guessingHero);
        //il faut s'assurer aussi que le voleur n'arriive pas a choisir lui même
        return allHeros.stream().findAny().orElse(null);
    }
    /**
     * once guessHero returns the guessed hero (or null), this methode
     * is responsible for finding the Hero object with the guessed hero name
     * @param chosenHero the guessed hero name, if null, we take a random hero name
     * @param infos information classe, used to find the hero with the guessed role name
     * @return if the guessed hero has been chosen by a player, the methode return the hero
     * else it returns null
     */
    public static IHero findChosenHero(HeroName chosenHero, IAToHero infos){

        IHero Hero = null;
        for (IHero hero : infos.getHeros()){

            if (hero.getName() == chosenHero) Hero = hero;
        }


        return Hero;
    }

}
