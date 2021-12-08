package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.cards.district.Manufacture;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

public class ManufactureTest {
        Manufacture manufacture;
        DistrictDeck deck;
        IAToWonder info;
    private Treasure Tresor;
    private Treasure Tresor2;


    @BeforeEach
        void setUp() {
            this.manufacture = new Manufacture();
            deck = new DistrictDeck(Initialization.districtList());

        }


        @Test
        void GetnameTest() {
            assertEquals(this.manufacture.getDistrictName(), DistrictName.MANUFACTURE);
            assertNotEquals(this.manufacture.getDistrictName(), DistrictName.MANOIR);
            assertNotEquals(this.manufacture.getDistrictName(), DistrictName.LACOURDESMIRACLES);
            assertNotEquals(this.manufacture, DistrictName.CHATEAU);
            assertNotEquals(this.manufacture, DistrictName.PALAIS);
            assertNotEquals(this.manufacture, DistrictName.TAVERNE);
            assertNotEquals(this.manufacture, DistrictName.MARCHE);
            assertNotEquals(this.manufacture, DistrictName.ECHAPPE);
            assertNotEquals(this.manufacture, DistrictName.LABORATOIRE);
        }

        @Test
        void Getdescriptiontest() {
            String desp =  "Une fois par tour, vous pouvez payer trois pieces d'or pour piocher trois cartes";
            String desp2=null;
            assertEquals(this.manufacture.getDescription(), desp);
            assertNotEquals(this.manufacture.getDescription(),desp2);

        }
        @Test
        void doactiontest() {

            IPlayer player = new IA("sam");
            IPlayer player2 = new IA("jerry");
            List<IDistrict> hand1 = new ArrayList<>();
            List<IDistrict> hand2 = new ArrayList<>();
            List<IDistrict> hand3 = new ArrayList<>();
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
            player.setHand(hand1);
            hand1.add(district1);
            hand1.add(district2);
            hand1.add(district3);
            hand2.add(district1);
            hand2.add(district3);
            hand3.add(district2);
            Tresor=new Treasure(30);
            Tresor2=new Treasure(15);
            IAToWonder info2 =new IAToWonder();
            IAToWonder info=new IAToWonder();
            info.setplayer(player);
            info.setdistrictdeck(new DistrictDeck(Initialization.districtList()));
            player.setHand(hand2);
            info.setTreasure(Tresor2);
            player.addGold(2);
            info2.setplayer(player2);
            info2.setTreasure(Tresor);
            player2.setHand(hand3);
            player2.addGold(4);
            info2.setdistrictdeck(new DistrictDeck(Initialization.districtList()));
            manufacture.doAction(info2);
            manufacture.doAction(info);
            assertNotNull(hand1);
            assertEquals(hand3.size(), 4);
            assertEquals(player2.getGold(),1);
            assertEquals(Tresor.getPieces(),33);
            assertEquals(hand2.size(),5);
            assertEquals(player.getGold(),-1);
            assertEquals(Tresor2.getPieces(),18);








        }
        @Test
        void TESTiswonder(){
            assertTrue(this.manufacture.isWonder());
            assertNotEquals(this.manufacture.isWonder(),false);

        }
    }

