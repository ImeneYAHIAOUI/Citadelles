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
    void testColor(){
        this.color = Color.BLUE;
        assertEquals(Color.BLUE, color);

        this.color = Color.YELLOW;
        assertEquals(Color.YELLOW, color);

        this.color = Color.GREEN;
        assertEquals(Color.GREEN, color);

        this.color = Color.RED;
        assertEquals(Color.RED, color);

        this.color = Color.PURPLE;
        assertEquals(Color.PURPLE, color);
    }
}