package fr.unice.polytech.startingpoint.player.IA.Strategies;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.heros.character.Assassin;
import fr.unice.polytech.startingpoint.heros.character.Condottiere;
import fr.unice.polytech.startingpoint.heros.character.Magician;
import fr.unice.polytech.startingpoint.player.CircularList;
import fr.unice.polytech.startingpoint.player.IA.BuilderBot;
import fr.unice.polytech.startingpoint.player.IA.HerosChoice;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;
import fr.unice.polytech.startingpoint.player.IPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeroDecisionBasedTest {
    HeroDecisionBased heroDecisionBased;
    IA player1;
    IA player2;
    IA player3;
    IA player4;
    IA player5;
    IAToHero infos;
    List<IPlayer> players;
    List<IPlayer> players2;
    HeroDeck heroes;
    HeroDeck heroes2;
    IDistrict district1;
    IDistrict district2;
    IDistrict district3;
    IDistrict district4;
    IDistrict district5;
    IDistrict district6;
    IDistrict district7;
    List<HerosChoice> thoughPath;
    HeroDecisionBased heroDecisionBased2;
    CircularList circularList;

    @BeforeEach
    void setUp() {
        this.heroDecisionBased = new HeroDecisionBased();
        this.heroDecisionBased2 = new HeroDecisionBased();
        this.infos = new IAToHero();
        this.player1 = new BuilderBot("Player test");
        this.heroes = Initialization.heroeList(8);
        this.player1 = new BuilderBot("Player1 test");
        this.player2 = new BuilderBot("Player2 test");
        this.player3 = new BuilderBot("Player3 test");
        this.player4 = new BuilderBot("Player4 test");
        this.player5 = new BuilderBot("Player5 test");
        this.players = new ArrayList<>();
        this.players2 = new ArrayList<>();
        this.players.add(player1);
        this.players.add(player2);
        this.players.add(player3);
        this.players2.add(player1);
        this.players2.add(player2);
        this.players2.add(player3);
        this.players2.add(player4);

        this.thoughPath = new ArrayList<>();
        this.district1 = this.addCards(2, Color.YELLOW, DistrictName.MARCHE);
        this.district2 = this.addCards(1, Color.YELLOW, DistrictName.PALAIS);
        this.district6 = this.addCards(4, Color.YELLOW, DistrictName.LIBRARY);
        this.district3 = this.addCards(3, Color.GREEN, DistrictName.LACOURDESMIRACLES);
        this.district4 = this.addCards(1, Color.GREEN, DistrictName.TAVERNE);
        this.district5 = this.addCards(2, Color.BLUE, DistrictName.PRISON);
        this.district7 = this.addCards(2, Color.GREEN, DistrictName.ECHAPPE);

    }

    // ===============================================================================================================
    //
    //                                              NORMAL STRATEGY
    //
    // ===============================================================================================================

    @Test
    void testArchitectChoice() {
        this.player1.addGold(20);
        assertEquals(20, this.player1.getGold());

        List<IDistrict> hand = new ArrayList<>();
        hand.add(district1);
        hand.add(district6);

        this.player1.setHand(hand);

        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1, this.heroes, this.thoughPath, this.players));
        assertEquals(HeroName.Architect, this.player1.getRole().getName());
    }

    @Test
    void testNoArchitectChoice1() {
        this.player1.addGold(20);
        assertEquals(20, this.player1.getGold());

        List<IDistrict> hand = new ArrayList<>();
        hand.add(district1);
        hand.add(district2);

        this.player1.setHand(hand);

        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1, this.heroes, this.thoughPath, this.players));
        assertNotEquals(HeroName.Architect, this.player1.getRole().getName());
    }

    @Test
    void testNoArchitectChoice2() {
        this.player1.addGold(20);
        assertEquals(20, this.player1.getGold());

        List<IDistrict> hand = new ArrayList<>();
        hand.add(district1);
        this.player1.buildDistrict(district2);

        this.player1.setHand(hand);

        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1, this.heroes, this.thoughPath, this.players));

        assertNotEquals(HeroName.Architect, this.player1.getRole().getName());
    }

    @Test
    void test1KingChoice() {
        this.player1.addGold(20);
        this.player1.buildDistrict(district2);
        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1, this.heroes, this.thoughPath, this.players));
        assertEquals(HeroName.King, this.player1.getRole().getName());
    }

    @Test
    void test2KingChoice() {
        this.player1.addGold(20);
        this.player1.buildDistrict(district1);
        this.player1.buildDistrict(district2);
        this.player1.buildDistrict(district4);
        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1, this.heroes, this.thoughPath, this.players));
        assertEquals(HeroName.King, this.player1.getRole().getName());
    }

    @Test
    void test3KingChoice() {
        this.player1.addGold(20);
        this.player1.buildDistrict(district2);
        this.player1.buildDistrict(district3);
        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1, this.heroes, this.thoughPath, this.players));
        assertEquals(HeroName.King, this.player1.getRole().getName());
    }

    @Test
    void test4KingChoice() {
        this.player1.addGold(20);
        this.player1.buildDistrict(district2);
        this.player1.buildDistrict(district3);
        this.heroes.chooseHero(HeroName.Merchant);
        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1, this.heroes, this.thoughPath, this.players));
        assertEquals(HeroName.King, this.player1.getRole().getName());
    }

    @Test
    void test5KingChoice() {
        this.player1.addGold(20);
        this.player1.buildDistrict(district2);
        this.player1.buildDistrict(district4);
        this.player1.buildDistrict(district3);
        this.heroes.chooseHero(HeroName.Merchant);
        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1, this.heroes, this.thoughPath, this.players));
        assertEquals(HeroName.King, this.player1.getRole().getName());
    }

    @Test
    void test1MerchantChoice() {
        this.player1.addGold(20);
        this.player1.buildDistrict(district3);
        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1, this.heroes, this.thoughPath, this.players));
        assertEquals(HeroName.Merchant, this.player1.getRole().getName());
    }

    @Test
    void test2MerchantChoice() {
        this.player1.addGold(20);
        this.player1.buildDistrict(district3);
        this.player1.buildDistrict(district4);
        this.player1.buildDistrict(district1);
        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1, this.heroes, this.thoughPath, this.players));
        assertEquals(HeroName.Merchant, this.player1.getRole().getName());
    }

    @Test
    void test3MerchantChoice() {
        this.player1.addGold(20);
        this.player1.buildDistrict(district1);
        this.player1.buildDistrict(district2);
        this.player1.buildDistrict(district3);
        this.heroes.chooseHero(HeroName.King);
        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1, this.heroes, this.thoughPath, this.players));
        assertEquals(HeroName.Merchant, this.player1.getRole().getName());
    }

    // ===============================================================================================================
    //
    //                                              PENULTIMATE ROUND STRATEGY
    //
    // ===============================================================================================================

    @Test
    void testPenultimateRoundStrategyKingChoice() {
        this.player1.addGold(30);
        this.player2.addGold(30);

        this.player1.buildDistrict(district1);
        this.player1.buildDistrict(district2);

        this.player2.buildDistrict(district1);
        this.player2.buildDistrict(district2);
        this.player2.buildDistrict(district3);
        this.player2.buildDistrict(district1);
        this.player2.buildDistrict(district2);
        this.player2.buildDistrict(district3);

        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1, this.heroes, this.thoughPath, this.players));
        assertEquals(HeroName.King, this.player1.getRole().getName());
    }

    @Test
    void testPenultimateRoundStrategyAssassinChoice() {
        this.player1.addGold(30);
        this.player2.addGold(30);

        this.player1.buildDistrict(district1);
        this.player1.buildDistrict(district2);

        this.player2.buildDistrict(district1);
        this.player2.buildDistrict(district2);
        this.player2.buildDistrict(district3);
        this.player2.buildDistrict(district1);
        this.player2.buildDistrict(district2);
        this.player2.buildDistrict(district3);

        this.heroes.chooseHero(HeroName.King);

        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1, this.heroes, this.thoughPath, this.players));
        assertEquals(HeroName.Assassin, this.player1.getRole().getName());
    }

    @Test
    void testPenultimateRoundStrategyBihsopChoice() {
        this.player1.addGold(30);
        this.player2.addGold(30);

        this.player1.buildDistrict(district1);
        this.player1.buildDistrict(district2);

        this.player2.buildDistrict(district1);
        this.player2.buildDistrict(district2);
        this.player2.buildDistrict(district3);
        this.player2.buildDistrict(district1);
        this.player2.buildDistrict(district2);
        this.player2.buildDistrict(district3);

        this.heroes.chooseHero(HeroName.King);
        this.heroes.chooseHero(HeroName.Assassin);

        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1, this.heroes, this.thoughPath, this.players));
        assertEquals(HeroName.Bishop, this.player1.getRole().getName());
    }

    @Test
    void testPenultimateRoundStrategyCondottiereChoice() {
        this.player1.addGold(30);
        this.player2.addGold(30);

        this.player1.buildDistrict(district1);
        this.player1.buildDistrict(district2);

        this.player2.buildDistrict(district1);
        this.player2.buildDistrict(district2);
        this.player2.buildDistrict(district3);
        this.player2.buildDistrict(district1);
        this.player2.buildDistrict(district2);
        this.player2.buildDistrict(district3);

        this.heroes.chooseHero(HeroName.King);
        this.heroes.chooseHero(HeroName.Assassin);
        this.heroes.chooseHero(HeroName.Bishop);

        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1, this.heroes, this.thoughPath, this.players));
        assertEquals(HeroName.Condottiere, this.player1.getRole().getName());
    }

    @Test
    void testPenultimateRoundStrategyRandomChoice() {
        this.player1.addGold(30);
        this.player2.addGold(30);
        this.player1.buildDistrict(district1);
        this.player1.buildDistrict(district2);
        this.player2.buildDistrict(district1);
        this.player2.buildDistrict(district2);
        this.player2.buildDistrict(district3);
        this.player2.buildDistrict(district1);
        this.player2.buildDistrict(district2);
        this.player2.buildDistrict(district3);

        this.heroes.chooseHero(HeroName.King);
        this.heroes.chooseHero(HeroName.Assassin);
        this.heroes.chooseHero(HeroName.Bishop);
        this.heroes.chooseHero(HeroName.Condottiere);

        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1, this.heroes, this.thoughPath, this.players));
        assertNotNull(this.player1.getRole().getName());
    }

    // ===============================================================================================================
    //
    //                                                     Last Round Strategy
    //
    // ===============================================================================================================
    @Test
    void FirstcaseStartegyTest() {
        player1.addGold(15);
        player2.addGold(15);
        player3.addGold(15);
        player1.buildDistrict(district1);
        player1.buildDistrict(district2);
        player1.buildDistrict(district3);
        player2.buildDistrict(district4);
        player2.buildDistrict(district6);
        player2.buildDistrict(district2);
        player2.buildDistrict(district3);
        player3.buildDistrict(district1);
        player3.buildDistrict(district2);
        player3.buildDistrict(district3);
        player3.buildDistrict(district4);
        player3.buildDistrict(district5);
        player3.buildDistrict(district6);
        player3.buildDistrict(district7);
        circularList = new CircularList(players);
        assertEquals(player3.getBuiltDistricts().size(), 7);
        player1.setRole(heroDecisionBased2.heroChoice(player1, heroes, thoughPath, circularList.getRotatePlayerList()));
        player2.setRole(heroDecisionBased2.heroChoice(player2, heroes, thoughPath, circularList.getRotatePlayerList()));
        assertEquals(player1.getRole().getName(), HeroName.Condottiere);




    }



    @Test
    void FirstcaseStartegyTest3() {
        player1.addGold(15);
        player2.addGold(15);
        player3.addGold(15);
        player1.buildDistrict(district1);
        player1.buildDistrict(district2);
        player1.buildDistrict(district3);
        player2.buildDistrict(district4);
        player2.buildDistrict(district6);
        player2.buildDistrict(district2);
        player2.buildDistrict(district3);
        player3.buildDistrict(district1);
        player3.buildDistrict(district2);
        player3.buildDistrict(district3);
        player3.buildDistrict(district4);
        player3.buildDistrict(district5);
        circularList = new CircularList(players);
        player1.setRole(heroDecisionBased2.heroChoice(player1, heroes, thoughPath,  circularList.getRotatePlayerList()));
        player2.setRole(heroDecisionBased2.heroChoice(player2, heroes, thoughPath, circularList.getRotatePlayerList()));
        assertNotEquals((player1.getRole().getName()), HeroName.Assassin);
        assertNotEquals((player2.getRole().getName()), HeroName.Condottiere);


    }


    @Test
    void SecondcaseStartegyTest3() {
        player1.addGold(15);
        player2.addGold(15);
        player3.addGold(15);
        player1.buildDistrict(district1);
        player1.buildDistrict(district2);
        player1.buildDistrict(district3);
        player2.buildDistrict(district4);
        player2.buildDistrict(district6);
        player2.buildDistrict(district2);
        player2.buildDistrict(district3);
        player3.buildDistrict(district1);
        player3.buildDistrict(district2);
        player3.buildDistrict(district3);
        player3.buildDistrict(district4);
        player3.buildDistrict(district5);
        player3.buildDistrict(district6);
        player3.buildDistrict(district7);
        player2.setCrown();

        this.heroes.chooseHero(HeroName.Bishop);
        assertTrue(player3.getBuiltDistricts().size()==7);
        circularList = new CircularList(players);
        assertEquals(heroes.size(),7);
        player1.setRole(heroDecisionBased2.heroChoice(player1, heroes, thoughPath,  circularList.getRotatePlayerList()));
        player2.setRole(heroDecisionBased2.heroChoice(player2, heroes, thoughPath,  circularList.getRotatePlayerList()));
        assertEquals((player1.getRole().getName()), HeroName.Assassin);
        assertEquals(player2.getRole().getName(),HeroName.Condottiere);









    }



    // ===============================================================================================================
    //
    //                                                      FUNCTION
    //
    // ===============================================================================================================

    /**
     * Init the district
     * @param price
     * @param color
     * @param nameOfCard
     * @return
     */
    private IDistrict addCards(int price, Color color, DistrictName nameOfCard){
        IDistrict district = null;
        try {
            district = new District(price,color,nameOfCard);
        } catch (CardException e) {
            e.printStackTrace();
        }
        return district;
    }
    // ===============================================================================================================
    //
    //                                                      Last round strategies
    //
    // ===============================================================================================================

    @Test
    void MostAheadPlayerStrategyTest(){
        player1.addGold(30);
        player2.addGold(10);
        this.player1.buildDistrict(district1);
        this.player1.buildDistrict(district2);
        this.player1.buildDistrict(district3);
        this.player1.buildDistrict(district4);
        this.player1.buildDistrict(district5);
        this.player1.buildDistrict(district6);
        this.player1.buildDistrict(district7);
        this.player2.buildDistrict(district1);
        player1.setRole(heroDecisionBased.heroChoice(player1,heroes,thoughPath,players));
        assertEquals(HeroName.Assassin,player1.getRole().getName());
        player1.setRole(heroDecisionBased.heroChoice(player1,heroes,thoughPath,players));
        assertEquals(HeroName.Bishop,player1.getRole().getName());
        player1.setRole(heroDecisionBased.heroChoice(player1,heroes,thoughPath,players));
        assertEquals(HeroName.Condottiere,player1.getRole().getName());
        player1.setRole(heroDecisionBased.heroChoice(player1,heroes,thoughPath,players));
        assertNotNull(player1.getRole().getName());
    }

    @Test
    void thirdCaseStrategyTestMagician(){
        player1.addGold(30);
        player2.addGold(10);
        this.player1.buildDistrict(district1);
        this.player1.buildDistrict(district2);
        this.player1.buildDistrict(district3);
        this.player1.buildDistrict(district4);
        this.player1.buildDistrict(district5);
        this.player1.buildDistrict(district6);
        this.player1.buildDistrict(district7);
        this.player2.buildDistrict(district1);
        player2.setCrown();
        this.heroes.chooseHero(HeroName.Condottiere);
        circularList = new CircularList(players);
        circularList.findPlayerWithCrown();
        player3.setRole(heroDecisionBased.heroChoice(player3,heroes,thoughPath,circularList.getRotatePlayerList()));
        assertEquals(HeroName.Magician,player3.getRole().getName());
        assertEquals(player1,player3.getChosenPlayer());
    }
    @Test
    void thirdCaseStrategyTestRandom(){
        player1.addGold(30);
        player2.addGold(10);
        this.player1.buildDistrict(district1);
        this.player1.buildDistrict(district2);
        this.player1.buildDistrict(district3);
        this.player1.buildDistrict(district4);
        this.player1.buildDistrict(district5);
        this.player1.buildDistrict(district6);
        this.player1.buildDistrict(district7);
        this.player2.buildDistrict(district1);
        player2.setCrown();
        this.heroes.chooseHero(HeroName.Condottiere);
        this.heroes.chooseHero(HeroName.Assassin);
        this.heroes.chooseHero(HeroName.Magician);
        circularList = new CircularList(players);
        circularList.findPlayerWithCrown();
        player2.setRole(heroDecisionBased.heroChoice(player2,heroes,thoughPath,circularList.getRotatePlayerList()));
        assertNotNull(player2.getRole());
        player2.unSetCrown();
        player1.setCrown();
        heroes.add(new Condottiere());
        heroes.add(new Assassin());
        heroes.add(new Magician());
        circularList = new CircularList(players);
        circularList.findPlayerWithCrown();
        player2.setRole(heroDecisionBased.heroChoice(player2,heroes,thoughPath,circularList.getRotatePlayerList()));
        assertNotNull(player2.getRole());
    }
    @Test
    void fourthCaseStrategyTestCondottiere(){
        player1.addGold(30);
        player2.addGold(10);
        this.player1.buildDistrict(district1);
        this.player1.buildDistrict(district2);
        this.player1.buildDistrict(district3);
        this.player1.buildDistrict(district4);
        this.player1.buildDistrict(district5);
        this.player1.buildDistrict(district6);
        this.player1.buildDistrict(district7);
        this.player2.buildDistrict(district1);
        player2.setCrown();
        this.heroes.chooseHero(HeroName.Assassin);
        circularList = new CircularList(players);
        circularList.findPlayerWithCrown();
        player2.setRole(heroDecisionBased.heroChoice(player2,heroes,thoughPath,circularList.getRotatePlayerList()));
        assertEquals(HeroName.Condottiere,player2.getRole().getName());
        assertEquals(player1,player2.getChosenPlayer());
    }

    @Test
    void fourthCaseStrategyTestBishop(){
        player1.addGold(30);
        player2.addGold(10);
        this.player1.buildDistrict(district1);
        this.player1.buildDistrict(district2);
        this.player1.buildDistrict(district3);
        this.player1.buildDistrict(district4);
        this.player1.buildDistrict(district5);
        this.player1.buildDistrict(district6);
        this.player1.buildDistrict(district7);
        this.player2.buildDistrict(district1);
        player2.setCrown();
        this.heroes.chooseHero(HeroName.Assassin);
        circularList = new CircularList(players);
        circularList.findPlayerWithCrown();
        player3.setRole(heroDecisionBased.heroChoice(player3,heroes,thoughPath,circularList.getRotatePlayerList()));
        assertEquals(HeroName.Bishop,player3.getRole().getName());
    }
    @Test
    void FourthCaseStrategyTestRandom(){
        player1.addGold(30);
        player2.addGold(10);
        this.player1.buildDistrict(district1);
        this.player1.buildDistrict(district2);
        this.player1.buildDistrict(district3);
        this.player1.buildDistrict(district4);
        this.player1.buildDistrict(district5);
        this.player1.buildDistrict(district6);
        this.player1.buildDistrict(district7);
        this.player2.buildDistrict(district1);
        player2.setCrown();
        this.heroes.chooseHero(HeroName.Assassin);
        this.heroes.chooseHero(HeroName.Condottiere);
        this.heroes.chooseHero(HeroName.Bishop);
        circularList = new CircularList(players);
        circularList.findPlayerWithCrown();
        player2.setRole(heroDecisionBased.heroChoice(player2,heroes,thoughPath,circularList.getRotatePlayerList()));
        assertNotNull(player2.getRole());
        player2.unSetCrown();
        player1.setCrown();
        heroes.add(new Condottiere());
        heroes.add(new Assassin());
        heroes.add(new Magician());
        circularList = new CircularList(players);
        circularList.findPlayerWithCrown();
        player2.setRole(heroDecisionBased.heroChoice(player2,heroes,thoughPath,circularList.getRotatePlayerList()));
        assertNotNull(player2.getRole());
    }
    @Test
    void thirdCaseStrategyTestAssassin(){
        player1.addGold(30);
        player2.addGold(10);
        this.player1.buildDistrict(district1);
        this.player1.buildDistrict(district2);
        this.player1.buildDistrict(district3);
        this.player1.buildDistrict(district4);
        this.player1.buildDistrict(district5);
        this.player1.buildDistrict(district6);
        this.player1.buildDistrict(district7);
        this.player2.buildDistrict(district1);
        player2.setCrown();
        this.heroes.chooseHero(HeroName.Condottiere);
        circularList = new CircularList(players);
        circularList.findPlayerWithCrown();
        player2.setRole(heroDecisionBased.heroChoice(player2,heroes,thoughPath,circularList.getRotatePlayerList()));
        assertEquals(HeroName.Assassin,player2.getRole().getName());
        assertNull(player2.getTargetedHero());
        player3.setHand(List.of(district1,district2,district3,district4));
        this.heroes.add(new Assassin());
        circularList = new CircularList(players);
        circularList.findPlayerWithCrown();
        player2.setRole(heroDecisionBased.heroChoice(player2,heroes,thoughPath,circularList.getRotatePlayerList()));
        assertEquals(HeroName.Magician,player2.getTargetedHero());
    }
}