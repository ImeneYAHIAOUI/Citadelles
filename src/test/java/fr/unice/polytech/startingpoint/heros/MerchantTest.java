package fr.unice.polytech.startingpoint.heros;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.Information;
import fr.unice.polytech.startingpoint.player.InformationTest;
import org.junit.After;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MerchantTest {
    Merchant merchant = null;

    @BeforeEach
    void setUp(){
        this.merchant = new Merchant();
    }

    @Test
    void testGetName(){
        assertNotEquals(this.merchant.getName(), HeroName.King);
        assertEquals(this.merchant.getName(), HeroName.Merchant);
        assertNotEquals(this.merchant.getName(), HeroName.Thief);
        assertNotEquals(this.merchant.getName(), HeroName.Assassin);
        assertNotEquals(this.merchant.getName(), HeroName.Architect);
        assertNotEquals(this.merchant.getName(), HeroName.Bishop);
        assertNotEquals(this.merchant.getName(), HeroName.Condottiere);
        assertNotEquals(this.merchant.getName(), HeroName.Magician);
    }

    @Test
    void testGetRank(){
        assertEquals(this.merchant.getRank(),6);
        assertNotEquals(this.merchant.getRank(),5);
        assertNotEquals(this.merchant.getRank(),3);
        assertNotEquals(this.merchant.getRank(),10);
        assertNotEquals(this.merchant.getRank(),-2);
    }

    @Test
    void testGet(){
        assertNotEquals(this.merchant.getColor(), Color.YELLOW);
        assertNotEquals(this.merchant.getColor(), Color.RED);
        assertNotEquals(this.merchant.getColor(), Color.PURPLE);
        assertEquals(this.merchant.getColor(), Color.GREEN);
        assertNotEquals(this.merchant.getColor(), Color.BLUE);
    }

    @Test
    void testDoActionWith2GreenDistrict(){
        Information info = new Information();
        Treasure treasure = new Treasure(30);
        IA player = new IA("Mooncake");
        HeroDeck heroes = new HeroDeck();
        IDistrict distrcit1 = null;
        IDistrict distrcit2 = null;
        IDistrict distrcit3 = null;

        player.addGold(2);

        heroes.add(this.merchant);

        try {
            distrcit1 = new District(2, Color.YELLOW, DistrictName.CHATEAU);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            distrcit2 = new District(1, Color.GREEN, DistrictName.TAVERNE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            distrcit3 = new District(6, Color.GREEN, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }

        assertEquals(2,player.getGold());

        player.addGold(2+1+6);
        player.buildDistrict(distrcit1);
        player.buildDistrict(distrcit2);
        player.buildDistrict(distrcit3);

        player.chooseHero(heroes,0);
        player.activateHero(null,null,treasure);

        assertEquals(5,player.getGold());
    }

    @Test
    void testDoActionWith0GreenDistrict(){
        Information info = new Information();
        Treasure treasure = new Treasure(30);
        IA player = new IA("Mooncake");
        HeroDeck heroes = new HeroDeck();
        IDistrict distrcit1 = null;
        IDistrict distrcit2 = null;
        IDistrict distrcit3 = null;

        player.addGold(2);

        heroes.add(this.merchant);

        try {
            distrcit1 = new District(2, Color.YELLOW, DistrictName.CHATEAU);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            distrcit2 = new District(1, Color.PURPLE, DistrictName.TAVERNE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            distrcit3 = new District(6, Color.BLUE, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }

        assertEquals(2,player.getGold());

        player.addGold(2+1+6);
        player.buildDistrict(distrcit1);
        player.buildDistrict(distrcit2);
        player.buildDistrict(distrcit3);

        player.chooseHero(heroes,0);
        player.activateHero(null,null,treasure);

        assertEquals(3,player.getGold());
    }
}