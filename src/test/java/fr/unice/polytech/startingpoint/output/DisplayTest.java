package fr.unice.polytech.startingpoint.output;

import fr.unice.polytech.startingpoint.cards.CardException;
import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.District;
import fr.unice.polytech.startingpoint.cards.DistrictName;
import fr.unice.polytech.startingpoint.core.Comparator;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DisplayTest {
    District district1;
    District district2;

    List<District> hand1;
    List<District> hand2;
    List<District> hand3;

    IA player1;
    IA player2;
    IA player3;


    List<IPlayer> players;
    List<IPlayer> players2;
    List<IPlayer> sortedPlayers;
    List<IPlayer> sortedPlayers2;

    Comparator gameComparator;
    GameResult result1;
    GameResult result2;
    String ranking;
    String winnerName;
    String fullresult;
    String ranking2;
    String ranking3;
    String winnersNames;


    @BeforeEach
    public void game() {

        try {
            district1 = new District(1, Color.YELLOW, DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district2 = new District(2, Color.BLUE, DistrictName.PALAIS);
        } catch (CardException e) {
            e.printStackTrace();
        }

        hand1 = new ArrayList<>();
        hand2 = new ArrayList<>();
        hand3 = new ArrayList<>();

        hand1.add(district1);
        hand2.add(district2);
        hand3.add(district1);


        player1 = new IA("sam");
        player2 = new IA("jerry");
        player3 = new IA("TOM");
        player1.addGold(3);
        player2.addGold(3);
        player3.addGold(3);
        player1.buildDistrict(district1);
        player2.buildDistrict(district2);
        player3.buildDistrict(district1);


        players = new ArrayList<>();
        players2 = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players2.add(player1);
        players2.add(player3);

        sortedPlayers = new ArrayList<>();
        sortedPlayers2 = new ArrayList<>();
        sortedPlayers.add(player2);
        sortedPlayers.add(player1);
        sortedPlayers2.add(player1);
        sortedPlayers2.add(player3);


        gameComparator = new Comparator();
        gameComparator.gameComp(players);

        result2 = new GameResult(players2);
        ranking = ("1st place : jerry -> 2 points.\n" + "2nd place : sam -> 1 points.\n");
        ranking2 = ("1st place : sam -> 2 points.\n" + "2nd place : jerry -> 1 points.\n");
        winnerName = "Winner : jerry\n\n";
        ranking3 = ("1st place : sam -> 1 points.\n" + "1st place : TOM -> 1 points.\n");
        result1 = new GameResult(sortedPlayers);
        fullresult = winnerName + ranking;
        winnersNames = "Winners : sam - TOM\n\n";


    }


    @Test
    void displayRank() {
        Assertions.assertEquals(Display.displayRank(sortedPlayers), ranking);
        Assertions.assertNotEquals(Display.displayRank(sortedPlayers), ranking2);
        Assertions.assertEquals(Display.displayRank(sortedPlayers2), ranking3);
    }

    @Test
    void displayWinners() {
        Assertions.assertEquals(Display.displayWinners(sortedPlayers), winnerName);
        Assertions.assertEquals(Display.displayWinners(sortedPlayers2), winnersNames);
    }

   /* @Test
    void dispalyResult() {
        Assertions.assertEquals(Display.displayResult(result1), fullresult);


}
*/
}


