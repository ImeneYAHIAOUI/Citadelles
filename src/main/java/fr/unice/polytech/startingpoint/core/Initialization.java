package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.cards.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public abstract class Initialization {

    /**
     * Initialization of the district deck
     * @return List<District>
     */
    public static List<District> districtList(){
        List<District> districtDeck = new ArrayList<District>();

        // YELLOW
        for(int i = 0; i < 3; i++)
            districtDeck.add(new District(1, Color.YELLOW,DistrictName.MANOIR));
        for(int i = 0; i < 3; i++)
            districtDeck.add(new District(2,Color.YELLOW,DistrictName.CHATEAU));
        for(int i = 0; i < 2; i++)
            districtDeck.add(new District(2,Color.YELLOW,DistrictName.PALAIS));

        Collections.shuffle(districtDeck);

        return districtDeck;
    }
}
