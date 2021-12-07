package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.player.IA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ObservatoryTest {

        Observatory observatoire;
        DistrictDeck deck;
        infoaction info;
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
        @Test
    void doactionTest(){
            Observatory observatory=new Observatory();
            infoaction infomock=mock(infoaction.class);
            List<IDistrict> trio= new ArrayList<>();
            List<IDistrict> hand1 = new ArrayList<>();
            try {
                district1 = new District(1, Color.YELLOW, DistrictName.MANOIR);
            } catch (CardException e) {
                e.printStackTrace();
            }
            District district2 = null;
            try {
                district2 = new District(2, Color.BLUE, DistrictName.PALAIS);
            } catch (CardException e) {
                e.printStackTrace();
            }
            District district3 = null;
            try {
                district3 = new District(1, Color.GREEN, DistrictName.TAVERNE);
            } catch (CardException e) {
                e.printStackTrace();
            }
            trio.add(district2);
            trio.add(district3);
            trio.add(district1);

            IA player=new IA("sam");
            when(infomock.getplayer()).thenReturn(player);
            when(infomock.getdistrictdeck()).thenReturn(new DistrictDeck(Initialization.districtList()));
            when(infomock.gettriocard()).thenReturn(trio);
            when(infomock.getchoice()).thenReturn(district1);
            observatory.doAction(infomock);
            assertEquals(infomock.gettriocard().size(),3);
            assertEquals(Initialization.districtList().size(),65);
            assertEquals(infomock.getplayer().getHand().get(0).getDistrictName(),infomock.getchoice().getDistrictName());





        }

    }
