package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.cards.district.Cemetry;
import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.cards.district.Laboratory;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.heros.character.Condottiere;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CemetryTest {
    IAToWonder info;
    IPlayer player;
    IDistrict card;
    DistrictDeck deck;
    Treasure treasure;
    @BeforeEach
    void setup(){
        treasure=new Treasure(15);
        info=new IAToWonder();
        Cemetry cemetry=new Cemetry();

        player=new IA("Ruby");
        try {
            card = new District(1, Color.YELLOW, DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }
        List<IDistrict> list=new ArrayList<>();
        list.add(card);
        list.add(new Laboratory());
        deck =new DistrictDeck(list);
        player.getHand().add(cemetry);
        info.setCard(card);
        info.setplayer(player);
        info.setTreasure(treasure);
        info.setdistrictdeck(deck);
        player.addGold(5);
    }
    @Test
    void doActionTest(){
        assertEquals(deck.getDeckSize(),2);
        assertEquals(player.getGold(),5);
        assertEquals(player.getHand().size(),1);
        assertEquals(treasure.getPieces(),15);
        ((IWonder)player.getHand().get(0)).doAction(info);
        assertEquals(deck.getDeckSize(),1);
        assertEquals(player.getGold(),4);
        assertEquals(player.getHand().size(),2);
        assertEquals(treasure.getPieces(),16);

    }


}
