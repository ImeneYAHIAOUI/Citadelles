package fr.unice.polytech.startingpoint.player;
import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.cards.Treasure;
import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.player.Strategies.*;


import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class IA extends Player{
    public String bot;
    public Predicate<IDistrict> isAffordable = district -> district.getPrice()<=gold ;
    public static BiFunction<Integer ,Integer,Integer > calculScore=(score, nbBuiltCard)->  100*score+10*nbBuiltCard;
    static Predicate<IDistrict> identicalCard(IDistrict district) {
        Predicate<IDistrict> identic = d -> d.getDistrictName().equals(district.getDistrictName());
        return identic;
    }

    public List<HerosChoice> thoughtPathList;
    /**
     *
     * @param playerName the IA object is constructed the same way as a Player object,
     *                   so we also only need the name of the player here.
     */

    public IA(String playerName){
        super(playerName);
        thoughtPathList = new ArrayList<>();
    }

    public void setBot(String bot) {
        this.bot = bot;
    }

    /**
     * this method chooses the hero for the bot based on the information it's given
     * it's random based for now
     */
    @Override
    public void chooseHero(HeroDeck heroes, Random rand, List<IPlayer> players) { // LEVEL 1
        /*
        if (roleIndex < 0 || roleIndex> heroes.size()){
            throw new RuntimeException("Invalide value");
        }
        this.setRole(heroes.get(roleIndex));
        heroes.remove(role);
        */

        IHero hero = null;
        this.thoughtPathList = new ArrayList<HerosChoice>();
        HeroDecisionStandard heroDecisionStandard = new HeroDecisionStandard();
        hero = heroDecisionStandard.heroDecision(this,players,heroes,this.thoughtPathList,rand);
        this.setRole(hero);
    }


    @Override
    public void activateHero(List<IPlayer> players, DistrictDeck districtDeck, Treasure treasure,Information info) {
        switch (role.getName()){
            case Merchant -> {
                info.setInformationForMerchant(this,treasure);
                role.doAction(info);
            }
            case King-> {
                        info.setInformationForKing(this,players,treasure);
                        role.doAction(info);
                    }
            case Magician ->
                    {
                info.setInformationForMagician(players,this, districtDeck);
<<<<<<< Updated upstream
                MagicianStrategies choice = new MagicianStrategies();
                choice.magicienChoice1(info,isAffordable);
=======
                MagicianStrategies.magicienChoice1(info,isAffordable);

>>>>>>> Stashed changes
                role.doAction(info);
                }
            case Assassin -> {
                info.setInformationForAssassin(players,this,districtDeck);
                AssassinChoice choice = new AssassinChoice();
                choice.AssassinChoice2(info);
                role.doAction(info);
            }
            case Thief ->  {
                info.setInformationForThief(this,players,districtDeck);
                ThiefChoice choice =new ThiefChoice();
                choice.ThiefChoice2(info);
                role.doAction(info);
            }
            case Bishop -> {
                info.setInformationForBishop(this,treasure);
                role.doAction(info);
            }

            }
        }



<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes


    /**
     * this method chooses what move to make for the bot based on the information it's given
     * it's random based for now
     */

    @Override
    public void doAction(Treasure treasure,Information info) {
        if(hand.stream().anyMatch(isAffordable) ){
            List<IDistrict> AffordableDistricts =  hand.stream().filter(isAffordable).collect(Collectors.toList());
            IDistrict chosenDistrict = AffordableDistricts.get(0);
            while(AffordableDistricts.size()>0 && builtDistricts.stream().anyMatch(identicalCard(chosenDistrict))){
                AffordableDistricts.remove(chosenDistrict);
                if(AffordableDistricts.size()>0) chosenDistrict = AffordableDistricts.get(0);
            }
            if(builtDistricts.stream().noneMatch(identicalCard(chosenDistrict))){
                buildDistrict(chosenDistrict);
                treasure.addToTreasure(chosenDistrict.getPrice());
                info.addBuiltDistrict(chosenDistrict);
            }
        }
    }

    @Override
    public void drawOrGetPieces(DistrictDeck deck, Treasure treasure,Information info){
        if(hand.size()>0){
            if( hand.stream().noneMatch(isAffordable)){
                NoAffordableCardsChoice(deck,treasure,info);
            }
            else{
                ChoiceBasedOnCardNumbers(deck,treasure,info);
            }
        }
        else{
            draw(deck,info,1);
        }
        /*DrawOrGetGoldStrategies choice =new DrawOrGetGoldStrategies();
        choice.drawOrGetPieces1(deck, treasure,info,isAffordable);*/
    }
    public static int searchForMaxNumberOfCards(Information infos){
        List<Integer> cardNumbers = infos.getCardCount();
        int maxCardNumber = cardNumbers.stream().max(Integer::compare).get();
        return maxCardNumber;
    }
    public static int searchForMaxGold(Information infos){
        List<Integer> gold = infos.getGold();
        int maxGold =  gold.stream().max(Integer::compare).get();
        return  maxGold;
    }
    public void draw(DistrictDeck deck, Information info, int num){
        IPlayer player = info.getCurrentPlayer();
        player.getDistrict(deck.giveDistrict(num));
        info.setDraw();
    }
    public void getGold(Treasure treasure, Information info, int amount){
        IPlayer player = info.getCurrentPlayer();
        int giveGold=treasure.removeGold(amount);
        player.addGold(giveGold);
        info.setGetGold();
    }
    public static List<IDistrict> searchForDoubles(List<IDistrict> hand, List<IDistrict> districtList){
        List<IDistrict> doubles = new ArrayList<>();
        hand.forEach(district -> {
            if(districtList.stream().anyMatch(d -> d.getDistrictName().equals(district.getDistrictName())
                    && ! d.equals(district) && doubles.stream().noneMatch(d2 -> d2.getDistrictName().equals(district.getDistrictName())))){
                doubles.add(district);
            }
        });
        return doubles;
    }
    public void NoAffordableCardsChoice(DistrictDeck deck,Treasure treasure,Information info){
        List<IDistrict> hand = info.getCurrentPlayer().getHand();
        int gold = info.getCurrentPlayer().getGold();
        if(hand.stream().anyMatch(district -> district.getPrice()<=gold+2 )) {
            getGold(treasure,info,2);
        }
        else{
            draw(deck,info,1);
        }
    }
    public  void ChoiceBasedOnCardNumbers(DistrictDeck deck,Treasure treasure,Information info){
        List<IDistrict> hand = info.getCurrentPlayer().getHand();
        if(hand.size()<3) {
            draw(deck,info,1);
        }
        else{
            getGold(treasure,info,2);
        }
    }

    public static HeroName guessHero(int CardNumber,int gold,List<IDistrict> builtDistricts){
        if (CardNumber<2) return HeroName.Magician;
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
        if (maxValue>0) return colorHeroes.get(colorValues.indexOf(maxValue));
        if(gold > 4) return HeroName.Architect;
        if(gold < 1 ) return HeroName.Thief;
        else return null;

    }



    public static IHero findChosenHero(HeroName chosenHero,Information infos){
        IHero Hero = null;
        if (chosenHero != null){
            for (IHero hero : infos.getHeros()){

                if (hero.getName() == chosenHero) Hero = hero;
            }
        }if(Hero == null){;
            Hero = infos.getHeros().stream().findAny().orElse(null);
        }

        return Hero;
    }

    @Override
    public void addBonusScore(int val){
        this.score += val;
    }
}
