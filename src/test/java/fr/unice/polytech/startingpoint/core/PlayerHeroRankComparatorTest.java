package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.NeutralBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerHeroRankComparatorTest {
    IA player1;
    IA player2;
    IA player3;
    HeroDeck heroes;
    PlayerHeroRankComparator comp;
    int result;
    int result1;
    int result3;
    @BeforeEach
    void setUp() {
        player1 = new NeutralBot("link");
        player2 = new NeutralBot("Yoshi");
        player3 = new NeutralBot("Kirby");
        heroes = Initialization.heroeList(3);
        player1.setRole(heroes.get(0));
        player2.setRole(heroes.get(1));
        player3.setRole(heroes.get(2));
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
