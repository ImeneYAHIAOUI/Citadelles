package fr.unice.polytech.startingpoint.player;
import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.cards.Treasure;
import fr.unice.polytech.startingpoint.heros.IHero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Information {
    private Map<String, List<IDistrict>> builtDistricts;
    private Map<String, Integer> cardCount;
    private Map<String, Integer> gold;
    private Map<String, IHero > heros;
    private IPlayer currentPlayer;
    private IPlayer CrownHolder;// le joeur qui a la couronne
    private IPlayer chosenPlayer;// on utilise cet attribut pour le magicien,voleur,assasien
    private List<IDistrict> chosenCards;
    private DistrictDeck deck;
    private Treasure treasure;

    public void setInformationForKing(IPlayer currentPlayer,List<IPlayer> players ,Treasure treasure){
        this.treasure=treasure;
        this.CrownHolder=players.stream().filter(player -> player.getCrown()).findFirst().get();
        this.currentPlayer=currentPlayer;

    }
    public void setInformationForMerchant(IPlayer player,Treasure treasure){
        this.treasure=treasure;
        this.currentPlayer=player;
    }
    public void setInformationForMagician(List<IPlayer>players, IPlayer currentPlayer, DistrictDeck districtDeck){
        this.chosenCards = new ArrayList<>();
        this.deck=districtDeck;
        this.currentPlayer=currentPlayer;
        this.builtDistricts=new HashMap<>();
        this.gold=new HashMap<>();
        this.cardCount=new HashMap<>();
        this.heros = new HashMap<>();
        int currentHeroRank=currentPlayer.getHeroRank();
        this.currentPlayer=players.stream().filter(player -> player.getHeroRank()==currentHeroRank).findFirst().get();
        players.stream().
                filter(player-> player.getHeroRank()!=currentHeroRank ).
                forEach(player->{
                    builtDistricts.put(player.getName(),player.getBuiltDistricts());
                    cardCount.put(player.getName(), player.getHand().size());
                    gold.put(player.getName(), player.getGold());
                });
        //il connait les personnages des joeurs qui ont joué avant lui ,par exp le voleur connait seulement qui est l'assasin
        players.stream().
                filter(player-> player.getHeroRank()<currentHeroRank).
                forEach(player-> heros.put(player.getName(), player.getRole()));

    }
    public void setChosenPlayer(String playerName,List<IPlayer>players){

        chosenPlayer=players.stream().filter(player -> player.getName().equals(playerName)).findFirst().orElse(null);
    }

    public  IPlayer getCrownHolder(){
        return CrownHolder;
    }
    public  void setCrownHolder(IPlayer crownholder){
        this.CrownHolder=crownholder;
    }

    public IPlayer getChosenPlayer(){
        return this.chosenPlayer;
    }
    public void setChosenPlayer(IPlayer player){
        this.chosenPlayer=player;
    }

    public DistrictDeck getDeck(){
        return this.deck;
    }
    public  void setDeck(DistrictDeck deck ){this.deck=deck;
    }
    public void setChosenCards(List<IDistrict> cards){
        this.chosenCards=cards;
    }
    public List<IDistrict> getChosenCards(){
        return this.chosenCards;
    }

    public  Treasure getTreasure(){
        return treasure;
    }
    public  void setTreasure(Treasure treasure){
        this.treasure=treasure;
    }
    public IPlayer getCurrentPlayer(){
        return this.currentPlayer;
    }
    public void setCurrentPlayer(IPlayer player){
        this.currentPlayer=player;
    }

    public Map<String,Integer> getGold(){
        return gold;
    }
    public void setGold(Map<String,Integer> gold){
        this.gold=gold;
    }

    public Map<String, Integer> getCardCount() {
        return cardCount;
    }
    public void setCardCount(Map<String, Integer> cardcount){
        this.cardCount=cardcount;
    }


    public Map<String, List<IDistrict>> getBuiltDistricts(){ return builtDistricts;}
    public void setBuiltDistricts(Map<String, List<IDistrict>> builtDistricts) {
        this.builtDistricts = builtDistricts;
    }

    public Map<String, IHero> getHeros(){
        return heros;
    }
    public void setHeros(Map<String, IHero> heros) {
        this.heros = heros;
    }



}
