package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.cards.district.Observatory;
import fr.unice.polytech.startingpoint.core.Initialization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

public class ObservatoryTest {

        Observatory observatoire;
        DistrictDeck deck;
        IAToWonder info;
        District district1;

        @BeforeEach
        void setUp() {
            this.observatoire = new Observatory();
            deck = new DistrictDeck(Initialization.districtList());


        }


        @Test
        void GetnameTest() {
            assertEquals(this.observatoire.getDistrictName(), DistrictName.OBSERVATORY);
            assertNotEquals(this.observatoire.getDistrictName(), DistrictName.MANOIR);
            assertNotEquals(this.observatoire.getDistrictName(), DistrictName.LABORATOIRE);
            assertNotEquals(this.observatoire, DistrictName.CHATEAU);
            assertNotEquals(this.observatoire, DistrictName.PALAIS);
            assertNotEquals(this.observatoire, DistrictName.TAVERNE);
            assertNotEquals(this.observatoire, DistrictName.MARCHE);
            assertNotEquals(this.observatoire, DistrictName.ECHAPPE);


        }

        @Test
        void Getdescriptiontest() {
            String desp = "Si vous choisissez de piocher des cartes au début de votre tour, vous en piochez trois, en choisissez une et défaussez les deux autres.";
            String desp2=null;
            assertEquals(this.observatoire.getDescription(), desp);
            assertNotEquals(this.observatoire.getDescription(),desp2);


        }

    }
