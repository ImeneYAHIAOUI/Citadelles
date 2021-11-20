package fr.unice.polytech.startingpoint.player;
import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.cards.District;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class IA extends Player{
    Predicate<IDistrict> isAffordable = district -> district.getPrice()<=pieces;
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
                magicienChoice(info);
                hero.doAction(info);
            }

            }
        }

        public void magicienChoice(Information infos){
            Collection<Integer> cardNumbers = infos.getCardCount().values();
            Collection<String> players = infos.getCardCount().keySet();
            int maxCardNumber = cardNumbers.stream().reduce(0,(x,y) -> Math.max(x,y));
            List<IDistrict> doubles = hand.stream().filter(district -> Collections.frequency(hand,district)>1).distinct().collect(Collectors.toList());
            List<IDistrict> chosenCards = new ArrayList<>();
            String chosenPlayer;

            if(hand.size() == 0){
                chosenPlayer = players.stream().filter(key -> infos.getCardCount().get(key) == maxCardNumber).findAny().orElse(null);
                infos.setChosenPlayer(chosenPlayer);
            }
            else if (hand.stream().noneMatch(district -> district.isWonder()) && hand.stream().noneMatch(isAffordable)){
                chosenPlayer = players.stream().filter(key -> infos.getGold().get(key) <= pieces+2)
                        .filter(key -> infos.getCardCount().get(key) >= hand.size()).findAny().orElse(null);

                if(chosenPlayer != null ) infos.setChosenPlayer(chosenPlayer);

                }
            else {
                if(doubles.size()>0){
                    chosenCards.addAll(doubles);
                for (IDistrict district : hand){
                    if(! district.isWonder() && district.getPrice() > pieces+2){
                        chosenCards.add(district);
                    }
                }
            }

            }
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
            IDistrict chosenDistrict = hand.stream().filter(isAffordable).findAny().get();
            buildDistrict(chosenDistrict);
        }
        else return;
    }

    public void drawOrGetPieces(){
        if( hand.stream().noneMatch(isAffordable)){
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
