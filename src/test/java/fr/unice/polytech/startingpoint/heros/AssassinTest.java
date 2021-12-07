package fr.unice.polytech.startingpoint.heros;

import fr.unice.polytech.startingpoint.core.Controller;
import fr.unice.polytech.startingpoint.heros.character.Assassin;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.Information;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AssassinTest {
    Assassin assassin;
    Information info;
    IA player;
    IA player2;
    String chosenPlayer;
    List<IPlayer> players;
    Controller controller;
    @BeforeEach
    void setup(){
        this.info = new Information();
        this.assassin =new Assassin();
        this.player=new IA("Imene");
        this.player2=new IA("sourour");
        players=new ArrayList<>();
        players.add(player);
        players.add(player2);
        info.setPlayers(players);
        info.setChosenPlayer(player2.getName());
        this.chosenPlayer=player2.getName();
        controller=new Controller();
        info.setController(controller);

    }
    @Test
    void doAction(){
        info.setChosenPlayer(chosenPlayer);
        assassin.doAction(info);
        assertEquals(controller.getAssassinated(),player2);

    }
}
