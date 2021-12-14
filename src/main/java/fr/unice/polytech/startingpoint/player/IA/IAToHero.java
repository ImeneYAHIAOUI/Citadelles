package fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.core.*;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
 * This class records the decisions of the AI in this object to transmit them to the hero
 */
public class IAToHero {
    private List<IPlayer> players;
    private List<List<IDistrict>> playerBuiltDistricts;
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
    private boolean draw;
    private boolean getGold;
    private List<IDistrict> builtDistrict =  new ArrayList<>();

    public IAToHero(){
        this.scores = new ArrayList<>();
        this.chosenCards = new ArrayList<>();
        this.playerBuiltDistricts=new ArrayList<>();
        this.playersName=new ArrayList<>();
        this.gold=new ArrayList<>();
        this.cardCount=new ArrayList<>();
        this.heros = new ArrayList<>();
    }



    // ========================================================================================================
    //                                 list fillers
    // ========================================================================================================

    void fillBuiltDistricts(){
        players.stream().
                filter(player-> player!=currentPlayer ).
                forEach(player->{
                    playerBuiltDistricts.add(player.getBuiltDistricts());
                });

    }
    void fillPlayerNames(){
        players.stream().
                filter(player-> player!=currentPlayer ).
                forEach(player-> {
                    playersName.add(player.getName());
                });
    }
    void fillGold(){
        players.stream().
                filter(player-> player!=currentPlayer ).
                forEach(player-> {
                    gold.add(player.getGold());
                });
    }
    void fillCardCount(){
        players.stream().
                filter(player-> player!=currentPlayer ).
                forEach(player-> {
                    cardCount.add(player.getHand().size());
                });
    }

    void fillScores(){
        players.stream().
                filter(player-> player!=currentPlayer ).
                forEach(player-> {
                    scores.add(player.getScore());
                });
    }

    void fillHeros(){
        players.stream().
                filter(player-> player!=currentPlayer ).
                forEach(player->{
                    heros.add(player.getRole());
                });
    }
    // ========================================================================================================
    //               information setters for heros ( each hero will need different informations)
    // ========================================================================================================
    /**
     * give necessary information
     * @param players
     * @param currentPlayer
     * @param districtDeck
     */
    public void setInformationForAssassinOrThief(List<IPlayer>players,IPlayer currentPlayer,DistrictDeck districtDeck){
        this.players = players;
        this.deck=districtDeck;
        this.currentPlayer=currentPlayer;
        fillBuiltDistricts();
        fillPlayerNames();
        fillGold();
        fillCardCount();
        fillScores();
        fillHeros();
    }


    public void setInformationForKing(IPlayer currentPlayer, List<IPlayer> players , Treasure treasure){
        this.treasure=treasure;
        this.CrownHolder=players.stream().filter(player -> player.getCrown()).findFirst().get();
        this.currentPlayer=currentPlayer;
    }
    /**
     * give necessary information for merchant
     * @param player
     * @param treasure
     */
    public void setInformationForMerchant(IPlayer player,Treasure treasure){
        this.treasure=treasure;
        this.currentPlayer=player;
    }

    /**
     * give necessary information for architect
     * @param player
     */
    public void setInformationForArchitect(IPlayer player, DistrictDeck districtDeck){
        this.currentPlayer=player;
        this.deck = districtDeck;
    }

    /**
     * give necessary information g
     * @param player
     * @param treasure
     */
    public void setInformationForBishop(IPlayer player,Treasure treasure){
        this.treasure=treasure;
        this.currentPlayer=player;

    }
    public void setInformationForCondottiere(List<IPlayer>players,IPlayer currentPlayer, DistrictDeck districtDeck,Treasure treasure){
        this.currentPlayer=currentPlayer;
        this.deck=districtDeck;
        this.treasure=treasure;
        this.players=players;
        Collections.sort(players,new PlayerScoreComparator());
        Collections.reverse(players);
        fillBuiltDistricts();
        fillPlayerNames();
        fillScores();
    }

    /**
     * give necessary information for magician
     * @param players
     * @param currentPlayer
     * @param districtDeck
     */
    public void setInformationForMagician(List<IPlayer>players, IPlayer currentPlayer, DistrictDeck districtDeck){
        this.players = players;
        this.deck=districtDeck;
        this.currentPlayer=currentPlayer;
        fillBuiltDistricts();
        fillPlayerNames();
        fillCardCount();
        fillGold();
    }

    /**
     * choice's player
     * @param playerName
     */
    public void setChosenPlayer(String playerName){

        chosenPlayer=players.stream().filter(player -> player.getName().equals(playerName)).findFirst().orElse(null);
    }

    /**
     * display players choice
     *
     */
    public String getChoice(){
        if(draw) return currentPlayer+" has chosen to draw";
        return currentPlayer+" has chosen to get gold";
    }
    // ========================================================================================================
    //                                 setters and getters
    // ========================================================================================================

    public  IPlayer getCrownHolder(){
        return CrownHolder;
    }
    public  void setCrownHolder(IPlayer crownHolder){
        this.CrownHolder=crownHolder;
    }
    public List<Integer> getScores(){
        return scores;
    }
    public void setScores(List<Integer> scores){
        this.scores=scores;
    }
    public void setPlayersName(List<String> names){

        this.playersName=names;
    }

    public IPlayer getChosenPlayer(){
        return chosenPlayer;
    }
    public List<String> getPlayersName(){
        return playersName;
    }
    public DistrictDeck getDeck(){
        return this.deck;
    }
    public  void setDeck(DistrictDeck deck ){this.deck=deck;}

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


    public List<List<IDistrict>> getBuiltDistricts(){ return playerBuiltDistricts;}
    public void setPlayerBuiltDistricts(List<List<IDistrict>> playerBuiltDistricts) {
        this.playerBuiltDistricts = playerBuiltDistricts;
    }


    public void setBuiltDistrict(List<IDistrict> builtDistrict) {
        this.builtDistrict = builtDistrict;
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
    public void setPlayers(List<IPlayer> players) {
        this.players=players;
    }

    public void setDraw(){
        draw = true;
    }
    public void setGetGold(){
        getGold = true;
    }
    public void addBuiltDistrict(IDistrict district){
        builtDistrict.add(district);
    }
    public List<IDistrict> getBuiltDistrict(){
        return builtDistrict;
    }



}