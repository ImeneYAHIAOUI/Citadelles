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
    IPlayer player;

    @BeforeEach
    void setUp() {
        this.king = new King();
        this.deck = new DistrictDeck(Initialization.districtList());
        this.info = new Information();
        this.player = new IA("Player1");
    }

    @Test
    void testGetName(){
        assertEquals(this.king.getName(), HeroName.King);
    }

    @Test
    void testGetRenk(){
        assertEquals(this.king.getRank(),4);
    }

    @Test
    void testGet(){
        assertEquals(this.king.getColor(), Color.YELLOW);
    }

    @Test
    void testDoAction(){
        List<IPlayer> listTest = new ArrayList<IPlayer>();
        IPlayer IA = new IA("1");
        IA.setCrown();
        listTest.add(IA);
        listTest.add(new IA("2"));
        listTest.add(this.player);

        IDistrict district1 = null;
        try {
            district1 = new District(2, Color.YELLOW, DistrictName.CHATEAU);
        } catch (CardException e) {
            e.printStackTrace();
        }
        IDistrict district2 = null;
        try {
            district2 = new District(2, Color.YELLOW, DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }
        List<IDistrict> deck = new ArrayList<IDistrict>();
        deck.add(district1);
        deck.add(district2);

        player.getDistrict(deck);

        info.setInformationForKing(this.player,listTest);
        assertFalse(this.player.getCrown());
        assertEquals(this.player.getGold(), 2);
        king.doAction(info);
        assertTrue(this.player.getCrown());
    }
}