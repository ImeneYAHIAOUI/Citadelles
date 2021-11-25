package fr.unice.polytech.startingpoint.cards;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest {
    private Color color;

    @BeforeEach
    void setUp() {

    }

    /**
     * Test the different possible colors of the cards
     */
    @Test
    void testBlueColor() {
        this.color = Color.BLUE;
        assertEquals(Color.BLUE, color);
        assertNotEquals(Color.YELLOW,color);
        assertNotEquals(Color.PURPLE,color);
        assertNotEquals(Color.GREEN,color);
        assertNotEquals(Color.RED,color);
    }

    @Test
    void testYellowColor() {
        this.color = Color.YELLOW;
        assertNotEquals(Color.BLUE, color);
        assertEquals(Color.YELLOW,color);
        assertNotEquals(Color.PURPLE,color);
        assertNotEquals(Color.GREEN,color);
        assertNotEquals(Color.RED,color);
    }

    @Test
    void testGreenColor() {
        this.color = Color.GREEN;
        assertNotEquals(Color.BLUE, color);
        assertNotEquals(Color.YELLOW,color);
        assertNotEquals(Color.PURPLE,color);
        assertEquals(Color.GREEN,color);
        assertNotEquals(Color.RED,color);
    }

    @Test
    void testRedColor() {
        this.color = Color.RED;
        assertNotEquals(Color.BLUE, color);
        assertNotEquals(Color.YELLOW,color);
        assertNotEquals(Color.PURPLE,color);
        assertNotEquals(Color.GREEN,color);
        assertEquals(Color.RED,color);
    }

    @Test
    void testPurpleColor(){
        this.color = Color.PURPLE;
        assertNotEquals(Color.BLUE, color);
        assertNotEquals(Color.YELLOW,color);
        assertEquals(Color.PURPLE,color);
        assertNotEquals(Color.GREEN,color);
        assertNotEquals(Color.RED,color);
    }
}