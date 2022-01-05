package fr.unice.polytech.startingpoint.player.HeroStrategies;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.cards.district.Laboratory;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.NeutralBot;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;
import fr.unice.polytech.startingpoint.player.IA.Strategies.MagicianChoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MagicianChoiceStrategiesTest {
    public Predicate<IDistrict> isAffordable;
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
    IAToHero information;
    IAToHero information2;
    IAToHero information3;
    IAToHero information4;
    IAToHero information5;
    DistrictDeck Mockdeck;
    DistrictDeck realDeck;
    Random mockRand;
    List<IPlayer> players;
    Predicate<IPlayer> canBuild;
    List<IDistrict> districtList;
    List<IDistrict> districtList2;
    List<IDistrict> districtList3;
    IDistrict District1;
    IDistrict District2;
    IDistrict District3;
    IDistrict District4;
    IDistrict District5;
    IDistrict District6;
    IDistrict District7;
    IDistrict District8;
    Treasure treasure;
    MagicianChoice choice = new MagicianChoice();




    @BeforeEach
    void setUp(){
        player1 = new NeutralBot("Link");
        player2 = new NeutralBot("Kirby");
        player3 = new NeutralBot("Kazuya");
        player4 = new NeutralBot("Yoshi");
        player5 = new NeutralBot("Peach");
        player6 = new NeutralBot("Zelda");
        player7 = new NeutralBot("Wario");
        player8 = new NeutralBot("Bowser");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        heroDeck = Initialization.heroeList(8);
        Mockdeck = mock(DistrictDeck.class);
        information = new IAToHero();
        information2 = new IAToHero();
        information3 = new IAToHero();
        information4 = new IAToHero();
        information5 = new IAToHero();
        mockRand = mock(Random.class);
        when(mockRand.nextInt(anyInt())).thenReturn(0,1,2);
        player1.setRole(heroDeck.get(0));
        heroDeck = Initialization.heroeList(8);
        player2.setRole(heroDeck.get(1));
        heroDeck = Initialization.heroeList(8);
        player3.setRole(heroDeck.get(2));
        heroDeck = Initialization.heroeList(8);

        treasure=new Treasure(32);
        canBuild = player -> player.getHand().stream().anyMatch(d -> d.getPrice()<=player.getGold());
        districtList = new ArrayList<>();
        districtList2 = new ArrayList<>();
        try {
            District1 = new District(1, Color.YELLOW,DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            District2 =new District(3,Color.GREEN,DistrictName.TAVERNE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            District3 =new District(5,Color.GREEN,DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }try {
            District4 =new District(3,Color.YELLOW,DistrictName.PALAIS);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            District5 =new District(1,Color.YELLOW,DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            District6 =new District(3,Color.GREEN,DistrictName.TAVERNE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            District7 =new District(5,Color.GREEN,DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }try {
            District8 =new District(3,Color.YELLOW,DistrictName.PALAIS);
        } catch (CardException e) {
            e.printStackTrace();
        }
        realDeck = new DistrictDeck(Initialization.districtList());
        districtList3 = new ArrayList<>();

    }


    @Test
    void exchangeWithMaxHandTest(){
        player1.setHand(List.of(District1,District2));
        player2.setHand(List.of(District1,District2,District3));
        information.setInformationForMagician(List.of(player1,player2,player3),player3,realDeck);
        isAffordable = district -> district.getPrice()<=player3.getGold();
        choice.magicienChoice(information,isAffordable);
        assertEquals(information.getChosenPlayer().getName(),"Kirby");
    }

    @Test
    void magicienChoice1TestChoosePlayer() {
        districtList.add(District2);
        districtList.add(District2);
        districtList.add(District1);
        districtList2.add(District1);
        districtList2.add(District2);
        player1.getDistrict(districtList);
        player2.getDistrict(districtList2);
        information.setInformationForMagician(players, player3, Mockdeck);
        isAffordable = district -> district.getPrice()<=player3.getGold();
        choice.magicienChoice(information,isAffordable);
        assertEquals(information.getChosenPlayer(), player1);
        assertEquals(information.getChosenCards().size(), 0);
    }


    @Test
    void magicienChoice1TestChoosePlayer2(){
        player2.addGold(2);
        player3.addGold(3);
        districtList.add(District3);
        when(Mockdeck.giveDistrict(1)).thenReturn(districtList);
        player1.setHand(Mockdeck.giveDistrict(1));
        player2.setHand(realDeck.giveDistrict(1));
        player3.setHand(realDeck.giveDistrict(1));
        information4.setInformationForMagician(players,player1,realDeck);
        isAffordable = district -> district.getPrice()<=player1.getGold();
        choice.magicienChoice(information4,isAffordable);
        assertEquals(information4.getChosenPlayer(),player2);
        assertEquals(information4.getChosenCards().size(),0);

    }
    @Test
    void magicienChoice1TestChoosePlayer3(){

        districtList.add(District3);
        when(Mockdeck.giveDistrict(1)).thenReturn(districtList);
        player1.setHand(Mockdeck.giveDistrict(1));
        player2.setHand(realDeck.giveDistrict(2));
        information4.setInformationForMagician(players,player1,realDeck);
        isAffordable = district -> district.getPrice()<=player1.getGold();
        choice.magicienChoice(information4,isAffordable);
        assertEquals(information4.getChosenPlayer(),player2);
        assertEquals(information4.getChosenCards().size(),0);
    }

    @Test
    void magicienChoice1TestChooseCards() {
        districtList.add(District3);
        districtList.add(District2);
        when(Mockdeck.giveDistrict(3)).thenReturn(districtList);
        player3.getDistrict(Mockdeck.giveDistrict(3));
        information2.setInformationForMagician(players, player3, Mockdeck);
        isAffordable = district -> district.getPrice()<=player3.getGold();
        choice.magicienChoice(information2,isAffordable);
        //assertTrue(information2.getChosenCards().size() > 0);
        //assertNull(information2.getChosenPlayer());
        //assertTrue(information2.getChosenCards().contains(District1));
    }

    @Test
    void magicienChoice1TestKeepHand() {
        player1.addGold(2);
        districtList.add(District2);
        districtList.add(new Laboratory());
        player1.setHand(new ArrayList<>());
        when(Mockdeck.giveDistrict(2)).thenReturn(districtList);
        player1.getDistrict(Mockdeck.giveDistrict(2));
        information3.setInformationForMagician(players, player1, Mockdeck);
        isAffordable = district -> district.getPrice()<=player1.getGold();
        choice.magicienChoice(information3,isAffordable);
        assertNull(information3.getChosenPlayer());
        assertEquals(information3.getChosenCards().size(), 0);
    }
    @Test
    void magicienChoice1TestKeepHand2(){
        districtList.clear();
        districtList.add(District3);
        player2.addGold( 1);
        player3.addGold(3);
        when(Mockdeck.giveDistrict(1)).thenReturn(districtList);
        information4.setInformationForMagician(players,player1,Mockdeck);
        player1.setHand(Mockdeck.giveDistrict(1));
        isAffordable = district -> district.getPrice()<=player1.getGold();
        choice.magicienChoice(information4,isAffordable);
        assertNull(information4.getChosenPlayer());
        assertEquals(information4.getChosenCards().size(),0);
    }
    @Test
    void exchangeUnaffordableHand(){
        player1.setHand(List.of(District1,District2,District3));
        player2.setHand(List.of(District1,District2,District3));
        player2.addGold(5);
        isAffordable = district -> district.getPrice()<=player1.getGold();
        information.setInformationForMagician(players,player1,realDeck);
        choice.magicienChoice(information,isAffordable);
        assertNull(information.getChosenPlayer());
        player2.removeGold(5);
        information2.setInformationForMagician(players,player1,realDeck);
        choice.magicienChoice(information2,isAffordable);
        assertEquals(player2,information2.getChosenPlayer());
    }
    @Test
    void exchangeHandWithDoubles1(){
        player1.setHand(List.of(District1,District2,District3));
        player1.addGold(13);
        player1.getBuiltDistricts().add(District5);
        player1.getBuiltDistricts().add(District6);
        player1.getBuiltDistricts().add(District7);
        player2.setHand(List.of(District4,District5));
        information.setInformationForMagician(players,player1,realDeck);
        isAffordable = district -> district.getPrice()<=player1.getGold();
        choice.magicienChoice(information,isAffordable);
        assertEquals(player2,information.getChosenPlayer());
        player1.getBuiltDistricts().removeAll(List.of(District5,District6,District7));
        player1.setHand(List.of(District1,District2,District3,District5,District6,District7));
        information2.setInformationForMagician(players,player1,realDeck);
        choice.magicienChoice(information2,isAffordable);
        assertEquals(player2,information2.getChosenPlayer());
    }
    @Test
    void exchangeHandWithDoubles2(){
        player1.setHand(List.of(District1,District2,District3));
        player1.addGold(13);
        player1.getBuiltDistricts().add(District4);
        player1.getBuiltDistricts().add(District5);
        player1.getBuiltDistricts().add(District6);
        information.setInformationForMagician(players,player1,realDeck);
        isAffordable = district -> district.getPrice()<=player1.getGold();
        choice.magicienChoice(information,isAffordable);
        assertTrue(information.getChosenCards().size()>0);
        player1.getBuiltDistricts().removeAll(List.of(District4,District6,District5));
        player1.getBuiltDistricts().add(District4);
        player1.setHand(List.of(District1,District2,District3,District4,District6,District7));
        information2.setInformationForMagician(players,player1,realDeck);
        choice.magicienChoice(information2,isAffordable);
        assertTrue(information2.getChosenCards().size()>0);
    }


}
