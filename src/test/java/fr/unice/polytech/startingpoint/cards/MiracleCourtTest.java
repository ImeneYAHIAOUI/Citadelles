package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MiracleCourtTest {
    MiracleCourt miracleCourt;
    DistrictDeck deck;
    infoaction info;
District district1;

    @BeforeEach
    void setUp() {
        this.miracleCourt = new MiracleCourt();
        deck = new DistrictDeck(Initialization.districtList());


    }


    @Test
    void GetnameTest() {
        assertEquals(this.miracleCourt.getDistrictName(), DistrictName.LACOURDESMIRACLES);
        assertNotEquals(this.miracleCourt.getDistrictName(), DistrictName.MANOIR);
        assertNotEquals(this.miracleCourt.getDistrictName(), DistrictName.LABORATOIRE);
        assertNotEquals(this.miracleCourt, DistrictName.CHATEAU);
        assertNotEquals(this.miracleCourt, DistrictName.PALAIS);
        assertNotEquals(this.miracleCourt, DistrictName.TAVERNE);
        assertNotEquals(this.miracleCourt, DistrictName.MARCHE);
        assertNotEquals(this.miracleCourt, DistrictName.ECHAPPE);


    }

    @Test
    void Getdescriptiontest() {
        String desp = "Pour le décompte final des points, la cour des miracles est considérée comme un quartier de la couleur de votre choix. Vous ne pouvez pas utilisez cette capacité si vous avez construit la cour des miracles au dernier tour de jeu.";
        String desp2=null;
        assertEquals(this.miracleCourt.getDescription(), desp);
        assertNotEquals(this.miracleCourt.getDescription(),desp2);


    }
    @Test
    void TESTiswonder(){
        assertTrue(this.miracleCourt.isWonder());
        assertNotEquals(this.miracleCourt.isWonder(),false);

    }
    @Test
    void doactiontest(){
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
        District district4=null;
        try {
            district4 = new District(4, Color.YELLOW, DistrictName.CHATEAU);
        } catch (CardException e) {
            e.printStackTrace();
        }
        District district5 = null;
        try {
            district5 = new District(2, Color.BLUE, DistrictName.EGLISE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        District district6 = null;
        try {
            district6 = new District(2, Color.GREEN, DistrictName.ECHAPPE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        District district7 = null;
        try {
            district7 = new District(2, Color.RED, DistrictName.PRISON);
        } catch (CardException e) {
            e.printStackTrace();
        }
        /** test cas  court des miracles non construit au dernier tour**/
        IA player2 = new IA("jerry");
        player2.addGold(20);
        infoaction info = new infoaction();
        List<IDistrict> hand1 = new ArrayList<>();
        MiracleCourt miracleCourt2= new MiracleCourt();
        List<IDistrict> builtDistricts =new ArrayList<>();
        hand1.add(district1);
        hand1.add(district2);
        hand1.add(district3);
        hand1.add(miracleCourt2);
        hand1.add(district4);
        hand1.add(district5);
        hand1.add(district6);
        hand1.add(district7);
        builtDistricts.add(district1);
        builtDistricts.add(district2);
        builtDistricts.add(district3);
        builtDistricts.add(miracleCourt2);
        builtDistricts.add(district5);
        builtDistricts.add(district6);
        builtDistricts.add(district7);
        builtDistricts.add(district4);
        player2.setHand(hand1);
        player2.addGold(22);
        info.setbuildlist(builtDistricts);
        info.setplayer(player2);
        info.setchoosencolor(Color.GREEN);
        miracleCourt2.doAction(info);
        assertEquals(miracleCourt2.getColor(),Color.GREEN);
        assertEquals(builtDistricts.size(),8);
        assertEquals(builtDistricts.get(3).getDistrictName(),DistrictName.LACOURDESMIRACLES);

        /** test cas court des  miracles construit au dernier tourn donc on peut pas avoir cangement de couleur **/
        MiracleCourt miracleCourt= new MiracleCourt();
        infoaction info3 = new infoaction();
        IPlayer player3 = new IA("sam");
        player3.addGold(20);
        List<IDistrict> hand2 = new ArrayList<>();
        List<IDistrict> builtDistricts2 =new ArrayList<>();
        hand2.add(district1);
        hand2.add(district2);
        hand2.add(district3);
        hand2.add(miracleCourt);
        hand2.add(district4);
        hand2.add(district5);
        hand2.add(district6);
        hand2.add(district7);
        builtDistricts2.add(district1);
        builtDistricts2.add(district2);
        builtDistricts2.add(district3);
        builtDistricts2.add(district4);
        builtDistricts2.add(district5);
        builtDistricts2.add(district6);
        builtDistricts2.add(district7);
        builtDistricts2.add(miracleCourt);
        player3.setHand(hand2);
        player3.addGold(22);
        info3.setbuildlist(builtDistricts2);
        info3.setplayer(player3);
        info3.setchoosencolor(Color.GREEN);
        miracleCourt.doAction(info3);
        assertEquals(miracleCourt.getColor(),Color.PURPLE);
        assertEquals(builtDistricts2.size(),8);
        assertEquals(builtDistricts2.get(7).getDistrictName(),DistrictName.LACOURDESMIRACLES);


    }
}

