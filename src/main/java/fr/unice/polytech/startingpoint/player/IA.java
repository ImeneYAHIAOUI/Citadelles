package fr.unice.polytech.startingpoint.player;
import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.cards.District;

import java.util.*;
import java.util.function.Predicate;


public class IA extends Player{
    Predicate<District> isAffordable = district -> district.getPrice()<=pieces;
    /**
     *
     * @param playerName the IA object is constructed the same way as a Player object,
     *                   so we also only need the name of the player here.
     */

    public IA(String playerName){
        super(playerName);
    }

    /**
     * this method chooses the hero for the bot based on the information it's given
     * it's random based for now
     */

    @Override
    public void chooseHero() {
        Random rand = new Random();
        int roleIndex = rand.nextInt(this.HeroList.size());
        this.setRole(roleIndex);
    }

    /**
     *
     * empty for now :)
     */

    @Override
    public void activateHero(DistrictDeck districtDeck,IHero hero, List<IPlayer> players) {
        switch (hero.getName()){
            case Merchant, King-> hero.doAction(new Information(districtDeck,hero.getRank(),players));
            case Magician -> {
                Information info=new Information(districtDeck ,hero.getRank(),players);
                makechoice(info);
                hero.doAction(info);
            }

            }
        }
        public void makechoice(Information infos){
        // on choisit dechanger les cartes   avec soit un joeur soit  la pioche
            // tu met a jour soit chosenNumberofcards soit chosenplayer dans information

        }


    /**
     * this method chooses what move to make for the bot based on the information it's given
     * it's random based for now
     */

    @Override
    public void doAction() {

        drawOrGetPieces();

        if(hand.stream().anyMatch(isAffordable)){
            District chosenDistrict = hand.stream().filter(isAffordable).findAny().get();
            buildDistrict(chosenDistrict);
        }
        else return;
    }

    public void drawOrGetPieces(){
        if(! hand.stream().anyMatch(isAffordable)){
            if(hand.stream().anyMatch(district -> district.getPrice()<=pieces+2)){
                addPieces(2);
            }
            else{
                getDistrict(deck.giveDistrict(1));
            }
        }
        else{
            if(hand.size()<3) {
                getDistrict(deck.giveDistrict(1));
            }
            else{
                addPieces(2);
            }
        }
    }





}
