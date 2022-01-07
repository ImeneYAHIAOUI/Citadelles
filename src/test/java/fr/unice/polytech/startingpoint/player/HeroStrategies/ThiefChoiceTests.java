package fr.unice.polytech.startingpoint.player.HeroStrategies;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.BOTs.NeutralBot;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;
import fr.unice.polytech.startingpoint.player.IA.Strategies.ThiefChoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class ThiefChoiceTests {
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
    ThiefChoice choice = new ThiefChoice();




    @BeforeEach
    void setUp(){
        player1 = new NeutralBot("Link");
        player2 = new NeutralBot("Kirby");
        player3 = new NeutralBot("Kazuya");
        player4 = new NeutralBot("Yoshi");
        player5 = new NeutralBot("Peach");
        player6 = new NeutralBot("Zelda");
        player7 = new NeutralBot("Wario");
        player8 = new NeutralBot("Bowser");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        heroDeck = Initialization.heroeList(8);
        Mockdeck = mock(DistrictDeck.class);
        information = new IAToHero();
        information2 = new IAToHero();
        information3 = new IAToHero();
        information4 = new IAToHero();
        information5 = new IAToHero();
        mockRand = mock(Random.class);
        when(mockRand.nextInt(anyInt())).thenReturn(0,1,2);
        player1.setRole(heroDeck.get(0));
        heroDeck = Initialization.heroeList(8);
        player2.setRole(heroDeck.get(1));
        heroDeck = Initialization.heroeList(8);
        player3.setRole(heroDeck.get(2));
        heroDeck = Initialization.heroeList(8);

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

        player1.addGold(3);
        player2.addGold(5);
        player3.addGold(6);
        List<IPlayer> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        player3.buildDistrict(District3);
        player3.buildDistrict(District5);
        information.setInformationForAssassinOrThief(players,player1,realDeck);

    }
    @Test
    void ThiefChoice1Test(){

        choice.ThiefChoice(information);
        assertEquals(information.getChosenPlayer(),player3);
    }
    @Test
    void findPlayerWithMaxGold(){
        assertEquals(player2.getName(),choice.findPlayerWithMaxGold(information));
    }



}
