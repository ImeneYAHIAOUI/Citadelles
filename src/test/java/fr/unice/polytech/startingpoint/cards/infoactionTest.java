package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class infoactionTest {
   List<IDistrict> hand1;
    List<IDistrict> hand2;
    List<IDistrict> hand3;
    List<IDistrict> hand4;
    District district1;
    District district2;
    District district3;
    infoaction infomock;
    IPlayer player;


    private infoaction info2;

    @Test
    public void getplayertest() {
        infoaction infomock = mock(infoaction.class);
        infoaction info = new infoaction();
        IPlayer player2 = new IA("jerry");
        IPlayer player5 = new IA("Link");
        IPlayer player4 = new IA("jam");
        IPlayer player3 = new IA("hana");
        when(infomock.getplayer()).thenReturn(player5);
        info.setplayer(player3);
        assertEquals(player3, info.getplayer());
        assertEquals(player5, infomock.getplayer());
        assertNotEquals(info.getplayer(), player2);
        assertNotEquals(info.getplayer(), infomock.getplayer());
        assertNotEquals(info.getplayer(), player5);
        assertNotEquals(info.getplayer(), player4);
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
            infoaction info2 = new infoaction();
            info2.setDistrictremove(district2);
            IPlayer player = new IA("sam");
            info2.setplayer(player);
            info2.setDistrictremove(district2);
            assertEquals(district2, info2.getDistrictremove());
            assertNotNull(info2.getDistrictremove());
            assertNotNull(info2.getplayer());


        }
        @Test
         public void getattributehandtest(){
            List<IDistrict> hand1 = new ArrayList<>();
            infoaction info =new infoaction();
            IPlayer player = new IA("sam");
            hand1.add(district1);
            hand1.add(district2);
            hand1.add(district3);
            player.setHand(hand1);
            info.setplayer(player);
            info.setdistrictdeck(new DistrictDeck(Initialization.districtList()));
            player.getDistrict(info.getattributeHand());
            assertEquals(hand1.size(),6);
            assertNotNull(info.getattributeHand());
            assertNotEquals(hand1.size(),3);

        }
       @Test
        public void gettreasure() {
        infoaction info =new infoaction();
        Treasure Tresor=new Treasure(30);
        info.setTreasure(Tresor);
        assertEquals(info.getTreasure(),Tresor);


        }

        @Test
    public void getchoosencolortest(){
        infoaction info=new infoaction();
        info.setchoosencolor(Color.GREEN);
        assertEquals(info.getchoosencolor(),Color.GREEN);
        assertNotEquals(info.getchoosencolor(),Color.PURPLE);
        assertNotEquals(info.getchoosencolor(),Color.YELLOW);
        assertNotEquals(info.getchoosencolor(),Color.BLUE);
        assertNotEquals(info.getchoosencolor(),Color.RED);

        }

    }

