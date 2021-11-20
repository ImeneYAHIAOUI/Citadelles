package fr.unice.polytech.startingpoint.player;

import fr.unice.polytech.startingpoint.cards.District;
import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.heros.IHero;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Information {

    private Map<String,List<District>> builtDistricts;
    private Map<String,Integer > hands;
    private Map<String, Integer > or;
    private Map<String, IHero > heros;
    private IPlayer currentPlayer;
    private DistrictDeck districtDeck;
    private IPlayer chosenPlayer; // cet attribut on l'utilisera pour le magicien,l'assasin et le voleur
    private List<District> chosenCards;
    public Information(DistrictDeck districtDeck , int currentHeroRank, List<IPlayer> players){
        this.builtDistricts=new HashMap<>();
        this.hands=new HashMap<>();
        this.or=new HashMap<>();
        this.heros=new HashMap<>();
        this.chosenPlayer=null;
        this.districtDeck=districtDeck;
        this.currentPlayer=players.stream().filter(player -> player.getTheHeroRank()==currentHeroRank).findAny().get();
        players.stream().
                filter(player-> player.getTheHeroRank()!=currentHeroRank ).
                forEach(player->{
                        builtDistricts.put(player.getName(),player.getBuiltDistricts());
                        hands.put(player.getName(), player.getHand().size());
                        or.put(player.getName(), player.getGold());
                });
        //il connait les personnages des joeurs qui ont joué avant lui ,par exp le voleur connait seulement qui est l'assasin
        players.stream().
                filter(player-> player.getTheHeroRank()<currentHeroRank).
                forEach(player-> heros.put(player.getName(), player.getRole()));


    }
    public void setChosenPlayer(IPlayer player){
        this.chosenPlayer=player;
    }
    public IPlayer getChosenPlayer(){
        return this.chosenPlayer;
    }
    public void setChosenCards(List<District> cards){
        this.chosenCards=cards;
    }
    public List<District> getChosenCards(){
        return this.chosenCards;
    }
    public IPlayer getCurrentPlayer(){
        return this.currentPlayer;
    }
    public DistrictDeck getDeck(){
        return this.districtDeck;
    }









}
