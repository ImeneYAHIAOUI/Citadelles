package fr.unice.polytech.startingpoint.player.IA.Strategies.wonderAction;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.cards.district.*;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.heros.character.*;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;
import fr.unice.polytech.startingpoint.player.IA.BOTs.NeutralBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    IA player5;
    IA player7;
    IA player8;
    IA player9;
    IA player10;

    private DistrictDeck deck  = new DistrictDeck(Initialization.districtList());

    private IA player4;
    private MiracleCourt miraclecourt;
    private MagicSchool magicSchool;
    private IA player6;


    private IAToHero infor=new IAToHero();
    private IAToHero infor2=new IAToHero();
    IAToWonder wonderInfo = new IAToWonder();
    @BeforeEach
    void setup(){
        player =new NeutralBot("Yaman");
        player1 = new NeutralBot("saman");
        player2 = new NeutralBot("Tokyo");
        player3 = new NeutralBot("LB");
        player4=  new NeutralBot("joe");
        player5=new NeutralBot("intellij");
        player6 = new NeutralBot("TOM");
        player7=new NeutralBot("karl");
        player8=new NeutralBot("ford");
        player9=new NeutralBot("samuel");
        player10=new NeutralBot("shiba");
        List<IDistrict> hand = new ArrayList<>();
        List<IDistrict> hand1 = new ArrayList<>();
        List<IDistrict> hand2 = new ArrayList<>();
        List<IDistrict> hand3 = new ArrayList<>();
        List<IDistrict> hand4 = new ArrayList<>();
        List<IDistrict> hand5 = new ArrayList<>();
        List<IDistrict> hand6 = new ArrayList<>();
        List<IDistrict> hand7 = new ArrayList<>();

       miraclecourt=new MiracleCourt();
       Laboratory laboratoire=new Laboratory();
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
        hand5.add(district5);
        hand5.add(district4);
        hand5.add(district2);
        player7.setHand(hand5);
        player7.buildDistrict(new Laboratory());
        player7.setGold(15);
        player8.setHand(hand6);
        player8.setGold(8);
        player8.buildDistrict(district4);
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
        hand4.add(district6);
        hand4.add(district4);
        hand4.add(district3);
        hand3.add(district1);
        hand3.add(district3);
        hand3.add(district4);
        player.buildDistrict(new Library());
        player2.buildDistrict(new Manufacture());
        hand1.add(district1);
        hand1.add(district5);
        hand1.add(district4);
        hand1.add(district2);
        player6.buildDistrict(miraclecourt);
        player6.buildDistrict(district1);
        player6.buildDistrict(district2);
        player6.buildDistrict(district3);
        player6.buildDistrict(district4);
        player5.setHand(hand4);
        player5.setGold(15);
        player5.buildDistrict(new Laboratory());
        player.setGold(25);
        player1.setGold(20);
        player2.setGold(20);
        player3.setGold(20);
        player3.buildDistrict(new Manufacture());
        player1.buildDistrict(new Manufacture());
        player1.buildDistrict(district3);
        player2.buildDistrict(new Manufacture());
        player.buildDistrict(district1);
        player.buildDistrict(district2);
        player.buildDistrict(new Library());
        player9.setGold(15);
        player9.setHand(hand7);
        player9.buildDistrict(new Observatory());
        player.setHand(hand);
        infor.setCurrentPlayer(player);
        infor2.setCurrentPlayer(player9);
        player10.setGold(15);
        player10.buildDistrict(new Drocoport());
        player10.buildDistrict(district1);
        player10.buildDistrict(district3);
        magicSchool = new MagicSchool();






    }
    @Test
    void LibraryTest(){
        player.applyLibrary();
        player.drawOrGetPieces( deck, tresor,  infor,wonderInfo);
        assertEquals(player.getHand().size(),2);
    }

    @Test
    void LibraryTest1(){
        player8.applyLibrary();
        player8.drawOrGetPieces( deck, tresor,  infor,wonderInfo);
        assertEquals(player8.getHand().size(),0);
        assertEquals(player8.getGold(),2);


    }
    @Test
    void  applymanufacrtureTest(){
        player1.applyManufacture(deck,tresor,wonderInfo);
        assertEquals(player1.getGold(),11);
        assertEquals(player1.getHand().size(),3);

    }
    @Test
    void applymanufactureTest1(){
        player2.applyManufacture(deck,tresor,wonderInfo);
        assertEquals(player2.getHand().size(),7);
       assertEquals(player2.getGold(),12);
    }
    @Test
    void applymanufactureTest2() {
        player3.applyManufacture(deck,tresor,wonderInfo);
        assertEquals(player3.getHand().size(),3);
        assertEquals(player3.getGold(),15);


    }
   @Test
    void applymiraclecourtTest() {
        player4.applyMiracleCourt(wonderInfo);
        assertEquals( miraclecourt.getColor(),Color.RED);


    }
    @Test
    void applymiraclecourtTest2(){
        player6.applyMiracleCourt(wonderInfo);
        assertEquals(miraclecourt.getColor(),Color.PURPLE);

    }
    @Test
    void applyLaboratoryTest(){
        player5.applyLaboratory(tresor,wonderInfo);
        assertEquals(player5.getGold(),11);
        assertEquals(player5.getHand().size(),2);

    }
    @Test
    void applyObservatoryTest(){
        player9.applyObservatory();
        player9.drawOrGetPieces( deck, tresor,  infor2,wonderInfo);
        assertEquals(player9.getHand().size(),1);}
    @Test
    void applyDrocoportTest(){

        player10.applyDrocoport(wonderInfo);
        assertEquals(player10.getBuiltDistricts().get(0).getPrice(),8);
        assertEquals(player10.getScore(),11);

    }
    @Test
    void applyMagicSchoolTest(){
        player1.setRole(new Assassin());
        player1.addGold(6);
        player1.buildDistrict(magicSchool);
        player1.applyMagicSchool(wonderInfo);
        assertEquals(Color.PURPLE,magicSchool.getColor());
        player1.setRole(new King());
        player1.applyMagicSchool(wonderInfo);
        assertEquals(Color.YELLOW,magicSchool.getColor());
        player1.setRole(new Bishop());
        player1.applyMagicSchool(wonderInfo);
        assertEquals(Color.BLUE,magicSchool.getColor());
        player1.setRole(new Merchant());
        player1.applyMagicSchool(wonderInfo);
        assertEquals(Color.GREEN,magicSchool.getColor());
        player1.setRole(new Condottiere());
        player1.applyMagicSchool(wonderInfo);
        assertEquals(Color.RED,magicSchool.getColor());
    }

    @Test
    void testApplyUniversity(){
        IA ia = new NeutralBot("BOB");
        ia.addGold(50);
        ia.setRole(new Assassin());
        ia.addGold(10);

        University university = new University();
        MiracleCourt miracleCourt = new MiracleCourt();
        IDistrict district = null;
        try {
            district = new District(2,Color.YELLOW, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }

        ia.buildDistrict(miracleCourt);
        ia.buildDistrict(district);
        ia.buildDistrict(university);
        assertEquals(ia.getBuiltDistricts().size(),3);

        assertEquals(6,university.getPrice());
        ia.applyUniversity(wonderInfo);
        assertEquals(8,university.getPrice());
    }
}


