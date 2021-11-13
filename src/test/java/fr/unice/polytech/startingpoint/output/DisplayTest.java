package fr.unice.polytech.startingpoint.output;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.District;
import fr.unice.polytech.startingpoint.core.GameComparator;
import fr.unice.polytech.startingpoint.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DisplayTest {  District district1;
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
    private Player Player;
    private StringBuilder ranking;
    private String rankingtest;
    private String winnername;


    @BeforeEach
    void game() {
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

        player1 = new Player(hand1, "sam");
        player2 = new Player(hand2, "jerry");
        player3 = new Player(hand3, "tom");
        player4 = new Player(hand4, "marie");

        player1.buildDistrict(2);
        player2.buildDistrict(4);
        player3.buildDistrict(1);
        player4.buildDistrict(3);

        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        sortedPlayers = new ArrayList<>();

        sortedPlayers.add(player2);
        sortedPlayers.add(player4);
        sortedPlayers.add(player1);
        sortedPlayers.add(player3);

        players2 = new ArrayList<>(players);

        gameComparator = new GameComparator(players);

        result1 = new GameResult(sortedPlayers);



        StringBuilder ranking = new StringBuilder();
        ranking.append("1st place : jerry -> 4 points \n");
        ranking.append("2nd place : marie -> 3 points\n");
        ranking.append("3rd place : sam   -> 3 points\n");
        ranking.append("4th place : tom   -> 1 points\n");
        String rankingtest = ranking.toString();
        String winnername = "jerry";
        String winnersnames = "Winners : jerry - marie";


    }


    @Test
    void displayRank() {
        assertEquals(Display.displayRank(result1.getRanking()), this.rankingtest);
        assertNotEquals(Display.displayRank(result2.getRanking()), this.rankingtest);


    }

    @Test
    void displayWinners() {

        assertEquals(Display.displayWinners(result1.getRanking()), this.winnername);
        player4.buildDistrict(4);
        String winnersname = "Winners : jerry - marie";
        assertEquals(Display.displayWinners(result1.getRanking()), winnersname);

    }

    @Test
    void dispalyResult() {
        StringBuilder fullresult = new StringBuilder();
        fullresult.append(winnername).append(rankingtest).toString();
        assertEquals(Display.displayResult(), fullresult);

    }
}


