package fr.unice.polytech.startingpoint.player;
import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.heros.IHero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IATest {
    IA player1 ;
    IA player2 ;
    IA player3 ;
    IA player4 ;
    IA player5 ;
    IA player6 ;
    IA player7 ;
    IA player8 ;
    HeroDeck heroDeck;
    IHero hero1;
    IHero hero2;
    IHero hero3;
    Information information;
    Information information2;
    Information information3;
    Information information4;
    DistrictDeck Mockdeck;
    Random mockRand;
    List<IPlayer> players;
    Predicate<IPlayer> canBuild;
    List<IDistrict> districtList;
    IDistrict District1;
    IDistrict District2;



    @BeforeEach
    void setUp(){
        player1 = new IA("Link");
        player2 = new IA("Kirby");
        player3 = new IA("Kazuya");
        player4 = new IA("Yoshi");
        player5 = new IA("Peach");
        player6 = new IA("Zelda");
        player7 = new IA("Wario");
        player8 = new IA("Bowser");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        heroDeck = Initialization.heroeList();
        Mockdeck = mock(DistrictDeck.class);
        information = new Information();
        information2 = new Information();
        information3 = new Information();
        information4 = new Information();
        mockRand = mock(Random.class);
        when(mockRand.nextInt(anyInt())).thenReturn(0,1,2);
        player1.chooseHero(heroDeck,mockRand.nextInt(anyInt()));
        heroDeck = Initialization.heroeList();
        player2.chooseHero(heroDeck,mockRand.nextInt(anyInt()));
        heroDeck = Initialization.heroeList();
        player3.chooseHero(heroDeck,mockRand.nextInt(anyInt()));
        heroDeck = Initialization.heroeList();

        canBuild = player -> player.getHand().stream().anyMatch(d -> d.getPrice()<=player.getGold());
        districtList = new ArrayList<>();
        District1 = new District(1, Color.YELLOW,DistrictName.MANOIR);
        District2 =new District(3,Color.GREEN,DistrictName.TAVERNE);
    }


    @Test
    void chooseHeroTest(){
        hero1 = heroDeck.get(0);

        when(mockRand.nextInt(anyInt())).thenReturn(0,1,2,4,-1);


        player1.chooseHero(heroDeck,mockRand.nextInt(anyInt()));

        assertEquals(player1.getRole(),hero1);
        assertFalse(heroDeck.contains(hero1));
        heroDeck = Initialization.heroeList();
        hero2 = heroDeck.get(1);

        player2.chooseHero(heroDeck,mockRand.nextInt(anyInt()));
        assertEquals(player2.getRole(),hero2);
        assertFalse(heroDeck.contains(hero2));

        heroDeck = Initialization.heroeList();
        hero3 = heroDeck.get(2);

        player3.chooseHero(heroDeck,mockRand.nextInt(anyInt()));
        assertEquals(player3.getRole(),hero3);
        assertFalse(heroDeck.contains(hero3));

        heroDeck = Initialization.heroeList();

        assertThrows(RuntimeException.class,() -> player4.chooseHero(heroDeck,mockRand.nextInt(anyInt())));

        heroDeck = Initialization.heroeList();

        assertThrows(RuntimeException.class,() -> player5.chooseHero(heroDeck,mockRand.nextInt(anyInt())));

    }

    @Test
    void activateHeroTest(){
        player2.setCrown();

        player1.activateHero(players,Mockdeck,information);
        assertNotNull(information.getCrownHolder());
        assertNotNull(information.getCurrentPlayer());
        assertNull(information.getCardCount());
        assertNull(information.getGold());
        assertNull(information.getDeck());
        assertNull(information.getChosenPlayer());
        assertNull(information.getBuiltDistricts());
        assertNull(information.getHeros());
        assertNull(information.getChosenCards());

        player2.activateHero(players,Mockdeck,information2);
        assertNull(information2.getCrownHolder());
        assertNotNull(information2.getCurrentPlayer());
        assertNull(information2.getCardCount());
        assertNull(information2.getGold());
        assertNull(information2.getDeck());
        assertNull(information2.getChosenPlayer());
        assertNull(information2.getBuiltDistricts());
        assertNull(information2.getHeros());
        assertNull(information2.getChosenCards());

        player3.activateHero(players,Mockdeck,information3);
        assertNull(information3.getCrownHolder());
        assertNotNull(information3.getCurrentPlayer());
        assertTrue(information3.getChosenPlayer()!=null || information3.getChosenCards()!=null);
        assertNotNull(information3.getCardCount());
        assertNotNull(information3.getGold());
        assertNotNull(information3.getDeck());
        assertNotNull(information3.getBuiltDistricts());
        assertNotNull(information3.getHeros());

    }

    @Test
    void magicienChoiceTest(){
        List<IDistrict> districtList1 = new ArrayList<>();
        List<IDistrict> districtList2 = new ArrayList<>();
        districtList1.add(District2);
        districtList1.add(District2);
        districtList1.add(District1);
        districtList2.add(District1);
        districtList2.add(District2);
        player1.getDistrict(districtList1);
        player2.getDistrict(districtList2);
        information.setInformationForMagician(players,player3,Mockdeck);

        player3.magicienChoice(information,players);
        assertEquals(information.getChosenPlayer(),player1);
        assertEquals(information.getChosenCards().size(),0);
        districtList.add(District1);
        districtList.add(District1);
        districtList.add(District2);

        when(Mockdeck.giveDistrict(3)).thenReturn(districtList);
        information2.setInformationForMagician(players,player3,Mockdeck);

        player3.getDistrict(Mockdeck.giveDistrict(3));
        player3.magicienChoice(information2,players);
        assertTrue(information2.getChosenCards().size()>0);
        assertNull(information2.getChosenPlayer());
        assertTrue(information2.getChosenCards().contains(District1));

        districtList.clear();
        districtList.add(District2);
        districtList.add(new Laboratory());
        /* ici on a pas de cartes qu'on peut acheter mais on choisis quand meme de ne pas
           échager sa main avec un autre joueur pour ne pas perdre la carte merveille
           on n'échange pas l'autre carte non plus car on peut l'acheter si on choisis de
           prendre des pieces
         */
        player1.hand.clear();

        when(Mockdeck.giveDistrict(2)).thenReturn(districtList);
        player1.getDistrict(Mockdeck.giveDistrict(2));
        information3.setInformationForMagician(players,player1,Mockdeck);
        player1.magicienChoice(information3,players);
        assertNull(information3.getChosenPlayer());
        assertEquals(information3.getChosenCards().size(),0);
        player1.hand.clear();
        districtList.clear();
        player1.gold = 0;
        districtList.add(District2);
        districtList.add(District2);
        player2.gold = 1;
        player3.gold = 3;

        when(Mockdeck.giveDistrict(2)).thenReturn(districtList);
        information4.setInformationForMagician(players,player1,Mockdeck);
        player1.getDistrict(Mockdeck.giveDistrict(2));
        player1.magicienChoice(information4,players);
        assertEquals(information4.getChosenPlayer(),player2);
        assertEquals(information3.getChosenCards().size(),0);





    }
    @Test
    void doActionTest(){

        districtList.add(District1);
        districtList.add(District2);

        when(Mockdeck.giveDistrict(2)).thenReturn(districtList);
        player1.getDistrict(Mockdeck.giveDistrict(2));
        int HandPreviousSize = player1.getHand().size();
        int BuiltDistrictNum = player1.getBuiltDistricts().size();
        int previousGoldAmount = player1.getGold();
        int previousScore = player1.getScore();
        player1.doAction();
        assertEquals(player1.getHand().size(),HandPreviousSize-1);
        assertEquals(player1.getBuiltDistricts().size(),BuiltDistrictNum+1);
        assertEquals(player1.getGold(),previousGoldAmount- District1.getPrice());
        assertEquals(player1.getScore(),previousScore+District1.getPrice());

        HandPreviousSize = player1.getHand().size();
        BuiltDistrictNum = player1.getBuiltDistricts().size();
        previousGoldAmount = player1.getGold();
        previousScore = player1.getScore();

        player1.doAction();
        assertEquals(player1.getHand().size(),HandPreviousSize);
        assertEquals(player1.getBuiltDistricts().size(),BuiltDistrictNum);
        assertEquals(player1.getGold(),previousGoldAmount);
        assertEquals(player1.getScore(),previousScore);



    }


    @Test
    void drawOrGetGoldTest(){

    }

}
