package fr.unice.polytech.startingpoint.output;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.District;
import fr.unice.polytech.startingpoint.cards.DistrictName;
import fr.unice.polytech.startingpoint.core.GameComparator;
import fr.unice.polytech.startingpoint.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DisplayTest {
    District district1;
    District district2;

    List<District> hand1;
    List<District> hand2;


     Player player1;
    Player player2;


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
    public void game() {

        district1 = new District(1, Color.YELLOW, DistrictName.MANOIR);
        district2 = new District(2, Color.BLUE, DistrictName.PALAIS);

        hand1 = new ArrayList<>();
        hand2 = new ArrayList<>();

        hand1.add(district1);
        hand2.add(district2);


        player1 = new Player(hand1, "sam");
        player2 = new Player(hand2, "jerry");

        player1.buildDistrict(0);
        player2.buildDistrict(0);


        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        sortedPlayers = new ArrayList<>();

        sortedPlayers.add(player2);
        sortedPlayers.add(player1);


        players2 = new ArrayList<>(players);

        gameComparator = new GameComparator(players);

        GameResult result1 = new GameResult( sortedPlayers);
        GameResult result2= new GameResult( players2 );

    }


    @Test
     void displayRank() {

        Display display1= new Display(result1);
        Display display2= new Display(result2);
        String ranking = ("1st place : jerry -> 2 points.\n"+"2nd place : sam -> 1 points.\n" );



        assertEquals( display1.displayRank(sortedPlayers), ranking);



    }

    @Test
    void displayWinners() {
        Display display1= new Display(result1);
        String winnername = "Winner : jerry\n\n";
        assertEquals(display1.displayWinners(sortedPlayers), winnername);

    }

    @Test
    void dispalyResult() {
        GameResult result1 = new GameResult( sortedPlayers);
        Display display1= new Display( result1);
        String winnername = "Winner : jerry\n\n";
        String ranking = ("1st place : jerry -> 2 points.\n"+"2nd place : sam -> 1 points.\n" );;
        String fullresult = winnername+ranking;
        assertEquals(display1.displayResult(), fullresult);

    }
}


