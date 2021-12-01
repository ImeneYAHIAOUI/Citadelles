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
        treasure.removeGold(2);
        assertEquals(treasure1.getPieces(),treasure.getPieces());
        assertEquals(30,treasure.getPieces());

    }
    @Test
    void addToTreasureTest(){
        treasure1.addToTreasure(2);
        assertEquals(treasure1.getPieces(),treasure.getPieces());
        assertEquals(32,treasure1.getPieces());
    }

}
