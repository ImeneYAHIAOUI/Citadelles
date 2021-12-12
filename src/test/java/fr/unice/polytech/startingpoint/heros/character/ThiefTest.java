package fr.unice.polytech.startingpoint.heros.character;

import fr.unice.polytech.startingpoint.heros.character.Assassin;
import fr.unice.polytech.startingpoint.heros.character.Merchant;
import fr.unice.polytech.startingpoint.heros.character.Thief;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ThiefTest {
    Thief thief;
    IAToHero info;
    IA player;
    IA player2;
    IA player3;
    IA player4;
    String chosenPlayer;
    List<IPlayer> players;
    @BeforeEach
    void setup(){
        this.info = new IAToHero();
        this.thief =new Thief();
        this.player= new IA("Mooncake");
        this.player2 = new IA("Kirby");//thief
        this.player3 = new IA("Kazuya");
        this.player4 = new IA("Yoshi");
        info.setCurrentPlayer(player3);
        player3.setRole(thief);
        players=new ArrayList<>();
        players.add(player);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        info.setPlayers(players);
        player2.setRole(new Merchant());
        player2.setIsAssigned();

        player.setRole(new Assassin());
        player4.setRole(new Merchant());

    }
    @Test
    void doActionWithChosenPlayerAssasin(){//le chosen player est un assassin ou une personne assasiné
        chosenPlayer=player.getName();
        info.setChosenPlayer(chosenPlayer);
        assertEquals(info.getChosenPlayer().getName(),chosenPlayer);
        thief.doAction(info);
        assertFalse(player.getStolenPerson());
        assertEquals(player.getStolenBy(),null);
        chosenPlayer=player2.getName();
        info.setChosenPlayer(chosenPlayer);
        assertEquals(info.getChosenPlayer().getName(),chosenPlayer);
        thief.doAction(info);
        assertFalse(player2.getStolenPerson());
        assertEquals(player2.getStolenBy(),null);
    }
    @Test
    void doActionTest(){
        chosenPlayer=player4.getName();
        info.setChosenPlayer(chosenPlayer);
        assertEquals(info.getChosenPlayer().getName(),chosenPlayer);
        thief.doAction(info);
        assertTrue(player4.getStolenPerson());
        assertEquals(player4.getStolenBy(),player3);


    }
}
