package fr.unice.polytech.startingpoint.cards;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistrictTest {
    District district = null;

    @BeforeEach
    void setUp() {
        this.district = new District(2,Color.BLUE,"Château");
    }

    /**
     * District price test
     */
    @Test
    void testGetPrice(){
        assertEquals(this.district.getPrice(), 2);
    }

    /**
     * District color test
     */
    @Test
    void testGetColor(){
        assertEquals(this.district.getColor(), Color.BLUE);
    }

    /**
     * District is wonder test
     */
    @Test
    void testIsWonder(){
        assertEquals(this.district.isWonder(), false);
    }
}