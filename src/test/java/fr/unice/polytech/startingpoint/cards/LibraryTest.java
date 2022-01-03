package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.cards.district.Library;
import fr.unice.polytech.startingpoint.core.Initialization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class LibraryTest {
    Library library;

    DistrictDeck deck;


    @BeforeEach
    void setUp() {
        this.library = new Library();
        deck = new DistrictDeck(Initialization.districtList());



    }


    @Test
    void GetnameTest() {
        assertEquals(this.library.getDistrictName(), DistrictName.LIBRARY);
        assertNotEquals(this.library.getDistrictName(), DistrictName.MANOIR);
        assertNotEquals(this.library.getDistrictName(), DistrictName.LACOURDESMIRACLES);
        assertNotEquals(this.library, DistrictName.CHATEAU);
        assertNotEquals(this.library, DistrictName.PALAIS);
        assertNotEquals(this.library, DistrictName.TAVERNE);
        assertNotEquals(this.library, DistrictName.MARCHE);
        assertNotEquals(this.library, DistrictName.ECHAPPE);
        assertNotEquals(this.library, DistrictName.LABORATOIRE);
    }

    @Test
    void Getdescriptiontest() {
        String desp = "Si vous choisissez de" +
                "piocher des cartes au " +
                "début de votre tour," +
                "vous en piochez deux " +
                "et les conservez " +
                "toutes les deux.\n";
        String desp2 = null;
        assertEquals(this.library.getDescription(), desp);
        assertNotEquals(this.library.getDescription(), desp2);

    }
    @Test
    void wondertest(){
        assertTrue(this.library.isWonder());
        assertNotEquals(this.library.isWonder(),false);

    }

}
