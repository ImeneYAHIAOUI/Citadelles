package fr.unice.polytech.startingpoint.heros;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.Information;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MagicianTest {

        Magician magician;
        DistrictDeck deck;
        Information info;
        IPlayer player;

        @BeforeEach
        void setUp() {
            this.magician= new Magician();
            this.deck = new DistrictDeck(Initialization.districtList());
            this.info = new Information();
            this.player = new IA("Player1");
        }

        @Test
        void testGetName(){
            assertEquals(this.magician.getName(), HeroName.Magician);
        }

        @Test
        void testGetRenk(){
            assertEquals(this.magician.getRank(),3);
        }

        @Test
        void testGet(){
            assertEquals(this.magician.getColor(), Color.PURPLE);
        }
        @Test
        void testDoAction(){

        }


    }