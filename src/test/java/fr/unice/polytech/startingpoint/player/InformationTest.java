package fr.unice.polytech.startingpoint.player;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;
import fr.unice.polytech.startingpoint.player.IA.Nastybot;
import fr.unice.polytech.startingpoint.player.IA.NiceBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InformationTest {
    IAToHero information;
    IAToHero information2;
    IAToHero information3;
    IAToHero information4;
    IAToHero mockInfo1 = mock(IAToHero.class);

    List<IA> players;
    List<IA> players1;
    List<IA> players2;
    List<IDistrict> listDeck;
    IA player1;
    IA player2;
    IA player3;
    IDistrict district1;
    IDistrict district2;
    IDistrict district3;
    IDistrict district4;
    IDistrict district5;
    DistrictDeck deck;
    DistrictDeck deck1;
    DistrictDeck deck2;
    List<Integer> cardCount1;
    List<Integer> cardCount2;
    List<IDistrict> hand1;
    List<IDistrict> hand2;
    List<IDistrict> hand3;
    List<IDistrict> hand4;
    List<List<IDistrict>>  builtDistricts;
    List<List<IDistrict>>  builtDistricts1;
    List<List<IDistrict>>  builtDistricts2;
    List<Integer> cardCount;
    List<Integer> gold;
    List<IHero>  heros;
    List<Integer> gold1;
    List<IHero> heros1;
     HeroDeck heroDeck;
    int currentHeroRank1;
    int currentHeroRank2;
    Treasure treasure;


    @BeforeEach
    void setUp(){
        try {
            district1 = new District(2, Color.YELLOW, DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district2 = new District(1,Color.BLUE,DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district3 = new District(1,Color.GREEN,DistrictName.PALAIS);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district4 = new District(2, Color.RED,DistrictName.CHATEAU);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district5 = new District(2, Color.YELLOW,DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }
        treasure=new Treasure(32);
        players=new ArrayList<IA>();
        players1=new ArrayList<IA>();
        players2=new ArrayList<IA>();
        listDeck=new ArrayList<IDistrict>();
        listDeck.add(district1);
        listDeck.add(district2);
        listDeck.add(district3);
        listDeck.add(district4);
        listDeck.add(district5);
        information = new IAToHero();
        information2 = new IAToHero();
        information3 = new IAToHero();
        information4 = new IAToHero();
        player1 = new Nastybot("Link");
        player2 = new NiceBot("Kirby");
        player3 = new NiceBot("Kazuya");
        hand1 = new ArrayList<>();
        hand2 = new ArrayList<>();
        hand3 = new ArrayList<>();
        hand4 = new ArrayList<>();
        hand1.add(district1);
        hand1.add(district2);
        hand2.add(district2);
        hand3.add(district3);
        player1.setHand(hand1);
        player2.setHand(hand2);
        player3.setHand(hand3);
        heroDeck = Initialization.heroeList(8);
        player1.setRole(heroDeck.get(0));
        player2.setRole(heroDeck.get(1));
        player3.setRole(heroDeck.get(2));
        player3.setCrown();

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players1.add(player1);
        players1.add(player3);
        players2.add(player2);
        players2.add(player3);
        deck = new DistrictDeck(Initialization.districtList());
        deck1 = new DistrictDeck(Initialization.districtList());
        information.setDeck(deck);

        information.setCrownHolder(player2);
        information2.setCrownHolder(player1);

        information.setCurrentPlayer(player2);
        information2.setCurrentPlayer(player3);
        builtDistricts=new ArrayList<>();
        builtDistricts2=new ArrayList<>();
        gold=new ArrayList<>();
        cardCount=new ArrayList<>();
        heros = new ArrayList<>();
        gold1=new ArrayList<>();
        cardCount1=new ArrayList<>();
        heros1 = new ArrayList<>();
        builtDistricts1=new ArrayList<>();
        currentHeroRank1=player2.getHeroRank();
        players.stream().
                filter(player-> player.getHeroRank()!=currentHeroRank1 ).
                forEach(player->{
                    builtDistricts.add(player.getBuiltDistricts());
                    cardCount.add(player.getHand().size());
                    gold.add(player.getGold());
                });
        players.stream().
                filter(player-> player.getHeroRank()<currentHeroRank1).
                forEach(player-> heros.add(player.getRole()));
        List<IPlayer> listTest = new ArrayList<IPlayer>(players);
        information.setInformationForMagician(listTest, player2, deck);
        currentHeroRank2=player3.getHeroRank();
        players1.stream().
                filter(player-> player.getHeroRank()!=currentHeroRank2 ).
                forEach(player->{
                    builtDistricts2.add(player.getBuiltDistricts());
                    cardCount1.add(player.getHand().size());
                    gold1.add(player.getGold());
                });
        players1.stream().
                filter(player-> player.getHeroRank()<currentHeroRank2).
                forEach(player-> heros1.add(player.getRole()));
        listTest = new ArrayList<IPlayer>(players1);
        information2.setInformationForMagician(listTest, player3, deck1);




    }
    @Test
    void getCrownHolderTest(){
        IPlayer player=information.getCrownHolder();
        IPlayer player4=information2.getCrownHolder();
        assertEquals(player2,player);
        assertEquals(player1, player4);
        assertNotEquals(player1,player);
        assertNotEquals(player2,player4);
}
    @Test
    void getChosenPlayerTest(){
        when(mockInfo1.getChosenPlayer()).thenReturn(player1,player2);
        IPlayer player=mockInfo1.getChosenPlayer();
        IPlayer player4=mockInfo1.getChosenPlayer();
        assertEquals(player1,player);
        assertEquals(player2, player4);
        assertNotEquals(player,player3);
        assertNotEquals(player1,player4);
    }
    @Test
    void getCurrentPlayerTest(){
        IPlayer player=information.getCurrentPlayer();
        IPlayer player4=information2.getCurrentPlayer();
        assertEquals(player2,player);
        assertEquals(player3, player4);
        assertNotEquals(player,player3);
        assertNotEquals(player1,player4);
    }
    @Test
    void getDeckTest(){
        deck2=information.getDeck();
        assertEquals(deck2,deck);

    }
    @Test
    void getCardCountTest() {
         cardCount2=information.getCardCount();
        assertEquals(cardCount,cardCount2);
        assertNotEquals(cardCount1,cardCount2);
        assertNotEquals(cardCount1,cardCount2);
    }
    @Test
    void getBuiltDistrictsTest() {
        builtDistricts1 = information.getBuiltDistricts();
        assertEquals(builtDistricts1, builtDistricts);
        assertNotEquals(information2.getBuiltDistricts(), builtDistricts);
    }
    @Test
    void getHerosTest() {
        heros1 = information.getHeros();
        assertEquals(new ArrayList<>(),heros1);
        assertNotEquals(information2.getHeros(), heros);
    }
    @Test
    void getGoldTest() {
        gold1 =  information.getGold();
        assertEquals(gold,gold1);
        assertNotEquals(information2.getGold(), gold);
    }
    @Test
    void setInformationForKingTest() {
        List<IPlayer> listTest = new ArrayList<IPlayer>(players2);
        information3.setInformationForKing(player2,listTest ,treasure);
        assertEquals(information3.getCurrentPlayer(),player2);
        assertEquals(information3.getCrownHolder(), player3);
        assertEquals(information3.getChosenPlayer(),null);
    }
    @Test
    void setInformationForMerchantTest() {
        information4.setInformationForMerchant(player3 ,treasure);
        assertEquals(information4.getCurrentPlayer(),player3);
        assertEquals(information4.getChosenPlayer(),null);

    }
    @Test
    void setInformationForMagicianTest() {
        List<IPlayer> listTest = new ArrayList<IPlayer>(players1);
        information4.setInformationForMagician(listTest, player3, deck1);
        assertEquals(information4.getCurrentPlayer(), player3);
        assertEquals(information4.getDeck(), deck1);
        assertEquals(information4.getBuiltDistricts(), builtDistricts2);
        assertEquals(information4.getGold(), gold1);
        assertEquals(information4.getCardCount(), cardCount1);
        assertEquals(information4.getHeros(), heros1);
    }



}
