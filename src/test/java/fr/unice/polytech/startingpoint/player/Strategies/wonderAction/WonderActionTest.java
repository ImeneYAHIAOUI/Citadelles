package fr.unice.polytech.startingpoint.player.Strategies.wonderAction;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WonderActionTest {
    IDistrict district1;
    IDistrict district2;
    IDistrict district3;
    IDistrict district4;
    IDistrict district5;
    IDistrict district6;

    private IA player;
    Treasure tresor;
    IA player1;
    IA player2;
    IA player3;
    WonderAction action;
    private DistrictDeck deck  = new DistrictDeck(Initialization.districtList());

    private IA player4;
    private MiracleCourt miraclecourt;

    @BeforeEach
    void setup(){
        player =new IA("Yaman");
        player1 = new IA("saman");
        player2 = new IA("Tokyo");
        player3 = new IA("LB");
        player4=  new IA("joe");
        List<IDistrict> hand1 = new ArrayList<>();
        List<IDistrict> hand2 = new ArrayList<>();
        List<IDistrict> hand3 = new ArrayList<>();
       miraclecourt=new MiracleCourt();
      this.tresor =new Treasure(30);

        try {
            district1 = new District(2, Color.YELLOW, DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district2 = new District(5,Color.BLUE,DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district3 = new District(1,Color.GREEN,DistrictName.PALAIS);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district4 = new District(6, Color.RED,DistrictName.CHATEAU);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district5 = new District(6, Color.YELLOW,DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district6 = new District(3, Color.YELLOW,DistrictName.PRISON);
        } catch (CardException e) {
            e.printStackTrace();
        }
        player1.setHand(hand2);
        player2.setHand(hand1);
        player3.setHand(hand3);
        player4.setGold(30);
        player4.buildDistrict(district1);
        player4.buildDistrict(district2);
        player4.buildDistrict(district3);
        player4.buildDistrict(new Library());
        player4.buildDistrict( miraclecourt);
        player4.buildDistrict(district6);
        hand3.add(district1);
        hand3.add(district3);
        hand3.add(district4);
        player.buildDistrict(new Library());
        player2.buildDistrict(new Manufacture());
        hand1.add(district1);
        hand1.add(district5);
        hand1.add(district4);
        hand1.add(district2);
        player.setGold(20);
        player1.setGold(20);
        player2.setGold(20);
        player3.setGold(20);
        player3.buildDistrict(new Manufacture());
        player1.buildDistrict(new Manufacture());
        player1.buildDistrict(district3);
        player2.buildDistrict(new Manufacture());
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
    @Test
    void  applymanufacrtureTest(){
        action.applyManufacture(player1,deck,tresor );
        assertEquals(player1.getGold(),11);
        assertEquals(player1.getHand().size(),3);

    }
    @Test
    void applymanufactureTest1(){
        action.applyManufacture(player2,deck,tresor);
        assertEquals(player2.getHand().size(),7);
       assertEquals(player2.getGold(),12);
    }
    @Test
    void applymanufactureTest2() {
        action.applyManufacture(player3,deck,tresor);
        assertEquals(player3.getHand().size(),3);
        assertEquals(player3.getGold(),15);


    }
   @Test
    void applymiraclecourtTest() {
        action.applyMiracleCourt(player4);
        assertEquals( miraclecourt.getColor(),Color.RED);


    }


}