package fr.unice.polytech.startingpoint.heros;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.Information;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MagicianTest {

        Magician magician;
        DistrictDeck deck;
        DistrictDeck mockDeck;
        List<IDistrict> districtList1;
        List<IDistrict> districtList2;
        List<IDistrict> districtList3;
        Information info1;
        Information info2;
        Information info3;
        HeroDeck heroes;
        IPlayer player;
        IPlayer player2;
        IPlayer player3;
        Random mockRand = mock(Random.class);
        List<IPlayer> players;


        @BeforeEach
        void setUp() {
            magician= new Magician();
            deck = new DistrictDeck(Initialization.districtList());
            mockDeck = mock(DistrictDeck.class);
            districtList1 = deck.giveDistrict(2);
            districtList2 = deck.giveDistrict(2);
            districtList3 = new ArrayList<>();
            districtList3.add(new Laboratory());
            districtList3.add(new District(1,Color.GREEN,DistrictName.TAVERNE));
            heroes = new HeroDeck();
            heroes = Initialization.heroeList();
            info1 = new Information();
            info2 = new Information();
            info3 = new Information();
            player = new IA("Player1");
            player2 = new IA("Player2");
            player3 = new IA("Player2");
            players = new ArrayList<>();
            players.add(player);
            players.add(player2);
            players.add(player3);
        }

        @Test
        void doActionTest(){
            when(mockRand.nextInt(anyInt())).thenReturn(0,0,0);
            player2.chooseHero(heroes,mockRand.nextInt(anyInt()));
            player3.chooseHero(heroes,mockRand.nextInt(anyInt()));
            player.chooseHero(heroes,mockRand.nextInt(anyInt()));

            when(mockDeck.giveDistrict(anyInt())).thenReturn(districtList2);
            info1.setInformationForMagician(players,player,mockDeck);
            districtList3 = player.getHand();
            info1.setChosenCards(districtList1);
            magician.doAction(info1);
            for (IDistrict district : districtList1) {
                assertFalse(player.getHand().contains(district));
            }
            for (IDistrict district : districtList2) {
                assertTrue(player.getHand().contains(district));
            }

            player2.setHand(districtList1);

            info2.setCurrentPlayer(player);
            info2.setInformationForMagician(players,player,deck);

            info2.setChosenPlayer("Player2",players);

            magician.doAction(info2);

            assertEquals(player2.getHand(), districtList2);
            assertEquals(player.getHand(), districtList1);
            assertNotEquals(player2.getHand(), districtList1);
            assertNotEquals(player.getHand(), districtList3);




        }
        @Test
        void testDoAction(){

        }


    }