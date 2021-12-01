package fr.unice.polytech.startingpoint.cards;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreasureTest {
    Treasure treasure;
    Treasure treasure1;

    @BeforeEach
    void setUp() {
        treasure = new Treasure(32);
        treasure1 = new Treasure(30);
    }

    @Test
    void getPiecesTest(){
        assertEquals(32,treasure.getPieces());
        assertEquals(30,treasure1.getPieces());
    }
    @Test
    void removeGoldTest(){
        int gold=treasure.removeGold(2);
        assertEquals(treasure1.getPieces(),treasure.getPieces());
        assertEquals(30,treasure.getPieces());
        assertEquals(2,gold);
         gold=treasure.removeGold(50);
         assertEquals(30,gold);
         assertEquals(0,treasure.getPieces());
         gold=treasure1.removeGold(30);
         assertEquals(30,gold);
         assertEquals(treasure1.getPieces(),0);

    }
    @Test
    void isEnoughTest(){
        assertEquals(false,treasure.isEnough(60));
        assertEquals(true,treasure.isEnough(2));
        assertEquals(true,treasure.isEnough(30));
    }
    @Test

    void addToTreasureTest(){
        treasure1.addToTreasure(2);
        assertEquals(treasure1.getPieces(),treasure.getPieces());
        assertEquals(32,treasure1.getPieces());
    }

}
