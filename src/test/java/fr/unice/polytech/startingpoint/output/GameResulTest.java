package fr.unice.polytech.startingpoint.output;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.core.Comparator;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.BOTs.NeutralBot;
import fr.unice.polytech.startingpoint.player.IPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

  import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GameResulTest {
    District district1;
    District district2;
    District district3;
    District district4;
    List<IDistrict> hand1;
    List<IDistrict> hand2;
    IA player1;
    IA player2;
    List<IPlayer> players;
    List<IPlayer> players2;
    List<IPlayer> sortedPlayers;
    Comparator gameComparator;
    GameResult result1;
    GameResult result2;
    private IA Player;
    @BeforeEach
    void game() {

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
        try {
            district3 = new District(3, Color.GREEN, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district4 = new District(5, Color.GREEN, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }

        hand1 = new ArrayList<>();
        hand2 = new ArrayList<>();

        hand1.add(district1);
        hand1.add(district2);
        hand2.add(district3);
        hand2.add(district4);

        player1 = new NeutralBot("sam");
        player1.getDistrict(hand1);

        player2 = new NeutralBot("jerry");
        player2.getDistrict(hand2);

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

        result1 = new GameResult( sortedPlayers);
        result2= new GameResult( players2 );

    }

    @Test
    void getRanking(){
        GameResult result1 = new GameResult( sortedPlayers);
        assertEquals(result1.getRanking(),sortedPlayers);
        assertNotEquals(result1.getRanking(),players2);
    }

}

