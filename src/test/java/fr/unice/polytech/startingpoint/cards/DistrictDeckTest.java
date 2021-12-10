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
        this.deckLength = 64;
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
    void testGive2District(){
        int givenListLength = 2;
        List<IDistrict> districtList = this.districtDeck.giveDistrict(givenListLength);
        assertEquals(districtList.size(),givenListLength);
        assertEquals(this.districtDeck.getDeckSize(), this.deckLength-givenListLength);
    }

    @Test
    void testGive0District(){
        this.districtDeck = new DistrictDeck(Initialization.districtList());
        assertEquals(this.districtDeck.getDeckSize(),this.deckLength);

        int givenListLength = 0;
        List<IDistrict> districtList = this.districtDeck.giveDistrict(givenListLength);
        assertEquals(districtList.size(),givenListLength);
        assertEquals(this.districtDeck.getDeckSize(), this.deckLength-givenListLength);
    }

    @Test
    void testGiveNegativeDistrict(){
        this.districtDeck = new DistrictDeck(Initialization.districtList());
        assertEquals(this.districtDeck.getDeckSize(),this.deckLength);

        int givenListLength = -2;
        List<IDistrict> districtList = this.districtDeck.giveDistrict(givenListLength);
        assertEquals(districtList.size(),0);
        assertEquals(this.districtDeck.getDeckSize(), this.deckLength);
    }

    @Test
    void testGiveMoreThanPossibleDistrict(){
        this.districtDeck = new DistrictDeck(Initialization.districtList());
        assertEquals(this.districtDeck.getDeckSize(),this.deckLength);

        int givenListLength = this.districtDeck.getDeckSize() + 5;
        List<IDistrict> districtList = this.districtDeck.giveDistrict(givenListLength);
        assertEquals(districtList.size(),this.deckLength);
        assertEquals(this.districtDeck.getDeckSize(), 0);
    }

    @Test
    void testGiveDistrict(){
        this.districtDeck = new DistrictDeck(Initialization.districtList());
        assertEquals(this.districtDeck.getDeckSize(),this.deckLength);
        int givenListLength = 3;
        List<IDistrict> districtList = this.districtDeck.giveDistrict(givenListLength);
    }
}