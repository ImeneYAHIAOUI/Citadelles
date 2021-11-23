package fr.unice.polytech.startingpoint.player;
import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.heros.IHero;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public  IPlayer getCrownHolder(){
        return CrownHolder;
    }
    public  void setCrownHolder(IPlayer crownholder){
        this.CrownHolder=crownholder;
    }
    public void setInformationForKing(IPlayer currentPlayer,List<IPlayer> players ){
        this.CrownHolder=players.stream().filter(player -> player.getCrown()).findFirst().get();
        this.currentPlayer=currentPlayer;

    }
    public void setInformationForMerchant(IPlayer player){
        this.currentPlayer=player;
    }
    public void setInformationForMagician(List<IPlayer>players, IPlayer currentPlayer, DistrictDeck districtDeck){
        this.deck=districtDeck;
        this.currentPlayer=currentPlayer;
        this.builtDistricts=new HashMap<>();
        this.gold=new HashMap<>();
        this.cardCount=new HashMap<>();
        this.heros = new HashMap<>();
        int currentHeroRank=currentPlayer.getTheHeroRank();
        this.currentPlayer=players.stream().filter(player -> player.getTheHeroRank()==currentHeroRank).findFirst().get();
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
    public void setChosenPlayer(String playerName,List<IPlayer>players){

        chosenPlayer=players.stream().filter(player -> player.getName().equals(playerName)).findFirst().orElse(null);
    }

    public IPlayer getChosenPlayer(){
        return this.chosenPlayer;
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
    public IPlayer getCurrentPlayer(){
        return this.currentPlayer;
    }
    public void setCurrentPlayer(IPlayer player){
        this.currentPlayer=player;
    }
    public Map<String,Integer> getGold(){
        return gold;
    }
    public Map<String, Integer> getCardCount() {
        return cardCount;
    }
    public Map<String, List<IDistrict>> getBuiltDistricts(){ return builtDistricts;}
    public Map<String, IHero> getHeros(){
        return heros;
    }


}
