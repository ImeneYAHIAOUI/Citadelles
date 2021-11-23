package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.core.Initialization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
    }

    @Test
    void Getdescriptiontest() {
        String desp = "Pour le décompte final des points, la cour des miracles est considérée comme un quartier de la couleur de votre choix. Vous ne pouvez pas utilisez cette capacité si vous avez construit la cour des miracles au dernier tour de jeu.";
        assertEquals(this.courtofmirales.getDescription(), desp);


    }
}
