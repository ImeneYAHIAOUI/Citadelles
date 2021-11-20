package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.heros.HeroDeck;
import fr.unice.polytech.startingpoint.heros.King;
import fr.unice.polytech.startingpoint.heros.Magician;
import fr.unice.polytech.startingpoint.heros.Merchant;
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
        for(int i = 0; i < 2; i++)
            districtDeck.add(new District(1, Color.YELLOW,DistrictName.MANOIR));
        for(int i = 0; i < 3; i++)
            districtDeck.add(new District(2,Color.YELLOW,DistrictName.CHATEAU));
        for(int i = 0; i < 3; i++)
            districtDeck.add(new District(2,Color.YELLOW,DistrictName.PALAIS));
        //Green
        for(int i = 0; i < 3; i++)
            districtDeck.add(new District(2,Color.GREEN,DistrictName.ECHAPPE));
        for(int i = 0; i < 3; i++)
            districtDeck.add(new District(1,Color.GREEN,DistrictName.TAVERNE));
        for(int i = 0; i < 2; i++)
            districtDeck.add(new District(2,Color.GREEN,DistrictName.MARCHE));
        //purple Wonder

        districtDeck.add(new District( 2, Color.PURPLE, DistrictName.LACOURDESMIRACLES));
        districtDeck.add(new District( 5, Color.PURPLE, DistrictName.LABORATOIRE));
        Collections.shuffle(districtDeck);

        return districtDeck;
    }

    /** add chosen hero to heroList
     *
     * @return heroList
     */
    public static HeroDeck heroeList(){
        HeroDeck heroes = new HeroDeck();
        heroes.add(new King());
        heroes.add(new Merchant());
        heroes.add(new Magician());

        return heroes;
    }
}
