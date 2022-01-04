package fr.unice.polytech.startingpoint.player.HeroStrategies;

import fr.unice.polytech.startingpoint.cards.CardException;
import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.DistrictName;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.heros.character.Architect;
import fr.unice.polytech.startingpoint.player.IA.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArchitectChoiceTest {
    Architect architect = null;

    @BeforeEach
    void setUp(){
        architect = new Architect();
    }
    @Test
    void testTheConstructionOfBUILDER(){
        IA ia = new BuilderBot("batisseur");
        ia.setRole(this.architect);
        ia.addGold(10);

        IDistrict district1 = addCards(2, Color.YELLOW, DistrictName.TAVERNE);
        IDistrict district2 = addCards(1,Color.BLUE,DistrictName.PALAIS);
        IDistrict district3 = addCards(4,Color.RED,DistrictName.CHATEAU);

        List<IDistrict> districtList = new ArrayList<>();
        districtList.add(district1);
        districtList.add(district2);
        districtList.add(district3);

        ia.getDistrict(districtList);

        assertEquals(3,ia.getHand().size());
        assertEquals(0,ia.getBuiltDistricts().size());

        Treasure treasure = new Treasure(30);
        IAToHero iaToHero = new IAToHero();

        ia.doAction(treasure,iaToHero);

        assertEquals(0,ia.getHand().size());
        assertEquals(3,ia.getBuiltDistricts().size());
        assertEquals(3,ia.getGold());
    }
    @Test
    void testTheConstructionOfBUILDER2(){
        IA ia1= new BuilderBot("batisseur1");
        ia1.setRole(this.architect);
        ia1.addGold(5);

        IDistrict district1 = addCards(2, Color.YELLOW, DistrictName.TAVERNE);
        IDistrict district2 = addCards(1,Color.BLUE,DistrictName.PALAIS);
        IDistrict district3 = addCards(4,Color.RED,DistrictName.CHATEAU);

        List<IDistrict> districtList = new ArrayList<>();
        districtList.add(district1);
        districtList.add(district2);
        districtList.add(district3);

        ia1.getDistrict(districtList);

        assertEquals(3,ia1.getHand().size());
        assertEquals(0,ia1.getBuiltDistricts().size());

        Treasure treasure = new Treasure(30);
        IAToHero iaToHero = new IAToHero();

        ia1.doAction(treasure,iaToHero);

        assertEquals(2,ia1.getHand().size());
       assertEquals(1,ia1.getBuiltDistricts().size());
       assertEquals(ia1.getBuiltDistricts().get(0).getDistrictName(),DistrictName.CHATEAU);
       assertEquals(1,ia1.getGold());
    }
    @Test
    void testTheConstructionOfBUILDER3(){
        IA ia3= new BuilderBot("batisseur2");
        ia3.setRole(this.architect);
        ia3.addGold(8);

        IDistrict district1 = addCards(3, Color.YELLOW, DistrictName.TAVERNE);
        IDistrict district2 = addCards(3,Color.BLUE,DistrictName.PALAIS);
        IDistrict district3 = addCards(2,Color.RED,DistrictName.CHATEAU);

        List<IDistrict> districtList = new ArrayList<>();
        districtList.add(district1);
        districtList.add(district2);
        districtList.add(district3);

        ia3.getDistrict(districtList);

        assertEquals(3,ia3.getHand().size());
        assertEquals(0,ia3.getBuiltDistricts().size());

        Treasure treasure = new Treasure(30);
        IAToHero iaToHero = new IAToHero();

        ia3.doAction(treasure,iaToHero);

        assertEquals(0,ia3.getHand().size());
        assertEquals(3,ia3.getBuiltDistricts().size());
        assertEquals(0,ia3.getGold());
    }
    @Test
    void testTheConstructionOfBUILDER4(){
        IA ia2= new BuilderBot("batisseur2");
        ia2.setRole(this.architect);
        ia2.addGold(5);

        IDistrict district1 = addCards(1, Color.YELLOW, DistrictName.TAVERNE);
        IDistrict district2 = addCards(1,Color.BLUE,DistrictName.PALAIS);
        IDistrict district3 = addCards(1,Color.RED,DistrictName.CHATEAU);

        List<IDistrict> districtList = new ArrayList<>();
        districtList.add(district1);
        districtList.add(district2);
        districtList.add(district3);

        ia2.getDistrict(districtList);

        assertEquals(3,ia2.getHand().size());
        assertEquals(0,ia2.getBuiltDistricts().size());

        Treasure treasure = new Treasure(30);
        IAToHero iaToHero = new IAToHero();

        ia2.doAction(treasure,iaToHero);

        assertEquals(2,ia2.getHand().size());
        assertEquals(1,ia2.getBuiltDistricts().size());
        assertEquals(4,ia2.getGold());
    }


    @Test
    void testTheConstructionOf3Districts(){
        IA ia = new NeutralBot("IA");
        ia.setRole(this.architect);
        ia.addGold(10);

        IDistrict district1 = addCards(2, Color.YELLOW, DistrictName.TAVERNE);
        IDistrict district2 = addCards(1,Color.BLUE,DistrictName.PALAIS);
        IDistrict district3 = addCards(2,Color.RED,DistrictName.CHATEAU);
        List<IDistrict> districtList = new ArrayList<>();
        districtList.add(district1);
        districtList.add(district2);
        districtList.add(district3);

        ia.getDistrict(districtList);

        assertEquals(3,ia.getHand().size());
        assertEquals(0,ia.getBuiltDistricts().size());

        Treasure treasure = new Treasure(30);
        IAToHero iaToHero = new IAToHero();

        ia.doAction(treasure,iaToHero);

        assertEquals(0,ia.getHand().size());
        assertEquals(3,ia.getBuiltDistricts().size());
        assertEquals(10-2-1-2,ia.getGold());
    }

    @Test
    void testTheConstructionOf2Districts(){
        IA ia = new NeutralBot("IA");
        ia.setRole(this.architect);
        ia.addGold(5);

        IDistrict district1 = addCards(2,Color.YELLOW,DistrictName.TAVERNE);
        IDistrict district2 = addCards(3,Color.BLUE,DistrictName.PALAIS);
        IDistrict district3 = addCards(2,Color.RED,DistrictName.CHATEAU);
        List<IDistrict> districtList = new ArrayList<>();
        districtList.add(district1);
        districtList.add(district2);
        districtList.add(district3);

        ia.getDistrict(districtList);

        assertEquals(3,ia.getHand().size());
        assertEquals(0,ia.getBuiltDistricts().size());

        Treasure treasure = new Treasure(30);
        IAToHero iaToHero = new IAToHero();

        ia.doAction(treasure,iaToHero);

        assertEquals(1,ia.getHand().size());
        assertEquals(2,ia.getBuiltDistricts().size());
        assertEquals(5-3-2,ia.getGold());
    }

    @Test
    void testTheConstructionOf1Districts(){
        IA ia = new NeutralBot("IA");
        ia.setRole(this.architect);
        ia.addGold(10);

        IDistrict district1 = addCards(2,Color.YELLOW,DistrictName.TAVERNE);
        IDistrict district2 = addCards(10,Color.BLUE,DistrictName.PALAIS);
        IDistrict district3 = addCards(11,Color.RED,DistrictName.CHATEAU);
        List<IDistrict> districtList = new ArrayList<>();
        districtList.add(district1);
        districtList.add(district2);
        districtList.add(district3);

        ia.getDistrict(districtList);

        assertEquals(3,ia.getHand().size());
        assertEquals(0,ia.getBuiltDistricts().size());

        Treasure treasure = new Treasure(30);
        IAToHero iaToHero = new IAToHero();

        ia.doAction(treasure,iaToHero);

        System.out.println(ia.getHand());
        assertEquals(2,ia.getHand().size());
        assertEquals(1,ia.getBuiltDistricts().size());
        assertEquals(0,ia.getGold());
    }

    @Test
    void testTheConstructionOf0Districts(){
        IA ia = new NeutralBot("IA");
        ia.setRole(this.architect);
        ia.addGold(1);

        IDistrict district1 = addCards(2,Color.YELLOW,DistrictName.TAVERNE);
        IDistrict district2 = addCards(10,Color.BLUE,DistrictName.PALAIS);
        IDistrict district3 = addCards(2,Color.RED,DistrictName.CHATEAU);
        List<IDistrict> districtList = new ArrayList<>();
        districtList.add(district1);
        districtList.add(district2);
        districtList.add(district3);

        ia.getDistrict(districtList);

        assertEquals(3,ia.getHand().size());
        assertEquals(0,ia.getBuiltDistricts().size());

        Treasure treasure = new Treasure(30);
        IAToHero iaToHero = new IAToHero();

        ia.doAction(treasure,iaToHero);

        assertEquals(3,ia.getHand().size());
        assertEquals(0,ia.getBuiltDistricts().size());
        assertEquals(1,ia.getGold());
    }

    @Test
    void testRandomDistrictChoice(){
        IA ia = new RandomBot("RandomBot");
        ia.setRole(this.architect);
        ia.addGold(1);

        IDistrict district1 = addCards(2,Color.YELLOW,DistrictName.TAVERNE);
        IDistrict district2 = addCards(10,Color.BLUE,DistrictName.PALAIS);
        IDistrict district3 = addCards(2,Color.RED,DistrictName.CHATEAU);
        List<IDistrict> districtList = new ArrayList<>();
        districtList.add(district1);
        districtList.add(district2);
        districtList.add(district3);

        ia.getDistrict(districtList);

        assertEquals(3,ia.getHand().size());
        assertEquals(0,ia.getBuiltDistricts().size());

        Treasure treasure = new Treasure(30);
        IAToHero iaToHero = new IAToHero();

        ia.doAction(treasure,iaToHero);

        assertEquals(3,ia.getHand().size());
        assertEquals(0,ia.getBuiltDistricts().size());
        assertEquals(1,ia.getGold());
    }

    private IDistrict addCards(int price, Color color, DistrictName nameOfCard){
        IDistrict district = null;
        try {
            district = new District(price,color,nameOfCard);
        } catch (CardException e) {
            e.printStackTrace();
        }
        return district;
    }
}