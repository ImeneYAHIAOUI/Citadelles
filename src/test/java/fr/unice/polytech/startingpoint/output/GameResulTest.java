package fr.unice.polytech.startingpoint.output;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.District;
import fr.unice.polytech.startingpoint.cards.DistrictName;
import fr.unice.polytech.startingpoint.core.GameComparator;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

  import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GameResulTest {   District district1;
     District district2;

    List<District> hand1;
    List<District> hand2;


    IA player1;
    IA player2;


    List<IPlayer> players;
    List<IPlayer> players2;
    List<IPlayer> sortedPlayers;

    GameComparator gameComparator;
    GameResult result1;
    private IA Player;
    @BeforeEach
    void game() {

        district1 = new District(1, Color.YELLOW, DistrictName.MANOIR);
        district2 = new District(2, Color.BLUE, DistrictName.PALAIS);

        hand1 = new ArrayList<>();
        hand2 = new ArrayList<>();

        hand1.add(district1);
        hand2.add(district2);


        player1 = new IA("sam");
        player1.getDistrict(hand1);

        player2 = new IA("jerry");
        player2.getDistrict(hand2);

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
    void getRanking(){
        GameResult result1 = new GameResult( sortedPlayers);
        assertEquals(result1.getRanking(),sortedPlayers);
        assertNotEquals(result1.getRanking(),players2);
    }

}

