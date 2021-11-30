package fr.unice.polytech.startingpoint.player;
import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.cards.Treasure;
import fr.unice.polytech.startingpoint.heros.HeroDeck;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class IA extends Player{
    Predicate<IDistrict> isAffordable = district -> district.getPrice()<=gold;


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
    public void chooseHero(HeroDeck heroes, int roleIndex) {
        if (roleIndex < 0 || roleIndex> heroes.size()){
            throw new RuntimeException("Invalide value");
        }
        this.setRole(heroes.get(roleIndex));
        heroes.remove(role);
    }

    @Override
    public void activateHero(List<IPlayer> players, DistrictDeck districtDeck, Treasure treasure) {
        Information info=new Information();
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

                magicienChoice(info);
                role.doAction(info);
                }

            }
        }

        public void magicienChoice(Information infos) {
            List<IPlayer> players = infos.getPlayers();
            Collection<Integer> cardNumbers = infos.getCardCount().values();
            Collection<String> playerNames = infos.getCardCount().keySet();
            int maxCardNumber = cardNumbers.stream().max(Integer::compare).get();
            List<IDistrict> doubles = hand.stream().filter(district -> Collections.frequency(hand,district.getDistrictName())>1).distinct().collect(Collectors.toList());
            List<IDistrict> chosenCards = new ArrayList<>();
            String chosenPlayer;
            if(hand.size() == 0){
                chosenPlayer = playerNames.stream().filter(key -> infos.getCardCount().get(key) == maxCardNumber).findAny().orElse(null);
                infos.setChosenPlayer(chosenPlayer,players);
            }
            else if (hand.stream().noneMatch(IDistrict::isWonder) && hand.stream().noneMatch(isAffordable)){

                chosenPlayer = playerNames.stream().filter(key -> infos.getGold().get(key) <= gold+2)
                        .filter(key -> infos.getCardCount().get(key) >= hand.size()).findAny().orElse(null);

                if(chosenPlayer != null ) infos.setChosenPlayer(chosenPlayer,players);

                }
            else {
                if(doubles.size()>0){
                    chosenCards.addAll(doubles);
                    for (IDistrict district : hand){
                        if(! district.isWonder() && district.getPrice() > gold+2){
                            chosenCards.add(district);
                        }
                    }
                }

            }
            infos.setChosenCards(chosenCards);
        }


    /**
     * this method chooses what move to make for the bot based on the information it's given
     * it's random based for now
     */

    @Override
    public void doAction(Treasure treasure) {
        if(hand.stream().anyMatch(isAffordable)){
            IDistrict chosenDistrict = hand.stream().filter(isAffordable).findAny().get();
            buildDistrict(chosenDistrict);
            treasure.addToTreasure(chosenDistrict.getPrice());

        }
        else return;
    }

    @Override
    public void drawOrGetPieces(DistrictDeck deck, Treasure treasure){
        Boolean treasureNotEmpty=treasure.getPieces()>=2;
        if(hand.size()>0){
            if( hand.stream().noneMatch(isAffordable)){
                if(hand.stream().anyMatch(district -> district.getPrice()<=gold+2 )&& treasureNotEmpty) {

                    addGold(2);
                    treasure.removeGold(2);
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
                    if(treasureNotEmpty){
                        addGold(2);
                        treasure.removeGold(2);

                    }
                }
            }
        }
        else{
            getDistrict(deck.giveDistrict(1));
        }
    }
}
