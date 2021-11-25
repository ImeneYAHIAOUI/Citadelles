package fr.unice.polytech.startingpoint.cards;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistrictNameTest {
    DistrictName districtName = null;

    @BeforeEach
    void setUp(){

    }

    @Test
    void testNameChateau(){
        this.districtName = DistrictName.CHATEAU;

        assertNotEquals(this.districtName, DistrictName.MANOIR);
        assertEquals(this.districtName, DistrictName.CHATEAU);
        assertNotEquals(this.districtName, DistrictName.PALAIS);
        assertNotEquals(this.districtName, DistrictName.TAVERNE);
        assertNotEquals(this.districtName, DistrictName.MARCHE);
        assertNotEquals(this.districtName, DistrictName.ECHAPPE);
        assertNotEquals(this.districtName, DistrictName.LACOURDESMIRACLES);
        assertNotEquals(this.districtName, DistrictName.LABORATOIRE);
    }

    @Test
    void testNamePalais(){
        this.districtName = DistrictName.PALAIS;

        assertNotEquals(this.districtName, DistrictName.MANOIR);
        assertNotEquals(this.districtName, DistrictName.CHATEAU);
        assertEquals(this.districtName, DistrictName.PALAIS);
        assertNotEquals(this.districtName, DistrictName.TAVERNE);
        assertNotEquals(this.districtName, DistrictName.MARCHE);
        assertNotEquals(this.districtName, DistrictName.ECHAPPE);
        assertNotEquals(this.districtName, DistrictName.LACOURDESMIRACLES);
        assertNotEquals(this.districtName, DistrictName.LABORATOIRE);
    }

    @Test
    void testNameManoir(){
        this.districtName = DistrictName.MANOIR;

        assertEquals(this.districtName, DistrictName.MANOIR);
        assertNotEquals(this.districtName, DistrictName.CHATEAU);
        assertNotEquals(this.districtName, DistrictName.PALAIS);
        assertNotEquals(this.districtName, DistrictName.TAVERNE);
        assertNotEquals(this.districtName, DistrictName.MARCHE);
        assertNotEquals(this.districtName, DistrictName.ECHAPPE);
        assertNotEquals(this.districtName, DistrictName.LACOURDESMIRACLES);
        assertNotEquals(this.districtName, DistrictName.LABORATOIRE);
    }

    @Test
    void testNameTaverne(){
        this.districtName = DistrictName.TAVERNE;

        assertNotEquals(this.districtName, DistrictName.MANOIR);
        assertNotEquals(this.districtName, DistrictName.CHATEAU);
        assertNotEquals(this.districtName, DistrictName.PALAIS);
        assertEquals(this.districtName, DistrictName.TAVERNE);
        assertNotEquals(this.districtName, DistrictName.MARCHE);
        assertNotEquals(this.districtName, DistrictName.ECHAPPE);
        assertNotEquals(this.districtName, DistrictName.LACOURDESMIRACLES);
        assertNotEquals(this.districtName, DistrictName.LABORATOIRE);
    }

    @Test
    void testNameMarche(){
        this.districtName = DistrictName.MARCHE;

        assertNotEquals(this.districtName, DistrictName.MANOIR);
        assertNotEquals(this.districtName, DistrictName.CHATEAU);
        assertNotEquals(this.districtName, DistrictName.PALAIS);
        assertNotEquals(this.districtName, DistrictName.TAVERNE);
        assertEquals(this.districtName, DistrictName.MARCHE);
        assertNotEquals(this.districtName, DistrictName.ECHAPPE);
        assertNotEquals(this.districtName, DistrictName.LACOURDESMIRACLES);
        assertNotEquals(this.districtName, DistrictName.LABORATOIRE);
    }

    @Test
    void testNameEchappe(){
        this.districtName = DistrictName.ECHAPPE;

        assertNotEquals(this.districtName, DistrictName.MANOIR);
        assertNotEquals(this.districtName, DistrictName.CHATEAU);
        assertNotEquals(this.districtName, DistrictName.PALAIS);
        assertNotEquals(this.districtName, DistrictName.TAVERNE);
        assertNotEquals(this.districtName, DistrictName.MARCHE);
        assertEquals(this.districtName, DistrictName.ECHAPPE);
        assertNotEquals(this.districtName, DistrictName.LACOURDESMIRACLES);
        assertNotEquals(this.districtName, DistrictName.LABORATOIRE);
    }

    @Test
    void testNameLaCourDesMiracles(){
        this.districtName = DistrictName.LACOURDESMIRACLES;

        assertNotEquals(this.districtName, DistrictName.MANOIR);
        assertNotEquals(this.districtName, DistrictName.CHATEAU);
        assertNotEquals(this.districtName, DistrictName.PALAIS);
        assertNotEquals(this.districtName, DistrictName.TAVERNE);
        assertNotEquals(this.districtName, DistrictName.MARCHE);
        assertNotEquals(this.districtName, DistrictName.ECHAPPE);
        assertEquals(this.districtName, DistrictName.LACOURDESMIRACLES);
        assertNotEquals(this.districtName, DistrictName.LABORATOIRE);
    }

    @Test
    void testNameLaboratoire(){
        this.districtName = DistrictName.LABORATOIRE;

        assertNotEquals(this.districtName, DistrictName.MANOIR);
        assertNotEquals(this.districtName, DistrictName.CHATEAU);
        assertNotEquals(this.districtName, DistrictName.PALAIS);
        assertNotEquals(this.districtName, DistrictName.TAVERNE);
        assertNotEquals(this.districtName, DistrictName.MARCHE);
        assertNotEquals(this.districtName, DistrictName.ECHAPPE);
        assertNotEquals(this.districtName, DistrictName.LACOURDESMIRACLES);
        assertEquals(this.districtName, DistrictName.LABORATOIRE);
    }
}