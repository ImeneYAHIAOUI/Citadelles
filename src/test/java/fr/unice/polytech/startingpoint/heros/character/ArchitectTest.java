package fr.unice.polytech.startingpoint.heros.character;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArchitectTest {
    Architect architect = null;

    @BeforeEach
    void setUp(){
        architect = new Architect();
    }

    @Test
    void testRank(){
        assertEquals(7,this.architect.getRank());
        assertNotEquals(6,this.architect.getRank());
        assertNotEquals(8,this.architect.getRank());
        assertNotEquals(-1,this.architect.getRank());
        assertNotEquals(14,this.architect.getRank());
        assertNotEquals(0,this.architect.getRank());
    }

    @Test
    void testColor(){
        assertEquals(Color.WHITE,this.architect.getColor());
        assertNotEquals(Color.RED,this.architect.getColor());
        assertNotEquals(Color.PURPLE,this.architect.getColor());
        assertNotEquals(Color.GREEN,this.architect.getColor());
        assertNotEquals(Color.YELLOW,this.architect.getColor());
        assertNotEquals(Color.BLUE,this.architect.getColor());
    }

    @Test
    void testWithNoAction(){
        IA ia = new IA("IA");
        ia.setRole(this.architect);
        assertEquals(0,ia.getHand().size());
    }

    @Test
    void TestDoAction(){
        IA ia = new IA("IA");
        ia.setRole(this.architect);

        Treasure treasure = new Treasure(30);
        IAToHero iaToHero = new IAToHero();

        DistrictDeck districtDeck = new DistrictDeck(Initialization.districtList());

        assertEquals(0,ia.getHand().size());
        ia.activateHero(null,districtDeck,treasure,iaToHero);
        assertEquals(2,ia.getHand().size());
    }

    @Test
    void TestDoActionWhithoutDistrict(){
        IA ia = new IA("IA");
        ia.setRole(this.architect);

        Treasure treasure = new Treasure(30);
        IAToHero iaToHero = new IAToHero();

        DistrictDeck districtDeck = new DistrictDeck(Initialization.districtList());

        // I empty the deck
        while(districtDeck.getDeckSize() > 0){
            districtDeck.giveDistrict(1);
        }

        assertEquals(0,districtDeck.getDeckSize());

        assertEquals(0,ia.getHand().size());
        ia.activateHero(null,districtDeck,treasure,iaToHero);
        assertEquals(0,ia.getHand().size());
    }

    @Test
    void testTheConstructionOf3Districts(){
        IA ia = new IA("IA");
        ia.setRole(this.architect);
        ia.addGold(10);

        IDistrict district1 = addCards(2,Color.YELLOW,DistrictName.TAVERNE);
        IDistrict district2 = addCards(1,Color.BLUE,DistrictName.PALAIS);
        IDistrict district3 = addCards(2,Color.RED,DistrictName.CHATEAU);
        List<IDistrict> districtList = new ArrayList<>();
        districtList.add(district1);
        districtList.add(district2);
        districtList.add(district3);

        ia.getDistrict(districtList);

        assertEquals(3,ia.getHand().size());
        assertEquals(0,ia.getBuiltDistricts().size());

        Treasure treasure = new Treasure(30);
        IAToHero iaToHero = new IAToHero();

        ia.doAction(treasure,iaToHero);

        assertEquals(0,ia.getHand().size());
        assertEquals(3,ia.getBuiltDistricts().size());
        assertEquals(10-2-1-2,ia.getGold());
    }

    @Test
    void testTheConstructionOf2Districts(){
        IA ia = new IA("IA");
        ia.setRole(this.architect);
        ia.addGold(10);

        IDistrict district1 = addCards(2,Color.YELLOW,DistrictName.TAVERNE);
        IDistrict district2 = addCards(10,Color.BLUE,DistrictName.PALAIS);
        IDistrict district3 = addCards(2,Color.RED,DistrictName.CHATEAU);
        List<IDistrict> districtList = new ArrayList<>();
        districtList.add(district1);
        districtList.add(district2);
        districtList.add(district3);

        ia.getDistrict(districtList);

        assertEquals(3,ia.getHand().size());
        assertEquals(0,ia.getBuiltDistricts().size());

        Treasure treasure = new Treasure(30);
        IAToHero iaToHero = new IAToHero();

        ia.doAction(treasure,iaToHero);

        assertEquals(1,ia.getHand().size());
        assertEquals(2,ia.getBuiltDistricts().size());
        assertEquals(10-2-2,ia.getGold());
    }

    @Test
    void testTheConstructionOf1Districts(){
        IA ia = new IA("IA");
        ia.setRole(this.architect);
        ia.addGold(10);

        IDistrict district1 = addCards(2,Color.YELLOW,DistrictName.TAVERNE);
        IDistrict district2 = addCards(10,Color.BLUE,DistrictName.PALAIS);
        IDistrict district3 = addCards(11,Color.RED,DistrictName.CHATEAU);
        List<IDistrict> districtList = new ArrayList<>();
        districtList.add(district1);
        districtList.add(district2);
        districtList.add(district3);

        ia.getDistrict(districtList);

        assertEquals(3,ia.getHand().size());
        assertEquals(0,ia.getBuiltDistricts().size());

        Treasure treasure = new Treasure(30);
        IAToHero iaToHero = new IAToHero();

        ia.doAction(treasure,iaToHero);

        assertEquals(2,ia.getHand().size());
        assertEquals(1,ia.getBuiltDistricts().size());
        assertEquals(10-2,ia.getGold());
    }

    @Test
    void testTheConstructionOf0Districts(){
        IA ia = new IA("IA");
        ia.setRole(this.architect);
        ia.addGold(1);

        IDistrict district1 = addCards(2,Color.YELLOW,DistrictName.TAVERNE);
        IDistrict district2 = addCards(10,Color.BLUE,DistrictName.PALAIS);
        IDistrict district3 = addCards(2,Color.RED,DistrictName.CHATEAU);
        List<IDistrict> districtList = new ArrayList<>();
        districtList.add(district1);
        districtList.add(district2);
        districtList.add(district3);

        ia.getDistrict(districtList);

        assertEquals(3,ia.getHand().size());
        assertEquals(0,ia.getBuiltDistricts().size());

        Treasure treasure = new Treasure(30);
        IAToHero iaToHero = new IAToHero();

        ia.doAction(treasure,iaToHero);

        assertEquals(3,ia.getHand().size());
        assertEquals(0,ia.getBuiltDistricts().size());
        assertEquals(10,ia.getGold());
    }

    private IDistrict addCards(int price, Color color, DistrictName nameOfCard){
        IDistrict district = null;
        try {
            district = new District(price,color,nameOfCard);
        } catch (CardException e) {
            e.printStackTrace();
        }
        return district;
    }
}