package fr.unice.polytech.startingpoint.heros.character;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.cards.district.Laboratory;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.BOTs.NeutralBot;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;
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
        List<IDistrict> identicalDistrictList1;
        List<IDistrict> identicalDistrictList2;
        IAToHero info1;
        IAToHero info2;
        IAToHero info3;
        HeroDeck heroes;
        IA player;
        IA player2;
        IA player3;
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
            identicalDistrictList1 = new ArrayList<>();
            identicalDistrictList2 = new ArrayList<>();
        try {
            identicalDistrictList1.add(new District(1,Color.GREEN,DistrictName.TAVERNE));
        } catch (CardException e) {
            e.printStackTrace();
        }try {
            identicalDistrictList1.add(new District(2,Color.YELLOW,DistrictName.PALAIS));
        } catch (CardException e) {
            e.printStackTrace();
        }try {
            identicalDistrictList2.add(new District(1,Color.GREEN,DistrictName.TAVERNE));
        } catch (CardException e) {
            e.printStackTrace();
        }try {
            identicalDistrictList2.add(new District(2,Color.YELLOW,DistrictName.PALAIS));
        } catch (CardException e) {
            e.printStackTrace();
        }
            try {
                districtList3.add(new District(1,Color.GREEN,DistrictName.TAVERNE));
            } catch (CardException e) {
                e.printStackTrace();
            }
            heroes = new HeroDeck();
            heroes = Initialization.heroeList(8);
            info1 = new IAToHero();
            info2 = new IAToHero();
            info3 = new IAToHero();
            player = new NeutralBot("Player1");
            player2 = new NeutralBot("Player2");
            player3 = new NeutralBot("Player3");
            players = new ArrayList<>();
            players.add(player);
            players.add(player2);
            players.add(player3);
            when(mockRand.nextInt(anyInt())).thenReturn(0,0,0);
            player2.setRole(heroes.get(0));
            player3.setRole(heroes.get(0));
            player.setRole(heroes.get(0));
        }

        @Test
        void doActionDrawTest(){
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
        }
        @Test
        void doActionExchangeTest(){
            player2.setHand(districtList1);
            player.setHand(districtList3);
            info2.setCurrentPlayer(player);
            info2.setInformationForMagician(players,player,deck);
            info2.setChosenPlayer("Player2");
            magician.doAction(info2);
            assertEquals(player2.getHand(), districtList3);
            assertEquals(player.getHand(), districtList1);
            assertNotEquals(player2.getHand(), districtList1);
            assertNotEquals(player.getHand(), districtList3);
        }
        @Test
        void doActionExchangeIdenticalCards(){
            when(mockDeck.giveDistrict(anyInt())).thenReturn(identicalDistrictList1).thenReturn(identicalDistrictList2);
            player.setHand(mockDeck.giveDistrict(2));
            player2.setHand(mockDeck.giveDistrict(2));
            info2.setCurrentPlayer(player);
            info2.setInformationForMagician(players,player,deck);
            info2.setChosenPlayer("Player2");
            magician.doAction(info2);
            assertEquals(player2.getHand(), identicalDistrictList1);
            assertEquals(player.getHand(), identicalDistrictList2);
            assertNotEquals(player2.getHand(), identicalDistrictList2);
            assertNotEquals(player.getHand(), identicalDistrictList1);

        }

    }