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
import fr.unice.polytech.startingpoint.cards.*;

import java.util.ArrayList;
import java.util.List;




public class IA extends Player implements IPlayer {
    public Predicate<IDistrict> isAffordable = district -> district.getPrice()<=gold ;
    public static BiFunction<Integer ,Integer,Integer > calculScore=(score, nbBuiltCard)->  100*score+10*nbBuiltCard;
    static Predicate<IDistrict> identicalCard(IDistrict district) {
        Predicate<IDistrict> identic = d -> d.getDistrictName().equals(district.getDistrictName());
        return identic;

    }
    public infoaction info=new infoaction() ;
    Manufacture manufacture=new Manufacture();
    MiracleCourt miracleCourt=new MiracleCourt();
    Laboratory laboratory=new Laboratory();

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


    /**
     * this method chooses the hero for the bot based on the information it's given
     * it's random based for now
     */
    @Override
    public void chooseHero(HeroDeck heroes, Random rand, List<IPlayer> players) { // LEVEL 1

        IHero hero = null;
        this.thoughtPathList = new ArrayList<HerosChoice>();
        HeroDecisionStandard heroDecisionStandard = new HeroDecisionStandard();
        hero = heroDecisionStandard.heroDecision(this,players,heroes,this.thoughtPathList,rand);
        this.setRole(hero);
    }


    @Override
    public void activateHero(List<IPlayer> players, DistrictDeck districtDeck, Treasure treasure, Information info ) {
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
                MagicianStrategies choice = new MagicianStrategies();
                choice.magicienChoice1(info,isAffordable);

                role.doAction(info);
                }
            case Assassin -> {
                info.setInformationForAssassin(players,this,districtDeck);
                AssassinChoice choice = new AssassinChoice();
                choice.AssassinChoice1(info);
                role.doAction(info);
            }
            case Thief ->  {
                info.setInformationForThief(this,players,districtDeck);
                ThiefChoice choice =new ThiefChoice();
                choice.ThiefChoice1(info);
                role.doAction(info);
            }
            case Bishop -> {
                info.setInformationForBishop(this,treasure);
                role.doAction(info);
            }

            }
        }



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

        DrawOrGetGoldStrategies choice =new DrawOrGetGoldStrategies();
        choice.drawOrGetPieces1(deck, treasure,info,isAffordable);
    }
    static public int searchForMaxNumberOfCards(Information infos){
        List<Integer> cardNumbers = infos.getCardCount();
        int maxCardNumber = cardNumbers.stream().max(Integer::compare).get();
        return maxCardNumber;
    }

    public static int searchForMaxGold(Information infos){
        List<Integer> gold = infos.getGold();
        int maxGold =  gold.stream().max(Integer::compare).get();
        return  maxGold;
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
    }    @Override
    public void applyLibrary(IA player, List<IDistrict> cards) {
        IDistrict wonder=player.getBuiltDistricts().stream()
                .filter(district -> district.isWonder() && district.getDistrictName()== DistrictName.LIBRARY)
                .findAny().orElse(null);
        if(wonder!=null){
            this.info.setplayer(player);
            this.info.setChosenCards(cards);
            ((IWonder )wonder).doAction(this.info);
        }
    }


    @Override
    public void applyDongeon() {

    }

    @Override
    public void applyLaboratory(IA player) {
        IDistrict wonder = player.getBuiltDistricts().stream()
                .filter(district -> district.isWonder() && district.getDistrictName() == DistrictName.LIBRARY)
                .findAny().orElse(null);
        IDistrict expensive  = player.getBuiltDistricts().stream()
                .filter(district -> district.getPrice()>4)
                .findAny().orElse(null);
        if (wonder != null) {
            if(player.getHand()!=null)
            info.setplayer(player);
            info.setDistrictremove((District) expensive);
            laboratory.doAction(info);



                }

            }





        @Override
        public void applyManufacture (IA player, DistrictDeck deck, Treasure tresor){
            int i;
            this.info.setTreasure(tresor);
            this.info.setplayer(player);
            this.info.setdistrictdeck(deck);
            IDistrict wonder = player.getBuiltDistricts().stream()
                    .filter(district -> district.getDistrictName() == DistrictName.MANUFACTURE)
                    .findAny().orElse(null);
            if (wonder != null & player.getGold() >= 3) {
                int s = 0;
                int c = 0;
                for (i = 0; i < player.getHand().size(); i++) {
                    if (player.getHand().get(i).getPrice() >= 3) {
                        s = s + 1;
                    } else c = c + 1;
                }
                if (s > c || player.getHand().size() == 0) {
                    manufacture.doAction(this.info);
                }

            }
        }

    @Override
    public void applyMiracleCourt(IA player) {
        List<Color> color = new ArrayList<>();
        List<Color> colorList = List.of(new Color[]{Color.PURPLE, Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW});
        IDistrict wonder = player.getBuiltDistricts().stream()
                .filter(district -> district.isWonder() && district.getDistrictName() == DistrictName.LACOURDESMIRACLES).findAny().orElse(null);
        if (wonder != null) {
            int val = 0;

            if (player.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.YELLOW)) {
                val++;
                color.add(Color.YELLOW);
            }
            if (player.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.RED)) {
                val++;
                color.add(Color.RED);
            }
            if (player.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.BLUE)) {
                val++;
                color.add(Color.BLUE);
            }
            if (player.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.PURPLE)) {
                val++;
                color.add(Color.PURPLE);
            }
            if (player.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.GREEN)) {
                val++;
                color.add(Color.GREEN);
            }
            if (val == 4) {
                Color choosencolor = color.stream().filter(color1 ->! colorList.contains(color1)).findAny().orElse(Color.PURPLE);
                this.info.setchoosencolor(choosencolor);
                this.info.setbuildlist(player.getBuiltDistricts());
                this.info.setplayer(player);
                this.miracleCourt.doAction(this.info);

            }

        }
    }

    @Override
    public void observatory(IA player){}


}
