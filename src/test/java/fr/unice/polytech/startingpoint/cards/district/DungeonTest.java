package fr.unice.polytech.startingpoint.cards.district;



import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.cards.DistrictName;
import fr.unice.polytech.startingpoint.core.Initialization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DungeonTest {
    Dungeon dungeon;
    DistrictDeck deck;
    @BeforeEach
    void setUp() {
        this.dungeon = new Dungeon();
        deck = new DistrictDeck(Initialization.districtList());
    }


    @Test
    void GetnameTest() {
        assertEquals(this.dungeon.getDistrictName(), DistrictName.DONGEON);
        assertNotEquals(this.dungeon.getDistrictName(), DistrictName.MANOIR);
        assertNotEquals(this.dungeon.getDistrictName(), DistrictName.LACOURDESMIRACLES);
        assertNotEquals(this.dungeon, DistrictName.CHATEAU);
        assertNotEquals(this.dungeon, DistrictName.PALAIS);
        assertNotEquals(this.dungeon, DistrictName.TAVERNE);
        assertNotEquals(this.dungeon, DistrictName.MARCHE);
        assertNotEquals(this.dungeon, DistrictName.ECHAPPE);
        assertNotEquals(this.dungeon, DistrictName.LABORATOIRE);
    }

    @Test
    void Getdescriptiontest() {
        String desp = "Le Donjon ne peut pas " +
                "être détruit par le " +
                "Condottière.\n";
        String desp2 = null;
        assertEquals(this.dungeon.getDescription(), desp);
        assertNotEquals(this.dungeon.getDescription(), desp2);

    }
    @Test
    void wondertest(){
        assertTrue(this.dungeon.isWonder());
        assertNotEquals(this.dungeon.isWonder(),false);

    }

}
