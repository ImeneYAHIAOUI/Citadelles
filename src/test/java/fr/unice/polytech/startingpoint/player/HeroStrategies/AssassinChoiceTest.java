package fr.unice.polytech.startingpoint.player.HeroStrategies;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.heros.character.Assassin;
import fr.unice.polytech.startingpoint.heros.character.Condottiere;
import fr.unice.polytech.startingpoint.heros.character.Magician;
import fr.unice.polytech.startingpoint.heros.character.Thief;
import fr.unice.polytech.startingpoint.player.IA.*;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.IA.Strategies.AssassinChoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AssassinChoiceTest {
    IA player1;
    IA player2;
    IA player3;
    IA player4;
    IA player5;
    IA player6;
    IA player7;
    IA player8;
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
    AssassinChoice choice = new AssassinChoice();


    @BeforeEach
    void setUp() {
        player1 = new NeutralBot("Link");
        player2 = new NeutralBot("Kirby");
        player3 = new NeutralBot("Kazuya");
        player4 = new NeutralBot("Yoshi");
        player5 = new BuilderBot("Peach");
        player6 = new RandomBot("Zelda");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        Mockdeck = mock(DistrictDeck.class);
        information = new IAToHero();
        information2 = new IAToHero();
        information3 = new IAToHero();
        player1.setRole(new Assassin());
        player2.setRole(new Thief());
        player3.setRole(new Magician());
        player4.setRole(new Condottiere());

        treasure = new Treasure(32);
        canBuild = player -> player.getHand().stream().anyMatch(d -> d.getPrice() <= player.getGold());
        districtList = new ArrayList<>();
        districtList2 = new ArrayList<>();
        try {
            District1 = new District(1, Color.YELLOW, DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            District2 = new District(3, Color.GREEN, DistrictName.TAVERNE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            District3 = new District(5, Color.GREEN, DistrictName.MARCHE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            District4 = new District(3, Color.YELLOW, DistrictName.PALAIS);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            District5 = new District(1, Color.YELLOW, DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            District6 = new District(1, Color.RED, DistrictName.TOURDEGUET);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            District7 = new District(2, Color.RED, DistrictName.PRISON);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            District8 = new District(3, Color.RED, DistrictName.CASERNE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        realDeck = new DistrictDeck(Initialization.districtList());
        districtList3 = new ArrayList<>();
        player1.addGold(3);
        player2.addGold(5);
        player3.addGold(6);
        player1.buildDistrict(District1);
        player1.buildDistrict(District5);
        player2.buildDistrict(District2);
        player3.buildDistrict(District3);
        List<IPlayer> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        information.setInformationForAssassinOrThief(players, player1, realDeck);
    }

    @Test
    void AssassinChoiceTestNeutralBot() {
        choice.AssassinChoice(information);
        assertEquals(information.getChosenPlayer(), player3);
    }

    @Test
    void AssassinChoiceTestBuilderBot(){
        information2.setInformationForAssassinOrThief(players,player5,realDeck);
        choice.AssassinChoice(information2);
        assertEquals(player3,information2.getChosenPlayer());
    }
    @Test
    void AssassinChoiceTestRandomBot(){
        information3.setInformationForAssassinOrThief(players,player6,realDeck);
        choice.AssassinChoice(information3);
        assertNotNull(information3.getChosenPlayer());
    }



    @Test
    void PossibleHeroAboutToWinTest() {
        assertEquals(HeroName.Magician, choice.possibleHeroAboutToWin(information));
        player2.addGold(10);
        player2.buildDistrict(District3);
        player2.buildDistrict(District4);
        districtList.add(District1);
        districtList.add(District2);
        districtList.add(District3);
        player2.setHand(districtList);
        player2.removeGold(player2.getGold());
        information2.setInformationForAssassinOrThief(players, player1, realDeck);
        assertEquals(HeroName.Thief, choice.possibleHeroAboutToWin(information2));
    }
    @Test
    void PossibleHeroAboutToWinTest2(){
        List<IHero> visibleHeroes = List.of(new Magician());
        information.setVisibleHeroes(visibleHeroes);
        assertNotEquals(HeroName.Magician, choice.possibleHeroAboutToWin(information));
        visibleHeroes = List.of(new Thief());
        player2.addGold(10);
        player2.buildDistrict(District3);
        player2.buildDistrict(District4);
        districtList.add(District1);
        districtList.add(District2);
        districtList.add(District3);
        player2.setHand(districtList);
        player2.removeGold(player2.getGold());
        information2.setInformationForAssassinOrThief(players,player1,realDeck);
        information2.setVisibleHeroes(visibleHeroes);
        assertNotEquals(HeroName.Thief, choice.possibleHeroAboutToWin(information2));
    }

    @Test
    void PossibleHeroAboutToWinTestCondottiere(){
        player4.addGold(10);
        player4.buildDistrict(District6);
        player4.buildDistrict(District7);
        player4.buildDistrict(District8);
        player4.setHand(List.of(District1,District3,District2));
        players.add(player4);
        information3.setInformationForAssassinOrThief(players,player1,realDeck);
        assertEquals(HeroName.Condottiere, choice.possibleHeroAboutToWin(information3));
    }
    @Test
    void PossibleHeroAboutToWinTestCondottiere2(){
        List<IHero> visibleHeroes = List.of(new Condottiere());
        player4.addGold(10);
        player4.buildDistrict(District6);
        player4.buildDistrict(District7);
        player4.buildDistrict(District8);
        player4.setHand(List.of(District1,District3,District2));
        players.add(player4);
        information3.setInformationForAssassinOrThief(players,player1,realDeck);
        information3.setVisibleHeroes(visibleHeroes);
        assertNotEquals(HeroName.Condottiere, choice.possibleHeroAboutToWin(information3));
    }
    @Test
    void enrichmentRiskTest(){
        assertFalse(choice.enrichmentRisk(information));
        player2.addGold(5);
        player3.addGold(4);
        information.setInformationForAssassinOrThief(players,player1,realDeck);
        assertTrue(choice.enrichmentRisk(information));
    }

    @Test
    void builderBotChoiceTest(){
        assertEquals(HeroName.Magician,choice.builderBotChoice(information));
        player4.addGold(15);
        player3.addGold(4);
        player2.addGold(10);
        player2.buildDistrict(District3);
        player2.buildDistrict(District4);
        districtList.add(District1);
        districtList.add(District2);
        districtList.add(District3);
        player2.setHand(districtList);
        player2.removeGold(player2.getGold());
        players.add(player4);
        information2.setInformationForAssassinOrThief(players,player1,realDeck);
        assertEquals(HeroName.Thief,choice.builderBotChoice(information2));
        districtList.add(District5);
        player1.setHand(districtList);
        information2.setInformationForAssassinOrThief(players,player1,realDeck);
        assertEquals(HeroName.Magician,choice.builderBotChoice(information2));
        player1.addGold(10);
        player1.buildDistrict(District3);
        player1.buildDistrict(District4);
        player1.buildDistrict(District5);

        player4.buildDistrict(District6);
        player4.buildDistrict(District7);
        player4.buildDistrict(District8);
        player4.buildDistrict(District3);
        player4.setHand(districtList);
        information3.setInformationForAssassinOrThief(players,player1,realDeck);
        assertEquals(HeroName.Condottiere,choice.builderBotChoice(information3));
    }




}
