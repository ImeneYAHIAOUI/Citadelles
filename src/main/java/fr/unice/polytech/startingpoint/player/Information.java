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
import java.util.stream.Collectors;

public class Information {
    private List<IPlayer> players;
    private List<List<IDistrict>> builtDistricts;
    private List<String> playersName;
    private List<Integer>cardCount;
    private List<Integer> gold;
    private List<IHero> heros;
    private List<Integer> scores;
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
    public void setInformationForAssasin(List<IPlayer>players,IPlayer currentPlayer){
        this.playersName=new ArrayList<>();
        this.currentPlayer=currentPlayer;
        this.builtDistricts=new ArrayList<>();
        int currentHeroRank=currentPlayer.getHeroRank();
        scores=new ArrayList<>();
        this.currentPlayer=players.stream().filter(player -> player.getHeroRank()==currentHeroRank).findFirst().get();
        players.stream().
                filter(player-> player.getHeroRank()!=currentHeroRank ).
                forEach(player->{
                    playersName.add(player.getName());
                    builtDistricts.add(player.getBuiltDistricts());
                    scores.add(player.getBuiltDistricts().stream().map(card -> card.getPrice()).reduce(0,(a,b)->a+b));
                });
    }
    public void setInformationForMerchant(IPlayer player,Treasure treasure){
        this.treasure=treasure;
        this.currentPlayer=player;
    }
    public void setInformationForMagician(List<IPlayer>players, IPlayer currentPlayer, DistrictDeck districtDeck){
        this.players = players;
        this.chosenCards = new ArrayList<>();
        this.deck=districtDeck;
        this.currentPlayer=currentPlayer;
        this.builtDistricts=new ArrayList<>();
        this.playersName=new ArrayList<>();
        this.gold=new ArrayList<>();
        this.cardCount=new ArrayList<>();
        this.heros = new ArrayList<>();
        int currentHeroRank=currentPlayer.getHeroRank();
        this.currentPlayer=players.stream().filter(player -> player.getHeroRank()==currentHeroRank).findFirst().get();
        players.stream().
                filter(player-> player.getHeroRank()!=currentHeroRank ).
                forEach(player->{
                    builtDistricts.add(player.getBuiltDistricts());
                    playersName.add(player.getName());
                    cardCount.add( player.getHand().size());
                    gold.add( player.getGold());
                });
        //il connait les personnages des joeurs qui ont joué avant lui ,par exp le voleur connait seulement qui est l'assasin
        players.stream().
                filter(player-> player.getHeroRank()<currentHeroRank).
                forEach(player-> heros.add( player.getRole()));

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
    public List<Integer> getScores(){
        return scores;
    }
    public void setScores(List<Integer> scores){
        this.scores=scores;
    }

    public IPlayer getChosenPlayer(){
        return this.chosenPlayer;
    }
    public void setChosenPlayer(IPlayer player){
        this.chosenPlayer=player;
    }
    public List<String> getPlayersName(){
        return playersName;
    }
    public void setPlayersName(List<String>playersName){
        this.playersName=playersName;
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

    public List<Integer> getGold(){
        return gold;
    }
    public void setGold(List<Integer> gold){
        this.gold=gold;
    }

    public List<Integer> getCardCount() {
        return cardCount;
    }
    public void setCardCount(List<Integer>cardcount){
        this.cardCount=cardcount;
    }


    public List<List<IDistrict>> getBuiltDistricts(){ return builtDistricts;}
    public void setBuiltDistricts(List<List<IDistrict>>builtDistricts) {
        this.builtDistricts = builtDistricts;
    }

    public List<IHero> getHeros(){
        return heros;
    }
    public void setHeros(List<IHero> heros) {
        this.heros = heros;
    }


    public List<IPlayer> getPlayers() {
        return players;
    }
}
