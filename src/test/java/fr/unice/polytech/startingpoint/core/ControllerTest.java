package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerTest {
    Controller controller;
    IPlayer thief;
    IA player;
    List<IPlayer> players;
    List<IPlayer> players1;
    IA player1;
    IA player2;
    IA player3;
    IA player4;
    @BeforeEach
    void setup(){
        controller=new Controller();
        thief=new IA("voleur");
        thief.addGold(12);
        player= new IA("joe");
        player.addGold(5);
        player1=new IA("adrien");
        player2=new IA("jean philippe");
        player3=new IA("Donial");
        player4=new IA("jules");
        player.setStolenPerson();
        player.setStolenBy(thief);
        player3.setIsAssigned();
        players=List.of(thief,player,player1,player2,player3,player4);
        players1=List.of(player1,player2,player4);


    }
    @Test
    void updateTest(){
        assertEquals(controller.getStolenPerson(),null);
        assertEquals(controller.getThief(),null);
        assertEquals(controller.getAssassinated(),null);
        controller.update(players);
        assertEquals(controller.getStolenPerson(),player);
        assertEquals(controller.getThief(),thief);
        assertEquals(controller.getAssassinated(),player3);
    }
    @Test
    void updateTest1(){
        //dans la liste des joeurs on a ni l'assasin ni le voleur
        controller.update(players1);
        assertEquals(controller.getStolenPerson(),null);
        assertEquals(controller.getThief(),null);
        assertEquals(controller.getAssassinated(),null);

    }
    @Test
    void GiveGoldToTheTiefTest(){
        controller.update(players);
        assertEquals(thief.getGold(),12);
        assertEquals(player.getGold(),5);
        controller.GiveGoldToTheTief();
        assertEquals(thief.getGold(),17);
        assertEquals(player.getGold(),0);

    }
    @Test
    void GiveGoldToTheTiefTest1(){
        controller.update(players);
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
        controller.update(players);
        controller.GiveGoldToTheTief();
        assertEquals(thief.getGold(),12);

    }
    @Test
    void GiveGoldToTheTiefTest3(){
        //thief is null
        controller.setThief(null);
        controller.GiveGoldToTheTief();
        assertEquals(player.getGold(),5);

    }
}
