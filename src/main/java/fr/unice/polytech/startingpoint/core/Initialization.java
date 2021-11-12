package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.District;

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
        for(int i = 0; i < 5; i++)
            districtDeck.add(new District(1, Color.YELLOW,"Manoir"));
        for(int i = 0; i < 4; i++)
            districtDeck.add(new District(2,Color.YELLOW,"Château"));
        for(int i = 0; i < 2; i++)
            districtDeck.add(new District(2,Color.YELLOW,"Palais"));

            
        return districtDeck;
    }
}
