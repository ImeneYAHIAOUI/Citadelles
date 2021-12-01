package fr.unice.polytech.startingpoint.player;
import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.heros.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IATest {
    IA player1 ;
    IA player2 ;
    IA player3 ;
    IA player4 ;
    IA player5 ;
    IA player6 ;
    IA player7 ;
    IA player8 ;
    HeroDeck heroDeck;
    IHero hero1;
    IHero hero2;
    IHero hero3;
    Information information;
    Information information2;
    Information information3;
    Information information4;
    Information information5;
    DistrictDeck Mockdeck;
    DistrictDeck realDeck;
    Random mockRand;
    List<IPlayer> players;
    Predicate<IPlayer> canBuild;
    List<IDistrict> districtList;
    List<IDistrict> districtList2;
    IDistrict District1;
    IDistrict District2;
    IDistrict District3;
    IDistrict District4;
    IDistrict District5;
    Treasure treasure;




    @BeforeEach
    void setUp(){
        player1 = new IA("Link");
        player2 = new IA("Kirby");
        player3 = new IA("Kazuya");
        player4 = new IA("Yoshi");
        player5 = new IA("Peach");
        player6 = new IA("Zelda");
        player7 = new IA("Wario");
        player8 = new IA("Bowser");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        heroDeck = Initialization.heroeList();
        Mockdeck = mock(DistrictDeck.class);
        information = new Information();
        information2 = new Information();
        information3 = new Information();
        information4 = new Information();
        information5 = new Information();
        mockRand = mock(Random.class);
        when(mockRand.nextInt(anyInt())).thenReturn(0,1,2);
        player1.chooseHero(heroDeck,mockRand.nextInt(anyInt()));
        heroDeck = Initialization.heroeList();
        player2.chooseHero(heroDeck,mockRand.nextInt(anyInt()));
        heroDeck = Initialization.heroeList();
        player3.chooseHero(heroDeck,mockRand.nextInt(anyInt()));
        heroDeck = Initialization.heroeList();

        treasure=new Treasure(32);
        canBuild = player -> player.getHand().stream().anyMatch(d -> d.getPrice()<=player.getGold());
        districtList = new ArrayList<>();
        districtList2 = new ArrayList<>();
        try {
            District1 = new District(1, Color.YELLOW,DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            District2 =new District(3,Color.GREEN,DistrictName.TAVERNE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            District3 =new District(5,Color.GREEN,DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }try {
            District4 =new District(3,Color.YELLOW,DistrictName.PALAIS);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            District5 =new District(1,Color.YELLOW,DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }
        realDeck = new DistrictDeck(Initialization.districtList());


    }


    @Test
    void chooseHeroTest(){
        hero1 = heroDeck.get(0);
        when(mockRand.nextInt(anyInt())).thenReturn(0,1,2);
        player1.chooseHero(heroDeck,mockRand.nextInt(anyInt()));
        assertEquals(player1.getRole(),hero1);

        assertFalse(heroDeck.contains(hero1));
        heroDeck = Initialization.heroeList();
        hero2 = heroDeck.get(1);
        player2.chooseHero(heroDeck,mockRand.nextInt(anyInt()));
        assertEquals(player2.getRole(),hero2);
        assertFalse(heroDeck.contains(hero2));
        heroDeck = Initialization.heroeList();
        hero3 = heroDeck.get(2);
        player3.chooseHero(heroDeck,mockRand.nextInt(anyInt()));
        assertEquals(player3.getRole(),hero3);
        assertFalse(heroDeck.contains(hero3));

    }
    @Test
    void chooseHeroTestForbidenValues(){
        when(mockRand.nextInt(anyInt())).thenReturn(3,-1);
        assertThrows(RuntimeException.class,() -> player4.chooseHero(heroDeck,mockRand.nextInt(anyInt())));
        heroDeck = Initialization.heroeList();
        assertThrows(RuntimeException.class,() -> player5.chooseHero(heroDeck,mockRand.nextInt(anyInt())));
    }

    @Test
    void activateHeroTestForKing(){
        player2.setCrown();
        player1.addGold(4);
        player1.buildDistrict(District2);
        player1.getDistrict(Mockdeck.giveDistrict(1));
        player1.activateHero(players,Mockdeck,treasure);
        assertTrue(player1.getCrown());
        assertFalse(player2.getCrown());
        assertEquals(player1.getGold(),1);
        player1.buildDistrict(District1);
        player1.activateHero(players,Mockdeck,treasure);
        assertEquals(player1.getGold(),1);

    }
    @Test
    void activateHeroTestForMerchant(){
        player2.addGold(1);
        player2.buildDistrict(District1);
        player2.activateHero(players,Mockdeck,treasure);
        assertEquals(player2.getGold(),1);
        player2.addGold(2);
        player2.buildDistrict(District2);
        player2.activateHero(players,Mockdeck,treasure);
        assertEquals(player2.getGold(),2);
    }
    @Test
    void activateHeroTestForMagicianChoosePlayer(){
        districtList.add(District3);
        districtList.add(District2);
        districtList.add(District1);
        player1.setHand(districtList);
        player3.activateHero(players,Mockdeck,treasure);
        assertEquals(player3.getHand(),districtList);
        assertEquals(player1.getHand(),new ArrayList<>());

    }
    @Test
    void activateHeroTestForMagicianChoosePlayer2(){
        player1.gold = 3;
        player2.gold = 2;
        player3.gold = 0;
        districtList = realDeck.giveDistrict(1);
        districtList2 = realDeck.giveDistrict(1);
        List<IDistrict> districtList3 = realDeck.giveDistrict(1);
        player1.setHand(districtList);
        player2.setHand(districtList2);
        player3.setHand(districtList3);
        player3.activateHero(players,realDeck,treasure);
        assertEquals(player3.getHand(),districtList2);
        assertEquals(player2.getHand(),districtList3);
        assertNotEquals(player3.getHand(),districtList);


    }
    @Test
    void magicienChoiceTestChoosePlayer() {
        districtList.add(District2);
        districtList.add(District2);
        districtList.add(District1);
        districtList2.add(District1);
        districtList2.add(District2);
        player1.getDistrict(districtList);
        player2.getDistrict(districtList2);
        information.setInformationForMagician(players, player3, Mockdeck);
        player3.magicienChoice(information);
        assertEquals(information.getChosenPlayer(), player1);
        assertEquals(information.getChosenCards().size(), 0);
    }


    @Test
    void magicienChoiceTestChoosePlayer2(){
        player1.gold = 0;
        player2.gold = 2;
        player3.gold = 3;
        player1.setHand(realDeck.giveDistrict(1));
        player2.setHand(realDeck.giveDistrict(1));
        player3.setHand(realDeck.giveDistrict(1));
        information4.setInformationForMagician(players,player1,realDeck);
        player1.magicienChoice(information4);
        assertEquals(information4.getChosenPlayer(),player2);
        assertEquals(information4.getChosenCards().size(),0);

    }
    @Test
    void magicienChoiceTestChoosePlayer3(){
        player1.gold = 1;
        player2.gold = 2;
        player3.gold = 2;
        districtList.add(District3);
        when(Mockdeck.giveDistrict(1)).thenReturn(districtList);
        player1.setHand(Mockdeck.giveDistrict(1));
        player2.setHand(realDeck.giveDistrict(2));
        information4.setInformationForMagician(players,player1,realDeck);
        player1.magicienChoice(information4);
        assertEquals(information4.getChosenPlayer(),player2);
        assertEquals(information4.getChosenCards().size(),0);
    }

    @Test
    void magicienChoiceTestChooseCards() {
        player3.addGold(3);
        districtList.add(District1);
        districtList.add(District5);
        districtList.add(District2);
        when(Mockdeck.giveDistrict(3)).thenReturn(districtList);
        information2.setInformationForMagician(players, player3, Mockdeck);
        player3.getDistrict(Mockdeck.giveDistrict(3));
        player3.magicienChoice(information2);
        assertTrue(information2.getChosenCards().size() > 0);
        assertNull(information2.getChosenPlayer());
        assertTrue(information2.getChosenCards().contains(District1));
    }
    @Test
    void magicienChoiceTestKeepHand() {
        player1.addGold(2);
        districtList.add(District2);
        districtList.add(new Laboratory());
        player1.hand.clear();
        when(Mockdeck.giveDistrict(2)).thenReturn(districtList);
        player1.getDistrict(Mockdeck.giveDistrict(2));
        information3.setInformationForMagician(players, player1, Mockdeck);
        player1.magicienChoice(information3);
        assertNull(information3.getChosenPlayer());
        assertEquals(information3.getChosenCards().size(), 0);
    }
    @Test
    void magicienChoiceTestKeepHand2(){
        player1.gold = 0;
        districtList.clear();
        districtList.add(District3);
        player2.gold = 1;
        player3.gold = 3;
        when(Mockdeck.giveDistrict(1)).thenReturn(districtList);
        information4.setInformationForMagician(players,player1,Mockdeck);
        player1.setHand(Mockdeck.giveDistrict(1));
        player1.magicienChoice(information4);
        assertNull(information4.getChosenPlayer());
        assertEquals(information4.getChosenCards().size(),0);
    }
    @Test
    void doActionTestBuild(){
        player1.addGold(2);
        districtList.add(District1);
        districtList.add(District2);

        when(Mockdeck.giveDistrict(2)).thenReturn(districtList);
        player1.getDistrict(Mockdeck.giveDistrict(2));
        int HandPreviousSize = player1.getHand().size();
        int BuiltDistrictNum = player1.getBuiltDistricts().size();
        int previousGoldAmount = player1.getGold();
        int previousScore = player1.getScore();
        player1.doAction(treasure);
        assertEquals(player1.getHand().size(),HandPreviousSize-1);
        assertEquals(player1.getBuiltDistricts().size(),BuiltDistrictNum+1);
        assertEquals(player1.getGold(),previousGoldAmount- District1.getPrice());
        assertEquals(player1.getScore(),previousScore+District1.getPrice());


    }
    @Test
    void doActionDontBuild(){
        player1.gold = 0;
        districtList.add(District1);
        districtList.add(District2);
        when(Mockdeck.giveDistrict(2)).thenReturn(districtList);
        player1.getDistrict(Mockdeck.giveDistrict(2));
        int HandPreviousSize = player1.getHand().size();
        int BuiltDistrictNum = player1.getBuiltDistricts().size();
        int previousGoldAmount = player1.getGold();
        int previousScore = player1.getScore();
        player1.doAction(treasure);
        assertEquals(player1.getHand().size(),HandPreviousSize);
        assertEquals(player1.getBuiltDistricts().size(),BuiltDistrictNum);
        assertEquals(player1.getGold(),previousGoldAmount);
        assertEquals(player1.getScore(),previousScore);
    }


    @Test
    void drawOrGetGoldTestDraw(){
        districtList.add(District1);
        districtList2.add(District3);
        when(Mockdeck.giveDistrict(1)).thenReturn(districtList);
        player4.drawOrGetPieces(Mockdeck,treasure);
        assertTrue(player4.getHand().contains(District1));
        districtList.clear();
        districtList.add(District1);
        when(Mockdeck.giveDistrict(1)).thenReturn(districtList);
        player1.getDistrict(districtList2);
        player1.drawOrGetPieces(Mockdeck,treasure);
        assertTrue(player1.getHand().contains(District1));
        player1.drawOrGetPieces(Mockdeck,treasure);
        assertTrue(player1.getHand().contains(District3));

    }

    @Test
    void drawOrGetGoldTestGetGold(){
        districtList.add(District1);
        player5.setHand(districtList);
        player5.drawOrGetPieces(realDeck,treasure);
        assertEquals(player5.getGold(),2);
        districtList.clear();
        districtList.add(District4);
        player5.setHand(districtList);
        player5.drawOrGetPieces(realDeck,treasure);
        assertEquals(player5.getGold(),4);
    }

}
