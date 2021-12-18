package fr.unice.polytech.startingpoint.player;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;
import fr.unice.polytech.startingpoint.player.IA.Strategies.DrawOrGetGoldStrategies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DrawOrGetGoldStrategiesTest {
    DistrictDeck mockDeck = mock(DistrictDeck.class);
    DistrictDeck realDeck ;
    IAToHero information;
    List<IDistrict> districtList;
    List<IDistrict> districtList2;
    IDistrict district1;
    IDistrict district2;
    IDistrict district3;
    IDistrict district4;
    IDistrict district5;
    Treasure treasure;
    Player player1;
    Player player2;
    Player player3;
    List<IPlayer> players;
    DrawOrGetGoldStrategies choice;
    Predicate<IDistrict> isAffordable;
    @BeforeEach
    void setUp(){
        players = new ArrayList<>();
        player1 = new IA("Player1");
        player2 = new IA("Player2");
        player3 = new IA("Player3");
        players.add(player1);
        players.add(player2);
        players.add(player3);
        player1.setCrown();
        information = new IAToHero();
        information.setInformationForKing(player1,players,treasure);
        try {
            district1 = new District(1, Color.YELLOW,DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district2 =new District(3,Color.GREEN,DistrictName.TAVERNE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district3 =new District(5,Color.GREEN,DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }try {
            district4 =new District(3,Color.YELLOW,DistrictName.PALAIS);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district5 =new District(1,Color.YELLOW,DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }
        districtList = new ArrayList<>();
        realDeck = new DistrictDeck(Initialization.districtList());
        choice = new DrawOrGetGoldStrategies();
        treasure = new Treasure(30);
        districtList2 = new ArrayList<>();
        isAffordable = d -> d.getPrice() <= player1.getGold();
    }

    @Test
    void chooseDistrictsBasedOnAffordabilityTest(){
        districtList.add(district1);
        districtList.add(district2);
        int size = realDeck.getDeckSize();
        List<IDistrict> chosenDist = choice.chooseDistrictsBasedOnAffordability(districtList,1,realDeck,new ArrayList<>());
        assertEquals(1,chosenDist.size());
        assertTrue(chosenDist.contains(district1));
        assertEquals(size+1, realDeck.getDeckSize());
        districtList.add(district1);
        size = realDeck.getDeckSize();
        chosenDist = choice.chooseDistrictsBasedOnAffordability(districtList,2,realDeck,new ArrayList<>());
        assertEquals(2,chosenDist.size());
        assertTrue(chosenDist.contains(district1) && chosenDist.contains(district2));
        assertEquals(size,realDeck.getDeckSize());
    }


    @Test
    void NoAffordableCardsChoiceTest(){
        districtList.add(district2);
        districtList.add(district3);
        player1.setHand(districtList);
        information.setInformationForKing(player1,players,treasure);
        choice.NoAffordableCardsChoice(realDeck,treasure,information);
        assertEquals(3,player1.getHand().size());
        player1.addGold(1);
        districtList.add(district1);
        assertEquals(1,player1.getGold());
        choice.NoAffordableCardsChoice(realDeck,treasure,information);
        assertEquals(3,player1.getGold());
    }


    @Test
    void draw1Test(){
        districtList.add(district1);
        districtList.add(district2);
        when(mockDeck.giveDistrict(2)).thenReturn(districtList);
        information.setInformationForKing(player1,players,treasure);
        choice.draw(mockDeck,information,2,1);
        assertTrue(player1.getHand().contains(district1));
        assertEquals(districtList.size(),1);
    }


    @Test
    void drawOrGetGold1Test1(){
        districtList.add(district1);
        districtList.add(district2);
        districtList2.add(district3);
        player1.setHand(districtList2);
        when(mockDeck.giveDistrict(2)).thenReturn(districtList);
        information.setInformationForKing(player1,players,treasure);
        choice.drawOrGetPieces(mockDeck,treasure,information,isAffordable);
        assertTrue(player1.getHand().contains(district1));
        assertEquals(districtList.size(),1);
        choice.drawOrGetPieces(mockDeck,treasure,information,isAffordable);
        assertEquals(2,player1.getGold());
    }
    @Test
    void drawOrGetGold1Test2(){
        player1.addGold(2);
        districtList2.add(district1);
        districtList2.add(district2);
        player1.setHand(districtList2);
        districtList.add(district4);
        districtList.add(district5);
        when(mockDeck.giveDistrict(2)).thenReturn(districtList);
        information.setInformationForKing(player1,players,treasure);
        choice.drawOrGetPieces(mockDeck,treasure,information,isAffordable);
        assertTrue(player1.getHand().contains(district5));
        assertEquals(districtList.size(),1);
        choice.drawOrGetPieces(mockDeck,treasure,information,isAffordable);
        assertEquals(4,player1.getGold());
    }
    @Test
    void drawOrGetGold1Test3(){
        districtList.add(district4);
        districtList.add(district5);
        when(mockDeck.giveDistrict(2)).thenReturn(districtList);
        information.setInformationForKing(player1,players,treasure);
        //player1 has an empty hand
        choice.drawOrGetPieces(mockDeck,treasure,information,isAffordable);
        assertTrue(player1.getHand().contains(district5));

    }


}
