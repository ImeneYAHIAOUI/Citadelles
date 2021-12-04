package fr.unice.polytech.startingpoint.cards;

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
        public void getHandTest () {
            List<IDistrict> hand1 = new ArrayList<>();
            List<IDistrict> hand2 = new ArrayList<>();
            List<IDistrict> hand3 = new ArrayList<>();
            List<IDistrict> hand4=new ArrayList<>();
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

            hand3.add(district1);
            hand3.add(district2);
            hand4.add(district1);
            hand4.add(district2);
            infoaction info2 = new infoaction();
            infoaction info3 =new infoaction();
            info2.setDistrictremove(district2);
            IPlayer player = new IA("sam");
            IPlayer player2 = new IA("jerry");
            info3.setplayer(player2);
            info2.setplayer(player);
            info2.setHAND(hand2);
            info3.setHAND(hand3);
            info3.setHAND(hand3);
            assertEquals(hand1, info2.getHAND());
            assertEquals(hand3,hand4);
            assertNotNull(hand3);
            assertNotEquals(hand1,hand3);



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
            info2.setHAND(hand2);
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
        public void getbuilddistricttest(){
        District district1=null;
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
            IPlayer player2 = new IA("jerry");
            List<IDistrict> hand1 = new ArrayList<>();
            List<IDistrict> builtDistricts =new ArrayList<>();
            List<IDistrict> builtDistricts2 =new ArrayList<>();
            MiracleCourt courtofmiracles= new MiracleCourt();
            infoaction info=new infoaction();
            hand1.add(district1);
            hand1.add(district2);
            hand1.add(district3);
            hand1.add(courtofmiracles);
            hand1.add(district1);
            hand1.add(district3);
            hand1.add(district2);
            hand1.add(district1);
            builtDistricts.add(district1);
            builtDistricts.add(district2);
            builtDistricts.add(district3);
            builtDistricts.add(courtofmiracles);
            builtDistricts.add(district1);
            builtDistricts.add(district3);
            builtDistricts.add(district2);
            builtDistricts.add(district1);
            player2.setHand(hand1);
            player2.addGold(25);
            info.setplayer(player2);
            info.setbuildlist(builtDistricts);
            assertEquals(info.getbuilddisctrict(),builtDistricts);
            assertNotEquals(info.getbuilddisctrict(),builtDistricts2);
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

