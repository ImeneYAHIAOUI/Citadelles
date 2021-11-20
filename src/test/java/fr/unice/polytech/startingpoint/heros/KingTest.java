package fr.unice.polytech.startingpoint.heros;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.District;
import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.player.Information;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {
    King king;
    Information information;
    DistrictDeck deck;

    @BeforeEach
    void setUp() {
        this.king = new King();
        deck = new DistrictDeck(Initialization.districtList());
        //information = new Information(deck,king,);

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
        //Xthis.information =
    }
}