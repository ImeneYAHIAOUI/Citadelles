package fr.unice.polytech.startingpoint.cards;

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

    @Test
    void doactiontest() {
        District district1 = null;
        District district2=null;
        Library library1 =new Library();
        List<IDistrict> hand1 =new ArrayList<>();
        List<IDistrict>  districtList =new ArrayList<>();
        List<IDistrict>  choosencards =new ArrayList<>();
        infoaction infomock=mock(infoaction.class);
        Treasure tresor = new Treasure(30);
        DistrictDeck districtdeck = new DistrictDeck(Initialization.districtList());
        Information information=new Information();


        IPlayer player =new IA("sam");
        try {
            district1 = new District(1, Color.YELLOW,DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district2 =new District(3,Color.GREEN,DistrictName.TAVERNE);
        } catch (CardException e) {
            e.printStackTrace();
        }

        information.setCurrentPlayer(player);
        infomock.setTreasure(tresor);
        hand1.add(library1);
        player.addGold(10);
        choosencards.add(district1);
        choosencards.add(district2);
        districtList.add(district1);
        districtList.add(district2);
        districtList.add(district1);
        districtList.add(library1);
        infomock.setplayer(player);
        infomock.setChosenCards((choosencards));
        player.setHand(hand1);
        when(infomock.getplayer()).thenReturn(player);
        when(infomock.getTreasure()).thenReturn(tresor);
       when(infomock.getChosenCards()).thenReturn(choosencards);
        library1.doAction(infomock);
        assertTrue(player.getHand().contains(district1));
        assertTrue(player.getHand().contains(district2));
        assertEquals(hand1.size(),3);
        assertEquals(player.getGold(),10);





    }
}
