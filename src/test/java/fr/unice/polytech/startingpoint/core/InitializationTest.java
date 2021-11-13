package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.cards.District;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InitializationTest {
    List<District> listTest = null;
    int length = 0;

    @BeforeEach
    void setUp() {
        this.listTest = new ArrayList<District>();
        this.length = 11;
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