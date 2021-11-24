package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.core.Initialization;

import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LaboratoryTest {
    Laboratory Laboratory;
    DistrictDeck deck;
    infoaction info;


    @BeforeEach
    void setUp() {
        this.Laboratory = new Laboratory();
        deck = new DistrictDeck(Initialization.districtList());

    }


    @Test
    void GetnameTest() {
        assertEquals(this.Laboratory.getDistrictName(), DistrictName.LABORATOIRE);
        assertNotEquals(this.Laboratory.getDistrictName(), DistrictName.MANOIR);
    }

    @Test
    void Getdescriptiontest() {
        String desp = "Une fois par tour, vous pouvez vous défausser d'une carte quartier de votre main et recevoir une pièce d'or en contrepartie";
        assertEquals(this.Laboratory.getDescription(), desp);


    }
@Test
    void doactiontest() {
        Laboratory laboratoire= new Laboratory();
        infoaction infomock=mock(infoaction.class);
        IPlayer player = new IA("sam");
        List<IDistrict> hand1 = new ArrayList<>();
        List<IDistrict> hand2 = new ArrayList<>();
        List<IDistrict> hand3 = new ArrayList<>();
        District district1 = new District(1, Color.YELLOW, DistrictName.MANOIR);
        District district2 = new District(2, Color.BLUE, DistrictName.PALAIS);
        District district3 = new District(1, Color.GREEN, DistrictName.TAVERNE);
        player.setHand(hand1);
        hand1.add(district1);
        hand1.add(district2);
        hand1.add(district3);
        hand2.add(district1);
        hand2.add(district3);
        hand3.add(district2);
        when(infomock.getplayer()).thenReturn(player);
        when(infomock.getHAND()).thenReturn(hand1);
        when(infomock.getDistrictremove()).thenReturn(district2);
        laboratoire.doAction(infomock);
        assertEquals(hand1,hand2);
        assertNotEquals(hand1,hand3);






    }
}

