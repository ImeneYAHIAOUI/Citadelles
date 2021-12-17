package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.cards.district.MagicSchool;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MagicSchoolTests {
    IAToWonder infos = mock(IAToWonder.class);
    MagicSchool magicSchool = new MagicSchool();

    @Test
    void doActionTest(){
        assertEquals(Color.PURPLE,magicSchool.getColor());
        when(infos.getChoosenColorOfMagicSchool()).thenReturn(Color.YELLOW,Color.RED,Color.GREEN,Color.BLUE,Color.PURPLE);
        magicSchool.doAction(infos);
        assertEquals(Color.YELLOW,magicSchool.getColor());
        magicSchool.doAction(infos);
        assertEquals(Color.RED,magicSchool.getColor());
        magicSchool.doAction(infos);
        assertEquals(Color.GREEN,magicSchool.getColor());
        magicSchool.doAction(infos);
        assertEquals(Color.BLUE,magicSchool.getColor());
        magicSchool.doAction(infos);
        assertEquals(Color.PURPLE,magicSchool.getColor());
    }

}
