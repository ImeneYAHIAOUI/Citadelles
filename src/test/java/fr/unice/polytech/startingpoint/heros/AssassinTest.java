package fr.unice.polytech.startingpoint.heros;

import fr.unice.polytech.startingpoint.core.Controller;
import fr.unice.polytech.startingpoint.heros.character.Assassin;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.IAToHero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AssassinTest {
    Assassin assassin;
    IAToHero info;
    IA player;
    IA player2;
    String chosenPlayer;
    List<IPlayer> players;
    Controller controller;
    @BeforeEach
    void setup(){
        this.info = new IAToHero();
        this.assassin =new Assassin();
        this.player=new IA("Imene");
        this.player2=new IA("sourour");
        players=new ArrayList<>();
        players.add(player);
        players.add(player2);
        info.setPlayers(players);
        info.setChosenPlayer(player2.getName());
        this.chosenPlayer=player2.getName();
        player2.setIsAssigned();



    }
    @Test
    void doAction(){
        info.setChosenPlayer(chosenPlayer);
        assassin.doAction(info);
        assertTrue(player2.getIsAssigned());
        assertFalse(player.getIsAssigned());

    }
}
