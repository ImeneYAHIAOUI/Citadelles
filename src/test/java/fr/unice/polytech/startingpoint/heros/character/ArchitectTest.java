package fr.unice.polytech.startingpoint.heros.character;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;
import fr.unice.polytech.startingpoint.player.IA.NeutralBot;
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
        IA ia = new NeutralBot("IA");
        ia.setRole(this.architect);
        assertEquals(0,ia.getHand().size());
    }

    @Test
    void TestDoAction(){
        IA ia = new NeutralBot("IA");
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
        IA ia = new NeutralBot("IA");
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
}