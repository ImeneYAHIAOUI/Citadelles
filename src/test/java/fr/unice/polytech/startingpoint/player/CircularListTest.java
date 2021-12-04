package fr.unice.polytech.startingpoint.player;

import fr.unice.polytech.startingpoint.heros.*;
import fr.unice.polytech.startingpoint.heros.character.Assassin;
import fr.unice.polytech.startingpoint.heros.character.King;
import fr.unice.polytech.startingpoint.heros.character.Merchant;
import fr.unice.polytech.startingpoint.heros.character.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CircularListTest {
    CircularList circularList = null;
    List<IPlayer> playerList = null;
    HeroDeck heroes = null;

    @BeforeEach
    void setUp(){
        IPlayer IA1 = new IA("1");
        IPlayer IA2 = new IA("2");
        IPlayer IA3 = new IA("3");
        IPlayer IA4 = new IA("4");

        this.playerList = new ArrayList<IPlayer>();

        this.playerList.add(IA1);
        this.playerList.add(IA2);
        this.playerList.add(IA3);
        this.playerList.add(IA4);

        this.heroes = new HeroDeck();
        this.heroes.add(new King());
        this.heroes.add(new Assassin());
        this.heroes.add(new Thief());
        this.heroes.add(new Merchant());
    }

    @Test
    void testSize(){
        this.circularList = new CircularList(this.playerList);
        assertEquals(4, this.circularList.size());
    }

    @Test
    void testSizeEmpty(){
        this.circularList = new CircularList(new ArrayList<IPlayer>());
        assertEquals(0, this.circularList.size());
    }

    @Test
    void testOrderPlayer1(){
        this.playerList.get(0).chooseHero(this.heroes,0);
        this.playerList.get(1).chooseHero(this.heroes,0);
        this.playerList.get(2).chooseHero(this.heroes,0);
        this.playerList.get(3).chooseHero(this.heroes,0);

        assertEquals(HeroName.King,this.playerList.get(0).getRole().getName());
        assertEquals(HeroName.Assassin,this.playerList.get(1).getRole().getName());
        assertEquals(HeroName.Thief,this.playerList.get(2).getRole().getName());
        assertEquals(HeroName.Merchant,this.playerList.get(3).getRole().getName());

        this.playerList.get(0).setCrown();

        this.circularList = new CircularList(this.playerList);
        assertEquals(4, this.circularList.size());

        this.circularList.findPlayerWithCrown();
        assertEquals(0,this.circularList.getKingIndex());

        List<IPlayer> list = this.circularList.getRotatePlayerList();
        assertEquals("1",list.get(0).getName());
        assertEquals("2",list.get(1).getName());
        assertEquals("3",list.get(2).getName());
        assertEquals("4",list.get(3).getName());
    }

    @Test
    void testOrderPlayer2(){
        this.playerList.get(1).chooseHero(this.heroes,0);
        this.playerList.get(0).chooseHero(this.heroes,0);
        this.playerList.get(2).chooseHero(this.heroes,0);
        this.playerList.get(3).chooseHero(this.heroes,0);

        assertEquals(HeroName.Assassin,this.playerList.get(0).getRole().getName());
        assertEquals(HeroName.King,this.playerList.get(1).getRole().getName());
        assertEquals(HeroName.Thief,this.playerList.get(2).getRole().getName());
        assertEquals(HeroName.Merchant,this.playerList.get(3).getRole().getName());

        this.playerList.get(1).setCrown();

        this.circularList = new CircularList(this.playerList);
        assertEquals(4, this.circularList.size());

        this.circularList.findPlayerWithCrown();
        assertEquals(1,this.circularList.getKingIndex());

        List<IPlayer> list = this.circularList.getRotatePlayerList();
        assertEquals("2",list.get(0).getName());
        assertEquals("3",list.get(1).getName());
        assertEquals("4",list.get(2).getName());
        assertEquals("1",list.get(3).getName());
    }

    @Test
    void testOrderPlayer3(){
        this.playerList.get(2).chooseHero(this.heroes,0);
        this.playerList.get(1).chooseHero(this.heroes,0);
        this.playerList.get(0).chooseHero(this.heroes,0);
        this.playerList.get(3).chooseHero(this.heroes,0);

        assertEquals(HeroName.Thief,this.playerList.get(0).getRole().getName());
        assertEquals(HeroName.Assassin,this.playerList.get(1).getRole().getName());
        assertEquals(HeroName.King,this.playerList.get(2).getRole().getName());
        assertEquals(HeroName.Merchant,this.playerList.get(3).getRole().getName());

        this.playerList.get(2).setCrown();

        this.circularList = new CircularList(this.playerList);
        assertEquals(4, this.circularList.size());

        this.circularList.findPlayerWithCrown();
        assertEquals(2,this.circularList.getKingIndex());

        List<IPlayer> list = this.circularList.getRotatePlayerList();
        assertEquals("3",list.get(0).getName());
        assertEquals("4",list.get(1).getName());
        assertEquals("1",list.get(2).getName());
        assertEquals("2",list.get(3).getName());
    }

    @Test
    void testOrderPlayer4(){
        this.playerList.get(3).chooseHero(this.heroes,0);
        this.playerList.get(2).chooseHero(this.heroes,0);
        this.playerList.get(1).chooseHero(this.heroes,0);
        this.playerList.get(0).chooseHero(this.heroes,0);

        assertEquals(HeroName.Merchant,this.playerList.get(0).getRole().getName());
        assertEquals(HeroName.Thief,this.playerList.get(1).getRole().getName());
        assertEquals(HeroName.Assassin,this.playerList.get(2).getRole().getName());
        assertEquals(HeroName.King,this.playerList.get(3).getRole().getName());

        this.playerList.get(3).setCrown();

        this.circularList = new CircularList(this.playerList);
        assertEquals(4, this.circularList.size());

        this.circularList.findPlayerWithCrown();
        assertEquals(3,this.circularList.getKingIndex());

        List<IPlayer> list = this.circularList.getRotatePlayerList();
        assertEquals("4",list.get(0).getName());
        assertEquals("1",list.get(1).getName());
        assertEquals("2",list.get(2).getName());
        assertEquals("3",list.get(3).getName());
    }
}