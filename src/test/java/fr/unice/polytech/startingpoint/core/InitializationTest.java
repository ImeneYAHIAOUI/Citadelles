package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.cards.District;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InitializationTest {
    List<IDistrict> listTest = null;
    int length = 0;

    @BeforeEach
    void setUp() {
        this.listTest = new ArrayList<IDistrict>();
        this.length = 8;
    }

    /**
     * Test the length of the returned district list
     */
    @Test
    void testDistrictList(){
        this.listTest = Initialization.districtList();
        assertEquals(this.listTest.size(), this.length);
    }
}