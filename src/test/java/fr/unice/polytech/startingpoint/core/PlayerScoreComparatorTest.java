package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.player.IA.BOTs.NeutralBot;
import fr.unice.polytech.startingpoint.player.IPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerScoreComparatorTest {
    IPlayer player1;
    IPlayer player2;
    IPlayer player3;
    IPlayer player4;
    PlayerScoreComparator comp;
    int result;
    int result1;
    int result3;
    @BeforeEach
    void setUp() {
        player1 = new NeutralBot("link");
        player2 = new NeutralBot("Yoshi");
        player3 = new NeutralBot("Kirby");
        player4 = new NeutralBot("Mery");

        player1.setScore(15);
        player2.setScore(9000);
        player3.setScore(2);
        player4.setScore(15);
        comp =new PlayerScoreComparator();

    }
    @Test
    void compareTest(){
        result=comp.compare(player1,player2);
        assertEquals(result,-1);
        result1=comp.compare(player1,player4);
        assertEquals(result1,0);
        result3=comp.compare(player2,player3);
        assertEquals(result3,1);


    }
}
