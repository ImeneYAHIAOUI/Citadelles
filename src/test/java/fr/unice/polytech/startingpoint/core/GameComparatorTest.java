package fr.unice.polytech.startingpoint.core;
import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.District;
import fr.unice.polytech.startingpoint.cards.DistrictName;
import fr.unice.polytech.startingpoint.output.GameResult;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
    IA player1;
    IA player2;
    IA player3;
    IA player4;

    List<IPlayer> players;
    List<IPlayer> players2;
    List<IPlayer> sortedPlayers;

    Comparator gameComparator;
    GameResult result1;
    GameResult result2;



    @BeforeEach
    void setUp() {
        district1 = new District(2, Color.YELLOW, DistrictName.MANOIR);
        district2 = new District(1, Color.BLUE, DistrictName.MANOIR);
        district3 = new District(1, Color.GREEN, DistrictName.CHATEAU);
        district4 = new District(2, Color.RED,DistrictName.PALAIS);

        hand1 = new ArrayList<>();
        hand2 = new ArrayList<>();
        hand3 = new ArrayList<>();
        hand4 = new ArrayList<>();


        hand1.add(district1);
        hand2.add(district2);
        hand3.add(district3);
        hand4.add(district4);

        player1 = new IA("link");
        player1.getDistrict(hand1);

        player2 = new IA("Yoshi");
        player2.getDistrict(hand2);

        player3 = new IA("Kirby");
        player3.getDistrict(hand3);

        player4 = new IA("Ness");
        player4.getDistrict(hand4);

        player1.buildDistrict(district1);
        player2.buildDistrict(district2);
        player3.buildDistrict(district3);
        player4.buildDistrict(district4);

        players = new ArrayList<IPlayer>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        sortedPlayers = new ArrayList<IPlayer>();

        sortedPlayers.add(player4);
        sortedPlayers.add(player1);
        sortedPlayers.add(player3);
        sortedPlayers.add(player2);

        players2 = new ArrayList<IPlayer>(players);

        gameComparator = new Comparator();
        gameComparator.gameComp(players);

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
