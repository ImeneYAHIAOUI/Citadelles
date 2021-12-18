package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.cards.district.Drocoport;
import fr.unice.polytech.startingpoint.cards.district.MiracleCourt;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.NeutralBot;
import fr.unice.polytech.startingpoint.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DrocoportTest {
 Drocoport drocoport;
    DistrictDeck deck;
    IAToWonder info;
    District district1;


    @BeforeEach
    void setUp() {
        this.drocoport = new Drocoport();
        this.info=new IAToWonder();
        deck = new DistrictDeck(Initialization.districtList());
        IA player1=new NeutralBot("james");
        List<IDistrict> hand1= new ArrayList<>();
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
        player1.setGold(15);
        info.setplayer(player1);
        player1.buildDistrict(drocoport);
        player1.buildDistrict(district3);
        player1.buildDistrict(district2);



    }


    @Test
    void GetnameTest() {
        assertEquals(this.drocoport.getDistrictName(), DistrictName.DROCOPORT);
        assertNotEquals(this.drocoport.getDistrictName(), DistrictName.MANOIR);
        assertNotEquals(this.drocoport.getDistrictName(), DistrictName.LABORATOIRE);
        assertNotEquals(this.drocoport, DistrictName.CHATEAU);
        assertNotEquals(this.drocoport, DistrictName.PALAIS);
        assertNotEquals(this.drocoport, DistrictName.TAVERNE);
        assertNotEquals(this.drocoport, DistrictName.MARCHE);
        assertNotEquals(this.drocoport, DistrictName.ECHAPPE);


    }

    @Test
    void Getdescriptiontest() {
        String desp = "Cette réalisation de prestige (on n'a pas vu de dragon dans le Royaume depuis bientôt mille ans) coûte six pièces d'or à bâtir mais vaut huit points dans le décompte de fin de partie.";
        String desp2=null;
        assertEquals(this.drocoport.getDescription(), desp);
        assertNotEquals(this.drocoport.getDescription(),desp2);


    }
    @Test
    void doactionTest(){
        assertEquals(info.getplayer().getBuiltDistricts().get(0).getPrice(),6);
        drocoport.doAction(info);
        assertEquals(info.getplayer().getBuiltDistricts().get(0).getPrice(),8);
        assertEquals(info.getplayer().getScore(),11);

    }
    @Test
    void doactionTest1(){
        assertEquals(info.getplayer().getBuiltDistricts().get(0).getPrice(),6);
    }


    @Test
    void TESTiswonder(){
        assertTrue(this.drocoport.isWonder());
        assertNotEquals(this.drocoport.isWonder(),false);

    }
}
