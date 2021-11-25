package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

public class infoactionTest {
    List<IDistrict> hand1;
    List<IDistrict> hand2;
    District district1;
    District district2;
    District district3;
    infoaction infomock;
    IPlayer player;
    private infoaction info2;
    @Test
   public void getplayertest() {
        infoaction infomock = mock(infoaction.class);
        IPlayer player2=new IA("jerry");
        IPlayer player1= infomock.getplayer();
        assertEquals(player1,player);
        assertNotEquals(player1,player2);
    }
    @Test
    public void getHandTest(){
        List<IDistrict> hand1 = new ArrayList<>();
        List<IDistrict> hand2 = new ArrayList<>();
        District district1 = null;
        try {
            district1 = new District(1, Color.YELLOW, DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }
        District district2 = null;
        try {
            district2 = new District(2, Color.BLUE, DistrictName.PALAIS);
        } catch (CardException e) {
            e.printStackTrace();
        }
        District district3 = null;
        try {
            district3 = new District(1, Color.GREEN, DistrictName.TAVERNE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        hand1.add(district1);
        hand1.add(district2);
        hand1.add(district3);
        hand2.add(district1);
        hand2.add(district2);
        hand2.add(district3);
        infoaction info2 = new infoaction();
        info2.setDistrictremove(district2);
        IPlayer player = new IA("sam");
        info2.setplayer(player);
        info2.setHAND(hand2);
        assertEquals(hand2,info2.getHAND());


    }
    @Test
    public void Districtremovetest(){
        List<IDistrict> hand2 = new ArrayList<>();
        District district1 = null;
        try {
            district1 = new District(1, Color.YELLOW, DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }
        District district2 = null;
        try {
            district2 = new District(2, Color.BLUE, DistrictName.PALAIS);
        } catch (CardException e) {
            e.printStackTrace();
        }
        District district3 = null;
        try {
            district3 = new District(1, Color.GREEN, DistrictName.TAVERNE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        hand2.add(district1);
        hand2.add(district2);
        hand2.add(district3);
        infoaction info2=new infoaction();
        info2.setDistrictremove(district2);
        IPlayer player = new IA("sam");
        info2.setplayer(player);
        info2.setHAND(hand2);
        info2.setDistrictremove(district2);
        assertEquals(district2,info2.getDistrictremove());

    }

}
