package fr.unice.polytech.startingpoint.player.Strategies.wonderAction;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WonderActionTest {
    IDistrict district1;
    IDistrict district2;
    IDistrict district3;
    IDistrict district4;
    IDistrict district5;
    private IA player;
    WonderAction action;
    @BeforeEach
    void setup(){
        player =new IA("Yaman");
        player.buildDistrict(new Library());
        try {
            district1 = new District(2, Color.YELLOW, DistrictName.MANOIR);
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
        player.setGold(20);
        player.buildDistrict(district1);
        player.buildDistrict(district2);
        player.getDistrict(List.of(district3));
        action =new WonderAction();

    }
    @Test
    void applyLibraryTest(){
        player.buildDistrict(new Library());
        action.applyLibrary(player,List.of(district5,district4));
        assertEquals(player.getHand().size(),3);
        action.applyLibrary(player,List.of(district1));
        assertEquals(player.getHand().size(),4);
    }
    @Test
    void applyLibraryTest1(){
        action.applyLibrary(player,List.of(district5,district4));
        assertEquals(player.getHand().size(),1);
    }


}