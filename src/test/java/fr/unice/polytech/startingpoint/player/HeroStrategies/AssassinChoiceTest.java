package fr.unice.polytech.startingpoint.player.HeroStrategies;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.Information;
import fr.unice.polytech.startingpoint.player.Player;
import fr.unice.polytech.startingpoint.player.Strategies.AssassinChoice;
import fr.unice.polytech.startingpoint.player.Strategies.MagicianStrategies;
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

public class AssassinChoiceTest {
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
    List<IDistrict> districtList3;
    IDistrict District1;
    IDistrict District2;
    IDistrict District3;
    IDistrict District4;
    IDistrict District5;
    Treasure treasure;
    AssassinChoice choice = new AssassinChoice();




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
        player1.setRole(heroDeck.get(0));
        heroDeck = Initialization.heroeList();
        player2.setRole(heroDeck.get(1));
        heroDeck = Initialization.heroeList();
        player3.setRole(heroDeck.get(2));
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
        districtList3 = new ArrayList<>();
        player1.addGold(3);
        player2.addGold(5);
        player3.addGold(6);
        player1.buildDistrict(District1);
        player1.buildDistrict(District5);
        player2.buildDistrict(District2);
        player3.buildDistrict(District3);
        List<IPlayer> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        information.setInformationForAssassin(players,player1,realDeck);
    }
    @Test
    void AssassinChoice1Test(){

        choice.AssassinChoice1(information);
        assertEquals(information.getChosenPlayer(),player3);
    }
    @Test
    void mostAdvancedPlayer(){
        assertEquals(player3.getName(),choice.mostAdvancedPlayer(information.getBuiltDistricts(),information.getScores(),information.getPlayersName()));
    }




}
