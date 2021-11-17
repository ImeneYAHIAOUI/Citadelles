package fr.unice.polytech.startingpoint.player;

import fr.unice.polytech.startingpoint.cards.Color;

import fr.unice.polytech.startingpoint.cards.DistrictName;
import fr.unice.polytech.startingpoint.cards.District;
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




    List<District> hand1;
    List<District> hand2;
    List<District> hand3;
    List<District> hand4;
    IA player1;
    IA player2;
    IA player3;


    @BeforeEach
    void setUp(){
        district1 = new District(2, Color.YELLOW,DistrictName.MANOIR);
        district2 = new District(1,Color.BLUE,DistrictName.MANOIR);
        district3 = new District(1,Color.GREEN,DistrictName.PALAIS);
        district4 = new District(2, Color.RED,DistrictName.CHATEAU);
        district5 = new District(2, Color.YELLOW,DistrictName.MANOIR);

        hand1 = new ArrayList<>();
        hand2 = new ArrayList<>();
        hand3 = new ArrayList<>();
        hand4 = new ArrayList<>();


        hand1.add(district1);
        hand2.add(district2);
        hand3.add(district3);
        hand4.add(district4);

        player1 = new IA("link");
        player1.getDistrict(hand1);

        player2 = new IA("Yoshi");
        player2.getDistrict(hand2);

        player3 = new IA("Kirby");
        player3.getDistrict(hand3);

        player3.setKing();


    }
    @Test
    void getHand(){
        assertEquals(player1.getHand(),hand1);
        assertEquals(player2.getHand(),hand2);
        assertNotEquals(player3.getHand(),hand4);


    }

    @Test
    void getBuiltDistricts(){
        List<District> hand5 = new ArrayList<>(hand1);
        assertEquals(player1.getBuiltDistricts(), new ArrayList<>());
        assertEquals(player2.getBuiltDistricts(), new ArrayList<>());
        player1.buildDistrict(0);
        assertEquals(player1.getBuiltDistricts(),hand5);

    }

    @Test
    void getScore(){
        assertEquals(player1.getScore(),2);
        player1.buildDistrict(0);
        //assertEquals(player1.getScore(),0);
    }



    @Test
    void isKing(){
        assertFalse(player1.isKing());
        assertTrue(player3.isKing());
        assertFalse(player2.isKing());
    }
    @Test
    void setKing(){

        assertFalse(player1.isKing());
        player2.setKing();
        assertTrue(player2.isKing());
    }
    @Test
    void unsetKing(){
        assertFalse(player1.isKing());
        player3.unsetKing();
        assertFalse(player3.isKing());
        assertFalse(player2.isKing());

    }

    @Test
    void addDistrict(){
        List<District> hand5 = new ArrayList<>(hand1);
        player1.addDistrict(district4);
        assertNotEquals(player1.getHand(), hand5);
        hand5.add(district4);
        assertEquals(player1.getHand(), hand5);

    }

    @Test
    void buildDistrict(){
        List<District> hand5 = new ArrayList<>(hand1);
        assertEquals(player1.getBuiltDistricts(),new ArrayList<>());
        player1.buildDistrict(0);
        assertEquals(player1.getBuiltDistricts(),hand5);
        hand5.remove(0);
        //assertEquals(player1.getHand(),hand1);
    }
}
