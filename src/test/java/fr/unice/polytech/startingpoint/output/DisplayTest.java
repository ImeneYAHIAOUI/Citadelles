package fr.unice.polytech.startingpoint.output;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.District;
import fr.unice.polytech.startingpoint.cards.DistrictName;
import fr.unice.polytech.startingpoint.core.Comparator;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class DisplayTest {
    District district1;
    District district2;

    List<District> hand1;
    List<District> hand2;


    IA player1;
    IA player2;


    List<IPlayer> players;
    List<IPlayer> players2;
    List<IPlayer> sortedPlayers;

    Comparator gameComparator;
    GameResult result1;
    GameResult result2;
    String ranking;
    String winnerName;
    String fullresult;

    @BeforeEach
    public void game() {

        district1 = new District(1, Color.YELLOW, DistrictName.MANOIR);
        district2 = new District(2, Color.BLUE, DistrictName.PALAIS);

        hand1 = new ArrayList<>();
        hand2 = new ArrayList<>();

        hand1.add(district1);
        hand2.add(district2);


        player1 = new IA("sam");
        player2 = new IA("jerry");

        player1.buildDistrict(district1);
        player2.buildDistrict(district2);


        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        sortedPlayers = new ArrayList<>();

        sortedPlayers.add(player2);
        sortedPlayers.add(player1);


        players2 = new ArrayList<>(players);

        gameComparator = new Comparator();
        gameComparator.gameComp(players);

        result2= new GameResult( players2 );
        ranking = ("1st place : jerry -> 2 points.\n"+"2nd place : sam -> 1 points.\n" );
        winnerName = "Winner : jerry\n\n";
        result1 = new GameResult( sortedPlayers);
        fullresult = winnerName+ranking;


    }

/*
    @Test
     void displayRank() {
        //assertEquals( Display.displayRank(sortedPlayers), ranking);
    }

    @Test
    void displayWinners() {
        //assertEquals(Display.displayWinners(sortedPlayers), winnerName);
    }

    @Test
    void dispalyResult() {
        //assertEquals(Display.displayResult(result1), fullresult);

    }*/
}


