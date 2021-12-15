package fr.unice.polytech.startingpoint.cards.district;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class UniversityTest {
    University university = null;
    private IAToWonder info;
    private DistrictDeck deck;

    @BeforeEach
    void setUp(){
        this.university = new University();
        this.info=new IAToWonder();
        this.deck = new DistrictDeck(Initialization.districtList());
       IA player1=new IA("james");
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
        player1.buildDistrict(university);
        player1.buildDistrict(district3);
        player1.buildDistrict(district2);


    }

    @Test
    void testPrice(){
        assertEquals(this.university.getPrice(),6);
        assertNotEquals(this.university.getPrice(),5);
        assertNotEquals(this.university.getPrice(),7);
        assertNotEquals(this.university.getPrice(),-1);
        assertNotEquals(this.university.getPrice(),15);
        assertNotEquals(this.university.getPrice(),8);
    }

    @Test
    void testColor(){
        assertEquals(this.university.getColor(), Color.PURPLE);
        assertNotEquals(this.university.getColor(), Color.BLUE);
        assertNotEquals(this.university.getColor(), Color.GREEN);
        assertNotEquals(this.university.getColor(), Color.RED);
        assertNotEquals(this.university.getColor(), Color.YELLOW);
        assertNotEquals(this.university.getColor(), Color.WHITE);
    }

    @Test
    void testName(){
        assertEquals(this.university.getDistrictName(),DistrictName.UNIVERSITY);
        assertNotEquals(this.university.getDistrictName(),DistrictName.LABORATOIRE);
        assertNotEquals(this.university.getDistrictName(),DistrictName.LACOURDESMIRACLES);
        assertNotEquals(this.university.getDistrictName(),DistrictName.MARCHE);
        assertNotEquals(this.university.getDistrictName(),DistrictName.CHATEAU);
        assertNotEquals(this.university.getDistrictName(),DistrictName.PALAIS);
    }

    @Test
    void wondertest(){
        assertTrue(this.university.isWonder());
        assertNotEquals(this.university.isWonder(),false);

    }
    @Test
    void doactiontest(){
        assertEquals(info.getplayer().getBuiltDistricts().get(0).getPrice(),6);
        university.doAction(info);
        assertEquals(info.getplayer().getBuiltDistricts().get(0).getPrice(),8);
        assertEquals(info.getplayer().getScore(),11);
    }
}