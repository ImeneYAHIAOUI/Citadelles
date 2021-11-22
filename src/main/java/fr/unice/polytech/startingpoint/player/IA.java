package fr.unice.polytech.startingpoint.player;
import fr.unice.polytech.startingpoint.cards.District;
import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.heros.IHero;

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
    public void chooseHero(int roleIndex) {
        if (roleIndex < 0 || roleIndex> this.HeroList.size()){
            throw new RuntimeException("Invalide value");
        }
        this.setRole(roleIndex);
    }


    @Override
    public void activateHero(List<IPlayer> players,DistrictDeck districtDeck,Information info) {
        //voir comment configurer Information pour se débarrasser du deck
        switch (role.getName()){
            case Merchant -> {
                info.setInformationForMerchant(this);
                role.doAction(info);
            }
            case King-> {
                        info.setInformationForKing(this,players);
                        role.doAction(info);
                    }
            case Magician ->
                    {
                info.setInformationForMagician(players,this, districtDeck);
                magicienChoice(info,players);
                role.doAction(info);
                }

            }
        }

        public void magicienChoice(Information infos, List<IPlayer> players){
            Collection<Integer> cardNumbers = infos.getCardCount().values();
            Collection<String> NameOfplayers = infos.getCardCount().keySet();
            int maxCardNumber = cardNumbers.stream().max(Integer::compare).get();
            List<IDistrict> doubles = hand.stream().filter(district -> Collections.frequency(hand,district)>1).distinct().collect(Collectors.toList());
            List<IDistrict> chosenCards = new ArrayList<>();
            String chosenPlayer;
            if(hand.size() == 0){
                chosenPlayer = NameOfplayers.stream().filter(key -> infos.getCardCount().get(key) == maxCardNumber).findAny().orElse(null);
                infos.setChosenPlayer(chosenPlayer,players);
            }
            else if (hand.stream().noneMatch(district -> district.isWonder()) && hand.stream().noneMatch(isAffordable)){
                chosenPlayer = NameOfplayers.stream().filter(key -> infos.getGold().get(key) <= pieces+2)
                        .filter(key -> infos.getCardCount().get(key) >= hand.size()).findAny().orElse(null);

                if(chosenPlayer != null ) infos.setChosenPlayer(chosenPlayer,players);

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
        }


    /**
     * this method chooses what move to make for the bot based on the information it's given
     * it's random based for now
     */

    @Override
    public void doAction() {

        if(hand.stream().anyMatch(isAffordable)){
            IDistrict chosenDistrict = hand.stream().filter(isAffordable).findAny().get();
            buildDistrict(chosenDistrict);
        }
        else return;
    }

    @Override
    public void drawOrGetPieces(DistrictDeck deck){
        List<IDistrict> districtList = deck.giveDistrict(1);
        if(districtList.size()>0){
            if( hand.stream().noneMatch(isAffordable)){
                if(hand.stream().anyMatch(district -> district.getPrice()<=pieces+2)){
                    addPieces(2);
                }
                else{
                    getDistrict(districtList);
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
        else{
            addPieces(2);
        }
    }





}
