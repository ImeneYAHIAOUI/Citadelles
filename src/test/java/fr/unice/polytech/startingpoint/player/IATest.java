package fr.unice.polytech.startingpoint.player;
import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.cards.district.Laboratory;
import fr.unice.polytech.startingpoint.core.Controller;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.heros.*;
import fr.unice.polytech.startingpoint.heros.character.Assassin;
import fr.unice.polytech.startingpoint.heros.character.Bishop;
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
    IAToHero information;
    IAToHero information2;
    IAToHero information3;
    IAToHero information4;
    IAToHero information5;
    DistrictDeck Mockdeck;
    DistrictDeck realDeck;
    Random mockRand;
    List<IPlayer> players;
    Predicate<IPlayer> canBuild;
    List<IDistrict> districtList;
    List<IDistrict> districtList2;
    List<IDistrict> districtList3;
    IDistrict District1;
    IDistrict District2;
    IDistrict District3;
    IDistrict District4;
    IDistrict District5;
    Treasure treasure;
    Controller controller;


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
        information = new IAToHero();
        information2 = new IAToHero();
        information3 = new IAToHero();
        information4 = new IAToHero();
        information5 = new IAToHero();
        player1.setRole(heroDeck.get(0));
        heroDeck = Initialization.heroeList();
        player2.setRole(heroDeck.get(1));
        heroDeck = Initialization.heroeList();
        player3.setRole(heroDeck.get(2));
        heroDeck = Initialization.heroeList();
        player4.setRole(heroDeck.get(3));
        heroDeck = Initialization.heroeList();
        player5.setRole(heroDeck.get(4));
        heroDeck = Initialization.heroeList();
        player6.setRole(heroDeck.get(5));
        heroDeck = Initialization.heroeList();
        controller = new Controller();

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
        districtList3 = new ArrayList<>();

    }




    @Test
    void activateHeroTestForKing(){
        player2.setCrown();
        player1.addGold(4);
        player1.buildDistrict(District2);
        player1.getDistrict(Mockdeck.giveDistrict(1));
        player1.activateHero(players,Mockdeck,treasure,information);
        assertTrue(player1.getCrown());
        assertFalse(player2.getCrown());
        assertEquals(player1.getGold(),1);
        player1.buildDistrict(District1);
        player1.activateHero(players,Mockdeck,treasure,information);
        assertEquals(player1.getGold(),1);

    }
    @Test
    void activateHeroTestForMerchant(){
        player2.addGold(1);
        player2.buildDistrict(District1);
        player2.activateHero(players,Mockdeck,treasure,information);
        assertEquals(player2.getGold(),1);
        player2.addGold(2);
        player2.buildDistrict(District2);
        player2.activateHero(players,Mockdeck,treasure,information);
        assertEquals(player2.getGold(),2);
    }
    @Test
    void activateHeroTestForMagicianChoosePlayer(){
        districtList.add(District3);
        districtList.add(District2);
        districtList.add(District1);
        player1.setHand(districtList);
        player3.activateHero(players,Mockdeck,treasure,information);
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
        districtList3.add(District5);
        when(Mockdeck.giveDistrict(1)).thenReturn(districtList3);
        player1.setHand(districtList);
        player2.setHand(districtList2);
        player3.setHand(districtList3);
        player3.activateHero(players,realDeck,treasure,information);
        assertEquals(player3.getHand(),districtList2);
        assertEquals(player2.getHand(),districtList3);
        assertNotEquals(player3.getHand(),districtList);
    }
    @Test
    void activateHeroTestForMagicianTestChooseCards(){
        player3.addGold(3);
        districtList.add(District1);
        districtList.add(District5);
        districtList.add(District2);
        when(Mockdeck.giveDistrict(3)).thenReturn(districtList);
        player3.getDistrict(Mockdeck.giveDistrict(3));
        player3.activateHero(players,realDeck,treasure,information);
        assertFalse(player3.getHand().contains(District1));
        assertEquals(player3.getHand().size(),3);
    }
    @Test
    void activateHeroTestForMagicianTestKeepHand(){
        player3.addGold(2);
        districtList.add(District2);
        districtList.add(new Laboratory());
        when(Mockdeck.giveDistrict(2)).thenReturn(districtList);
        player3.setHand(Mockdeck.giveDistrict(2));
        player3.activateHero(players,realDeck,treasure,information);
        assertEquals(player3.getHand(),districtList);
    }
    @Test
    void activateHeroTestForAssassin(){
        player1.addGold(2);
        players.add(player1);
        players.add(player2);
        players.add(player4);
        player1.buildDistrict(District1);
        information.setInformationForAssassin(players,player4,realDeck);
        player4.activateHero(players,realDeck,treasure,information);
        assertNotNull(information.getChosenPlayer());
    }

    @Test
    void activateHeroTestForThief(){
        player1.addGold(2);
        players.add(player1);
        players.add(player2);
        players.add(player5);
        information.setInformationForThief(player5,players,realDeck);
        player5.activateHero(players,realDeck,treasure,information);
        assertNotNull(information.getChosenPlayer());
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
        player1.doAction(treasure,information);
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
        player1.doAction(treasure,information);
        assertEquals(player1.getHand().size(),HandPreviousSize);
        assertEquals(player1.getBuiltDistricts().size(),BuiltDistrictNum);
        assertEquals(player1.getGold(),previousGoldAmount);
        assertEquals(player1.getScore(),previousScore);
    }


    @Test
    void searchForMaxNumberOfCards(){
        player2.setHand(realDeck.giveDistrict(2));
        player3.setHand(realDeck.giveDistrict(3));
        player4.setHand(realDeck.giveDistrict(1));
        player4.setRole(new Assassin());
        players.add(player4);
        information.setInformationForMagician(players,player1,realDeck);
        assertEquals(IA.searchForMaxNumberOfCards(information),3);
        player5.setHand(realDeck.giveDistrict(5));
        player5.setRole(new Bishop());
        players.add(player5);
        information.setInformationForMagician(players,player1,realDeck);
        assertEquals(IA.searchForMaxNumberOfCards(information),5);

    }

    @Test
    void searchForMaxGold(){
        player2.gold =0;
        player3.gold =2;
        player4.gold =3;
        player5.gold = 5;
        player4.setRole(new Assassin());
        players.add(player4);
        player5.setRole(new Bishop());
        players.add(player5);
        information.setInformationForMagician(players,player1,realDeck);
        assertEquals(IA.searchForMaxGold(information),5);
    }



    @Test
    void searchForDoubles(){
        districtList.add(District1);

        districtList.add(District2);
        districtList.add(District5);
        assertEquals(IA.searchForDoubles(districtList,districtList), List.of(District5,District1));
        districtList2.add(District5);
        assertEquals(IA.searchForDoubles(districtList,districtList2), List.of(District5));
    }
    @Test
    void guessHeroTest(){
        districtList.add(District1);
        districtList.add(District2);
        districtList.add(District5);
        assertEquals(IA.guessHero(3,0,districtList,null),HeroName.King);
        assertEquals(IA.guessHero(0,2,null,null),HeroName.Magician);
        assertEquals(IA.guessHero(3,6,districtList2,null),HeroName.Architect);
        assertNotEquals(IA.guessHero(3,0,districtList2,HeroName.Thief),HeroName.Thief);
        assertEquals(IA.guessHero(3,0,districtList2,HeroName.Assassin),HeroName.Thief);
        districtList.remove(District5);
        districtList.add(District3);
        assertEquals(IA.guessHero(3,0,districtList,null),HeroName.Merchant);

    }
    @Test
    void findChosenHeroTest(){
        List<IPlayer> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player5);
        information.setInformationForThief(player5,players,realDeck);
        assertEquals(IA.findChosenHero(HeroName.Magician,information),player3.getRole());
        assertNull(IA.findChosenHero(HeroName.Assassin,information));
        assertNull(IA.findChosenHero(HeroName.Bishop,information));
    }
    @Test
    void addBonusScore(){
        player1.score=0;
        player1.addBonusScore(10);
        assertEquals(player1.getScore(),10);
    }



}
