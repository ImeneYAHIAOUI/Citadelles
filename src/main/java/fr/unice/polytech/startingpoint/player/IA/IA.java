package fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.IA.Strategies.*;


import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.player.Player;

import java.util.ArrayList;
import java.util.List;


public class IA extends Player {
    public StrategyBot strategyBot;
    public Bots bot;
    public List<HerosChoice> thoughtPathList;
    public Predicate<IDistrict> isAffordable = district -> district.getPrice()<=gold ;
    public static BiFunction<Integer ,Integer,Integer > calculScore=(score, nbBuiltCard)->  100*score+10*nbBuiltCard;
    static Predicate<IDistrict> identicalCard(IDistrict district) {
        Predicate<IDistrict> identic = d -> d.getDistrictName().equals(district.getDistrictName());
        return identic;
    }
    /**
     *
     * @param playerName the IA object is constructed the same way as a Player object,
     *                   so we also only need the name of the player here.
     */
    public IA(String playerName){
        super(playerName);
        thoughtPathList = new ArrayList<>();
    }
    public void setBot(Bots bot){
        this.bot=bot;
    }

    // ===============================================================================================================
    //
    //                                               HERO CHOICE
    //
    // ===============================================================================================================

    /**
     * this method chooses the hero for the bot based on the information it's given
     *
     */
    @Override
    public void chooseHero(HeroDeck heroes, Random rand, List<IPlayer> players) { // LEVEL 1
        if(bot.equals(Bots.random)){
            setRole(heroes.randomChoice());
            return;
        }

        IHero hero = null;
        this.thoughtPathList = new ArrayList<HerosChoice>();
        HeroDecisionStandard heroDecisionStandard = new HeroDecisionStandard();
        hero = heroDecisionStandard.heroDecision(this,players,heroes,this.thoughtPathList,rand);
        this.setRole(hero);
    }

    // ===============================================================================================================
    //
    //                                                HERO ACTION
    //
    // ===============================================================================================================

    /**
     * this methode calls the action methode for the chosen hero
     * @param players,districtDeck,info the list of players
     * @param districtDeck
     * @param treasure
     * @param info
     */
    @Override
    public void activateHero(List<IPlayer> players, DistrictDeck districtDeck, Treasure treasure, IAToHero info ) {
        switch (role.getName()){
            case Merchant -> {
                info.setInformationForMerchant(this,treasure);
                role.doAction(info);
            }
            case King-> {
                info.setInformationForKing(this,players,treasure);
                role.doAction(info);
            }
            case Magician -> {
                info.setInformationForMagician(players,this, districtDeck);
                MagicianChoice choice = new MagicianChoice();
                choice.magicienChoice(info,isAffordable);
                role.doAction(info);
                }
            case Assassin -> {
                info.setInformationForAssassinOrThief(players,this,districtDeck);
                AssassinChoice choice = new AssassinChoice();
                choice.AssassinChoice(info);
                role.doAction(info);
            }
            case Thief ->  {
                info.setInformationForAssassinOrThief(players,this,districtDeck);
                ThiefChoice choice =new ThiefChoice();
                choice.ThiefChoice(info);
                role.doAction(info);
            }
            case Bishop -> {
                info.setInformationForBishop(this,treasure);
                role.doAction(info);
            }
            case Architect -> {
                info.setInformationForArchitect(this,districtDeck);
                role.doAction(info);
            }
            case Condottiere -> {
                CondottiereChoice condottiereChoice=new CondottiereChoice();
                info.setInformationForCondottiere(players,this,districtDeck,treasure);
                condottiereChoice.makeChoice(info);
                role.doAction(info);
            }
        }
    }

    // ===============================================================================================================
    //
    //                                      CHOOSE BETWEEN GOLD OR DISTRICT
    //
    // ===============================================================================================================
    /**
     * we use a methode from DrawOrGetGoldStrategies based on what strategy we want our IA to follow
     * for know we only have one methode
     */
    @Override
    public void drawOrGetPieces(DistrictDeck deck, Treasure treasure, IAToHero info,IAToWonder info2){
        // ============================================================================================================
        // If I have the wonder I apply its power

        // Once per turn, you can discard a neighborhood card from your hand and receive a gold coin in return.
        this.applyLaboratory(treasure,info2);

        // Once per turn, you can pay three gold to draw three cards.
        this.applyManufacture(deck,treasure,info2);

        // ============================================================================================================

        DrawOrGetGoldStrategies choice =new DrawOrGetGoldStrategies();
        choice.drawOrGetPieces(deck, treasure,info,isAffordable);
    }

    // ===============================================================================================================
    //
    //                                  BUILD OR NOT BUILD? THAT IS THE QUESTION
    //
    // ===============================================================================================================

    /**
     * this method chooses what move to make for the bot based on the information it's given
     * it's random based for now
     */
    @Override
    public void doAction(Treasure treasure, IAToHero info) {
        if(this.getRole().getName().equals(HeroName.Architect)){
            ArchitectChoice architectChoice = new ArchitectChoice();
            architectChoice.buildDistrict(this,treasure,info);
        }else {
            if (hand.stream().anyMatch(isAffordable)) {
                List<IDistrict> AffordableDistricts = hand.stream().filter(isAffordable).collect(Collectors.toList());
                IDistrict chosenDistrict = AffordableDistricts.get(0);
                while (AffordableDistricts.size() > 0 && builtDistricts.stream().anyMatch(identicalCard(chosenDistrict))) {
                    AffordableDistricts.remove(chosenDistrict);
                    if (AffordableDistricts.size() > 0) chosenDistrict = AffordableDistricts.get(0);
                }
                if (builtDistricts.stream().noneMatch(identicalCard(chosenDistrict))) {
                    buildDistrict(chosenDistrict);
                    treasure.addToTreasure(chosenDistrict.getPrice());
                    info.addBuiltDistrict(chosenDistrict);
                }
            }
        }
    }





    /**
     * in certain cases, players can get bonuses, this method is responsible
     * for attributing them
     */
    @Override
    public void addBonusScore(int val){
        this.score += val;
    }

    public List<HerosChoice> getThoughPath(){
        return thoughtPathList;
    }

    // ========================================================================================================
    //
    //                       WONDER: Make a choice according to the application of wonders
    //
    // ========================================================================================================

    /**
     * make a choice according to the action of Library
     *
     */
    @Override
    public int applyLibrary() {
        int numberOfCard = 0;
        if(bot.equals(Bots.random)){
            Random random=new Random();
            numberOfCard=random.nextInt(2)+1;
        }
        else{
            if(this.getBuiltDistricts().stream().map(wonder -> wonder.getDistrictName()).anyMatch(districtName -> districtName.equals(DistrictName.LIBRARY))){
                numberOfCard = 2;
            }else{
                numberOfCard = 1;
            }

        }
        return numberOfCard;
    }

    /**
     * Find the wonder
     * @param districtName
     * @return
     */
    private IDistrict findWonder(DistrictName districtName){
        IDistrict wonder = this.getBuiltDistricts().stream()
                .filter(district -> district.isWonder() && district.getDistrictName() == districtName)
                .findAny()
                .orElse(null);
        return wonder;
    }

    @Override
    public void applyDrocoport(IAToWonder info) {
        IDistrict wonder = findWonder(DistrictName.DROCOPORT);
        if (wonder != null) {
            info.setplayer(this);
            ((IWonder) wonder).doAction(info);
        }
    }
    /**
     * make a choice according to the action of University
     */
    @Override
    public void applyUniversity(IAToWonder info) {
        IDistrict wonder = findWonder(DistrictName.UNIVERSITY);
        if (wonder != null) {
            info.setplayer(this);
            ((IWonder) wonder).doAction(info);
        }
    }

    /**
     * make a choice according to the action of Laboratory
     * @param tresor
     */
    @Override
    public void applyLaboratory(Treasure tresor,IAToWonder info) {
        IDistrict wonder = findWonder(DistrictName.LABORATOIRE);
        IDistrict expensive=null;
        if(bot.equals(Bots.random)) {
            Random rand=new Random();
            if(hand.size()>0){
                int index=rand.nextInt(hand.size());
                expensive=this.getHand().get(index);
            }

        }else{
            expensive = this.getHand().stream()
                        .filter(district -> district.getPrice() > 4)
                        .findAny().orElse(null);



        }
        if (wonder != null) {
            info.setInformationForLaboratory(tresor,expensive, this);
            ((IWonder) wonder).doAction(info);
        }
    }

    /**
     * make a choice according to the action of Manufacture
     * @param deck
     * @param tresor
     */
    @Override
    public void applyManufacture (DistrictDeck deck, Treasure tresor,IAToWonder info){
        int i;

        info.setTreasure(tresor);
        info.setplayer(this);
        info.setdistrictdeck(deck);
        IDistrict wonder =findWonder( DistrictName.MANUFACTURE);
        if (wonder != null & this.getGold() >= 3) {
            if(bot.equals(Bots.random)) {
                int decision=(new Random()).nextInt(2);
                if(decision==1) ((IWonder) wonder).doAction(info) ;


            } else {
                int s = 0;
                int c = 0;
                for (i = 0; i < this.getHand().size(); i++) {
                    if (this.getHand().get(i).getPrice() >= 3) {
                        s = s + 1;
                    } else c = c + 1;
                }
                if (s > c || this.getHand().size() == 0) {
                    ((IWonder) wonder).doAction(info);
                }
            }
        }
    }

    /**
     *  make a choice according to the action of miracle court
     */
    @Override
    public void applyMiracleCourt(IAToWonder info) {
        List<Color> color = new ArrayList<>();
        List<Color> colorList = List.of(Color.PURPLE, Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW);
        IDistrict wonder = findWonder(DistrictName.LACOURDESMIRACLES);
        if (wonder != null) {
            int val = 0;

            if (this.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.YELLOW)) {
                val++;
                color.add(Color.YELLOW);
            }
            if (this.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.RED)) {
                val++;
                color.add(Color.RED);
            }
            if (this.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.BLUE)) {
                val++;
                color.add(Color.BLUE);
            }
            if (this.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.PURPLE)) {
                val++;
                color.add(Color.PURPLE);
            }
            if (this.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.GREEN)) {
                val++;
                color.add(Color.GREEN);
            }
            if (val == 4) {
                Color chosenColor = colorList.stream().filter(color1 ->! color.contains(color1)).findAny().orElse(Color.PURPLE);
                info.setplayer(this);
                info.setInformationForMiracleCourt(chosenColor, this);
                ((IWonder )wonder).doAction(info);
            }
        }
    }

    /**
     * make a choice according to the action of observatory
     */
    @Override
    public int applyObservatory(){
        int numberOfCard = 0;
        if(bot.equals(Bots.random)){
            Random random=new Random();
            if(random.nextInt(2)==1){
                numberOfCard=3;
            }else{
                numberOfCard=2;
            }
        }
        else{
            if(this.getBuiltDistricts().stream().map(wonder -> wonder.getDistrictName()).anyMatch(districtName -> districtName.equals(DistrictName.OBSERVATORY))){
                numberOfCard = 3;
            }else{
                numberOfCard = 2;
            }
        }



        return numberOfCard;
    }

    /**
     * make a choice according to the action of magicschool
     * @param informations
     */
    @Override
    public void applyMagicSchool(IAToWonder informations){
        Color playerColor = getRole().getColor();
        if (playerColor != Color.WHITE){
            List<IDistrict> possessedWonders = getBuiltDistricts().stream().
                    filter(district -> district.isWonder()).collect(Collectors.toList());

            IWonder MagicSchool = (IWonder) possessedWonders.stream().
                    filter(district -> district.getDistrictName().equals(DistrictName.ECOLEDEMAGIE)).
                    findAny().orElse(null);

            if (MagicSchool != null){
                informations.setInformationForMagicSchool(playerColor,this);
                MagicSchool.doAction(informations);
            }
        }
    }

    /**
     * make a choice according to the action of cemetry
     * @param deck
     * @param tresor
     * @param card
     * @param info
     */
    @Override
    public void applyCemetery(DistrictDeck deck, Treasure tresor, IDistrict card, IAToWonder info){
        IDistrict wonder = findWonder(DistrictName.CEMETRY);
        List<IDistrict> doubles = Utils.searchForDoubles(hand,this.getBuiltDistricts());
        Boolean doAction=false;
        if(bot.equals(Bots.random)) {
            if ((new Random()).nextInt(2) == 1) {
                doAction = true;
            }
        }else{
            if(!doubles.contains(card) ){
                doAction=true;
            }
        }
        if(doAction && role.getName()!=HeroName.Condottiere && this.getGold()>=1) {
            if (wonder != null) {
                info.setInformationForCemetry(card, this);
                info.setTreasure(tresor);
                info.setdistrictdeck(deck);
                ((IWonder) wonder).doAction(info);
            }
        }

       }

}

