package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.cards.district.Laboratory;
import fr.unice.polytech.startingpoint.core.Initialization;

import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.player.IA.BOTs.NeutralBot;
import fr.unice.polytech.startingpoint.player.IPlayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LaboratoryTest {
    fr.unice.polytech.startingpoint.cards.district.Laboratory Laboratory;
    DistrictDeck deck;


    @BeforeEach
    void setUp() {
        this.Laboratory = new Laboratory();
        deck = new DistrictDeck(Initialization.districtList());
        int gold;

    }


    @Test
    void GetnameTest() {
        assertEquals(this.Laboratory.getDistrictName(), DistrictName.LABORATOIRE);
        assertNotEquals(this.Laboratory.getDistrictName(), DistrictName.MANOIR);
        assertNotEquals(this.Laboratory.getDistrictName(), DistrictName.LACOURDESMIRACLES);
        assertNotEquals(this.Laboratory, DistrictName.CHATEAU);
        assertNotEquals(this.Laboratory, DistrictName.PALAIS);
        assertNotEquals(this.Laboratory, DistrictName.TAVERNE);
        assertNotEquals(this.Laboratory, DistrictName.MARCHE);
        assertNotEquals(this.Laboratory, DistrictName.ECHAPPE);
    }

    @Test
    void Getdescriptiontest() {
        String desp = "Une fois par tour, vous pouvez vous défausser d'une carte quartier de votre main et recevoir une pièce d'or en contrepartie";
        String desp2=null;
        assertEquals(this.Laboratory.getDescription(), desp);
        assertNotEquals(this.Laboratory.getDescription(),desp2);

    }
@Test
    void doactiontest() {
        Laboratory laboratoire= new Laboratory();
        IAToWonder infomock=mock(IAToWonder.class);
        IAToWonder info2 =mock(IAToWonder.class);
        IAToWonder info3= new IAToWonder();
        IPlayer player = new NeutralBot("sam");
    IPlayer player2 = new NeutralBot("jerry");
    IPlayer player3= new NeutralBot("Tom");
        List<IDistrict> hand1 = new ArrayList<>();
        List<IDistrict> hand2 = new ArrayList<>();
        List<IDistrict> hand3 = new ArrayList<>();
    District district1 = null;
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
    player.setHand(hand1);
    Treasure tresor= new Treasure(30);

        hand1.add(district1);
        hand1.add(district2);
        hand1.add(district3);
        hand2.add(district1);
        hand2.add(district3);
        hand3.add(district2);
        when(infomock.getplayer()).thenReturn(player);
        when(infomock.getTreasure()).thenReturn(tresor);
        when(infomock.getChoosenCardOfLaboratory()).thenReturn(district2);
        when(info2.getplayer()).thenReturn(player2);

        when(info2.getTreasure()).thenReturn(tresor);
        when(info2.getChoosenCardOfLaboratory()).thenReturn(null);
        assertEquals(hand1.size(),3);
        laboratoire.doAction(infomock);
        laboratoire.doAction(info2);

        info3.setInformationForLaboratory(tresor,district2,player3);

        laboratoire.doAction(info3);
        int nbgold = player.getGold();
        assertEquals(hand1,hand2);
        assertEquals(hand1.size(),2);
        assertNotEquals(hand1,hand3);
        assertEquals(nbgold,1);
        assertNotEquals(nbgold,2);







    }
    @Test
    void TESTiswonder(){
        assertTrue(this.Laboratory.isWonder());
        assertNotEquals(this.Laboratory.isWonder(),false);

    }
}

