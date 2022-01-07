package fr.unice.polytech.startingpoint.player;

import fr.unice.polytech.startingpoint.cards.CardException;
import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.DistrictName;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.heros.HeroDeck;

import fr.unice.polytech.startingpoint.heros.IHero;

import fr.unice.polytech.startingpoint.player.IA.*;
import fr.unice.polytech.startingpoint.player.IA.BOTs.Nastybot;
import fr.unice.polytech.startingpoint.player.IA.BOTs.NeutralBot;
import fr.unice.polytech.startingpoint.player.IA.BOTs.NiceBot;
import fr.unice.polytech.startingpoint.player.IA.Strategies.choiceHeroes.HeroDecisionStandard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.mock;

public class NiceNastyBot {

    IA ia1 ;
    IA ia2 ;
    IA ia3 ;
    IA ia4;
    List<IPlayer> players ;
    HeroDeck heroes ;
    List<HerosChoice> thoughPath ;
    HeroDecisionStandard heroDecisionStandard ;
    IDistrict district1 = null;
    IDistrict district2 = null;
    IDistrict district3 = null;
    IDistrict district4 = null;
    IDistrict district5 = null;
    IDistrict district6 = null;
    IDistrict district7 = null;
    IDistrict district8 = null;
    IHero hero = null;
    Random rand = null;

    private IDistrict addCards(int price, Color color, DistrictName nameOfCard){
        IDistrict district = null;
        try {
            district = new District(price,color,nameOfCard);
        } catch (CardException e) {
            e.printStackTrace();
        }
        return district;
    }

    @BeforeEach
    void setup() {
        this.ia1 = new NiceBot("IA1");
        this.ia2 = new NeutralBot("IA2");
        this.ia3 = new Nastybot("IA3");
        this.ia4=new NeutralBot("ia4");
        this.rand = new Random();
        this.heroDecisionStandard = new HeroDecisionStandard();
        this.players = new ArrayList<IPlayer>();
        this.players.add(this.ia1);
        this.players.add(this.ia2);
        this.players.add(this.ia3);
        this.thoughPath = new ArrayList<HerosChoice>();
        this.heroes = null;
        this.heroes = Initialization.heroeList(8);
    }
         @Test
    void testHeroDecisionNasty(){
        Assertions.assertEquals(ia1.strategyBot, StrategyBot.NICE_BOT);
        Assertions.assertNotEquals(ia1.strategyBot, StrategyBot.NASTY_BOT);
        //Assertions.assertEquals(ia1.strategyBot,Bots.nonSpecified);
    }

    @Test
    void testHeroDecisionNice(){
        Assertions.assertEquals(ia3.strategyBot, StrategyBot.NASTY_BOT);
        Assertions.assertNotEquals(ia3.strategyBot, StrategyBot.NICE_BOT);
        //Assertions.assertEquals(ia3.bot,Bots.nonSpecified);
    }

}
