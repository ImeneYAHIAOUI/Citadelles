package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerComparatorTest {
    IA player1;
    IA player2;
    IA player3;

    List<IPlayer> players;
    List<IPlayer> players1;
    List<IPlayer> players2;
    HeroDeck heroes;
    PlayerComparator comp;
    @BeforeEach
    void setUp() {
        player1 = new IA("link");
        player2 = new IA("Yoshi");
        player3 = new IA("Kirby");
        players = new ArrayList<IPlayer>();
        players1 = new ArrayList<IPlayer>();
        heroes = Initialization.heroeList();
        player1.setRole(heroes.get(0));
        player2.setRole(heroes.get(1));
        player3.setRole(heroes.get(2));
        //magicien king merchant
        //king merchant magicien heros
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players1.add(player3);
        players1.add(player1);
        players1.add(player2);
       comp =new PlayerComparator(players);
    }
    @Test
    void getSortedPlayersTest(){
        players2=comp.getSortedPlayers();
        assertEquals(players1,players2);


    }
}
