package fr.unice.polytech.startingpoint.player;
import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.heros.IHero;
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
    DistrictDeck deck;
    Random mockRand;
    List<IPlayer> players;
    Predicate<IPlayer> canBuild;



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
        deck = new DistrictDeck(Initialization.districtList());
        hero1 = heroDeck.get(0);
        hero2 = heroDeck.get(1);
        hero3 = heroDeck.get(2);
        information = new Information();
        information2 = new Information();
        information3 = new Information();
        mockRand = mock(Random.class);
        when(mockRand.nextInt(anyInt())).thenReturn(0,1,2);
        player1.chooseHero(heroDeck,mockRand.nextInt(anyInt()));
        heroDeck = Initialization.heroeList();
        player2.chooseHero(heroDeck,mockRand.nextInt(anyInt()));
        heroDeck = Initialization.heroeList();
        player3.chooseHero(heroDeck,mockRand.nextInt(anyInt()));
        player1.getDistrict(deck.giveDistrict(4));
        player2.getDistrict(deck.giveDistrict(4));
        player3.getDistrict(deck.giveDistrict(4));
        canBuild = player -> player.getHand().stream().anyMatch(d -> d.getPrice()<=player.getGold());

    }


    @Test
    void chooseHeroTest(){

        when(mockRand.nextInt(anyInt())).thenReturn(0,1,2,4,-1);


        player1.chooseHero(heroDeck,mockRand.nextInt(anyInt()));

        assertEquals(player1.getRole(),hero1);
        assertFalse(heroDeck.contains(hero1));
        heroDeck = Initialization.heroeList();


        player2.chooseHero(heroDeck,mockRand.nextInt(anyInt()));
        assertEquals(player2.getRole(),hero2);
        assertFalse(heroDeck.contains(hero2));

        heroDeck = Initialization.heroeList();

        player3.chooseHero(heroDeck,mockRand.nextInt(anyInt()));
        assertEquals(player3.getRole(),hero3);
        assertFalse(heroDeck.contains(hero3));

        heroDeck = Initialization.heroeList();

        assertThrows(RuntimeException.class,() -> player4.chooseHero(heroDeck,mockRand.nextInt(anyInt())));

        heroDeck = Initialization.heroeList();

        assertThrows(RuntimeException.class,() -> player5.chooseHero(heroDeck,mockRand.nextInt(anyInt())));

    }

    @Test
    void activateHeroTest(){
        player2.setCrown();

        player1.activateHero(players,deck,information);
        assertNotNull(information.getCrownHolder());
        assertNotNull(information.getCurrentPlayer());
        assertNull(information.getCardCount());
        assertNull(information.getGold());
        assertNull(information.getDeck());
        assertNull(information.getChosenPlayer());
        assertNull(information.getBuiltDistricts());
        assertNull(information.getHeros());
        assertNull(information.getChosenCards());

        player2.activateHero(players,deck,information2);
        assertNull(information2.getCrownHolder());
        assertNotNull(information2.getCurrentPlayer());
        assertNull(information2.getCardCount());
        assertNull(information2.getGold());
        assertNull(information2.getDeck());
        assertNull(information2.getChosenPlayer());
        assertNull(information2.getBuiltDistricts());
        assertNull(information2.getHeros());
        assertNull(information2.getChosenCards());

        player3.activateHero(players,deck,information3);
        assertNull(information3.getCrownHolder());
        assertTrue(information3.getCurrentPlayer()!=null || information3.getChosenCards()!=null);
        assertNotNull(information3.getCardCount());
        assertNotNull(information3.getGold());
        assertNotNull(information3.getDeck());
        assertNotNull(information3.getChosenPlayer());
        assertNotNull(information3.getBuiltDistricts());
        assertNotNull(information3.getHeros());

    }

    @Test
    void magicienChoiceTest(){

        information.setInformationForMagician(players,player3,deck);
        player3.magicienChoice(information,players);
        assertTrue(information.getCurrentPlayer()!=null || information.getChosenCards()!=null);
    }
    @Test
    void doActionTest(){

        int HandPreviousSize = player1.getHand().size();
        int BuiltDistrictNum = player1.getBuiltDistricts().size();
        int previousGoldAmount = player1.getGold();
        int previousScore = player1.getScore();
        while(canBuild.test(player1)){
            player1.doAction();
            IDistrict builtDistrict = player1.getBuiltDistricts().get(BuiltDistrictNum);
            assertEquals(player1.getHand().size(),HandPreviousSize-1);
            assertEquals(player1.getBuiltDistricts().size(),BuiltDistrictNum+1);
            assertEquals(player1.getGold(),previousGoldAmount- builtDistrict.getPrice());
            assertEquals(player1.getScore(),previousScore+builtDistrict.getPrice());
            HandPreviousSize = player1.getHand().size();
            BuiltDistrictNum = player1.getBuiltDistricts().size();
            previousGoldAmount = player1.getGold();
            previousScore = player1.getScore();
        }
        player1.doAction();
        assertEquals(player1.getHand().size(),HandPreviousSize);
        assertEquals(player1.getBuiltDistricts().size(),BuiltDistrictNum);
        assertEquals(player1.getGold(),previousGoldAmount);
        assertEquals(player1.getScore(),previousScore);

    }

    @Test
    void drawOrGetGoldTest(){
        
    }

}
