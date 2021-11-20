package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.core.Initialization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DistrictDeckTest {
    DistrictDeck districtDeck;
    int deckLength;

    @BeforeEach
    void setUp() {
        this.districtDeck = new DistrictDeck(Initialization.districtList());
        this.deckLength = 8;
    }

    /**
     * Test the deck size
     */
    @Test
    void testDistrictDeck(){
        assertEquals(this.districtDeck.getDeckSize(), this.deckLength);
    }

    /**
     * Test getting card from deck.
     * When taking n cards, the deck size must decrease by n
     */
    @Test
    void testGiveDistrict(){
        int givenListLength = 2;
        List<IDistrict> districtList = this.districtDeck.giveDistrict(givenListLength);
        assertEquals(districtList.size(),givenListLength);
        assertEquals(this.districtDeck.getDeckSize(), this.deckLength-givenListLength);
    }
}