package fr.unice.polytech.startingpoint.cards.district;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.DistrictName;
import fr.unice.polytech.startingpoint.cards.IAToWonder;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UniversityTest {
    University university = null;
    IAToWonder informations = mock(IAToWonder.class);

    @BeforeEach
    void setUp(){
        university = new University();
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
    void testDoAction(){
        when(informations.getplayer()).thenReturn(new IA("mockPlayer"));
        assertEquals(this.university.getPrice(),6);
        this.university.doAction(informations);
        assertEquals(this.university.getPrice(),8);
    }
}