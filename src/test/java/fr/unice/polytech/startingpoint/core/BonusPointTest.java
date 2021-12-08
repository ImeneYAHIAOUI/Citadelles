package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BonusPointTest {
    BonusPoint bonusPoint = null;
    IA IA1 = null;
    IA IA2 = null;

    IDistrict district1 = null;
    IDistrict district2 = null;
    IDistrict district3 = null;
    IDistrict district4 = null;
    IDistrict district5 = null;
    IDistrict district6 = null;
    IDistrict district7 = null;
    IDistrict district8 = null;

    @BeforeEach
    void setUp() {
        this.bonusPoint = new BonusPoint();
        this.IA1 = new IA("IA1");
        this.IA2 = new IA("IA2");
    }

    @Test
    void testBonusFirst(){
        try {
            this.district1 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district2 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district3 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district4 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district5 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district6 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district7 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district8 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }


        this.IA1.buildDistrict(district1);
        this.IA1.buildDistrict(district2);
        this.IA1.buildDistrict(district3);
        this.IA1.buildDistrict(district4);
        this.IA1.buildDistrict(district5);
        this.IA1.buildDistrict(district6);
        this.IA1.buildDistrict(district7);
        this.IA1.buildDistrict(district8);

        this.IA2.buildDistrict(district1);
        this.IA2.buildDistrict(district2);
        this.IA2.buildDistrict(district3);
        this.IA2.buildDistrict(district4);

        List<IPlayer> list = new ArrayList<IPlayer>();
        list.add(this.IA1);
        list.add(this.IA2);

        bonusPoint.obtainBonus(list);

        assertEquals(4,IA1.getScore());
        assertEquals(0,IA2.getScore());
    }

    @Test
    void testBonusWhit2First(){
        try {
            this.district1 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district2 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district3 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district4 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district5 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district6 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district7 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district8 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }


        this.IA1.buildDistrict(district1);
        this.IA1.buildDistrict(district2);
        this.IA1.buildDistrict(district3);
        this.IA1.buildDistrict(district4);
        this.IA1.buildDistrict(district5);
        this.IA1.buildDistrict(district6);
        this.IA1.buildDistrict(district7);
        this.IA1.buildDistrict(district8);

        this.IA2.buildDistrict(district1);
        this.IA2.buildDistrict(district2);
        this.IA2.buildDistrict(district3);
        this.IA2.buildDistrict(district4);
        this.IA2.buildDistrict(district5);
        this.IA2.buildDistrict(district6);
        this.IA2.buildDistrict(district7);
        this.IA2.buildDistrict(district8);

        List<IPlayer> list = new ArrayList<IPlayer>();
        list.add(this.IA1);
        list.add(this.IA2);

        bonusPoint.obtainBonus(list);

        assertEquals(4,IA1.getScore());
        assertEquals(2,IA2.getScore());
    }

    @Test
    void testBonusWithColor(){
        try {
            this.district1 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district2 = new District(0, Color.BLUE, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district3 = new District(0, Color.GREEN, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district4 = new District(0, Color.RED, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district5 = new District(0, Color.PURPLE, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district6 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district7 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district8 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }


        this.IA1.buildDistrict(district1);
        this.IA1.buildDistrict(district1);
        this.IA1.buildDistrict(district1);
        this.IA1.buildDistrict(district1);
        this.IA1.buildDistrict(district1);
        this.IA1.buildDistrict(district1);
        this.IA1.buildDistrict(district1);
        this.IA1.buildDistrict(district1);

        this.IA2.buildDistrict(district1);
        this.IA2.buildDistrict(district2);
        this.IA2.buildDistrict(district3);
        this.IA2.buildDistrict(district4);
        this.IA2.buildDistrict(district5);
        this.IA2.buildDistrict(district6);

        List<IPlayer> list = new ArrayList<IPlayer>();
        list.add(this.IA1);
        list.add(this.IA2);

        bonusPoint.obtainBonus(list);

        assertEquals(4,IA1.getScore());
        assertEquals(3,IA2.getScore());
    }

    @Test
    void testBonusWithColorPlusFirst(){
        try {
            this.district1 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district2 = new District(0, Color.BLUE, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district3 = new District(0, Color.GREEN, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district4 = new District(0, Color.RED, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district5 = new District(0, Color.PURPLE, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district6 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district7 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district8 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }


        this.IA1.buildDistrict(district1);
        this.IA1.buildDistrict(district2);
        this.IA1.buildDistrict(district3);
        this.IA1.buildDistrict(district4);
        this.IA1.buildDistrict(district5);
        this.IA1.buildDistrict(district6);
        this.IA1.buildDistrict(district7);
        this.IA1.buildDistrict(district8);

        this.IA2.buildDistrict(district1);
        this.IA2.buildDistrict(district2);
        this.IA2.buildDistrict(district3);
        this.IA2.buildDistrict(district4);
        this.IA2.buildDistrict(district5);
        this.IA2.buildDistrict(district6);

        List<IPlayer> list = new ArrayList<IPlayer>();
        list.add(this.IA1);
        list.add(this.IA2);

        bonusPoint.obtainBonus(list);

        assertEquals(4+3,IA1.getScore());
        assertEquals(3,IA2.getScore());
    }

    @Test
    void testBonusWithColorPlusTwoFirst(){
        try {
            this.district1 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district2 = new District(0, Color.BLUE, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district3 = new District(0, Color.GREEN, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district4 = new District(0, Color.RED, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district5 = new District(0, Color.PURPLE, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district6 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district7 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            this.district8 = new District(0, Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }


        this.IA1.buildDistrict(district1);
        this.IA1.buildDistrict(district2);
        this.IA1.buildDistrict(district3);
        this.IA1.buildDistrict(district4);
        this.IA1.buildDistrict(district5);
        this.IA1.buildDistrict(district6);
        this.IA1.buildDistrict(district7);
        this.IA1.buildDistrict(district8);

        this.IA2.buildDistrict(district1);
        this.IA2.buildDistrict(district2);
        this.IA2.buildDistrict(district3);
        this.IA2.buildDistrict(district4);
        this.IA2.buildDistrict(district5);
        this.IA2.buildDistrict(district6);
        this.IA2.buildDistrict(district7);
        this.IA2.buildDistrict(district8);

        List<IPlayer> list = new ArrayList<IPlayer>();
        list.add(this.IA1);
        list.add(this.IA2);

        bonusPoint.obtainBonus(list);

        assertEquals(4+3,IA1.getScore());
        assertEquals(3+2,IA2.getScore());
    }
}