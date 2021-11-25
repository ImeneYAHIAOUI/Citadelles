package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.core.Initialization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class courtofmiraclesTest {
    CourtOfMiracles courtofmirales;
    DistrictDeck deck;
    infoaction info;


    @BeforeEach
    void setUp() {
        this.courtofmirales = new CourtOfMiracles();
        deck = new DistrictDeck(Initialization.districtList());

    }


    @Test
    void GetnameTest() {
        assertEquals(this.courtofmirales.getDistrictName(), DistrictName.LACOURDESMIRACLES);
        assertNotEquals(this.courtofmirales.getDistrictName(), DistrictName.MANOIR);
        assertNotEquals(this.courtofmirales.getDistrictName(), DistrictName.LABORATOIRE);
        assertNotEquals(this.courtofmirales, DistrictName.CHATEAU);
        assertNotEquals(this.courtofmirales, DistrictName.PALAIS);
        assertNotEquals(this.courtofmirales, DistrictName.TAVERNE);
        assertNotEquals(this.courtofmirales, DistrictName.MARCHE);
        assertNotEquals(this.courtofmirales, DistrictName.ECHAPPE);


    }

    @Test
    void Getdescriptiontest() {
        String desp = "Pour le décompte final des points, la cour des miracles est considérée comme un quartier de la couleur de votre choix. Vous ne pouvez pas utilisez cette capacité si vous avez construit la cour des miracles au dernier tour de jeu.";
        String desp2=null;
        assertEquals(this.courtofmirales.getDescription(), desp);
        assertNotEquals(this.courtofmirales.getDescription(),desp2);


    }
    @Test
    void TESTiswonder(){
        assertTrue(this.courtofmirales.isWonder());
        assertNotEquals(this.courtofmirales.isWonder(),false);

    }
}

