package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.cards.district.Library;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.Information;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibraryTest {
    Library library;

    DistrictDeck deck;
    infoaction info;
    private Treasure Tresor;
    private Treasure Tresor2;


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


}
