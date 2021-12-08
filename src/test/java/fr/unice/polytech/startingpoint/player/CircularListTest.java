package fr.unice.polytech.startingpoint.player;

import fr.unice.polytech.startingpoint.heros.*;
import fr.unice.polytech.startingpoint.heros.character.Assassin;
import fr.unice.polytech.startingpoint.heros.character.King;
import fr.unice.polytech.startingpoint.heros.character.Merchant;
import fr.unice.polytech.startingpoint.heros.character.Thief;
import fr.unice.polytech.startingpoint.player.IA.IA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CircularListTest {
    CircularList circularList = null;
    List<IA> playerList = null;
    HeroDeck heroes = null;

    @BeforeEach
    void setUp(){
        IA IA1 = new IA("1");
        IA IA2 = new IA("2");
        IA IA3 = new IA("3");
        IA IA4 = new IA("4");

        this.playerList = new ArrayList<IA>();

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
        List<IPlayer> list = new ArrayList<>(this.playerList);
        this.circularList = new CircularList(list);
        assertEquals(4, this.circularList.size());
    }

    @Test
    void testSizeEmpty(){
        this.circularList = new CircularList(new ArrayList<IPlayer>());
        assertEquals(0, this.circularList.size());
    }

    @Test
    void testOrderPlayer1(){
        this.playerList.get(0).setRole(heroes.get(0));
        this.playerList.get(1).setRole(heroes.get(1));
        this.playerList.get(2).setRole(heroes.get(2));
        this.playerList.get(3).setRole(heroes.get(3));

        assertEquals(HeroName.King,this.playerList.get(0).getRole().getName());
        assertEquals(HeroName.Assassin,this.playerList.get(1).getRole().getName());
        assertEquals(HeroName.Thief,this.playerList.get(2).getRole().getName());
        assertEquals(HeroName.Merchant,this.playerList.get(3).getRole().getName());

        this.playerList.get(0).setCrown();

        List<IPlayer> listTest = new ArrayList<>(this.playerList);
        this.circularList = new CircularList(listTest);
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
        this.playerList.get(1).setRole(heroes.get(0));
        this.playerList.get(0).setRole(heroes.get(1));
        this.playerList.get(2).setRole(heroes.get(2));
        this.playerList.get(3).setRole(heroes.get(3));

        assertEquals(HeroName.Assassin,this.playerList.get(0).getRole().getName());
        assertEquals(HeroName.King,this.playerList.get(1).getRole().getName());
        assertEquals(HeroName.Thief,this.playerList.get(2).getRole().getName());
        assertEquals(HeroName.Merchant,this.playerList.get(3).getRole().getName());

        this.playerList.get(1).setCrown();

        List<IPlayer> listTest = new ArrayList<>(this.playerList);
        this.circularList = new CircularList(listTest);
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
        this.playerList.get(2).setRole(heroes.get(0));
        this.playerList.get(1).setRole(heroes.get(1));
        this.playerList.get(0).setRole(heroes.get(2));
        this.playerList.get(3).setRole(heroes.get(3));

        assertEquals(HeroName.Thief,this.playerList.get(0).getRole().getName());
        assertEquals(HeroName.Assassin,this.playerList.get(1).getRole().getName());
        assertEquals(HeroName.King,this.playerList.get(2).getRole().getName());
        assertEquals(HeroName.Merchant,this.playerList.get(3).getRole().getName());

        this.playerList.get(2).setCrown();

        List<IPlayer> listTest = new ArrayList<>(this.playerList);
        this.circularList = new CircularList(listTest);
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
        this.playerList.get(3).setRole(heroes.get(0));
        this.playerList.get(2).setRole(heroes.get(1));
        this.playerList.get(1).setRole(heroes.get(2));
        this.playerList.get(0).setRole(heroes.get(3));

        assertEquals(HeroName.Merchant,this.playerList.get(0).getRole().getName());
        assertEquals(HeroName.Thief,this.playerList.get(1).getRole().getName());
        assertEquals(HeroName.Assassin,this.playerList.get(2).getRole().getName());
        assertEquals(HeroName.King,this.playerList.get(3).getRole().getName());

        this.playerList.get(3).setCrown();

        List<IPlayer> listTest = new ArrayList<>(this.playerList);
        this.circularList = new CircularList(listTest);
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