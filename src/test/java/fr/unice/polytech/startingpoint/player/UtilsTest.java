package fr.unice.polytech.startingpoint.player;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.core.Controller;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.heros.character.Assassin;
import fr.unice.polytech.startingpoint.heros.character.Bishop;
import fr.unice.polytech.startingpoint.player.IA.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

public class UtilsTest {


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
        player1 = new NiceBot("Link");
        player2 = new Nastybot("Kirby");
        player3 = new Nastybot("Kazuya");
        player4 = new NiceBot("Yoshi");
        player5 = new NeutralBot("Peach");
        player6 = new NeutralBot("Zelda");
        player7 = new NeutralBot("Wario");
        player8 = new NeutralBot("Bowser");
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
            District1 = new District(1, Color.YELLOW, DistrictName.MANOIR);
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
    void searchForMaxNumberOfCards(){
        player2.setHand(realDeck.giveDistrict(2));
        player3.setHand(realDeck.giveDistrict(3));
        player4.setHand(realDeck.giveDistrict(1));
        player4.setRole(new Assassin());
        players.add(player4);
        information.setInformationForMagician(players,player1,realDeck);
        assertEquals(Utils.searchForMaxNumberOfCards(information),3);
        player5.setHand(realDeck.giveDistrict(5));
        player5.setRole(new Bishop());
        players.add(player5);
        information.setInformationForMagician(players,player1,realDeck);
        assertEquals(Utils.searchForMaxNumberOfCards(information),5);
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
        assertEquals(Utils.searchForMaxGold(information),5);
    }

    @Test
    void searchForDoubles(){
        districtList.add(District1);

        districtList.add(District2);
        districtList.add(District5);
        assertEquals(Utils.searchForDoubles(districtList,districtList), List.of(District5,District1));
        districtList2.add(District5);
        assertEquals(Utils.searchForDoubles(districtList,districtList2), List.of(District5));
    }

    @Test
    void guessHeroTest(){
        districtList.add(District1);
        districtList.add(District2);
        districtList.add(District5);
        assertEquals(Utils.guessHero(3,1,districtList,null,new ArrayList<>()), HeroName.King);
        assertEquals(Utils.guessHero(0,2,null,null,new ArrayList<>()),HeroName.Magician);
        assertEquals(Utils.guessHero(3,6,districtList2,null,new ArrayList<>()),HeroName.Architect);
        assertNotEquals(Utils.guessHero(3,0,districtList2,HeroName.Thief,new ArrayList<>()),HeroName.Thief);
        assertEquals(Utils.guessHero(3,0,districtList2,HeroName.Assassin,new ArrayList<>()),HeroName.Thief);
        districtList.remove(District5);
        districtList.add(District3);
        assertEquals(Utils.guessHero(3,1,districtList,null,new ArrayList<>()),HeroName.Merchant);

    }
    @Test
    void findChosenHeroTest(){
        List<IPlayer> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player5);
        information.setInformationForAssassinOrThief(players,player5,realDeck);
        assertEquals(Utils.findChosenHero(HeroName.Magician,information),player3.getRole());
        assertNull(Utils.findChosenHero(HeroName.Assassin,information));
        assertNull(Utils.findChosenHero(HeroName.Bishop,information));
    }

}
