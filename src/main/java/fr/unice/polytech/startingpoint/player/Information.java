package fr.unice.polytech.startingpoint.player;

import fr.unice.polytech.startingpoint.cards.District;
import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.heros.IHero;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Information {

    private List<IPlayer> players;
    private IPlayer king;
    private Map<String, List<IDistrict>> builtDistricts;
    private Map<String, Integer> cardCount;
    private Map<String, Integer> gold;
    private Map<String, IHero > heros;
    private IPlayer currentPlayer;
    private DistrictDeck districtDeck;
    private IPlayer chosenPlayer; // cet attribut on l'utilisera pour le magicien,l'assasin et le voleur
    private List<IDistrict> chosenCards;


    public Information(DistrictDeck districtDeck , int currentHeroRank, List<IPlayer> players){
        this.players = players;
        this.builtDistricts=new HashMap<>();
        this.gold=new HashMap<>();
        this.cardCount=new HashMap<>();
        this.heros = new HashMap<>();
        this.chosenPlayer=null;
        this.districtDeck=districtDeck;
        this.currentPlayer=players.stream().filter(player -> player.getTheHeroRank()==currentHeroRank).findAny().get();
        players.stream().
                filter(player-> player.getTheHeroRank()!=currentHeroRank ).
                forEach(player->{
                        builtDistricts.put(player.getName(),player.getBuiltDistricts());
                    cardCount.put(player.getName(), player.getHand().size());
                        gold.put(player.getName(), player.getGold());
                });
        //il connait les personnages des joeurs qui ont joué avant lui ,par exp le voleur connait seulement qui est l'assasin
        players.stream().
                filter(player-> player.getTheHeroRank()<currentHeroRank).
                forEach(player-> heros.put(player.getName(), player.getRole()));


    }
    public void setChosenPlayer(String playerName){

        this.chosenPlayer=players.stream().filter(player -> player.getName().equals(playerName)).findFirst().orElse(null);
    }

    public IPlayer getChosenPlayer(){
        return this.chosenPlayer;
    }
    public void setChosenCards(List<IDistrict> cards){
        this.chosenCards=cards;
    }
    public List<IDistrict> getChosenCards(){
        return this.chosenCards;
    }
    public IPlayer getCurrentPlayer(){
        return this.currentPlayer;
    }
    public DistrictDeck getDeck(){
        return this.districtDeck;
    }

    public Map<String,Integer> getGold(){
        return gold;
    }
    public Map<String, Integer> getCardCount() {
        return cardCount;
    }

    public void setKing(IPlayer king) {
        this.king = king;
    }
}
