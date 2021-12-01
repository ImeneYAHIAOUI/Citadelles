package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class courtofmiraclesTest {
    CourtOfMiracles courtofmirales;
    DistrictDeck deck;
    infoaction info;
District district1;

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
    @Test
    void doactiontest(){
        infoaction info=new infoaction();
        CourtOfMiracles courtofmiracles= new CourtOfMiracles();
        CourtOfMiracles courtofmiracles2= new CourtOfMiracles();

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

        IA player2 = new IA("jerry");
        List<IDistrict> hand1 = new ArrayList<>();
        List<IDistrict> builtDistricts =new ArrayList<>();
        hand1.add(district1);
        hand1.add(district2);
        hand1.add(district3);
        hand1.add(courtofmiracles2);
        hand1.add(district1);
        hand1.add(district3);
        hand1.add(district2);
        hand1.add(district1);
        player2.buildDistrict(district1);
        player2.buildDistrict(district2);
        player2.buildDistrict(district3);
        player2.buildDistrict(courtofmiracles2);
        player2.buildDistrict(district1);
        player2.buildDistrict(district3);
        player2.buildDistrict(district2);
        player2.buildDistrict(district1);
        player2.setHand(hand1);
        player2.addGold(25);
        info.setbuildlist(player2.getBuiltDistricts());
        info.setplayer(player2);
        info.setHAND(hand1);
        info.setchoosencolor(Color.GREEN);
        courtofmiracles2.doAction(info);
        assertEquals(info.getchoosencolor(),Color.GREEN);
        assertEquals(player2.getBuiltDistricts().size(),8);
        assertEquals(courtofmiracles2.getColor(),Color.GREEN);
        infoaction info3 = new infoaction();
        IPlayer player3 = new IA("sam");
        List<IDistrict> hand2 = new ArrayList<>();
        List<IDistrict> builtDistricts2 =new ArrayList<>();
        hand2.add(district1);
        hand2.add(district2);
        hand2.add(district3);
        hand2.add(courtofmiracles);
        hand2.add(district1);
        hand2.add(district3);
        hand2.add(district2);
        hand2.add(district1);
        builtDistricts2.add(district1);
        builtDistricts2.add(district2);
        builtDistricts2.add(district3);
        builtDistricts2.add(district1);
        builtDistricts2.add(district1);
        builtDistricts2.add(district3);
        builtDistricts2.add(district2);
        builtDistricts2.add(courtofmiracles);
        player3.setHand(hand2);
        info.setbuildlist(builtDistricts2);
        info3.setplayer(player3);
        info3.setchoosencolor(Color.GREEN);
        courtofmiracles.doAction(info3);
        assertEquals(courtofmiracles.getColor(),Color.PURPLE);
        assertEquals(builtDistricts2.get(7).getDistrictName(),DistrictName.LACOURDESMIRACLES);




    }
}

