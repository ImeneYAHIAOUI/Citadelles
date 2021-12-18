package fr.unice.polytech.startingpoint.heros.character;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.heros.character.Merchant;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;
import fr.unice.polytech.startingpoint.player.IA.NeutralBot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MerchantTest {
    Merchant merchant = null;
    IAToHero info;

    @BeforeEach
    void setUp(){

        this.merchant = new Merchant();
        info = new IAToHero();
    }

    @Test
    void testGetName(){
        Assertions.assertNotEquals(this.merchant.getName(), HeroName.King);
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
        IAToHero info = new IAToHero();
        Treasure treasure = new Treasure(30);
        IA player = new NeutralBot("Mooncake");
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

        player.setRole(heroes.get(0));
        player.activateHero(null,null,treasure,info);

        assertEquals(5,player.getGold());
    }

    @Test
    void testDoActionWith0GreenDistrict(){
        IAToHero info = new IAToHero();
        Treasure treasure = new Treasure(30);
        IA player = new NeutralBot("Mooncake");
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

        player.setRole(heroes.get(0));
        player.activateHero(null,null,treasure,info );

        assertEquals(3,player.getGold());
    }
}