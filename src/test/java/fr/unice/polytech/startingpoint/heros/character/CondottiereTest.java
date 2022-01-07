package fr.unice.polytech.startingpoint.heros.character;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;
import fr.unice.polytech.startingpoint.player.IA.BOTs.NeutralBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CondottiereTest {
    DistrictDeck deck;
    Condottiere condottiere;
    IAToHero info;
    IA player;
    IA player2;
    Treasure treasure;
    IDistrict card;
    IDistrict cardDestroyed;
    @BeforeEach
    void setup() {
        deck=new  DistrictDeck(new ArrayList<>());
        try {
            cardDestroyed = new District(4, Color.GREEN, DistrictName.PORT);

        } catch (CardException e) {
            e.printStackTrace();
        }
        this.info = new IAToHero();
        this.treasure = new Treasure(15);
        this.condottiere = new Condottiere();
        this.player = new NeutralBot("Marie");
        this.player2 = new NeutralBot("melanie");
        info.setPlayers(List.of(player,player2));
        player.setRole(condottiere);
        info.setTreasure(treasure);
        info.setCurrentPlayer(player);
        try {
            card = new District(1, Color.GREEN, DistrictName.TAVERNE);
            player.getBuiltDistricts().add(card);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            card = new District(2, Color.YELLOW, DistrictName.PALAIS);
            player.getBuiltDistricts().add(card);
        } catch (CardException e) {
            e.printStackTrace();
        }
        player.addGold(7);
        player2.getBuiltDistricts().add(card);
        player2.getBuiltDistricts().add(cardDestroyed);
        info.setChosenPlayer("melanie");
        info.setDeck(deck);

    }
    @Test
    void doActionTest(){// il n'a pas des quartiers  militaire + pas de destruction
        assertEquals(player.getGold(),7);
        condottiere.doAction(info);
        assertEquals(player.getGold(),7);
        assertEquals(treasure.getPieces(),15);
        assertEquals(info.getChosenPlayer().getBuiltDistricts().size(),2);

    }
    @Test
    void doActionTest1(){//avec des quartiers millitaires + pas de destruction
        try {
            card = new District(5, Color.RED, DistrictName.FORTERESSE);
            player.getBuiltDistricts().add(card);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            card = new District(2, Color.RED, DistrictName.PRISON);
            player.getBuiltDistricts().add(card);
        } catch (CardException e) {
            e.printStackTrace();
        }
        assertEquals(player.getGold(),7);
        condottiere.doAction(info);
        assertEquals(player.getGold(),9);
        assertEquals(treasure.getPieces(),13);
        assertEquals(info.getChosenPlayer().getBuiltDistricts().size(),2);

    }
    @Test
    void doActionTest2(){//avec des quartiers millitaires mais la tresorie est vide
        try {
            card = new District(5, Color.RED, DistrictName.FORTERESSE);
            player.getBuiltDistricts().add(card);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            card = new District(2, Color.RED, DistrictName.PRISON);
            player.getBuiltDistricts().add(card);
        } catch (CardException e) {
            e.printStackTrace();
        }
        assertEquals(player.getGold(),7);
        info.getTreasure().removeGold(15);
        assertEquals(treasure.getPieces(),0);
        condottiere.doAction(info);
        assertEquals(player.getGold(),7);
        assertEquals(treasure.getPieces(),0);
        assertEquals(info.getChosenPlayer().getBuiltDistricts().size(),2);
        assertEquals(info.getDeck().getDeckSize(),0);
    }
    @Test
    void doActionTest4(){//il a detruit un quartier + quartiers militaires
        try {
            card = new District(5, Color.RED, DistrictName.FORTERESSE);
            player.getBuiltDistricts().add(card);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            card = new District(2, Color.RED, DistrictName.PRISON);
            player.getBuiltDistricts().add(card);
        } catch (CardException e) {
            e.printStackTrace();
        }
        player.setCardDestroyedByCondottiere(cardDestroyed);
        assertEquals(player.getGold(),7);
        assertEquals(treasure.getPieces(),15);
        assertEquals(deck.getDeckSize(),0);
        condottiere.doAction(info);
        assertEquals(player.getGold(),6);
        assertEquals(treasure.getPieces(),16);
        assertEquals(info.getChosenPlayer().getBuiltDistricts().size(),1);
        assertEquals(deck.getDeckSize(),1);
        assertEquals(player.getCardDestroyedByCondottiere(),null);
    }
}
