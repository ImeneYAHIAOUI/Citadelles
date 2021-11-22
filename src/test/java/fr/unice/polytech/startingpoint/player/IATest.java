package fr.unice.polytech.startingpoint.player;
import fr.unice.polytech.startingpoint.cards.DistrictName;
import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.District;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.heros.IHero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        heroDeck = Initialization.heroeList();




    }

    @Test
    void chooseHeroTest(){
        Random mockRand = mock(Random.class);
        when(mockRand.nextInt(anyInt())).thenReturn(0,1,2,4,-1);

        IHero hero1 = heroDeck.get(0);

        player1.chooseHero(heroDeck,mockRand.nextInt(anyInt()));

        assertEquals(player1.getRole(),hero1);
        assertFalse(heroDeck.contains(hero1));
        heroDeck = Initialization.heroeList();

        IHero hero2 = heroDeck.get(1);

        player2.chooseHero(heroDeck,mockRand.nextInt(anyInt()));
        assertEquals(player2.getRole(),hero2);
        assertFalse(heroDeck.contains(hero2));

        heroDeck = Initialization.heroeList();

        IHero hero3 = heroDeck.get(2);

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


    }

    @Test
    void magicienChoiceTest(){

    }
}
