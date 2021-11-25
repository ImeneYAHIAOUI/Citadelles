package fr.unice.polytech.startingpoint.cards;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistrictTest {
    District district1 = null;
    District district2 = null;
    District districtError = null;

    @BeforeEach
    void setUp() {
        try {
            this.district1 = new District(2,Color.BLUE,DistrictName.CHATEAU);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district2 = new District(10,Color.YELLOW,DistrictName.TAVERNE);
        } catch (CardException e) {
            e.printStackTrace();
        }
    }

    /**
     * District price test
     */
    @Test
    void testGetPrice(){
        assertEquals(this.district1.getPrice(), 2);
        assertNotEquals(this.district1.getPrice(), 3);

        assertEquals(this.district2.getPrice(), 10);
        assertNotEquals(this.district2.getPrice(), 2);
    }
/*
    @Test
    void testErrorInitPrice(){
        CardException throw =Assertions.assertThrows(CardException.class, new District(-2,Color.PURPLE,DistrictName.MARCHE))

        assertThrows(new District(-2,Color.PURPLE,DistrictName.MARCHE),CardException);
    }
*/
    /**
     * District color test
     */
    @Test
    void testGetColor(){
        assertEquals(this.district1.getColor(), Color.BLUE);
        assertNotEquals(this.district1.getColor(), Color.GREEN);
        assertNotEquals(this.district1.getColor(), Color.YELLOW);
        assertNotEquals(this.district1.getColor(), Color.PURPLE);
        assertNotEquals(this.district1.getColor(), Color.RED);

        assertNotEquals(this.district2.getColor(), Color.BLUE);
        assertNotEquals(this.district2.getColor(), Color.GREEN);
        assertEquals(this.district2.getColor(), Color.YELLOW);
        assertNotEquals(this.district2.getColor(), Color.PURPLE);
        assertNotEquals(this.district2.getColor(), Color.RED);
    }

    @Test
    void testGetName(){
        assertNotEquals(this.district1.getDistrictName(), DistrictName.MANOIR);
        assertEquals(this.district1.getDistrictName(), DistrictName.CHATEAU);
        assertNotEquals(this.district1.getDistrictName(), DistrictName.TAVERNE);
        assertNotEquals(this.district1.getDistrictName(), DistrictName.MARCHE);
        assertNotEquals(this.district1.getDistrictName(), DistrictName.PALAIS);
        assertNotEquals(this.district1.getDistrictName(), DistrictName.LABORATOIRE);
        assertNotEquals(this.district1.getDistrictName(), DistrictName.LACOURDESMIRACLES);
        assertNotEquals(this.district1.getDistrictName(), DistrictName.ECHAPPE);

        assertNotEquals(this.district2.getDistrictName(), DistrictName.MANOIR);
        assertNotEquals(this.district2.getDistrictName(), DistrictName.CHATEAU);
        assertEquals(this.district2.getDistrictName(), DistrictName.TAVERNE);
        assertNotEquals(this.district2.getDistrictName(), DistrictName.MARCHE);
        assertNotEquals(this.district2.getDistrictName(), DistrictName.PALAIS);
        assertNotEquals(this.district2.getDistrictName(), DistrictName.LABORATOIRE);
        assertNotEquals(this.district2.getDistrictName(), DistrictName.LACOURDESMIRACLES);
        assertNotEquals(this.district2.getDistrictName(), DistrictName.ECHAPPE);
    }

    /**
     * District is wonder test
     */
    @Test
    void testIsWonder(){
        assertEquals(this.district1.isWonder(), false);
        assertEquals(this.district2.isWonder(), false);
    }
}