package fr.unice.polytech.startingpoint.core;
import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.District;
import fr.unice.polytech.startingpoint.output.GameResult;
import fr.unice.polytech.startingpoint.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameComparatorTest {
    District district1;
    District district2;
    District district3;
    District district4;




    List<District> hand1;
    List<District> hand2;
    List<District> hand3;
    List<District> hand4;
    Player player1;
    Player player2;
    Player player3;
    Player player4;

    List<Player> players;
    List<Player> players2;
    List<Player> sortedPlayers;

    GameComparator gameComparator;
    GameResult result1;
    GameResult result2;



    @BeforeEach
    void setUp() {
        district1 = new District(2, Color.YELLOW, "Manoir");
        district2 = new District(1, Color.BLUE, "Temple");
        district3 = new District(1, Color.GREEN, "Traverne");
        district4 = new District(2, Color.RED, "Tour");

        hand1 = new ArrayList<>();
        hand2 = new ArrayList<>();
        hand3 = new ArrayList<>();
        hand4 = new ArrayList<>();


        hand1.add(district1);
        hand2.add(district2);
        hand3.add(district3);
        hand4.add(district4);

        player1 = new Player(hand1, "link");
        player2 = new Player(hand2, "Yoshi");
        player3 = new Player(hand3, "Kirby");
        player4 = new Player(hand4, "Ness");

        player1.buildDistrict(0);
        player2.buildDistrict(0);
        player3.buildDistrict(0);
        player4.buildDistrict(0);

        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        sortedPlayers = new ArrayList<>();

        sortedPlayers.add(player4);
        sortedPlayers.add(player1);
        sortedPlayers.add(player3);
        sortedPlayers.add(player2);

        players2 = new ArrayList<>(players);

        gameComparator = new GameComparator(players);

        result1 = new GameResult(sortedPlayers);
        result2 = new GameResult(players2);
    }

    @Test
    void getSortedPlayers(){

        assertNotEquals(gameComparator.getSortedPlayers(),players2);
        assertEquals(gameComparator.getSortedPlayers(),sortedPlayers);

    }

    @Test
    void getResult(){
        assertEquals(gameComparator.getResult().getRanking(),result1.getRanking());
        assertNotEquals(gameComparator.getResult().getRanking(),result2.getRanking());

    }

}
