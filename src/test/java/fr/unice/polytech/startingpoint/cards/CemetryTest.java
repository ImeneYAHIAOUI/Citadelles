package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.cards.district.Cemetery;
import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.cards.district.Laboratory;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.player.IA.BOTs.NeutralBot;
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
    Cemetery cemetry;
    @BeforeEach
    void setup(){
        treasure=new Treasure(15);
        info=new IAToWonder();
        cemetry=new Cemetery();

        player=new NeutralBot("Ruby");
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
        assertEquals(cemetry.isWonder(),true);
        assertEquals(cemetry.getColor(),Color.PURPLE);
        assertEquals(cemetry.getPrice(),5);
        assertEquals(cemetry.getDistrictName(),DistrictName.CEMETRY);
        assertEquals(cemetry.getColor(),Color.PURPLE);
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
