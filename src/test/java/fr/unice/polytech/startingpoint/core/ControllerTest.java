package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.heros.character.Merchant;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerTest {
    Controller controller;
    IPlayer thief;
    IA player;
    @BeforeEach
    void setup(){
        controller=new Controller();
        thief=new IA("voleur");
        thief.addGold(12);
        player= new IA("joe");
        player.addGold(5);
        controller.setThief(thief);
        controller.setStolenPerson(player);

    }
    @Test
    void GiveGoldToTheTiefTest(){
        assertEquals(thief.getGold(),12);
        assertEquals(player.getGold(),5);
        controller.GiveGoldToTheTief();
        assertEquals(thief.getGold(),17);
        assertEquals(player.getGold(),0);

    }
    @Test
    void GiveGoldToTheTiefTest1(){
        assertEquals(thief.getGold(),12);
        player.removeGold(player.getGold());
        assertEquals(player.getGold(),0);
        controller.GiveGoldToTheTief();
        assertEquals(thief.getGold(),12);
        assertEquals(player.getGold(),0);

    }
    @Test
    void GiveGoldToTheTiefTest2(){
        controller.setStolenPerson(null);
        controller.GiveGoldToTheTief();
        assertEquals(thief.getGold(),12);

    }
    @Test
    void GiveGoldToTheTiefTest3(){
        controller.setThief(null);
        controller.GiveGoldToTheTief();
        assertEquals(player.getGold(),5);

    }
}
