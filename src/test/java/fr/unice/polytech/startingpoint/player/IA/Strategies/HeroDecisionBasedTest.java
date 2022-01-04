package fr.unice.polytech.startingpoint.player.IA.Strategies;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.player.IA.BuilderBot;
import fr.unice.polytech.startingpoint.player.IA.HerosChoice;
import fr.unice.polytech.startingpoint.player.IA.IA;
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
    List<IPlayer> players;
    HeroDeck heroes;
    IDistrict district1;
    IDistrict district2;
    IDistrict district3;
    IDistrict district4;
    IDistrict district5;
    IDistrict district6;
    List<HerosChoice> thoughPath;

    @BeforeEach
    void setUp(){
        this.heroDecisionBased = new HeroDecisionBased();
        this.player1 = new BuilderBot("Player test");
        this.heroes = Initialization.heroeList(8);
        this.player1 = new BuilderBot("Player1 test");
        this.player2 = new BuilderBot("Player2 test");
        this.player3 = new BuilderBot("Player3 test");
        this.players = new ArrayList<>();
        this.players.add(player1);
        this.players.add(player2);
        this.players.add(player3);

        this.thoughPath = new ArrayList<>();
        this.district1 = this.addCards(2,Color.YELLOW,DistrictName.MARCHE);
        this.district2 = this.addCards(1,Color.YELLOW, DistrictName.PALAIS);
        this.district6 = this.addCards(4,Color.YELLOW, DistrictName.LIBRARY);
        this.district3 = this.addCards(3,Color.GREEN, DistrictName.LACOURDESMIRACLES);
        this.district4 = this.addCards(1,Color.GREEN,DistrictName.TAVERNE);
        this.district5 = this.addCards(2,Color.BLUE, DistrictName.PRISON);
    }

    // ===============================================================================================================
    //
    //                                              NORMAL STRATEGY
    //
    // ===============================================================================================================

    @Test
    void testArchitectChoice(){
        this.player1.addGold(20);
        assertEquals(20, this.player1.getGold());

        List<IDistrict> hand = new ArrayList<>();
        hand.add(district1);
        hand.add(district6);

        this.player1.setHand(hand);

        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1,this.heroes,this.thoughPath,this.players));
        assertEquals(HeroName.Architect, this.player1.getRole().getName());
    }

    @Test
    void testNoArchitectChoice1(){
        this.player1.addGold(20);
        assertEquals(20, this.player1.getGold());

        List<IDistrict> hand = new ArrayList<>();
        hand.add(district1);
        hand.add(district2);

        this.player1.setHand(hand);

        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1,this.heroes,this.thoughPath,this.players));
        assertNotEquals(HeroName.Architect, this.player1.getRole().getName());
    }

    @Test
    void testNoArchitectChoice2(){
        this.player1.addGold(20);
        assertEquals(20, this.player1.getGold());

        List<IDistrict> hand = new ArrayList<>();
        hand.add(district1);
        this.player1.buildDistrict(district2);

        this.player1.setHand(hand);

        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1,this.heroes,this.thoughPath,this.players));

        assertNotEquals(HeroName.Architect, this.player1.getRole().getName());
    }

    @Test
    void test1KingChoice(){
        this.player1.addGold(20);
        this.player1.buildDistrict(district2);
        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1,this.heroes,this.thoughPath,this.players));
        assertEquals(HeroName.King, this.player1.getRole().getName());
    }

    @Test
    void test2KingChoice(){
        this.player1.addGold(20);
        this.player1.buildDistrict(district1);
        this.player1.buildDistrict(district2);
        this.player1.buildDistrict(district4);
        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1,this.heroes,this.thoughPath,this.players));
        assertEquals(HeroName.King, this.player1.getRole().getName());
    }

    @Test
    void test3KingChoice(){
        this.player1.addGold(20);
        this.player1.buildDistrict(district2);
        this.player1.buildDistrict(district3);
        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1,this.heroes,this.thoughPath,this.players));
        assertEquals(HeroName.King, this.player1.getRole().getName());
    }

    @Test
    void test4KingChoice(){
        this.player1.addGold(20);
        this.player1.buildDistrict(district2);
        this.player1.buildDistrict(district3);
        this.heroes.chooseHero(HeroName.Merchant);
        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1,this.heroes,this.thoughPath,this.players));
        assertEquals(HeroName.King, this.player1.getRole().getName());
    }

    @Test
    void test5KingChoice(){
        this.player1.addGold(20);
        this.player1.buildDistrict(district2);
        this.player1.buildDistrict(district4);
        this.player1.buildDistrict(district3);
        this.heroes.chooseHero(HeroName.Merchant);
        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1,this.heroes,this.thoughPath,this.players));
        assertEquals(HeroName.King, this.player1.getRole().getName());
    }

    @Test
    void test1MerchantChoice(){
        this.player1.addGold(20);
        this.player1.buildDistrict(district3);
        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1,this.heroes,this.thoughPath,this.players));
        assertEquals(HeroName.Merchant, this.player1.getRole().getName());
    }

    @Test
    void test2MerchantChoice(){
        this.player1.addGold(20);
        this.player1.buildDistrict(district3);
        this.player1.buildDistrict(district4);
        this.player1.buildDistrict(district1);
        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1,this.heroes,this.thoughPath,this.players));
        assertEquals(HeroName.Merchant, this.player1.getRole().getName());
    }

    @Test
    void test3MerchantChoice(){
        this.player1.addGold(20);
        this.player1.buildDistrict(district1);
        this.player1.buildDistrict(district2);
        this.player1.buildDistrict(district3);
        this.heroes.chooseHero(HeroName.King);
        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1,this.heroes,this.thoughPath,this.players));
        assertEquals(HeroName.Merchant, this.player1.getRole().getName());
    }

    // ===============================================================================================================
    //
    //                                              PENULTIMATE ROUND STRATEGY
    //
    // ===============================================================================================================

    @Test
    void testPenultimateRoundStrategyKingChoice(){
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

        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1,this.heroes,this.thoughPath,this.players));
        assertEquals(HeroName.King, this.player1.getRole().getName());
    }

    @Test
    void testPenultimateRoundStrategyAssassinChoice(){
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

        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1,this.heroes,this.thoughPath,this.players));
        assertEquals(HeroName.Assassin, this.player1.getRole().getName());
    }

    @Test
    void testPenultimateRoundStrategyBihsopChoice(){
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

        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1,this.heroes,this.thoughPath,this.players));
        assertEquals(HeroName.Bishop, this.player1.getRole().getName());
    }

    @Test
    void testPenultimateRoundStrategyCondottiereChoice(){
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

        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1,this.heroes,this.thoughPath,this.players));
        assertEquals(HeroName.Condottiere, this.player1.getRole().getName());
    }

    @Test
    void testPenultimateRoundStrategyRandomChoice(){
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

        this.player1.setRole(this.heroDecisionBased.heroChoice(this.player1,this.heroes,this.thoughPath,this.players));
        assertNotNull(this.player1.getRole().getName());
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
}