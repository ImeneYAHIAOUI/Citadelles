package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerHeroRankComparatorTest {
    IPlayer player1;
    IPlayer player2;
    IPlayer player3;
    HeroDeck heroes;
    PlayerHeroRankComparator comp;
    int result;
    int result1;
    int result3;
    @BeforeEach
    void setUp() {
        player1 = new IA("link");
        player2 = new IA("Yoshi");
        player3 = new IA("Kirby");
        heroes = Initialization.heroeList();
        player1.chooseHero(heroes,0);
        player2.chooseHero(heroes,0);
        player3.chooseHero(heroes,0);
        comp =new PlayerHeroRankComparator();

    }
    @Test
    void compareTest(){
        result=comp.compare(player1,player2);
        assertEquals(result,1);
        result1=comp.compare(player1,player1);
        assertEquals(result1,0);
        result3=comp.compare(player1,player3);
        assertEquals(result3,-1);


    }
}
