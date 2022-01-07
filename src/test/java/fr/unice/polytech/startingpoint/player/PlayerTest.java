package fr.unice.polytech.startingpoint.player;

import fr.unice.polytech.startingpoint.cards.*;

import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.heros.character.King;
import fr.unice.polytech.startingpoint.heros.character.Magician;
import fr.unice.polytech.startingpoint.heros.character.Merchant;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.BOTs.Nastybot;
import fr.unice.polytech.startingpoint.player.IA.BOTs.NiceBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class PlayerTest {
    District district1;
    District district2;
    District district3;
    District district4;
    District district5;

    IHero king;
    IHero magicien;
    IHero marchent;


    List<IDistrict> hand1;
    List<IDistrict> hand2;
    List<IDistrict> hand3;
    List<IDistrict> hand4;
    IA player1;
    IA player2;
    IA player3;


    @BeforeEach
    void setUp(){
        try {
            district1 = new District(2, Color.YELLOW,DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district2 = new District(1,Color.BLUE,DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district3 = new District(1,Color.GREEN,DistrictName.PALAIS);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district4 = new District(2, Color.RED,DistrictName.CHATEAU);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district5 = new District(2, Color.YELLOW,DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }

        hand1 = new ArrayList<>();
        hand2 = new ArrayList<>();
        hand3 = new ArrayList<>();
        hand4 = new ArrayList<>();


        hand1.add(district1);
        hand2.add(district2);
        hand3.add(district3);
        hand4.add(district4);

        player1 = new Nastybot("Link");
        player1.setHand(hand1);

        player2 = new NiceBot("Yoshi");
        player2.setHand(hand2);

        player3 = new Nastybot("Kirby");
        player3.setHand(hand3);

        player3.setCrown();

        king = new King();
        marchent = new Merchant();
        magicien = new Magician();

        player1.addGold(2);
        player2.addGold(2);
        player3.addGold(2);
    }

    @Test
    void getBuiltDistricts(){
        List<IDistrict> hand5 = new ArrayList<IDistrict>(hand1);
        assertEquals(player1.getBuiltDistricts(), new ArrayList<>());
        assertEquals(player2.getBuiltDistricts(), new ArrayList<>());
        player1.buildDistrict(district1);
        assertEquals(player1.getBuiltDistricts(),hand5);

    }
    @Test
    void getHand(){
        assertEquals(player1.getHand(),hand1);
        assertEquals(player2.getHand(),hand2);
        assertNotEquals(player3.getHand(),hand4);


    }


    @Test
    void getCrown(){
        assertFalse(player1.getCrown());
        assertTrue(player3.getCrown());
        assertFalse(player2.getCrown());
    }

    @Test
    void setCrown(){

        assertFalse(player1.getCrown());
        player2.setCrown();
        assertTrue(player2.getCrown());
    }

    @Test
    void unsetCrown(){
        assertFalse(player1.getCrown());
        player3.unSetCrown();
        assertFalse(player3.getCrown());
        assertFalse(player2.getCrown());

    }

    @Test
    void getScore(){
        assertEquals(player1.getScore(),0);
        player1.buildDistrict(district1);
        assertEquals(player1.getScore(),2);
    }

    @Test
    void setRole(){
        player1.setRole(king);
        assertEquals(player1.role,king);
        player2.setRole(magicien);
        assertNotEquals(player2.role,marchent);
        assertEquals(player2.role,magicien);
    }
    @Test
    void getRole(){
        player1.role = king;
        player2.role = magicien;
        player3.role = marchent;
        assertEquals(player1.getRole(),king);
        assertEquals(player2.getRole(),magicien);
        assertEquals(player3.getRole(),marchent);
        assertNotEquals(player1.getRole(),marchent);
        assertNotEquals(player2.getRole(),king);
        assertNotEquals(player3.getRole(),magicien);
    }
    @Test
    void addGold(){
        assertEquals(player1.gold, 2);
        player1.addGold(3);
        assertEquals(player1.gold,5);
    }
    @Test
    void getGold(){
        assertEquals(2,player1.getGold());
        assertEquals(2,player2.getGold());
        player1.addGold(3);
        assertEquals(player1.getGold(),5);
    }
    @Test
    void getName(){
           assertEquals(player1.getName(),"Link");
           assertEquals(player2.getName(),"Yoshi");
           assertEquals(player3.getName(),"Kirby");
    }

    @Test
    void setHand(){
        player1.setHand(hand4);
        player2.setHand(hand4);
        player3.setHand(hand4);
        assertEquals(player1.getHand(),hand4);
        assertEquals(player2.getHand(),hand4);
        assertEquals(player3.getHand(),hand4);
    }

    @Test
    void buildDistrict(){
        List<IDistrict> hand5 = new ArrayList<IDistrict>(hand1);
        assertEquals(player1.getBuiltDistricts(),new ArrayList<>());
        player1.buildDistrict(district1);
        assertEquals(player1.getBuiltDistricts(),hand5);
        hand5.remove(district1);
        assertEquals(player1.getHand(),hand1);
    }

    @Test
    void getDistrict(){
        List<IDistrict> districtList = new ArrayList<>();
        districtList.add(district4);
        player1.getDistrict(districtList);
        assertTrue(player1.getHand().contains(district4));
    }
    @Test
    void getHandTest(){
        assertEquals(player1.getHand(),hand1);
        assertEquals(player2.getHand(),hand2);
        assertEquals(player3.getHand(),hand3);
    }
    @Test
    void getHeroRank(){
        player1.setRole(king);
        player2.setRole(magicien);
        player3.setRole(marchent);
        assertEquals(player1.getHeroRank(),4);
        assertEquals(player2.getHeroRank(),3);
        assertEquals(player3.getHeroRank(),6);
    }
    @Test
    void toStringTest(){
        assertEquals(player1.toString(),"Link");
        assertEquals(player2.toString(),"Yoshi");
        assertEquals(player3.toString(),"Kirby");
    }
}
