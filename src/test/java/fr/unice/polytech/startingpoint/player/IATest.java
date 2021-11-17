package fr.unice.polytech.startingpoint.player;
import fr.unice.polytech.startingpoint.cards.DistrictName;
import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.District;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class IATest {
    District district1;

    List<District> hand1;

    IA player1;
    IA ia1;

    @BeforeEach
    void setUp(){
        district1 = new District(2, Color.YELLOW,DistrictName.MANOIR);
        hand1 = new ArrayList<>();
        hand1.add(district1);
        player1 = new IA("Peach");
        player1.getDistrict(hand1);
        ia1 = new IA("player1");

    }

    @Test
    void move(){
        List<District> hand2 = new ArrayList<>(hand1);
        assertEquals(player1.getBuiltDistricts(),new ArrayList<>());
        //assertEquals(player1.getScore(),2);
        //ia1.move();
        //assertEquals(player1.getBuiltDistricts(),hand2);
        //assertEquals(player1.getScore(),0);


    }
}
