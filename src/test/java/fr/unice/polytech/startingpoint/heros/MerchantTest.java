package fr.unice.polytech.startingpoint.heros;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.player.Information;
import fr.unice.polytech.startingpoint.player.InformationTest;
import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MerchantTest {
    Merchant merchant = null;
    Information information = null;

    @BeforeEach
    void setUp(){
        merchant = new Merchant();
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
    void testDoAction(){

    }
}