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
    HeroDeck deck;
    IHero hero1;
    IHero hero2;
    IHero hero3;
    IHero hero4;


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

        deck = Initialization.heroeList();
        player1.HaveTheListOfHeroes(deck);
        player2.HaveTheListOfHeroes(deck);
        player3.HaveTheListOfHeroes(deck);
        player4.HaveTheListOfHeroes(deck);
        player5.HaveTheListOfHeroes(deck);
        player6.HaveTheListOfHeroes(deck);
        player7.HaveTheListOfHeroes(deck);
        player8.HaveTheListOfHeroes(deck);



    }

    @Test
    void chooseHeroTest(){
        Random mockRand = mock(Random.class);



    }

    @Test
    void activateHeroTest(){


    }
}
