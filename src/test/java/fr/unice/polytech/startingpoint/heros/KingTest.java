package fr.unice.polytech.startingpoint.heros;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.Information;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {
    King king;
    DistrictDeck deck;
    Information info;
    IA player;
    HeroDeck heroes;
    Treasure treasure;

    @BeforeEach
    void setUp() {
        this.king = new King();
        this.deck = new DistrictDeck(Initialization.districtList());
        this.info = new Information();
        this.player = new IA("Player1");
        this.heroes = new HeroDeck();
        treasure=new Treasure(32);
    }

    @Test
    void testGetName(){
        assertEquals(this.king.getName(), HeroName.King);
        assertNotEquals(this.king.getName(), HeroName.Merchant);
        assertNotEquals(this.king.getName(), HeroName.Thief);
        assertNotEquals(this.king.getName(), HeroName.Assassin);
        assertNotEquals(this.king.getName(), HeroName.Architect);
        assertNotEquals(this.king.getName(), HeroName.Bishop);
        assertNotEquals(this.king.getName(), HeroName.Condottiere);
        assertNotEquals(this.king.getName(), HeroName.Magician);
    }

    @Test
    void testGetRank(){
        assertEquals(this.king.getRank(),4);
        assertNotEquals(this.king.getRank(),5);
        assertNotEquals(this.king.getRank(),3);
        assertNotEquals(this.king.getRank(),10);
        assertNotEquals(this.king.getRank(),-2);
    }

    @Test
    void testGet(){
        assertEquals(this.king.getColor(), Color.YELLOW);
        assertNotEquals(this.king.getColor(), Color.RED);
        assertNotEquals(this.king.getColor(), Color.PURPLE);
        assertNotEquals(this.king.getColor(), Color.GREEN);
        assertNotEquals(this.king.getColor(), Color.BLUE);
    }

    /**
     * Integration test
     */
    @Test
    void testDoActionWith1DistrictYellow(){
        List<IPlayer> listTest = new ArrayList<IPlayer>();
        this.heroes.add(this.king);

        // IA 1 at the crown
        IPlayer IA = new IA("1");
        IA.setCrown();

        //IA 1 does not have the crown
        IPlayer IA2 = new IA("2");

        // A list of three players
        listTest.add(IA);
        listTest.add(IA2);
        listTest.add(this.player);

        // Initialization of 2 districts
        IDistrict district1 = null;
        try {
            district1 = new District(2, Color.YELLOW, DistrictName.CHATEAU);
        } catch (CardException e) {
            e.printStackTrace();
        }
        IDistrict district2 = null;
        try {
            district2 = new District(4, Color.RED, DistrictName.ECHAPPE);
        } catch (CardException e) {
            e.printStackTrace();
        }

        // I add gold to the player so that he can buy the 2 districts
        this.player.addGold(4+2);
        assertEquals(6+2,this.player.getGold());

        // The player builds the 2 districts
        this.player.buildDistrict(district1);
        this.player.buildDistrict(district2);

        // I test the player's gold
        assertEquals(2, this.player.getGold());

        // The tested player takes the hero king
        player.chooseHero(this.heroes,0);

        // I fill in the info object so that the king's action can take effect.
        // This object contains the useful info of the game so that the hero's action can have its effect
        info.setInformationForKing(this.player,listTest,treasure);

        // I test the player with the crown
        assertTrue(IA.getCrown());

        // I test players without the crown
        assertFalse(IA2.getCrown());
        assertFalse(this.player.getCrown());

        // I carry out the king's action
        king.doAction(info);

        // I control the gold of the player tested
        assertEquals(3,this.player.getGold());

        // The player tested at the crown
        assertTrue(this.player.getCrown());

        //The AI that had the crown doesn't have it anymore
        assertFalse(IA.getCrown());
        assertFalse(IA2.getCrown());
    }

    /**
     * Integration test
     */
    @Test
    void testDoActionWith0DistrictYellow(){
        List<IPlayer> listTest = new ArrayList<IPlayer>();
        this.heroes.add(this.king);

        // IA 1 at the crown
        IPlayer IA = new IA("1");
        IA.setCrown();

        //IA 1 does not have the crown
        IPlayer IA2 = new IA("2");

        // A list of three players
        listTest.add(IA);
        listTest.add(IA2);
        listTest.add(this.player);

        // Initialization of 2 districts
        IDistrict district1 = null;
        try {
            district1 = new District(2, Color.GREEN, DistrictName.CHATEAU);
        } catch (CardException e) {
            e.printStackTrace();
        }
        IDistrict district2 = null;
        try {
            district2 = new District(4, Color.RED, DistrictName.ECHAPPE);
        } catch (CardException e) {
            e.printStackTrace();
        }

        // I add gold to the player so that he can buy the 2 districts
        this.player.addGold(4+2);
        assertEquals(6+2,this.player.getGold());

        // The player builds the 2 districts
        this.player.buildDistrict(district1);
        this.player.buildDistrict(district2);

        // I test the player's gold
        assertEquals(2, this.player.getGold());

        // The tested player takes the hero king
        player.chooseHero(this.heroes,0);

        // I fill in the info object so that the king's action can take effect.
        // This object contains the useful info of the game so that the hero's action can have its effect
        info.setInformationForKing(this.player,listTest,treasure);

        // I test the player with the crown
        assertTrue(IA.getCrown());

        // I test players without the crown
        assertFalse(IA2.getCrown());
        assertFalse(this.player.getCrown());

        // I carry out the king's action
        king.doAction(info);

        // I control the gold of the player tested
        assertEquals(2,this.player.getGold());

        // The player tested at the crown
        assertTrue(this.player.getCrown());

        //The AI that had the crown doesn't have it anymore
        assertFalse(IA.getCrown());
        assertFalse(IA2.getCrown());
    }

    /**
     * Integration test
     */
    @Test
    void testDoActionWithPlayerAlreadyHavingTheCrown(){
        List<IPlayer> listTest = new ArrayList<IPlayer>();
        this.heroes.add(this.king);

        // IA 1 does not have the crown
        IPlayer IA = new IA("1");

        //IA 1 does not have the crown
        IPlayer IA2 = new IA("2");

        // Player test at the crown
        this.player.setCrown();

        // A list of three players
        listTest.add(IA);
        listTest.add(IA2);
        listTest.add(this.player);

        // Initialization of 2 districts
        IDistrict district1 = null;
        try {
            district1 = new District(2, Color.YELLOW, DistrictName.CHATEAU);
        } catch (CardException e) {
            e.printStackTrace();
        }
        IDistrict district2 = null;
        try {
            district2 = new District(4, Color.RED, DistrictName.ECHAPPE);
        } catch (CardException e) {
            e.printStackTrace();
        }

        // I add gold to the player so that he can buy the 2 districts
        this.player.addGold(4+2);
        assertEquals(6+2,this.player.getGold());

        // The player builds the 2 districts
        this.player.buildDistrict(district1);
        this.player.buildDistrict(district2);

        // I test the player's gold
        assertEquals(2, this.player.getGold());

        // The tested player takes the hero king
        player.chooseHero(this.heroes,0);

        // I fill in the info object so that the king's action can take effect.
        // This object contains the useful info of the game so that the hero's action can have its effect
        info.setInformationForKing(this.player,listTest,treasure);

        // I test the player with the crown
        assertTrue(this.player.getCrown());

        // I test players without the crown
        assertFalse(IA2.getCrown());
        assertFalse(IA.getCrown());

        // I carry out the king's action
        king.doAction(info);

        // I control the gold of the player tested
        assertEquals(3,this.player.getGold());

        // The player tested at the crown
        assert(this.player.getCrown());

        //The AI that had the crown doesn't have it anymore
        assertFalse(IA.getCrown());
        assertFalse(IA2.getCrown());
    }
}