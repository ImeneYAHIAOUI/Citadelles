package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.cards.CardException;
import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.cards.DistrictName;
import fr.unice.polytech.startingpoint.cards.district.Cemetry;
import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.cards.district.MiracleCourt;
import fr.unice.polytech.startingpoint.heros.character.*;
import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.district.MagicSchool;
import fr.unice.polytech.startingpoint.heros.character.Bishop;
import fr.unice.polytech.startingpoint.heros.character.King;
import fr.unice.polytech.startingpoint.heros.character.Magician;
import fr.unice.polytech.startingpoint.heros.character.Merchant;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    Treasure treasure;
    Controller controller;
    IPlayer thief;
    IA player;
    List<IPlayer> players;
    List<IPlayer> players1;
    List<IPlayer> players2;
    IA player1;
    IA player2;
    IA player3;
    IA player4;
    MagicSchool magicSchool;
    DistrictDeck districtDeck;
    MiracleCourt miracleCourt;
    Cemetry cemetry;
    District district1;
    District district2;
    District district3;
    @BeforeEach
    void setup(){
        districtDeck = new DistrictDeck(Initialization.districtList());
        treasure=new Treasure(11);
        controller=new Controller();
        thief=new IA("voleur");
        thief.setRole(new Thief());
        thief.addGold(12);
        player= new IA("joe");
        player.addGold(5);
        player1=new IA("adrien");
        player2=new IA("jean philippe");
        player3=new IA("Donial");
        player4=new IA("jules");
        player.setRole(new Condottiere());
        player1.setRole(new Architect());
        player2.setRole(new King());
        player3.setRole(new Bishop());
        player4.setRole(new Magician());
        player.setStolenPerson();
        player.setStolenBy(thief);
        player3.setIsAssigned();
        players=List.of(thief,player,player1,player2,player3,player4);
        players1=List.of(player1,player2,player4);
        magicSchool = new MagicSchool();
        cemetry=new Cemetry();
        player1.addGold(6);
        player2.getBuiltDistricts().add(cemetry);
        player1.buildDistrict(magicSchool);
        players2=new ArrayList<>();
        players2.add(player2);
        players2.add(player1);
        players2.add(player);
        miracleCourt = new MiracleCourt();
        try {
            district1 = new District(1, Color.YELLOW, DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district2 =new District(3,Color.GREEN,DistrictName.TAVERNE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district3 =new District(3,Color.RED,DistrictName.PRISON);
        } catch (CardException e) {
            e.printStackTrace();
        }
        player2.addGold(20);
        player2.buildDistrict(miracleCourt);
        player2.buildDistrict(magicSchool);
        player2.buildDistrict(district1);
        player2.buildDistrict(district2);
        player2.buildDistrict(district3);

    }
    @Test
    void updateTest(){
        assertEquals(controller.getStolenPerson(),null);
        assertEquals(controller.getThief(),null);
        assertEquals(controller.getAssassinated(),null);
        controller.update(players,treasure,districtDeck);
        assertEquals(controller.getStolenPerson(),player);
        assertEquals(controller.getThief(),thief);
        assertEquals(controller.getAssassinated(),player3);
    }
    @Test
    void updateTest1(){
        //dans la liste des joeurs on a ni l'assasin ni le voleur
        controller.update(players1,treasure,districtDeck);
        assertEquals(controller.getStolenPerson(),null);
        assertEquals(controller.getThief(),null);
        assertEquals(controller.getAssassinated(),null);

    }
    @Test
    void updateTest2(){
        player.setStolenBy(null);
        //dans la liste des joeurs on a ni l'assasin ni le voleur
        controller.update(players2,treasure,districtDeck);
        assertEquals(controller.getStolenPerson(),player);
        assertEquals(controller.getThief(),null);
        assertEquals(controller.getAssassinated(),null);
        assertEquals(controller.getCondottiere(),player);
        assertEquals(controller.getCemeteryHolder(),player2);


    }
    @Test
    void HaveCemeteryTest(){
        controller.update(players2,treasure,districtDeck);
        assertEquals(controller.getCemeteryHolder(),player2);
    }
    @Test
    void HaveCemetryTest1(){
        controller.update(List.of(thief,player,player1,player3,player4),treasure,districtDeck);
        assertEquals(controller.getCemeteryHolder(),null);
    }

    @Test
    void GiveGoldToTheTiefTest(){
        controller.update(players,treasure,districtDeck);
        assertEquals(thief.getGold(),12);
        assertEquals(player.getGold(),5);
        controller.GiveGoldToTheTief();
        assertEquals(thief.getGold(),17);
        assertEquals(player.getGold(),0);

    }
    @Test
    void GiveGoldToTheTiefTest1(){
        controller.update(players,treasure,districtDeck);
        assertEquals(controller.getThief().getGold(),12);
        controller.getStolenPerson().removeGold(controller.getStolenPerson().getGold());
        assertEquals(controller.getStolenPerson().getGold(),0);
        controller.GiveGoldToTheTief();
        assertEquals(thief.getGold(),12);
        assertEquals(player.getGold(),0);

    }
    @Test
    void GiveGoldToTheTiefTest2(){
        //stolen person is null
        player.unSetStolenPerson();
        controller.update(players,treasure,districtDeck);
        controller.GiveGoldToTheTief();
        assertEquals(thief.getGold(),12);

    }
    @Test
    void GiveGoldToTheThiefTest3(){
        //thief is null
        controller.setThief(null);
        controller.GiveGoldToTheTief();
        assertEquals(player.getGold(),5);

    }

    @Test
    void changeMagicSchoolColorTest(){
        player1.setRole(new Magician());
        controller.changeMagicSchoolColor(player1);
        assertEquals(Color.PURPLE,magicSchool.getColor());
        player1.setRole(new King());
        controller.changeMagicSchoolColor(player1);
        assertEquals(Color.YELLOW,magicSchool.getColor());
        player1.setRole(new Bishop());
        controller.changeMagicSchoolColor(player1);
        assertEquals(Color.BLUE,magicSchool.getColor());
        player1.setRole(new Merchant());
        controller.changeMagicSchoolColor(player1);
        assertEquals(Color.GREEN,magicSchool.getColor());
        player1.setRole(new Condottiere());
        controller.changeMagicSchoolColor(player1);
        assertEquals(Color.RED,magicSchool.getColor());

    }
    @Test
    void resetMagicianSchoolColorTest(){
        player1.setRole(new Condottiere());
        controller.changeMagicSchoolColor(player1);
        assertEquals(Color.RED,magicSchool.getColor());
        controller.resetMagicSchoolColor(players);
        assertEquals(Color.PURPLE,magicSchool.getColor());
        controller.resetMagicSchoolColor(players);
        assertEquals(Color.PURPLE,magicSchool.getColor());
    }
    @Test
    void addToBuildDistrictTest(){
        controller.setBuiltDistricts();
        controller.addTOBuiltDistricts(List.of(magicSchool));
        assertTrue(controller.builtDistrictsThisRound.contains(magicSchool));
    }
    @Test
    void setBuiltDistrictsTest(){
        controller.setBuiltDistricts();
        assertEquals(new ArrayList<>(),controller.builtDistrictsThisRound);
        controller.addTOBuiltDistricts(List.of(magicSchool));
        assertTrue(controller.builtDistrictsThisRound.contains(magicSchool));
        controller.setBuiltDistricts();
        assertEquals(new ArrayList<>(),controller.builtDistrictsThisRound);
    }
    @Test
    void changeMiracleCourtColorTest(){
        controller.setBuiltDistricts();
        controller.changeMiracleCourtColor(players);
        assertEquals(Color.BLUE,miracleCourt.getColor());
    }
    @Test
    void changeMiracleCourtColorTest2BuiltInFinalRound(){
        controller.setBuiltDistricts();
        controller.addTOBuiltDistricts(List.of(miracleCourt));
        controller.changeMiracleCourtColor(players);
        assertEquals(Color.PURPLE,miracleCourt.getColor());
    }
    @Test
    void setBuiltDistricts(){
        assertNull(controller.builtDistrictsThisRound);
        controller.setBuiltDistricts();
        assertNotNull(controller.builtDistrictsThisRound);
        controller.addTOBuiltDistricts(List.of(district1,district2));
        assertTrue(controller.builtDistrictsThisRound.size() > 0);
        controller.setBuiltDistricts();
        assertEquals(0,controller.builtDistrictsThisRound.size());
    }
    @Test
    void addToBuiltDistricts(){
        controller.setBuiltDistricts();
        assertEquals(0,controller.builtDistrictsThisRound.size());
        controller.addTOBuiltDistricts(List.of(district1,district2));
        assertTrue(controller.builtDistrictsThisRound.contains(district1));
        assertTrue(controller.builtDistrictsThisRound.contains(district2));
    }

}
